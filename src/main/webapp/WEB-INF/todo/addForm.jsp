<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO ADD</title>
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/todo/todo.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">

<script>
function addTodo() {
	if(form.content.value == "") {
		alert('TODO를 입력하세요.');
		form.content.focus();
	}
	form.submit();
}
</script>
</head>
<body>
	<div class="page-wrapper">
		<nav class="nav-bar">
			<div class="nav-logo">
				<a href="#" id="text-deco">HBTI</a>
			</div>
			<div class="nav-menu">
				<ul class="menu-ul">
					<li class="menu-li"><a href="<c:url value='/todo/view'/>"
						id="text-deco">ToDo</a></li>
					<li class="menu-li"><a href="<c:url value='/group/main' />"
						id="text-deco">Group</a></li>
					<li class="menu-li"><a href="<c:url value='/user/myPage' />"
						id="text-deco">MyPage</a></li>
				</ul>
			</div>
			<div class="nav-logout">
				<a href="#" id="text-deco">Logout</a>
			</div>
		</nav>
	</div>


	<div class="contents">
		<p id="sub-title">TODO ADD</p>
		<!-- TODO LIST 설명 출력 -->
		<div class="contents-split">
			<p id="intro">
				당신의 TODAY TODO는 무엇인가요?
			</p>
			<form name="form" action="<c:url value='/todo/add '/>">
				<input type="text" name="content" placeHolder="TODO 입력하기"><a onClick="addTodo()"
						id="a-deco">
			<i class="fas fa-plus-square"></i>&nbsp;추가&nbsp;</a>
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