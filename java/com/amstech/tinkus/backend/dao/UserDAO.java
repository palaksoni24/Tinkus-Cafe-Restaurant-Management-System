package com.amstech.tinkus.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amstech.tinkus.backend.dto.UserDTO;
import com.amstech.tinkus.backend.util.DBUtil;

public class UserDAO {
	
		private DBUtil dbUtil;
			
		public UserDAO(DBUtil dbUtil) {
			System.out.println("Creating UserDAO Object");
			this.dbUtil = dbUtil;
		}

		private final String USER_DATA_INSERT = "insert into user(city_id,first_name,last_name,email, mobile_number,address,pan_number,password)"
				+ "value(?,?,?,?,?,?,?,?)";
		
		private final String USER_DATA_UPDATE = "update user set first_name = ?, last_name = ?,address=?  where id = ?";
		
		private final String USER_DATA_DELETE_BY_ID = "delete from user where id = ?";
		
		//private final String USER_FIND_BY_ID = "select user.id, user.first_name, user.last_name, user.address, city.id as city_id, city.name as city_name, state.id as state_id, state.name as state_name, country.id as country_id, country.name as country_name from user join city on user.city_id = city.id join state on city.state_id = state.id join country on state.country_id = country.id where user.id = ? ";

		//private final String USER_DATA_FIND_BY_ID = "select * from user where id = ?";
		private final String USER_DATA_FIND_BY_ID ="select user.id, user.first_name, user.last_name, user.address,user.image,user.pan_number,user.password,user.email,user.mobile_number, city.id as city_id, city.name as city_name, state.id as state_id, state.name as state_name, country.id as country_id, country.name as country_name from user join city on user.city_id = city.id join state on city.state_id = state.id join country on state.country_id = country.id where user.id = ? ";

		private final String USER_DATA_FIND_BY_MOBILE = "select * from user where mobile_number = ?";

		private final String USER_DATA_FIND_ALL = "select * from user";
		
		private final String USER_DATA_FIND_FOR_LOGIN = "select * from user where email=? and password=?";

		public int save(UserDTO userDTO) throws ClassNotFoundException, SQLException {
			Connection connection = null;
			PreparedStatement pstmt = null;
		    int count;
			try {
				System.out.println("Try Block");
				connection = dbUtil.getConnection();
	
				pstmt = connection.prepareStatement(USER_DATA_INSERT);
				pstmt.setInt(1, userDTO.getCityId());
				pstmt.setString(2, userDTO.getFirstName());
				pstmt.setString(3, userDTO.getLastName());
				pstmt.setString(4, userDTO.getEmail());
				pstmt.setString(5, userDTO.getMobileNumber());
				pstmt.setString(6, userDTO.getAddress());
				pstmt.setString(7, userDTO.getPanNumber());
				pstmt.setString(8, userDTO.getPassword());
			
			    count = pstmt.executeUpdate(); 
			} catch (Exception e) {
				System.out.println("Catch Block");
				e.printStackTrace();
				throw e;
			} finally {
				// close
				System.out.println("Finally Block");
				dbUtil.close(connection, pstmt, null);
			}
			return count;
		}

		public int Update(UserDTO userDTO) throws Exception {
			
			Connection connection = null;
			PreparedStatement pstmt = null;
			int count;
			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_DATA_UPDATE);
				pstmt.setString(1, userDTO.getFirstName());
				pstmt.setString(2, userDTO.getLastName());
				pstmt.setString(3, userDTO.getAddress());
				pstmt.setInt(4,userDTO.getId());
				
