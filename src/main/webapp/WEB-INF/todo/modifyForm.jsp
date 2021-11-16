<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MODIFY TODO</title>
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
		<p id="sub-title">MODIFY TODO</p>
		<!-- TODO LIST 설명 출력 -->
		<div class="contents-split">
			<p id="intro">TODO를 추가하거나 수정할 수 있습니다.</p>
		</div>
		<div class="contents-split">
			<p id="contents-title">TODAY TODO LIST</p>
			<form name="form" action="<c:url value='/todo/date' />">
				<div class="split"></div>
				<p>
					<c:if test='${!isTodayTodo}'>
						<p id="intro">오늘의 TODO LIST가 없습니다. 추가해주세요!</p>
					</c:if>
				<div class="split"></div>
				<c:forEach var="todo" items="${todoList}">
					<div>
						<!-- is_done 여부에 따라 다르게 보여줌 -->
						<span> <c:if test="${todo.is_done eq 1}">
								<a
									href="<c:url value="/todo/doCheck">
										<c:param name="todo_id" value='${todo.todo_id }'/>
										</c:url>"><i
									class="fas fa-check-square"></i></a>
							</c:if> <c:if test="${todo.is_done eq 0}">
								<a
									href="<c:url value="/todo/doNotCheck">
										<c:param name="todo_id" value='${todo.todo_id }'/>
										</c:url>"><i
									class="far fa-square"></i></a>
							</c:if>
						</span> <span>${todo.content}</span> <span><a
							href="<c:url value="/todo/delete">
										<c:param name="todo_id" value='${todo.todo_id }'/>
										</c:url>"><i
								class="far fa-trash-alt"></i></a></span>
					</div>
				</c:forEach>
				<p>
					<a onClick="modifyTodo('<c:url value='/todo/addForm'/>')"
						id="a-deco"> <i class="fas fa-plus-square"></i>&nbsp;추가&nbsp;
					</a>
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