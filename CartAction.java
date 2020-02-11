package com.internousdev.bianco.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.bianco.dao.CartInfoDAO;
import com.internousdev.bianco.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware{
	
	private Map<String,Object>session;
	private String userId;
	private List<CartInfoDTO> cartList =new ArrayList<CartInfoDTO>();
	private int totalPrice;
	
	public String execute() throws SQLException{
		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		String result =ERROR;
		
		//セッションタイムアウトの場合
		if(session.get("logined") ==null){
			return "sessionError";

		//ログイン済み=1
		}else if(session.get("logined")==("1")){
			userId =session.get("user_id").toString();
			
		//ログインしていない時=0
		}else if(session.get("logined")==("0")){
			
			userId = session.get("tmpUserId").toString();
		}
	
		//カートの情報があるかないか
		if(cartInfoDAO.cartCheck(userId)){
			//カート場がある時CartInfoDTOに各商品情報を格納
			cartList = cartInfoDAO.getCartInfo(userId);
		}
		
		//カート内合計金額
		totalPrice = cartInfoDAO.getTotalPlace(userId);
			result =SUCCESS;
			return result;
	}


	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<CartInfoDTO> getCartList() {
		return cartList;
	}

	public void setCartList(List<CartInfoDTO> cartList) {
		this.cartList = cartList;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
