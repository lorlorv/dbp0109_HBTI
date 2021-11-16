<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/53a8c415f1.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="<c:url value='/css/loginForm.css' />"
	type="text/css">
<script>
	function login() {
		if (form.user_id.value == "") {
			alert("ID를 입력하세요.");
			form.userId.focus();
			return false;
		}
		if (form.password.value == "") {
			alert("비밀번호를 입력하세요.");
			form.password.focus();
			return false;
		}
		form.submit();
	}

	function userCreate(targetUri) {
		form.action = targetUri;
		form.method = "GET"; // register form 요청
		form.submit();
	}
</script>
</head>
<body>
	<div class="HBTI-info">
		<p class="info">MBTI 건강 습관 형성 서비스</p>
		<br>
		<p class="info">: HBTI</p>
		<br>
		<p class="info">설명 찌끄리는 곳,..</p>
	</div>
	<div class="login">
		<div class="login-img">
			<img class="login-img"
				src="<c:url value='/images/login_img/HBTI.jpg'/> ">
		</div>
		<div class="login-form">
			<p id="login-title">&lt;HBTI/&gt;</p>
			<form name="form" action="<c:url value='/user/login' />" method="POST">
			
				<div class="input-box">
					<input id="user_id" type="text" name="user_id" placeholder="아이디">
					<label for="user_id">아이디</label>
				</div>

				<div class="input-box">
					<input id="password" type="password" name="password"
						placeholder="비밀번호"> <label for="password">비밀번호</label>
				</div>
				<c:if test="${loginFailed}">
					<br>
					<font color="red"><c:out value="${exception.getMessage()}" /></font>
					<br>
				</c:if>
				<input type="submit" value="LOGIN" onClick="login()">
				<input type="button" value="JOIN" 
					onClick="userCreate('<c:url value='/user/join'/>')">
			</form>
		</div>
	</div>
</body>
</html>