package com.amstech.tinkus.backend.service;

import java.sql.SQLException;
import java.util.List;

import com.amstech.tinkus.backend.dao.UserDAO;
import com.amstech.tinkus.backend.dto.UserDTO;

public class UserService {


		private UserDAO userDAO;

		public UserService(UserDAO userDAO) {
			System.out.println("Creating UserService Object");
			this.userDAO = userDAO;
		}

		public int save(UserDTO userDTO) throws ClassNotFoundException, SQLException {
			
			int count = userDAO.save(userDTO);
			if (count != 0) {
							
			}
			return count;
		}

		public int update(UserDTO userDTO) throws Exception {
			return userDAO.Update(userDTO);
		}

		public int deleteByUserId(int id) throws ClassNotFoundException, SQLException {
			int count = userDAO.deleteUserById(id);
			if (count != 0) {
			}
			return count;
		}

		public UserDTO findById(int id) throws ClassNotFoundException, SQLException {
			return userDAO.findById(id);
		}

		public List<UserDTO> findAll() throws ClassNotFoundException, SQLException {
			return userDAO.findAll();
		}

		public  UserDTO login(String email ,String password) throws Exception {
			// TODO Auto-generated method stub
			return userDAO.login(email, password);
		}

		public UserDTO findByMobile(String mobileNumber) throws Exception {
			return userDAO.findByMobile(mobileNumber);

		}

		

}

	


