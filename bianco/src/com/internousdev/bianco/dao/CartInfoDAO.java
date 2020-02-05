package com.internousdev.bianco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.bianco.dto.CartInfoDTO;
import com.internousdev.bianco.util.DBConnector;

public class CartInfoDAO {
	
	private DBConnector db =new DBConnector();
	private Connection con =db.getConnection();
	
	//カート削除機能
	public CartInfoDTO getCartInfo(String id)throws SQLException{
		CartInfoDTO dto =new CartInfoDTO();
		String sql ="select * from product_info where id =?";
		
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(id));
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				dto.setId(rs.getString("id"));
				dto.setProductName(rs.getString("productName"));
				dto.setProductNameKana(rs.getString("productNameKana"));
				dto.setImageFileName(rs.getString("imageFileName"));
				dto.setPrice(rs.getString("price"));
				dto.setReleaseCompany(rs.getString("releaseCompany"));
				dto.setReleaseDate(rs.getString("releaseDate"));
				dto.setProductCount(rs.getString("productCount"));
				dto.setTotalPrice(rs.getString("totalPrice"));
				dto.setRegistDate(rs.getString("registDate"));
				dto.setUpdatedDate(rs.getString("updatedDate"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return dto;	
	}
}