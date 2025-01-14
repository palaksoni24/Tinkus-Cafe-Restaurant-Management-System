package com.amstech.tinkus.backend.service;

import java.util.List;

import com.amstech.tinkus.backend.dao.CountryDAO;
import com.amstech.tinkus.backend.dto.CountryDTO;

public class CountryService {

	private CountryDAO countryDAO;

	public CountryService(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public List<CountryDTO> findAll() throws Exception {
		return countryDAO.findAll();
	}

}