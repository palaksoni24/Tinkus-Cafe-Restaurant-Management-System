package com.amstech.tinkus.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amstech.tinkus.backend.dto.ItemDTO;
import com.amstech.tinkus.backend.util.DBUtil;

public class ItemDAO {
	
	private DBUtil dbUtil;
	
  public ItemDAO (DBUtil dbUtil) {
	  this.dbUtil = new DBUtil();
	  
  }
  
  public final String  ITEM_DATA_INSERT = "insert into item(name,cost,item_image,quantity,category_id ,type_id,cuisine_id,restaurant_id)" + "value(?,?,?,?,?,?,?,?)";
  public final String ITEM_DATA_UPDATE = " update item set item_image =? ,cost =? where id =?";
  
  public int save(ItemDTO itemDTO) throws ClassNotFoundException, SQLException  {
	  Connection connection = null;
	  PreparedStatement pstmt = null;
	  int count ;
	  try {
		  
		  System.out.println("TRY BLOCK");
		 connection = dbUtil.getConnection();
		 pstmt = connection.prepareStatement(ITEM_DATA_INSERT);
		 
		 pstmt.setString(1,itemDTO.getName());
		 pstmt.setDouble(2,itemDTO.getCost());
		 pstmt.setString(3,itemDTO.getItemImage());
		 pstmt.setInt(4,itemDTO.getQuantity());
		 pstmt.setInt(5,itemDTO.getCategoryId());
		 pstmt.setInt(6,itemDTO.getTypeId());
		 pstmt.setInt(7,itemDTO.getCuisineId());
		 pstmt.setInt(8,itemDTO.getRestaurantId());
		 
		 count = pstmt.executeUpdate();
		 
	  }catch(Exception e){
		  System.out.println("CATCH BLOCK");
		  e.printStackTrace();
		  throw e;
		  
	  }finally {
		  System.out.println("FINALLY BLOCK");
		  dbUtil.close(connection, pstmt, null);
	  }
	return count;
	  
  }
  public int update(ItemDTO itemDTO) throws ClassNotFoundException, SQLException  {
	  Connection connection = null;
	  PreparedStatement pstmt = null;
	  int count ;
	  try {
		  
		  System.out.println("TRY BLOCK");
		 connection = dbUtil.getConnection();
		 pstmt = connection.prepareStatement(ITEM_DATA_UPDATE);
		 
		 pstmt.setDouble(1,itemDTO.getCost());
		 pstmt.setString(2,itemDTO.getItemImage());
		 pstmt.setInt(3,itemDTO.getId());
		
		 count = pstmt.executeUpdate();
		 
	  }catch(Exception e){
		  System.out.println("CATCH BLOCK");
		  e.printStackTrace();
		  throw e;
		  
	  }finally {
		  System.out.println("FINALLY BLOCK");
		  dbUtil.close(connection, pstmt, null);
	  }
	return count;
	  
  }
   
   
   
   
}
