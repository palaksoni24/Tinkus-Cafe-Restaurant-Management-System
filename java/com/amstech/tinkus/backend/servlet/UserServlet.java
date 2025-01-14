package com.amstech.tinkus.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.amstech.tinkus.backend.dao.UserDAO;
import com.amstech.tinkus.backend.dto.UserDTO;
import com.amstech.tinkus.backend.service.UserService;
import com.amstech.tinkus.backend.util.DBUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
//@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	
	
	private UserService userService;
	private UserDAO userDAO;
	private DBUtil dbUtil;
  
    public UserServlet() {
    	System.out.println("Creating UserServlet Object");
    	this.dbUtil = new DBUtil();
		this.userDAO = new UserDAO(dbUtil);
		this.userService = new UserService(userDAO);
    }


	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("UserServlet: Init Method");
	}

	
protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	System.out.println("UserServlet: DoGet method");

	String task = request.getParameter("task");
	System.out.println("task: " + task);

	if (task.equalsIgnoreCase("findByMobile")) {
		findByMobile(request, response);
	} else if (task.equalsIgnoreCase("findAll")) {
		findAll(request, response);
	}
	 else if (task.equalsIgnoreCase("deleteByid")) {
		deleteById(request, response);
  }   else if (task.equalsIgnoreCase("findByid")) {
	findById(request, response);
}
	
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub Dopost is generally used to update or post some information to the server Post method variables are not displayed in the URL.
		
		System.out.println("UserServlet: Post Method");
		String task = request.getParameter("task");
		System.out.println("Task :" + task);
		if(task.equalsIgnoreCase("login"))
			login(request , response);
		else if (task.equalsIgnoreCase("signup"))
		saveUser(request, response);
		else if (task.equalsIgnoreCase("updateById"))
			updateById(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String mobileNumber = request.getParameter("mnumber");
			String password = request.getParameter("password");
			UserDTO userDTO = userService.login(mobileNumber, password);
			if(userDTO != null) {
				System.out.println("User login successfully..");
				RequestDispatcher rd =request.getRequestDispatcher("home.jsp");
				request.setAttribute("activeUserDTO", userDTO);
				rd.forward(request, response);
			}else{
				System.out.println("Username password is wrong..");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "failed");
				request.setAttribute("message", "Username password is wrong");
				request.setAttribute("redirectUrl", "login.jsp");
				rd.forward(request, response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();;
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "failed");
			request.setAttribute("message", e.getMessage());
			request.setAttribute("redirectUrl", "signup.jsp");
			rd.forward(request, response);
			response.sendRedirect("signup.jsp");
			
		}
		
	}

	public void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.getWriter().append("saving user detail...........");
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName(request.getParameter("firstName")); // request.getParam('firstName');
		userDTO.setLastName(request.getParameter("lastName")); // request.getParam('lastName');
		userDTO.setEmail(request.getParameter("email")); // request.getParam('email');
		
		userDTO.setMobileNumber(request.getParameter("mobileNumber")); // request.getParam('mobileNumber');
		userDTO.setAddress(request.getParameter("address"));
		userDTO.setPanNumber(request.getParameter("panNumber"));
		userDTO.setPassword(request.getParameter("password"));// request.getParam('address');
		userDTO.setCityId(1);
		
		try {
			int count = userService.save(userDTO);
			if (count > 0) {
				RequestDispatcher rd =request.getRequestDispatcher("message.jsp");
				request.setAttribute("status","success");
				request.setAttribute("message", "User created successfully");
				request.setAttribute("redirectUrl", "login.jsp");
				rd.forward(request, response);
				System.out.println("User account created successfully.");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Failed");
				request.setAttribute("message", "Failed to create user account");
				request.setAttribute("redirectUrl", "signup.jsp");
				rd.forward(request, response);
				System.out.println("Failed to create user account.");
			}
		} catch (Exception e) {

			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Failed");
			request.setAttribute("message", e.getMessage());
			request.setAttribute("redirectUrl", "signup.jsp");
			rd.forward(request, response);
			response.sendRedirect("signup.jsp");
			e.printStackTrace();
		} 

	}


	private void findByMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mobileNumber = request.getParameter("mobileNumber");
		System.out.println("Fetching user data by mobileNumber: " + mobileNumber);

		try {

			UserDTO userDTO = userService.findByMobile(mobileNumber);
			if (userDTO != null) {
				System.out.println("User found successfully, firstName: " + userDTO.getFirstName());

				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				request.setAttribute("userDTO", userDTO);
				rd.forward(request, response);
			} else {

				System.out.println("Failed to find user by mobile number: " + mobileNumber);
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Failed");
				request.setAttribute("message", "Failed to find user by mobile number: " + mobileNumber);
				request.setAttribute("redirectUrl", "home.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Failed");
			request.setAttribute("message", e.getMessage());
			request.setAttribute("redirectUrl", "home.jsp");
			rd.forward(request, response);
			response.sendRedirect("home.jsp");
		}
	
	}
	private void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Fetching all user data ");

		try {

			List<UserDTO> userDTOList = userService.findAll();
			if (!userDTOList.isEmpty()) {
				System.out.println("User found successfully, user count: " + userDTOList.size());

				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				request.setAttribute("userDTOList", userDTOList);
				rd.forward(request, response);
			} else {

				System.out.println("Failed to find all users.");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Failed");
				request.setAttribute("message", "Failed to find all users.");
				request.setAttribute("redirectUrl", "home.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Failed");
			request.setAttribute("message", e.getMessage());
			request.setAttribute("redirectUrl", "home.jsp");
			rd.forward(request, response);
			response.sendRedirect("home.jsp");
		}
	}

	private void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("Delete user by id: " + id);

		try {

			int count = userService.deleteByUserId(Integer.parseInt(id));
			if (count > 0) {
				System.out.println("User deleted successfully, id: " + id);
				request.setAttribute("status", "Success");
				request.setAttribute("message", "User deleted successfully, id: " + id);
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			} else {

				System.out.println("Failed to delete user by id: " + id);
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Failed");
				request.setAttribute("message", "Failed to delete user by id: " + id);
				request.setAttribute("redirectUrl", "home.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Failed");
			request.setAttribute("message", e.getMessage());
			request.setAttribute("redirectUrl", "home.jsp");
			rd.forward(request, response);
			response.sendRedirect("home.jsp");
		}

	}private void updateById(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.getWriter().append("Updating user detail...");

		UserDTO userDTO = new UserDTO();
		userDTO.setId(Integer.parseInt(request.getParameter("id")));
		userDTO.setFirstName(request.getParameter("firstName"));
		userDTO.setLastName(request.getParameter("lastName"));
		userDTO.setEmail(request.getParameter("email"));
		userDTO.setMobileNumber(request.getParameter("mobileNumber"));
		userDTO.setAddress(request.getParameter("address"));


		try {
			int count = userService.update(userDTO);
			if (count > 0) {

				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Success");
				request.setAttribute("message", "User updated successfully");
				request.setAttribute("redirectUrl", "home.jsp");
				rd.forward(request, response);

				System.out.println("User updated successfully.");
			} else {
				
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Failed");
				request.setAttribute("message", "Failed to update user account");
				request.setAttribute("redirectUrl", "home.jsp");
				rd.forward(request, response);

				System.out.println("Failed to create user account.");
			}
		} catch (Exception e) {
			
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Failed");
			request.setAttribute("message", e.getMessage());
			request.setAttribute("redirectUrl", "home.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}
	private void findById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("Fetching user data by id: " + id);

		try {

			UserDTO userDTO = userService.findById(Integer.parseInt(id));
			if (userDTO != null) {
				System.out.println("User found successfully, firstName: " + userDTO.getFirstName());

				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				request.setAttribute("userDTOEdit", userDTO);
				rd.forward(request, response);
			} else {

				System.out.println("Failed to find user by id: " + id);
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Failed");
				request.setAttribute("message", "Failed to find user by id: " + id);
				request.setAttribute("redirectUrl", "home.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("status", "Failed");
			request.setAttribute("message", e.getMessage());
			request.setAttribute("redirectUrl", "home.jsp");
			rd.forward(request, response);
			response.sendRedirect("home.jsp");
		}

}
//	public void destroy() {
//		// TODO Auto-generated method stub
//		System.out.println("UserServlet:Destroy Method");
//	}
//	
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	// TODO Auto-generated method stub
//	System.out.println("UserServlet: Service Method");
//}handling HTTP request and providing responses HTTPSERVLET

//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	// TODO Auto-generated method stub DoGet method generally is used to query or to get some information from the server Get method is visible to every one (It will be displayed in the address bar of browser ).
//	
////	System.out.println(" UserServlet: Get Method");
////	PrintWriter printWriter = response.getWriter();//Returns a PrintWriter object that can send character text to the client.
////	response.setContentType("text/html");
////	reqCount++;
////
////	printWriter.append("<html>");//The PrintWriter object is used to transmit character text to the client./prints text data to a character stream.
////	printWriter.append("<head>");//	It is used to append the specified character to the writer.
////	printWriter.append("<title> This is first page </title>");
////	printWriter.append("</head>");
////	printWriter.append("<body>");
////	printWriter.append("<h1> Welcome to my first servlet page</h1>");
////	printWriter.append("<h2> First Name :" +firstName + "</h2>");
////	printWriter.append("<h2> Last Name :" +lastName + "</h2>");
////	printWriter.append("<h1> Request Count: " + reqCount+ "</h1>");
////
////	printWriter.append("</body>");
////	printWriter.append("</html>");
////    printWriter.close();  Defines an object that receives requests from the client and sends them to any resource (such as a servlet, HTML file, or JSP file) on the serve
////  private String firstName="Rupali";The RequestDispatcher interface provides the facility of dispatching the request to another resource it may be html, servlet or jsp
////  private String lastName="Neelkanth";The RequestDispatcher interface provides the facility of dispatching the request to another resource it may be html, servlet or jsp. This interface can also be used to include the content of another resource also. It is one of the way of servlet collaboration.
////  private Integer reqCount = 0;
//}

}
