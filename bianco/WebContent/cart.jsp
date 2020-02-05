<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cart画面</title>
</head>
<body>
	<div id="header"></div>
	<div id="main">
		<div id="top"><p>商品一覧</p></div>
		<s:if test="CartInfoDTOList==null">
			<h3>カート情報はありません。</h3>
		</s:if>
		<s:elseif test="message==null">
			<table border="1">
					<tr>
						<th>#</th>
						<th>商品名</th>
						<th>ふりがな</th>
						<th>商品画像</th>
						<th>値段</th>
						<th>発売会社</th>
						<th>発売年月日</th>
						<th>購入個数</th>
						<th>合計金額</th>
					</tr>
				<s:iterator value="CartInfoDTOList">
					<tr>
						<td><a href='<s:url action="DeleteCartAction">
						<s:param name="id"value="%{id}"/></s:url>'><input type ="checkbox"></a></td>
						<td><s:property value="productName"/></td>
						<td><s:property value="productNameKana"/></td>
						<td><s:property value="imageFileName"/><span>個</span></td>
						<td><s:property value="price"/><span>円</span></td>
						<td><s:property value="releaseCompany"/></td>
						<td><s:property value="releaseDate"/></td>
						<td><s:property value="productCount"/></td>
						<td><s:property value="totalPrice"/></td>
						<td><s:property value="registDate"/></td>
						<td><s:property value="updatedDate"/></td>
					</tr>
				</s:iterator>
			</table>
			<tr>
				<td><a href='<s:url action="ItemListDeleteConfirmAction"/>'><s:submit value="決済"/></a></td>
				<td><a href='<s:url action="DeleteCartAction"/>'><s:submit value="削除"/></a></td>
			</tr>
		</s:elseif>
		<s:if test="message !=null">
			<h3><s:property value="message"/></h3>
		</s:if>
	</div>
	<div id="footer"></div>	
	
</body>
</html>