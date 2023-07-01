package com.soap.service;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CountryRepository {
	@Autowired
	private static final Map<String, Country> countries = new HashMap<>();

	@PostConstruct
	public void initData() {
		Country spain = new Country();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setCurrency(Currency.EUR);
		spain.setPopulation(46704314);

		countries.put(spain.getName(), spain);

		Country poland = new Country();
		poland.setName("Poland");
		poland.setCapital("Warsaw");
		poland.setCurrency(Currency.PLN);
		poland.setPopulation(38186860);

		countries.put(poland.getName(), poland);

		Country uk = new Country();
		uk.setName("United Kingdom");
		uk.setCapital("London");
		uk.setCurrency(Currency.GBP);
		uk.setPopulation(63705000);

		countries.put(uk.getName(), uk);

		Country col = new Country();
		col.setName("Colombia");
		col.setCapital("Bogotá");
		col.setCurrency(Currency.COP);
		col.setPopulation(50000000);

		countries.put(col.getName(), col);
	}

	public Country findCountry(String name) {
		Assert.notNull(name, "The country's name must not be null");
		return countries.get(name);
	}

	public void addCountry(String name,int population, String capital){
		Country country = new Country();
		country.setName(name);
		country.setCapital(capital);
		country.setPopulation(population);
		countries.put(name, country);
	}

	public void removeByName(String name){
		countries.remove(name);
	}

	public List<Country> getAll(){
		List <Country> abc = new ArrayList<>();
		for (Map.Entry<String,Country> c : this.countries.entrySet()) {
			abc.add(c.getValue());
		}
		return abc;
	}

	public Country updateCountry(String name,String newName,
		String newCapital,int newPopulation){
		countries.remove(name);
		Country country = new Country();
		country.setCapital(newCapital);
		country.setName(newName);
		country.setPopulation(newPopulation);	
		countries.replace(name,country);
		countries.put(newName, country);
		return country;
	}
}
