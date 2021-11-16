<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 화면</title>
<link rel="stylesheet" href="<c:url value='/css/joinForm.css' />"
	type="text/css">
<link rel="stylesheet" media="screen"
	href="<c:url value='/css/snow_style.css' />" type="text/css">
<script>
	function userCreate() {		
		if (form.user_id.value == "") {
			alert("ID를 입력하세요.");
			form.user_id.focus();
			return false;
		}
		if (form.password.value == "") {
			alert("비밀번호를 입력하세요.");
			form.password.focus();
			return false;
		}
		if (form.password2.value == "") {
			alert("비밀번호를 한 번 더 입력하세요.");
			form.password2.focus();
			return false;
		}
		if (form.password.value != form.password2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			form.password2.focus();
			return false;
		}
		if (form.name.value == "") {
			alert("닉네임을 입력하세요.");
			form.name.focus();
			return false;
		}
		form.submit();
	}

	// 이미지 업로드
	function PreviewImage() {
		// 파일리더 생성 
		var preview = new FileReader();
		preview.onload = function(e) {
			// img id 값 
			document.getElementById("user_image").src = e.target.result;
		};
		// input id 값 
		preview.readAsDataURL(document.getElementById("image").files[0]);
	};
</script>
</head>
<body>
	<div id="container">
		<p id="join-title">JOIN</p>
		<div class="join-container">
			<div id="image_preview">
				<img id="user_image"
					src="<c:url value='/images/mypage_img/profile-image.jpg'/>" />
			</div>
			<form name="form" method="POST" action="<c:url value='/user/join'/>" enctype="multipart/form-data">
				<div class="join-form">


					<c:if test="${registerFailed}">
						<font color="red"><c:out value="${exception.getMessage()}" /></font>
					</c:if>

					<!-- 아이디 -->
					<div class="input-box">
						<input id="user_id" type="text" name="user_id" placeholder="아이디">
						<label for="user_id">아이디</label>
					</div>
					<!-- 비밀번호 -->
					<div class="input-box">
						<input id="password" type="password" name="password"
							placeholder="비밀번호"> <label for="password">비밀번호</label>
					</div>
					<!-- 비밀번호 확인 -->
					<div class="input-box">
						<input id="password2" type="password"
							name="password2" placeholder="비밀번호 확인"> <label
							for="password2">비밀번호 확인</label>
					</div>
					<!-- 닉네임 -->
					<div class="input-box">
						<input id="name" type="text" name="name" placeholder="닉네임">
						<label for="name">닉네임 </label>
					</div>
					<!-- 한줄 소개 -->
					<div class="input-box">
						<input id="descr" type="text" name="descr"
							placeholder="한줄소개"> <label for="descr">한줄소개</label>
					</div>
					<!-- 프로필 이미지 -->
					<div class="input-box">
						<input id="image" type="file" name="image"
							onchange="PreviewImage();"> <label for="image">프로필
							이미지</label>
					</div>

					<input type="button" value="HBTI TEST →" onClick="userCreate()">
				</div>
			</form>

		</div>
	</div>
</body>
</html>