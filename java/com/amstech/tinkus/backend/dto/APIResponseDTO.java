package com.amstech.tinkus.backend.dto;

public class APIResponseDTO {

		private String status;
		private String message;
		private long currentDate;
		private Object data;

		public APIResponseDTO(String status, String message, long currentDate) {
			super();
			this.status = status;
			this.message = message;
			this.currentDate = currentDate;
		}
		
		public APIResponseDTO(String status, String message, long currentDate, Object data) {
			super();
			this.status = status;
			this.message = message;
			this.currentDate = currentDate;
			this.data = data;
		}

	}


