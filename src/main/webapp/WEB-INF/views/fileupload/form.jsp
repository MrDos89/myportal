<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload</title>
</head>
<body>
    <h1>파일 업로드 테스트</h1>
    <form method="post"
          action="<c:url value='/upload' />"
          enctype="multipart/form-data">
        <label>파일 선택:</label>
        <input type="file" name="file" required />
        <button type="submit">업로드</button>
    </form>

    <!-- 업로드 실패 시 메시지 출력 -->
    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>
</body>
</html>
