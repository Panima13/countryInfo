package com.parmin.country;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;
@Service
public class CountryService {

	    private final HttpClient client = HttpClient.newHttpClient();

	    public String getCountry(String countryName)
	            throws IOException, InterruptedException {

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(
	                        "https://api.restcountries.com/countries/v5/names.common/" + countryName))
	                .header("Authorization",
	                        "Bearer rc_live_db92af4688594896aba962c402f11b81")
	                .GET()
	                .build();

	        HttpResponse<String> response =
	                client.send(request, HttpResponse.BodyHandlers.ofString());

	        return response.body();
	    }


}
