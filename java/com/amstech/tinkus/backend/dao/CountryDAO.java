package com.amstech.tinkus.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amstech.tinkus.backend.dto.CountryDTO;
import com.amstech.tinkus.backend.util.DBUtil;

	public class CountryDAO {

		private final String COUNTRY_FIND_ALL = "select * from country";

		private DBUtil dbUtil;

		public CountryDAO(DBUtil dbUtil) {
			this.dbUtil = dbUtil;
		}

		public List<CountryDTO> findAll() throws Exception {
			Connection connection = null;
			PreparedStatement pstmt = null;
			List<CountryDTO> countryDTOList = new ArrayList<>();

			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(COUNTRY_FIND_ALL);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					CountryDTO CountryDTO = new CountryDTO();
					CountryDTO.setId(rs.getInt("id"));
					CountryDTO.setName(rs.getString("name"));
					countryDTOList.add(CountryDTO);
				}
				return countryDTOList;
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.close(connection, pstmt);
			}
		}

	}


