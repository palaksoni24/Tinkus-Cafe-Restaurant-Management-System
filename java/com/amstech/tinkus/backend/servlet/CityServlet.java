package com.amstech.tinkus.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amstech.tinkus.backend.dao.CityDAO;
import com.amstech.tinkus.backend.dto.CityDTO;
import com.amstech.tinkus.backend.service.CityService;
import com.amstech.tinkus.backend.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cities")
public class CityServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private CityService cityService;
		private CityDAO cityDAO;
		private DBUtil dbUtil;
	    
	    public CityServlet() {
	    	this.dbUtil = new DBUtil();
			this.cityDAO = new CityDAO(dbUtil);
			this.cityService = new CityService(cityDAO);
	    
	    }
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter writer = response.getWriter();

			try {
				response.setStatus(200);
				response.setContentType("application/json");
				int stateId = Integer.parseInt(request.getParameter("stateId"));

				List<CityDTO> cityDTOList = cityService.findBystateId(stateId);
				System.out.println("List : " + cityDTOList.size());
				if (!cityDTOList.isEmpty()) {
					Map<String, Object> resp = new HashMap<>();
					resp.put("status", "success");
					resp.put("message", "City found successfully");
					resp.put("data", cityDTOList);
					resp.put("currentDate", new Date().getTime());
					String data = new com.google.gson.Gson().toJson(resp);
					writer.append(data);

				} else {
					Map<String, Object> resp = new HashMap<>();
					resp.put("status", "failed");
					resp.put("message", "Failed to find city");
					resp.put("data", null);
					resp.put("currentDate", new Date().getTime());
					String data = new com.google.gson.Gson().toJson(resp);
					writer.append(data);
				}
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(500);
				Map<String, Object> resp = new HashMap<>();
				resp.put("status", "error");
				resp.put("message", e.getMessage());
				resp.put("data", null);
				resp.put("currentDate", new Date().getTime());
				String data = new com.google.gson.Gson().toJson(resp);
				writer.append(data);
			}

		}
				
			
		}
//			try {
//				response.setContentType("application/json");
//				PrintWriter writer = response.getWriter();
//				int stateId = Integer.parseInt(request.getParameter("stateId"));
//
//				List<CityDTO> cityDTOList = cityService.findBystateId(stateId);
//				System.out.println("List : " + cityDTOList.size());
//				if (!cityDTOList.isEmpty()) {
//					
//					String data = new com.google.gson.Gson().toJson(cityDTOList);
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
			
			
		