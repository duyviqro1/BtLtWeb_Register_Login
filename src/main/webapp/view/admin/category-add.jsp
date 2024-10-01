<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix ="c" uri = "jakarta.tags.core" %>
<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype = "multipart/form-data">
	<label for="cate_name">Category Name:</label>
	<br> 
		<input type="text" id="cate_name" name="cate_name" >
	<br> 
		<label for="image">Image:</label>
	<br> 
		<img height="150" width="200" src="" id="images"/>
		<input type="file" onchange="chooseFile(this)" id="images" name="image">
	<br>
		<label for="active">Active:</label>
	<br> 
		<input type="radio" id="ston" name="active" value="1" checked>
	<label for="html">Hoạt động</label><br>
	
	<input type="radio" id="stoff" name="active" value="0">
	<label for="css">Khóa</label><br>

	<br> <input type="submit" value="Submit">
</form>
