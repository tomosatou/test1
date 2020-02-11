package com.internousdev.bianco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.bianco.dto.CartInfoDTO;
//import com.internousdev.bianco.dto.ProductInfoDTO;
import com.internousdev.bianco.util.DBConnector;

public class CartInfoDAO {
	
	private DBConnector db =new DBConnector();
	private Connection con =db.getConnection();
	private List<CartInfoDTO> cartInfoDTO =new ArrayList<CartInfoDTO>();
	
	
	//△カート情報があるかないか(カート情報を持ってくるメソッド)CartAction(mypageDAO)
	public List<CartInfoDTO> getCartInfo(String userId)throws SQLException{
		DBConnector db =new DBConnector();
		Connection con =db.getConnection();
		List<CartInfoDTO> cartInfoDTO =new ArrayList<CartInfoDTO>();
		
		//テーブル結合(商品情報カート、カート情報)
		String sql="SELECT pi.product_id,pi.product_name,pi.product_name_kana,"
				+ "pi.image_file_path,pi.price,pi.release_company,pi.release_date,"
				+ "ci.product_count,pi.price * ci.product_count totalprice,"
				+ "FROM cart_info ci"
				+ "LEFT JOIN product_info pi"
				+ "ON ci.product_id = pi.product_id"
				+ "WHERE ci.user_id=?"
				+ "ORDER BY regist_date desc,update_date desc";
		
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,userId);
	
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				//カラムproduct_idの値をフィールドproduct_idにセット
				dto.setProductId(rs.getString("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setPrice(rs.getString("price"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setReleaseDate(rs.getString("release_date"));
				dto.setProductCount(rs.getString("product_count"));
				dto.setTotalPrice(rs.getString("total_price"));
				dto.setRegistDate(rs.getString("regist_date"));
				dto.setUpdateDate(rs.getString("update_date"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return cartInfoDTO;
	}
	
	// カート(cart_infoテーブル)に商品の情報を入れる
	public int cartinsert(String userId, String tempUserId, int productId, String productCount, int price) {
		DBConnector db =new DBConnector();
		Connection con =db.getConnection();
		int count = 0;
		String sql = "insert into cart_info(user_id, temp_user_id, product_id, product_count, price, regist_date)"
					+ "values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, tempUserId);
			ps.setInt(3, productId);
			ps.setString(4, productCount);
			ps.setInt(5, price);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return count;
	}
	
	
	//〇カート合計金額(トータルプライス)CartAction
	public int getTotalPlace(String loginId)throws SQLException{
		DBConnector db =new DBConnector();
		Connection con =db.getConnection();
		int totalPrice =0;
		
		String sql="select sum(product_count * price) as total_price "
				+ "from cart_info "
				+ "where login_id=? "
				+ "group by user_id";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);			
			ps.setString(1, loginId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				totalPrice = rs.getInt("total_price");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return totalPrice;
	}
	
	//〇cart.jspの削除ボタンを押した時にDeleteCartActionにて使用される機能
		public int delete(String userId, int productId) {
			DBConnector db =new DBConnector();
			Connection con =db.getConnection();
			int count = 0;
			String sql = "delete from cart_info where user_id=? and product_id=?";

			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, userId);
				ps.setInt(2, productId);
				//executeUpdateでpsのsqlを実行
				count = ps.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				con.close();
			}
			return count;
		}
	
	//△ユーザーIDに紐付くカート情報を宛先情報テーブル全削除
	public int SettlementDeleteAll(String userId)throws SQLException{
		DBConnector db =new DBConnector();
		Connection con =db.getConnection();
		DestinationDTO dto =new DestinationDTO();
		String sql = "delete * from destination_info where user_id=? ";
		int result =0;
		
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,userId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				if(rs == 0){
					rs = 0;
				}else{
					rs=1;
				
				}	
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return result;
	}
	
	//〇追加する商品IDと一致するデータが存在チェック(AddCartaction)
	
	public boolean isExistCart(String userId,String productId){
		DBConnector db =new DBConnector();
		Connection con =db.getConnection();
		
		String sql ="select count(*) as count from cart_info where user_id=? product_id=?";
		boolean result = false;
		
		try{
			
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,productId);
			ps.setString(1,userId);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				if(rs.getInt("COUNT")>0){
					result =true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		}
		
	
	//〇商品IDとカート情報テーブルから持ってきた商品IDが一値した場合
	//すでに商品が入っている場合は、updateする
	//個数更新(存在する)カウントアップ
	public int productUpDate(String userId,String tempUserId,int productId,String productCount){
		DBConnector db =new DBConnector();
		Connection con =db.getConnection();
		int result =0;	
		String sql ="UPDATE cart_info SET user_id=?, temp_user_id=?, product_id=?, product_count=(product_count+?)"
				+ "where product_id=? and  user_id=?";
		
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, tempUserId);
			ps.setInt(3, productId);
			ps.setString(4, productCount);
			ps.setInt(6,productId);
			ps.setString(7, userId);
			
			result=ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return result;
	}

	
	//〇追加する商品IDと一致するデータが存在しない(カウントしない)
	public int linToUserId(String userId,String productId,String tmpUserId){
		DBConnector db =new DBConnector();
		Connection con =db.getConnection();
		String sql ="update * from cart_info where user_id=? product_id=? product_count=? ";
		int result =0;	
		
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setString(2,productId);
			ps.setString(3,tmpUserId);
			
			result=ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return result;
	}
	
	//△ログイン情報をアップデードする(LoginAction)決算画面にて使用
	public String  LoginUpdate(String userId,String id){
		DBConnector db =new DBConnector();
		Connection con =db.getConnection();
		UserInfoDTO dto =new UserInfoDTO();
		
		String sql="update * from user_info where user_id =? id=?";
		
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setString(2,id);
			ResultSet rs=ps.executeQuery();
			
			//検索結果があれば
			while(rs.next()){
				//必要な列から値を取り出し、オブジェクトを生成
				dto.setUserId(rs.getString("userId"));
				dto.setId(rs.getString("userId"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return dto;	
	}
	
	//仮ユーザーIDと商品を繋ぐ　linktouserid
	public String  linkToUserId(String userId,String tmpUserId){
		DBConnector db =new DBConnector();
		Connection con =db.getConnection();
		int count =0;
		
		String sql="update cart_info set user_id=? , temp_user_id=null where temp_user_id=?";
		
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,userId);
			ps.setString(2,tmpUserId);
			ResultSet rs=ps.executeQuery();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return count;	
	}
	
	
	//△仮ユーザーID用のカート削除機能
	public String tmpdelet(String tmpUserId,int productId)throws SQLException{
		
		DBConnector db =new DBConnector(); //データベース接続管理クラスの変数宣言
		Connection con =db.getConnection(); //データベース接続情報
		PreparedStatement ps; //sql管理情報
		int result =0; //検索結果
		
		String sql ="delete * from cart_info where user_id =? product_id=?"; //カート削除
		
		try{
			//DELETE文の登録と実行
			ps=con.prepareStatement(sql);
			ps.setString(1,tmpUserId);
			ps.setInt(2,productId);
			
			result=ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return result;	
	}
	
	
	//〇カート削除機能(チェックされたものを削除)DeletCartAction(itemDeleteCompleteDAO)
	public String deletCartInfo(String userId,int productId)throws SQLException{
		
		DBConnector db =new DBConnector(); //データベース接続管理クラスの変数宣言
		Connection con =db.getConnection(); //データベース接続情報
		PreparedStatement ps; //sql管理情報
		int result =0; //検索結果
		
		String sql ="delete * from cart_info where user_id =? product_id=?"; //カート削除
		
		try{
			//DELETE文の登録と実行
			ps=con.prepareStatement(sql); 
			ps.setString(1,userId);
			ps.setInt(2,productId);
			
			result=ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return result;	
	}
}