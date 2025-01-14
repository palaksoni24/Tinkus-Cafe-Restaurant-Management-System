package com.amstech.tinkus.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.amstech.tinkus.backend.dao.UserDAO;
import com.amstech.tinkus.backend.dto.APIResponseDTO;
import com.amstech.tinkus.backend.dto.UserDTO;
import com.amstech.tinkus.backend.dto.UserLoginDTO;
import com.amstech.tinkus.backend.service.UserService;
import com.amstech.tinkus.backend.util.DBUtil;
import com.google.gson.Gson;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/usersAPI")
public class UserServletAPI extends HttpServlet {
	
	private UserService userService;
	private UserDAO userDAO;
	private DBUtil dbUtil;
  
	public UserServletAPI() {
		System.out.println("UserServlet: Object created..");
		this.dbUtil = new DBUtil();
		this.userDAO = new UserDAO(dbUtil);
		this.userService = new UserService(userDAO);
	}


	public void init(ServletConfig config) throws ServletException {
		System.out.println("UserServlet: Init Method");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet: DoGet method");

		String task = request.getParameter("task");
		System.out.println("task: " + task);

		if (task.equalsIgnoreCase("findByMobile")) {
			findByMobileNumber(request, response);
		} else if (task.equalsIgnoreCase("findAll")) {
			findAll(request, response);
		}  else if (task.equalsIgnoreCase("findByid")) {
		    findById(request, response);
	}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	System.out.println("UserServlet: Post Method");
	String task = request.getParameter("task");
	System.out.println("Task :" + task);
	if(task.equalsIgnoreCase("login"))
		login(request , response);
	else if (task.equalsIgnoreCase("signup"))
	    saveUser(request, response);
	else 
		System.out.println("No task found.");
	
}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("UserServlet: doPut method..");
		update(request, response);
	
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("UserServlet: doDelete method..");
		deleteById(request, response);
	}


private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			try {
				response.setStatus(200);
				String inputStringJson = request.getReader().lines().collect(Collectors.joining());

				UserLoginDTO userLoginDTO = new Gson().fromJson(inputStringJson, UserLoginDTO.class);

				String username = userLoginDTO.getUsername();
				String password = userLoginDTO.getPassword();

				UserDTO userDTO = userService.login(username, password);
				if (userDTO != null) {
					APIResponseDTO apiResponseDTO = new APIResponseDTO("Success", "User login successfully.", new Date().getTime(), userDTO);
					String data = new com.google.gson.Gson().toJson(apiResponseDTO);
					writer.append(data);

				} else {
					APIResponseDTO apiResponseDTO = new APIResponseDTO("Failed", "user name and password is invalid", new Date().getTime());
					String data = new com.google.gson.Gson().toJson(apiResponseDTO);
					writer.append(data);

				}

			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(500);
				APIResponseDTO apiResponseDTO = new APIResponseDTO("Error", e.getMessage(), new Date().getTime());
				String data = new com.google.gson.Gson().toJson(apiResponseDTO);
				writer.append(data);

			}
		}

public void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
	response.setContentType("application/json");
	PrintWriter writer = response.getWriter();
	try {
		response.setStatus(200);

		String userDTOString = request.getReader().lines().collect(Collectors.joining());

		UserDTO userDTO = new Gson().fromJson(userDTOString, UserDTO.class);
		System.out.println(userDTO.getFirstName());
		int count = userService.save(userDTO);
		if (count > 0) {

			APIResponseDTO apiResponseDTO = new APIResponseDTO("Success", "User created successfuly.",
					new Date().getTime());
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);

		} else {
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Failed", "Failed to create user.",
					new Date().getTime());
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);

		}
	} catch (Exception e) {
		e.printStackTrace();
		response.setStatus(500);
		APIResponseDTO apiResponseDTO = new APIResponseDTO("Error", e.getMessage(), new Date().getTime());
		String data = new com.google.gson.Gson().toJson(apiResponseDTO);
		writer.append(data);
	}

}


