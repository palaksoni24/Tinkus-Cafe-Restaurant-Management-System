package com.amstech.tinkus.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amstech.tinkus.backend.dto.CityDTO;
import com.amstech.tinkus.backend.util.DBUtil;

	public class CityDAO {

		private final String CITY_FIND_BY_STATE_ID = "select * from city where state_id = ?";

		private DBUtil dbUtil;

		public CityDAO(DBUtil dbUtil) {
			this.dbUtil = dbUtil;
		}

		public List<CityDTO> findByStateId(int stateId) throws Exception {
			Connection connection = null;
			PreparedStatement pstmt = null;
			List<CityDTO> cityDTOList = new ArrayList<>();

			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(CITY_FIND_BY_STATE_ID);
				pstmt.setInt(1, stateId);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					CityDTO cityDTO = new CityDTO();
					cityDTO.setId(rs.getInt("id"));
					cityDTO.setName(rs.getString("name"));
					cityDTO.setImage(rs.getString("image"));
					cityDTOList.add(cityDTO);
					
				}
				return cityDTOList;
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.close(connection, pstmt);
			}
		}

	}
