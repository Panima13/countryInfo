package com.parmin.country;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;







@Controller
public class Contoller {	
	
	@Autowired
	private CountryService countryService;
	List<MenuItem> menus = List.of(new MenuItem("Home","/home"),
			new MenuItem("Search","search"),
			new MenuItem("About","/about"));
	
	@GetMapping({"/" ,"/home"})
	public String home(Model model) {
		model.addAttribute("page","home");
		model.addAttribute("menus" , menus);
		
	    return "country/country";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("page","about");
		model.addAttribute("menus" , menus);
		
	    return "country/country";
	}

	@GetMapping("/search")
	String country(@RequestParam (required = false) String searchtext , Model model) {
		model.addAttribute("page","search");
		model.addAttribute("menus" , menus);		
		String response ="";
		try {
			response = countryService.getCountry(searchtext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if(response ==null || response.isBlank()) {
				model.addAttribute("showResult", false);
				return "country/country";
			}
			model.addAttribute("showResult", true);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ArrayList<Map<String, Object>> countryInfoList = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> countryInfoAllData =
			    objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {});
		Map<String, Object> countryInfoData =
				(Map<String, Object>) countryInfoAllData.get("data");
		List<Map<String, Object>> countryInfoObjects =
				 (List<Map<String, Object>>) countryInfoData.get("objects");

		

		
		if(countryInfoObjects!= null && !countryInfoObjects.isEmpty()) {
			
			for (Map<String, Object>  countryInfo: countryInfoObjects) {		
				
				 countryInfoList.add(countryInfo);
			}
				
		}
		
		model.addAttribute("countryInfoList" , countryInfoList);
		
		return "country/country";
	}
	

}
