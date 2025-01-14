package com.amstech.tinkus.backend.service;

import java.util.List;

import com.amstech.tinkus.backend.dao.StateDAO;
import com.amstech.tinkus.backend.dto.StateDTO;

public class StateService {

	private StateDAO stateDAO;

	public StateService(StateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public List<StateDTO> findByCountryId(int countryId) throws Exception {
		return stateDAO.findByCountryId(countryId);
	}

}
