package com.parmin.country;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;







@Controller
public class Contoller {	
	
	@Autowired
	private CountryService countryService;
	
		
	@GetMapping("/{country}")
	String country(@PathVariable String country,Model model) {
		Map<String, Object> countryInfo = new HashMap<String, Object>();
		String response ="";
		try {
			response = countryService.getCountry(country);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> countryInfoAllData =
			    objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {});
		Map<String, Object> countryInfoData =
				(Map<String, Object>) countryInfoAllData.get("data");
		List<Map<String, Object>> countryInfoObjects =
				 (List<Map<String, Object>>) countryInfoData.get("objects");
		
	    if(!countryInfoObjects.isEmpty()) countryInfo = (Map<String, Object>) countryInfoObjects.get(0);
			   
		
		model.addAttribute("countryinfo" , countryInfo);
		return "country/country";
	}
	

}
