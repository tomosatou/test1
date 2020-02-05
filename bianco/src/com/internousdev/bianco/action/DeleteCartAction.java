package com.internousdev.bianco.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.bianco.dao.CartInfoDAO;
import com.internousdev.bianco.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCartAction extends ActionSupport implements SessionAware{
	
	private String id;
	private CartInfoDTO cartDetailsDTO;
	private Map<String,Object>session;
	
	public String execute() throws SQLException{
		CartInfoDAO dao = new CartInfoDAO();
		cartDetailsDTO = dao.getCartInfo(id);
		
		if(cartDetailsDTO.getProductName() == null){
			cartDetailsDTO = null ;
		}
		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CartInfoDTO getCartDetailsDTO() {
		return cartDetailsDTO;
	}

	public void setCartDetailsDTO(CartInfoDTO cartDetailsDTO) {
		this.cartDetailsDTO = cartDetailsDTO;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
