<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>HBTI 그룹</title>
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />" type="text/css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">
</head>
<body>
	<div class="page-wrapper" >
		<nav class="nav-bar">
			<div class="nav-logo">
				<a href="#" id="text-deco">HBTI</a>
			</div>
			<div class="nav-menu">
				<ul class="menu-ul">
					<li class="menu-li"><a href="#" id="text-deco">ToDo</a></li>
					<li class="menu-li"><a href="#" id="text-deco">Group</a></li>
					<li class="menu-li"><a href="#" id="text-deco">MyPage</a></li>
				</ul>
			</div>
			<div class="nav-logout">
				<a href="#" id="text-deco">Logout</a>
			</div>
		</nav>
	</div>
	<div class="contents">
		<p id="sub-title">Hello! HBTI World</p>
		<div class="contents-split">
			<p id="contents-title">GROUP LIST</p>
			<p id="intro">원하는 그룹에 가입하거나 자신이 그룹을 생성할 수 있습니다.</p>
		</div>
		<div class="contents-split">
			<p id="contents-title">GROUP LIST</p>
		
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