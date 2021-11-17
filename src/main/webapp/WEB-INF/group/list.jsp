<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>HBTI 그룹 리스트</title>

<!--  MainContainer 스타일시트 -->
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />"
	type="text/css">

<!-- Contents 스타일시트 -->
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">

<!-- list 스타일시트 -->
<link rel="stylesheet" href="<c:url value='/css/group/groupList.css' />"
	type="text/css">

<!-- 아이콘 -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
	
<script type="text/javascript">

function search() {
	form.submit();
}
</script>
</head>
<body>
	<div class="page-wrapper">
		<nav class="nav-bar">
			<div class="nav-logo">
				<a href="#" id="text-deco">&lt;HBTI/&gt;</a>
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

			<!-- 그룹 정원이 초과되었다면 가입하지 못한다는 경고창 -->
			<c:if test="${joinFailed}">
	  	  	<script>alert('${exception.getMessage()}'); </script>
	    	</c:if>
	    	
	<!-- contents -->
	<div class="contents">
		<p id="sub-title">Hello! HBTI World</p>
		<div class="contents-split">
			<p id="intro">
				HBTI에 오신 여러분들 모두 환영합니다.<br> 이곳에서는 나와 같은 HBTI를 가진 사람들과 함께 건강 습관을
				실천할 수 있습니다.<br> 운동 키워드를 통해 검색하여 보다 마음에 맞는 사람들을 찾아보세요!
			</p>

			<div class="search-group">
				<form name="form" method="POST" action="<c:url value='/group/search'/>">
					<input type="text" class="search-txt" name="searchKeyword" placeholder="그룹 이름 입력">
					<a class="search-btn" href="javascript:search();">
						<i class="fas fa-search"></i>
					</a>
				</form>
			</div>
		</div>
		<div class="contents-split">
			<p id="contents-title">GROUP LIST</p>
			<p id="intro">원하는 그룹에 가입하거나 자신이 그룹을 생성할 수 있습니다.</p>

			<div class="create">
				<a href="<c:url value='/group/createForm' />" id="a-deco"><i class="fas fa-plus-square"></i>&nbsp;Create Group&nbsp;</a>
			</div>
	    
			<c:forEach var="group" items="${groupList}" varStatus="status">
				<div class="list-box">
					<table>
						<tr>
						<td rowspan="2">
						<i id="icon" class="${group.icon}"></i>
						</td>
						<td>
							그룹이름 : ${group.name}
							</td>
							<td rowspan="2">
							${group.numberOfMem}/${group.limitation}
							</td>
							<td rowspan="2"><a href= "<c:url value='/group/join' >
												<c:param name="group_id" value='${group.group_id}' />
												</c:url>" id="a-deco">JOIN</a></td>
							<tr>
							<td>한줄소개 : ${group.descr}</td>
							</tr>
					</table>
				</div>
			</c:forEach>
			
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