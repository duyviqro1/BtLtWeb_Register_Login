<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix ="c" uri = "jakarta.tags.core" %>
    
<form action="${pageContext.request.contextPath}/admin/category/update" method="post" enctype = "multipart/form-data">
<input type="text" id="cate_id" name="cate_id" value = "${cate.cate_id }" hidden = "hidden">
	<label for="cate_name">Category Name:</label>
	<br> 
		<input type="text" id="cate_name" name="cate_name" value = "${cate.cate_name }">
	<br> 
		<label for="image">Image:</label>
	<br> 
		<c:if test="${cate.image.substring(0,5) !='https'}">
			<c:url value= "/image?fname=${cate.image }" var="imgUrl">  </c:url>
		</c:if>
			<c:if test="${cate.image.substring(0,5) =='https'}">
			<c:url value="${cate.image }" var="imgUrl" > </c:url>
			</c:if>
			
			<img height="150" width="200" src="${imgUrl}" id="images"/>
		<input type="file" onchange="chooseFile(this)" id="image" name="image" value = "${cate.image }">
	<br>
		<input type="radio" id="ston" name="active" value="1" ${cate.active ==1?'checked':'' }>
	<label for="html">Hoạt động</label><br>
	
	<input type="radio" id="stoff" name="active" value="0" ${cate.active !=1?'checked':'' }>
	<label for="css">Khóa</label><br>
	
	<input type="submit" value="Update">
</form>