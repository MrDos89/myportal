<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<img src="<c:url value="/upload-images/${imageFileName }"/>"/>
	<a href="<c:url value="/fileupload/form" />">다시 업로드</a>
</body>
</html>