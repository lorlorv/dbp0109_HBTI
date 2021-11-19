<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>챌린지 게시물 등록하기</title>
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="<c:url value='/css/challenge/challengeAdd.css' />" type="text/css">

<script>
function postAdd() {
	if(form.content.value == "") {
		alert("내용을 입력하세요!");
		form.text.focus();
		return false;
	}if(form.challenge.src == "") {
		alert("미션 인증 사진을 등록하세요!");
		return false;
	}
	form.submit();
}

//이미지 업로드
function PreviewImage() {
   // 파일리더 생성 
   var preview = new FileReader();
   preview.onload = function(e) {
      // img id 값 
      document.getElementById("challenge").src = e.target.result;
   };
   // input id 값 
   preview.readAsDataURL(document.getElementById("image").files[0]);
};

</script>
</head>
<body>
	<div class="page-wrapper" >
		<nav class="nav-bar">
			<div class="nav-logo">
				<a href="<c:url value='/main'/>" id="text-deco">&lt;HBTI/&gt;</a>
			</div>
			<div class="nav-menu">
				<ul class="menu-ul">
					<li class="menu-li"><a href="<c:url value='/todo/view'/>" id="text-deco">ToDo</a></li>
					<li class="menu-li"><a href="<c:url value='/group/main' />" id="text-deco">Group</a></li>
					<li class="menu-li"><a href="<c:url value='/user/myPage' />" id="text-deco">MyPage</a></li>
				</ul>
			</div>
			<div class="nav-logout">
				<a href="<c:url value='/user/logout'/>" id="text-deco">Logout</a>
			</div>
		</nav>
	</div>
	<div class="contents">
		<p id="sub-title">ADD CHALLENGE POST</p>
		<div class="contents-split">
		<form name="form" method="POST" action="<c:url value='/challenge/add' />"  enctype="multipart/form-data">
		<p id="contents-title">
		<%= new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date()) %></p>
			<p id="intro">
				오늘의 챌린지는 어떠셨나요?
				<br>당신의 이야기를 공유해주세요.
			</p>
			<textarea id="text" name="content"></textarea>
			<div class="split"> 
			<p></div>
			 <p id="intro">
				챌린지 인증 사진을 업로드해주세요!</p>
			<div class="image_container">
				<img id="challenge"/>
			</div>
			<input type="file" name="image" id="image" accept="image/*" onchange="PreviewImage();"/>
			
			<div id="len"></div>
				<a onclick="postAdd()" id="a-deco">
			<i class="fas fa-plus-square"></i>&nbsp;등록하기&nbsp;</a>
			
		</form>
		</div>
	
	</div>
	<footer>
    	<ul>
        <li><p>copyright ⓒ 2021 All rights reserved by 코딩궁딩.</p></li>
        <li>
          <a class="text-deco" href="mailto:leuns36@naver.com" target="_top">
            ✉Mail me : leuns36@naver.com
          </a>
        </li>
      </ul>
    </footer>
</body>
</html>