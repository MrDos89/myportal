<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Result</title>
</head>
<body>
    <h1>파일 업로드 성공</h1>
    <p>업로드된 파일:</p>
    <img src="<c:url value='/upload-images/${imageFilename}' />" alt="Uploaded Image" style="max-width: 500px;">

    <p>
        <a href="<c:url value='/fileupload/form' />">다시 업로드</a>
    </p>
</body>
</html>
