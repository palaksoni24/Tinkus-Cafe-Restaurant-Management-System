package com.amstech.tinkus.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.amstech.tinkus.backend.dao.StateDAO;
import com.amstech.tinkus.backend.dto.APIResponseDTO;
import com.amstech.tinkus.backend.dto.StateDTO;
import com.amstech.tinkus.backend.service.StateService;
import com.amstech.tinkus.backend.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


	@WebServlet("/states")
	public class StateServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private StateService stateService;
		private StateDAO stateDAO;
		private DBUtil dbUtil;
		

	    public StateServlet() {
	    	this.dbUtil = new DBUtil();
			this.stateDAO = new StateDAO(dbUtil);
			this.stateService = new StateService(stateDAO);
	    }

		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			PrintWriter writer = response.getWriter();

			try {
				response.setStatus(200);
				response.setContentType("application/json");
				int countryId = Integer.parseInt(request.getParameter("countryId"));

				List<StateDTO> stateDTOList = stateService.findByCountryId(countryId);
				System.out.println("List : " + stateDTOList.size());
				if (!stateDTOList.isEmpty()) {

					APIResponseDTO apiResponseDTO = new APIResponseDTO("success", "State found successfully.",
							new Date().getTime(), stateDTOList);
					String data = new com.google.gson.Gson().toJson(apiResponseDTO);
					writer.append(data);
				} else {
					APIResponseDTO apiResponseDTO = new APIResponseDTO("Failed", "Failed to find state.",
							new Date().getTime());
					String data = new com.google.gson.Gson().toJson(apiResponseDTO);
					writer.append(data);
				}
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(500);
				APIResponseDTO apiResponseDTO = new APIResponseDTO("Error", e.getMessage(), new Date().getTime());
				String data = new com.google.gson.Gson().toJson(apiResponseDTO);
				writer.append(data);
			}

		}

	}

			
			
			
			
			
			
			
			
			
//			try {
//				response.setContentType("application/json");
//				PrintWriter writer = response.getWriter();
//				int countryId = Integer.parseInt(request.getParameter("countryId"));
//
//				List<StateDTO> stateDTOList = stateService.findByCountryId(countryId);
//				System.out.println("List : " + stateDTOList.size());
//				if (!stateDTOList.isEmpty()) {
//					
//					String data = new com.google.gson.Gson().toJson(stateDTOList);
//					writer.append(data);
//
//				} else {
//					// return message
//				}
//				writer.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//				// return message
//			}
//
//		}
//		
//		}