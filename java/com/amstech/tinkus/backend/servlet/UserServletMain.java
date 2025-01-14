package com.amstech.tinkus.backend.servlet;

import java.sql.SQLException;
import java.util.List;

import com.amstech.tinkus.backend.dao.UserDAO;
import com.amstech.tinkus.backend.dto.UserDTO;
import com.amstech.tinkus.backend.service.UserService;
import com.amstech.tinkus.backend.util.DBUtil;

public class UserServletMain {

	private UserService userService;
	private UserDAO userDAO;
	private DBUtil dbUtil;

	public UserServletMain() {
		this.dbUtil = new DBUtil();
		this.userDAO = new UserDAO(dbUtil);
		this.userService = new UserService(userDAO);
	}

	public static void main(String[] args) throws Exception {
		long a = System.currentTimeMillis();
		UserServletMain main = new UserServletMain();

// request
		String task = "findbyid"; // req.getparam('task');
//String task = "update"; // req.getparam('task');

// check for task
		if (task.equalsIgnoreCase("signup")) {
			System.out.println("Task: Save");
			main.save();
		} else if (task.equalsIgnoreCase("update")) {
			System.out.println("Task: Update");
			main.update();
		} else if (task.equalsIgnoreCase("delete")) {
			System.out.println("Task: delete");
			main.deleteUserById();
		} else if (task.equalsIgnoreCase("findById")) {
			System.out.println("Task:findById");
			main.findByUserId();
		} else if (task.equalsIgnoreCase("findAll")) {
			System.out.println("Task:findAll");
			main.findAll();
		} else {
			System.out.println("No task found");
		}
		long b = System.currentTimeMillis();

		long time = b - a;

		System.out.println("time: " + time);
// resp
// ms > micro sec. > nano sec

	}

	public void save() {
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("Hemant"); // request.getParam('firstName');
		userDTO.setLastName("Ahirwar"); // request.getParam('lastName');
		userDTO.setEmail("hae@gmail.com"); // request.getParam('email');
		// request.getParam('password');
		userDTO.setMobileNumber("9953126869"); // request.getParam('mobileNumber');
		userDTO.setAddress("312 veda");
		userDTO.setPanNumber("AAA7654A");// request.getParam('address');
		userDTO.setCityId(1); // request.getParam('cityId');

		try {
			int count = userService.save(userDTO);
			if (count > 0) {
				System.out.println("User account created successfully.");
			} else {
				System.out.println("Failed to create user account.");
			}
		} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update() {
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("Harikishan"); // request.getParam('firstName');
		userDTO.setLastName("Sharma"); // request.getParam('lastName');
		userDTO.setEmail("hari@gmail.com");
		userDTO.setMobileNumber("9953122269");
		userDTO.setId(1);
		try {
			int count = userService.update(userDTO);
			if (count > 0) {
				System.out.println("Use Data Update successfully");
			} else {
				System.out.println("Failed Use Data Update");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteUserById() {
		int userId = 25;
		try {
			int count = userService.deleteByUserId(userId);
			if (count > 0) {
				System.out.println("User Data Delete successfully");
			} else {
				System.out.println("Failed User Data Delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void findByUserId() {
		int userid = 2;
		try {
			UserDTO userDTO = userService.findById(userid);
			
			if (userDTO != null) {
				System.out.println("User data Found Successfully");
				System.out.println("firstname : " + userDTO.getFirstName());
				System.out.println("Image :"+userDTO.getImage());
			} else {
				System.out.println("User data not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void findAll() throws Exception {
		try {
			List<UserDTO> userDTOList = userService.findAll();
			if (!userDTOList.isEmpty()) {
				System.out.println("User data found successfully");
				System.out.println("Size" + userDTOList.size());

				for (UserDTO userDTO : userDTOList) {
					System.out.println("Firstname " + userDTO.getFirstName());
					System.out.println("Image :"+userDTO.getImage());
				}
			} else {
				System.out.println("User data not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
