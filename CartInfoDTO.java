package com.internousdev.bianco.dto;

public class CartInfoDTO {
	
	private int id;
	private String userId;
	private String tempUserId;
	private int productId;
	private String productCount;
	private int price;
	private String productName;
	private String productNameKana;
	private String imageFilePath;
	private String imageFileName;

	private String releaseCompany;
	private int totalePeice;
	private int sumtotal;
	private String registDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTempUserId() {
		return tempUserId;
	}
	public void setTempUserId(String tempUserId) {
		this.tempUserId = tempUserId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductCount() {
		return productCount;
	}
	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public String getReleaseCompany() {
		return releaseCompany;
	}
	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}
	public int getTotalePeice() {
		return totalePeice;
	}
	public void setTotalePeice(int totalePeice) {
		this.totalePeice = totalePeice;
	}
	public int getSumtotal() {
		return sumtotal;
	}
	public void setSumtotal(int sumtotal) {
		this.sumtotal = sumtotal;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	
	
}
