package com.amstech.tinkus.backend.service;

import java.sql.SQLException;

import com.amstech.tinkus.backend.dao.ItemDAO;
import com.amstech.tinkus.backend.dao.UserDAO;
import com.amstech.tinkus.backend.dto.ItemDTO;
import com.amstech.tinkus.backend.dto.UserDTO;

public class ItemService {
	
	private ItemDAO itemDAO;
	
	public ItemService(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
		
	}
	public int save(ItemDTO itemDTO) throws ClassNotFoundException, SQLException {
		int count = itemDAO.save(itemDTO);
		if(count!=0) {
			
		}
			return count;
	}
	
//	public int update(ItemDTO itemDTO) throws ClassNotFoundException, SQLException {
//		int count = itemDAO.update(itemDTO);
//
//			return count;
//	}
	
}
