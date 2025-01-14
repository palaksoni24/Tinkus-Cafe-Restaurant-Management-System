package com.amstech.tinkus.backend.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amstech.tinkus.backend.dto.StateDTO;
import com.amstech.tinkus.backend.util.DBUtil;


public class StateDAO {

	private final String STATE_FIND_BY_COUNTRY_ID = "select * from state where country_id = ?";

	private DBUtil dbUtil;

	public StateDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public List<StateDTO> findByCountryId(int countryId) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<StateDTO> stateDTOList = new ArrayList<>();

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(STATE_FIND_BY_COUNTRY_ID);
			pstmt.setInt(1, countryId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				StateDTO stateDTO = new StateDTO();
				stateDTO.setId(rs.getInt("id"));
				stateDTO.setName(rs.getString("name"));
				stateDTOList.add(stateDTO);
			}
			return stateDTOList;
		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}

}