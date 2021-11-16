<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO LIST</title>
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/todo/todo.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">

<script>
function searchDate() {
	if(form.todo_date.value == "") {
		alert("날짜를 입력하세요");
		form.todo_date.focus();
		return false;
	}
	form.submit();
}

function modifyTodo(targetURI) {
	form.action = targetURI;
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
		<p id="sub-title">TODO</p>
		<!-- TODO LIST 설명 출력 -->
		<div class="contents-split">
			<p id="intro">
				나만의 TODO LIST를 작성하세요!<br> 사소한 건강 습관, 공부, 일정 모두 좋습니다! 오늘도 좋은 하루를
				위해 힘내보아요.
			</p>
		</div>
		<div class="contents-split">
			<p id="contents-title">TODAY TODO LIST</p>
			<p id="intro">
			투두를 갱신/변경하려면 편집하기를 누르세요.<br>
			원하는 날짜의 TODO LIST를 확인하려면 검색창을 활용하세요.</p>
			<form name="form" action="<c:url value='/todo/date' />">
				<input type="date" name="todo_date" placeHolder="yy/mm/dd 형식으로 입력">
				<a onClick="searchDate()" id="a-deco">날짜 검색</a>
				<p>
				<div class="split"></div>
				<p>
					<c:if test='${empty todoList}'>
						<p id="intro">오늘의 TODO LIST가 없습니다. 추가해주세요!</p>
					</c:if>
					<c:forEach var="todo" items="${todoList}" varStatus="status">
						<div class="list-box">
						<!-- is_done 여부에 따라 다르게 보여줌 -->
							<span>${status.index + 1}번</span>
							<span>${todo.content}</span>
							<span>
							<c:if test="${todo.is_done eq 1}">
							<i class="fas fa-check-square"></i></c:if>
							<c:if test="${todo.is_done eq 0}">
							<i class="far fa-square"></i></c:if>
										</span>
						</div>
						<div class="split"></div>
					</c:forEach>
					<p>
					<a onClick="modifyTodo('<c:url value='/todo/modifyForm'/>')"
						id="a-deco"><i class="fas fa-plus-square"></i>&nbsp;편집하기&nbsp;</a>
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