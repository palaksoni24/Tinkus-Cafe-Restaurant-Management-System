package com.amstech.tinkus.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.amstech.tinkus.backend.dao.CountryDAO;
import com.amstech.tinkus.backend.dto.APIResponseDTO;
import com.amstech.tinkus.backend.dto.CountryDTO;
import com.amstech.tinkus.backend.service.CountryService;
import com.amstech.tinkus.backend.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

	@WebServlet("/countries")
	public class CountryServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private CountryService countryService;
		private CountryDAO countryDAO;
		private DBUtil dbUtil;

	    
	    public CountryServlet() {
	    	this.dbUtil = new DBUtil();
			this.countryDAO = new CountryDAO(dbUtil);
			this.countryService = new CountryService(countryDAO);
	    	
	    }
	    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			PrintWriter writer = response.getWriter();

			try {
				response.setStatus(200);
				response.setContentType("application/json");

				List<CountryDTO> countryDTOList = countryService.findAll();
				System.out.println("List : " + countryDTOList.size());
				if (!countryDTOList.isEmpty()) {
					APIResponseDTO apiResponseDTO = new APIResponseDTO("success", "Country found successfully.",new Date().getTime(), countryDTOList);
					String data = new com.google.gson.Gson().toJson(apiResponseDTO);
					writer.append(data);

				} else {
					APIResponseDTO apiResponseDTO = new APIResponseDTO("Failed", "Failed to find countries.",new Date().getTime());
					String data = new com.google.gson.Gson().toJson(apiResponseDTO);
					writer.append(data);
				}
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(500);
				APIResponseDTO apiResponseDTO = new APIResponseDTO("Error", e.getMessage(),new Date().getTime());
				String data = new com.google.gson.Gson().toJson(apiResponseDTO);
				writer.append(data);
			}

		}

	

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			try {
//				response.setContentType("application/json");
//				PrintWriter writer = response.getWriter();
//
//				List<CountryDTO> countryDTOList = countryService.findAll();
//				System.out.println("List : " + countryDTOList.size());
//				if (!countryDTOList.isEmpty()) {
//					// return data
////					ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
////					String data = ow.writeValueAsString(countryDTOList);
//
//					String data = new com.google.gson.Gson().toJson(countryDTOList);
//
//					
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
		}