private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
	response.setContentType("application/json");
	PrintWriter writer = response.getWriter();

	try {
		response.setStatus(200);

		String dataInString = request.getReader().lines().collect(Collectors.joining());

		UserDTO userDTO = new Gson().fromJson(dataInString, UserDTO.class);

		int count = userService.update(userDTO);
		if (count > 0) {

			UserDTO userDTOSaved = userService.findById(userDTO.getId());
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Success", "User updated successfuly.",
					new Date().getTime(), userDTOSaved);
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);

		} else {
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Failed", "Failed to update user.",
					new Date().getTime());
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);

		}

	} catch (Exception e) {
		e.printStackTrace();
		response.setStatus(500);
		response.setStatus(500);
		APIResponseDTO apiResponseDTO = new APIResponseDTO("Error", e.getMessage(), new Date().getTime());
		String data = new com.google.gson.Gson().toJson(apiResponseDTO);
		writer.append(data);

	}
}

public void findById(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	response.setContentType("application/json");
	PrintWriter writer = response.getWriter();
	try {
		response.setStatus(200);
		int id = Integer.parseInt(request.getParameter("userId"));

		UserDTO userDTO = userService.findById(id);

		if (userDTO != null) {
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Success", "User found successfully.",
					new Date().getTime(), userDTO);
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);
		} else {
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Failed", "No user found.", new Date().getTime());
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);
		}

	} catch (Exception e) {
		e.printStackTrace();
		response.setStatus(500);
		APIResponseDTO apiResponseDTO = new APIResponseDTO("Error", e.getMessage(), new Date().getTime());
		String data = new com.google.gson.Gson().toJson(apiResponseDTO);
		writer.append(data);
	}
}

public void findByMobileNumber(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	response.setContentType("application/json");
	PrintWriter writer = response.getWriter();
	try {
		response.setStatus(200);
		String mobileNumber = request.getParameter("mobileNumber");

		UserDTO userDTO = userService.findByMobile(mobileNumber);
		if (userDTO != null) {
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Success", "User found successfully.",
					new Date().getTime(), userDTO);
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);
		} else {
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Failed", "No user found.", new Date().getTime());
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);
		}

	} catch (Exception e) {
		e.printStackTrace();
		response.setStatus(500);
		APIResponseDTO apiResponseDTO = new APIResponseDTO("Error", e.getMessage(), new Date().getTime());
		String data = new com.google.gson.Gson().toJson(apiResponseDTO);
		writer.append(data);
	}

}



public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("application/json");
	PrintWriter writer = response.getWriter();
	try {
		response.setStatus(200);
		List<UserDTO> userDTOList = userService.findAll();
		if (!userDTOList.isEmpty()) {
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Success", "User found successfully.",
					new Date().getTime(), userDTOList);
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);
		} else {
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Failed", "No user found.", new Date().getTime());
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);
		}

	} catch (Exception e) {
		e.printStackTrace();
		response.setStatus(500);
		APIResponseDTO apiResponseDTO = new APIResponseDTO("Error", e.getMessage(), new Date().getTime());
		String data = new com.google.gson.Gson().toJson(apiResponseDTO);
		writer.append(data);
	}
}

public void deleteById(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	response.setContentType("application/json");
	PrintWriter writer = response.getWriter();
	try {
		response.setStatus(200);
		int id = Integer.parseInt(request.getParameter("userId"));
		int count = userService.deleteByUserId(id);
		if (count > 0) {
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Success", "User deleted successfully.",
					new Date().getTime());
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);
		} else {
			APIResponseDTO apiResponseDTO = new APIResponseDTO("Failed", "No user found.", new Date().getTime());
			String data = new com.google.gson.Gson().toJson(apiResponseDTO);
			writer.append(data);
		}

	} catch (Exception e) {
		e.printStackTrace();
		response.setStatus(500);
		APIResponseDTO apiResponseDTO = new APIResponseDTO("Error", e.getMessage(), new Date().getTime());
		String data = new com.google.gson.Gson().toJson(apiResponseDTO);
		writer.append(data);
	}
}

}
