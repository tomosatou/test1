package com.internousdev.bianco.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.bianco.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCartAction extends ActionSupport implements SessionAware{
	
	private Collection<String> checkList;
	private String productId;
	private String sex;
	private String productName;
	private String productNameKana;
	private String imageFilePath;
	private String imageFileName;
	private String price;
	private String releaseCompany;
	private String releaseDate;
	private String totalPrice;
	private String productCount;
	private String subtotal;
	private Map<String, Object> session;

	
	public String execute() throws SQLException{
		String userId = null;
		List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();
		String result = ERROR;
		
		// カート内削除文、処理内容はcartInfoDAO.delete
		for (String productId : checkList) {
			if (productId.equals("false")) {
				break;
			}

			String loginId = "";
			if (session.containsKey("loginId")) {
				loginId = session.get("userId").toString();
			} else {
				loginId = session.get("tempUserId").toString();
			}

			count += cartInfoDAO.delete(loginId, Integer.parseInt(productId));
			
			}
		
		// 削除後の結果をsessionに入れて遷移できるようにする

			cartInfoDtoList = cartInfoDAO.getCartInfoDtoList(userId);
			Iterator<CartInfoDTO> iterator = cartInfoDtoList.iterator();
			if (!(iterator.hasNext())) {
				cartInfoDtoList = null;
			}
			session.put("cartInfoDtoList", cartInfoDtoList);

			int totalPrice = Integer.parseInt(String.valueOf(cartInfoDAO.getTotalPrice(userId)));
			session.put("totalPrice", totalPrice);
		
		result = SUCCESS;
	}
		

	public Collection<String> getCheckList() {
		return checkList;
	}


	public void setCheckList(Collection<String> checkList) {
		this.checkList = checkList;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductNameKana() {
		return productNameKana;
	}


	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}


	public String getImageFilePath() {
		return imageFilePath;
	}


	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}


	public String getImageFileName() {
		return imageFileName;
	}


	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getReleaseCompany() {
		return releaseCompany;
	}


	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public String getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getProductCount() {
		return productCount;
	}


	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}


	public String getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}


	public Map<String, Object> getSession() {
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
