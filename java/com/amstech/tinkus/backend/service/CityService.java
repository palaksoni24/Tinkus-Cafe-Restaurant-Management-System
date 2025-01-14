package com.amstech.tinkus.backend.service;

import java.util.List;
import com.amstech.tinkus.backend.dao.CityDAO;
import com.amstech.tinkus.backend.dto.CityDTO;

	public class CityService {

		private CityDAO cityDAO;

		public CityService(CityDAO cityDAO) {
			this.cityDAO = cityDAO;
		}

		public List<CityDTO> findBystateId(int stateId) throws Exception {
			return cityDAO.findByStateId(stateId);
		}

	
	}