				count = pstmt.executeUpdate() ;		
				}
			catch(Exception e)
			{
			throw e;
			}
			finally {
				dbUtil.close(connection, pstmt);
			}
	        return count;
		}
		public int deleteUserById(int id) throws ClassNotFoundException, SQLException {
			Connection connection = null;
			PreparedStatement pstmt = null;
			int count = 0;
			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_DATA_DELETE_BY_ID);
				pstmt.setInt(1, id);
				count = pstmt.executeUpdate();
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.close(connection, pstmt);
			}

			return count;
		}

		public UserDTO findById(int id) throws ClassNotFoundException, SQLException {
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			UserDTO userDTO = null;
			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_DATA_FIND_BY_ID);
				pstmt.setInt(1, id);

				rs = pstmt.executeQuery();//
				if (rs.next()) {
					userDTO = new UserDTO();
					userDTO.setId(rs.getInt("id"));
					userDTO.setFirstName(rs.getString("first_name"));
					userDTO.setLastName(rs.getString("last_name"));
					userDTO.setAddress(rs.getString("address"));
					//angular
					userDTO.setCityId(Integer.parseInt(rs.getString("city_id")));
					userDTO.setCityName(rs.getString("city_name"));
					userDTO.setStateId(Integer.parseInt(rs.getString("state_id")));
					userDTO.setStateName(rs.getString("state_name"));
					userDTO.setCountryId(Integer.parseInt(rs.getString("country_id")));
					userDTO.setCountryName(rs.getString("country_name"));
					userDTO.setImage(rs.getString("image"));
				}
				return userDTO;
			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.close(connection, pstmt);
			}
		}

		public List<UserDTO> findAll() throws ClassNotFoundException, SQLException {
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<UserDTO> userDTOList = new ArrayList<>();
			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_DATA_FIND_ALL);

				rs = pstmt.executeQuery(); 

				while (rs.next()) { 
					UserDTO userDTO = new UserDTO();
					userDTO.setId(rs.getInt("id"));
					userDTO.setFirstName(rs.getString("first_name"));
					userDTO.setLastName(rs.getString("last_name"));
					userDTO.setEmail(rs.getString("email"));
					userDTO.setMobileNumber(rs.getString("mobile_number"));
					userDTO.setAddress(rs.getString("address"));
					userDTO.setPanNumber("panNumber");;
					userDTO.setImage(rs.getString("image"));
					userDTOList.add(userDTO);

				}

			} catch (Exception e) {
				throw e;
			} finally {
				dbUtil.close(connection, pstmt, rs);
			}

			return userDTOList;
		}
		public UserDTO login(String email ,String password) throws Exception {
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			UserDTO userDTO = null;
			try {
				
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_DATA_FIND_FOR_LOGIN);
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				rs =pstmt.executeQuery();
				if(rs.next()) {
					userDTO = new UserDTO();
					userDTO.setId(rs.getInt("id"));
					userDTO.setFirstName(rs.getString("first_name"));
					userDTO.setLastName(rs.getString("last_name"));
					//userDTO.setEmail(rs.getString("email"));
					userDTO.setMobileNumber(rs.getString("mobile_number"));
					userDTO.setAddress(rs.getString("address"));
				}
			} catch (Exception e) {
				 e.printStackTrace();
				 throw e;
			} finally {
				dbUtil.close(connection, pstmt, rs);
			}

			return userDTO;
		}
		public UserDTO findByMobile(String mobileNumber) throws Exception {
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			UserDTO userDTO = null;
			try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(USER_DATA_FIND_BY_MOBILE);
				pstmt.setString(1, mobileNumber);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					userDTO = new UserDTO();
					userDTO.setId(rs.getInt("id"));
					userDTO.setFirstName(rs.getString("first_name"));
					userDTO.setLastName(rs.getString("last_name"));
					userDTO.setEmail(rs.getString("email"));
					userDTO.setMobileNumber(rs.getString("mobile_number"));
					userDTO.setAddress(rs.getString("address"));
					userDTO.setPanNumber(rs.getString("pan_number"));
					userDTO.setImage(rs.getString("image"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				dbUtil.close(connection, pstmt, rs);
			}
			return userDTO;
		}
		
		}


	
	 
	
	

	


