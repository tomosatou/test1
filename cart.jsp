<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート画面</title>
<script type = "text/javascript">
  		
  	    function checkValue() {
  	    	
  	    	var checkList = document.getElementsByClassName("checkList");
  	    	//初期化
  	    	var checkFlag = 0;
  	    	
  	    	for (var i = 0; i < checkList.length; i++) {
  	    		
  	    		if(checkList[i].checked) {
  	    			
  	    			checkFlag = 1;
  	    			break;
  	    		}
  	    	}

  	    	if (checkFlag == 1) {
  	    		
  	    		document.getElementById('deleteButton').disabled = "";
  	    	} else {
  	    		
  	    		document.getElementById('deleteButton').disabled = "true";
  	    	}
  	 	}

	</script>
</head>
<body>
	<div id="header"></div>
	<div id="main">
		<h1>カート画面</h1>
		<s:if test="CartInfoDTOList==null">
			<h3>カート情報はありません。</h3>
		</s:if>
		<s:elseif test="#session.message==null">
			<form action="DeleteCartAction">
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
							<td><input type ="checkbox"class = "checkList" name = "checkList" value = "ProductInfoDTO.id" onchange = "checkValue()"></td>
							<td><s:property value="ProductInfoDTO.productName"/></td>
							<td><s:property value="ProductInfoDTO.productNameKana"/></td>
							<td><s:property value="imageFilePath"/>
							<img src="pic1.jpg"></td>
							<td><s:property value="ProductInfoDTO.price"/><span>円</span></td>
							<td><s:property value="ProductInfoDTO.releaseCompany"/></td>
							<td><s:property value="ProductInfoDTO.releaseDate"/></td>
							<td><s:property value="ProductInfoDTO.productCount"/><span>個</span></td>
							<td><s:property value="ProductInfoDTO.totalPrice"/><span>円</span></td>
							<td><s:property value="ProductInfoDTO.registDate"/></td>
							<td><s:property value="ProductInfoDTO.updatedDate"/></td>
						</tr>
					</s:iterator>
				</table>
				<div class ="total-box">
					<h2><s:label value="カート合計金額 :" />
						<span class="subPrice"> <s:property value="#session.subPrice" /> 円</span>
					</h2>
				</div>
				<input type="submit" value="削除" id="deleteButton" disabled="disabled"/>
			</form>
			<form  action="GoLoginAction"  method="session">
				<input type="hidden" name="cartPage" value="true" id="GoLoginAction"/>
				<input type="submit" value="決済" id="settlementButton"/>
			</form>
		</s:elseif>
	</div>
	<div id="footer"></div>	
	
</body>
</html>