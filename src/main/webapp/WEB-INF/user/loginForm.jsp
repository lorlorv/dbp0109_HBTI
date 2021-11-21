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
	<div class="container">
			<div class="HBTI-info">
			<div class="center-box">
				<p class="info-title">
					안녕하세요!<br> <i class="fas fa-quote-left"></i>&nbsp;
					코딩궁딩입니다&nbsp;<i class="fas fa-quote-right"></i>
				</p>
				<br>
				<div class="dev_img">
					<table>
						<tr>
							<td><img src="<c:url value='/images/dev_img/a.jpg'/>" /></td>
							<td><img src="<c:url value='/images/dev_img/b.jpg'/>" /></td>
							<td><img src="<c:url value='/images/dev_img/c.png'/>" /></td>
						</tr>
						<tr>
							<td style="font-size: 25px;">이은서</td>
							<td style="font-size: 25px;">신유정</td>
							<td style="font-size: 25px;">유정민</td>
						</tr>
						<tr>
							<td style="font-size: 20px;">20190995</td>
							<td style="font-size: 20px;">20190974</td>
							<td style="font-size: 20px;">20190980</td>
						</tr>

					</table>
				</div>

			</div>
		</div>
		<div class="left-HBTI-info">
			<div class="left-box">
				<p class="info-title">작은 습관을 쌓아 인생을 바꾸세요!</p>
				<br>
				<p class="info-sub">
					작은 습관들이 모여 나의 하루가 되고 인생이 됩니다. <br> 필요하지만 잘 실천하지 못했던 일들이 있다면,<br>
					HBTI와 함께 인생을 변화시키세요.
				</p>
				<br>
				<p class="info"></p>
			</div>
		</div>

		<div class="right-HBTI-info">
			<div class="right-box">
				<p class="info-title">01. 나의 성향에 맞춰 HBTI를 테스트하세요.</p>
				<br>
				<p class="info-sub">
					12개의 운동 상황 문답을 통해 자신의 HBTI를 부여받고 <br>즐겁게 활동 가능한 운동을 추천 받으세요!
				</p>
				<br>
				<p class="info"></p>
			</div>
		</div>


		<div class="left-HBTI-info">
			<div class="left-box">
				<p class="info-title">02. 같은 HBTI를 가진 회원끼리 그룹을 결성해 챌린지를 달성하세요</p>
				<br>
				<p class="info-sub">
					같은 성향의 사람들이 모인만큼 의지가 달라지고 책임감이 강해집니다. <br>일일 챌린지를 달성해 성취감을
					높이세요!
				</p>
				<br>
				<p class="info"></p>
			</div>
		</div>

		<div class="right-HBTI-info">
			<div class="right-box">
				<p class="info-title">03. 나의 HBTI 랭킹을 높이기 위해 노력하세요!</p>
				<br>
				<p class="info-sub">
					그룹에 들어가 일일 챌린지를 깬다면<br> 우리 HBTI가 1등을 할 확률이 더욱 높아집니다!
				</p>
				<br>
				<p class="info"></p>
			</div>
		</div>

	</div>


	<div class="login">
		<div class="login-img">
			<p id="login-title">&lt;HBTI/&gt;</p>
		</div>

		<div class="login-form">
			<form name="form" action="<c:url value='/user/login' />"
				method="POST">

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
				<input type="submit" value="LOGIN" onClick="login()"> <input
					type="button" value="JOIN"
					onClick="userCreate(
								'<c:url value='/user/join'/>')">
			</form>
		</div>
	</div>
</body>
</html>