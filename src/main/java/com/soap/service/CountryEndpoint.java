package com.soap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://soap.com/service";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	//find one by name
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}

	//add one
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "postCountryRequest")
	@ResponsePayload
	public PostCountryResponse postCountry(@RequestPayload PostCountryRequest request) {
		PostCountryResponse response = new PostCountryResponse();
		countryRepository.addCountry(request.getName(),request.getPopulation(),
			request.getCapital());
		response.setCountry(countryRepository.findCountry(request.getName()));
		return response;
	}

	//delete one by name
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCountryRequest")
	@ResponsePayload
	public DeleteCountryResponse deleteCountry(@RequestPayload DeleteCountryRequest request) {
		DeleteCountryResponse response = new DeleteCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));
		countryRepository.removeByName(request.getName());
		return response;
	}

	//update by name
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCountryRequest")
	@ResponsePayload
	public UpdateCountryResponse updateCountry(@RequestPayload UpdateCountryRequest request) {
		UpdateCountryResponse response = new UpdateCountryResponse();
		countryRepository.updateCountry(request.getName(),
		request.getNewName(),request.getNewCapital(),request.getNewPopulation());
		return response;
	}

	//get all
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCountryRequest")
	@ResponsePayload
	public GetAllCountryResponse getCountry(@RequestPayload GetAllCountryRequest request) {
		GetAllCountryResponse response = new GetAllCountryResponse();
		// response.setCountry(countryRepository.findCountry(request.getName()));
	response.setCountries(countryRepository.getAll());
		return response;
	}
}
