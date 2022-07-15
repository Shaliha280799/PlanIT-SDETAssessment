package com.planit.challenges;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChallengeOne {

	public static void main(String[] args) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://petstore.swagger.io/v2/pet/findByStatus?status=available")).GET().build();

			HttpClient client = HttpClient.newHttpClient();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			List<Pet> petList = new ArrayList<>();

			petList = Arrays.asList(mapper.readValue(response.body(), Pet[].class));
			System.out.println("Total Pets :" + petList.size());

		} catch (Exception e) {

			System.out.println("Unable to fetch the pets count, please try again later");

		}
	}

}
