<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>챌린지 게시물 조회하기</title>
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="<c:url value='/css/challenge/challengeView.css' />"
	type="text/css">

</head>
<body>
	<div class="page-wrapper">
		<nav class="nav-bar">
			<div class="nav-logo">
				<a href="#" id="text-deco">HBTI</a>
			</div>
			<div class="nav-menu">
				<ul class="menu-ul">
					<li class="menu-li"><a href="<c:url value='/todo/view'/>" id="text-deco">ToDo</a></li>
					<li class="menu-li"><a href="<c:url value='/group/main' />"
						id="text-deco">Group</a></li>
					<li class="menu-li"><a href="<c:url value='/user/myPage' />" id="text-deco">MyPage</a></li>
				</ul>
			</div>
			<div class="nav-logout">
				<a href="<c:url value='/user/logout'/>" id="text-deco">Logout</a>
			</div>
		</nav>
	</div>
	
	<c:if test="${addFailed}">
		<script>
			alert('${Exception.getMessage()}');
		</script>
	</c:if>
	
	<c:if test="${updateFailed }">
	<script>
			alert('${Exception.getMessage()}');
		</script>
	</c:if>
	
	<div class="contents">
		<p id="sub-title">CHALLENGE POST</p>
		<div class="contents-split">
			<form name="form" method="POST"
				action="<c:url value='/challenge/add' />">
				<p id="contents-title">${postInfo.writer_name} 님의 챌린지 인증</p>
				<p id="intro">
					<a href="<c:url value='/challenge/updateLike_btn'>
									<c:param name="post_id" value='${postInfo.post_id}' />
									<c:param name="writer_id" value='${postInfo.writer_id }'/>
									</c:url>" id="like-btn"><i class="fas fa-heart"></i></a>&nbsp;${postInfo.like_btn}
				</p>
				<p id="intro">챌린지 인증 사진</p>
				<img id="challenge"
					src="<c:url value='/upload/${postInfo.image}' />">
				<p>
				<div class="split"></div>
				<p id="intro">챌린지 인증 내용</p>
				<div class="challenge-content">${postInfo.content }</div>
				<p>
				<div>
					<span><a href="<c:url value='/group/main'/> " id="a-deco">&nbsp;돌아가기&nbsp;</a></span>
					<span><a href="<c:url value='/challenge/updateForm'>
							<c:param name="writer_id" value="${postInfo.writer_id }"/>
							</c:url>" id="a-deco">&nbsp;수정하기&nbsp;</a></span>
				</div>
			</form>
		</div>

	</div>
	<footer>
		<ul>
			<li><p>copyright ⓒ 2021 All rights reserved by 코딩궁딩.</p></li>
			<li><a class="text-deco" href="mailto:leuns36@naver.com"
				target="_top"> ✉Mail me : leuns36@naver.com </a></li>
		</ul>
	</footer>
</body>
</html>