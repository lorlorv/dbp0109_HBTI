<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>HBTI 그룹 만들기</title>
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/groupCreate.css' />" type="text/css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">

<script>
function groupCreate() {
	if(form.icon.value == "") {
		alert("iCON을 선택해주세요!");
		form.icon.focus();
		return false;
	}
	if(form.group_name.value="") {
		alert("그룹 이름을 입력하세요!");
		form.group_name.focus();
		return false;
	}
	if(form.group_member.value="") {
		alert("그룹 인원을 설정해주세요!");
		form.group_member.focus();
		return false;
	}
	if(form.group_descr.value="") {
		alert("그룹 소개를 입력해주세요!");
		form.group_descr.focus();
		return false;
	}
	form.submit();
}
</script>
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
		<p id="sub-title">HBTI GROUP CREATE</p>
		<div class="contents-main">
		<form name="form" method="POST" action="<c:url value='/group/join' />">
			<p id="contents-title">GROUP ICON</p>
			<input type="radio" id="icon_1" name="icon" value='1'>
			<label for="icon_1"><i class="fas fa-running"></i></label>
			<input type="radio" id="icon_2" name="icon" value='2'>
			<label for="icon_2"><i class="fas fa-swimmer"></i></label>
			<input type="radio" id="icon_3" name="icon" value='3'>
			<label for="icon_3"><i class="fas fa-biking"></i></label>
			<input type="radio" id="icon_4" name="icon" value='4'>
			<label for="icon_4"><i class="fas fa-dumbbell"></i></label>
			<input type="radio" id="icon_5" name="icon" value='5'>
			<label for="icon_5"><i class="fas fa-table-tennis"></i></label>
			<input type="radio" id="icon_6" name="icon" value='6'>
			<label for="icon_6"><i class="far fa-futbol"></i></label>
			<input type="radio" id="icon_7" name="icon" value='7'>
			<label for="icon_7"><i class="fas fa-cat"></i></label>
			<input type="radio" id="icon_8" name="icon" value='8'>
			<label for="icon_8"><i class="far fa-kiss-wink-heart"></i></label>
			
			<p id="contents-title">GROUP NAME</p>
			<input type="text"  name="group_name" id="input_deco">	
			<p id="contents-title">NUMBER OF MEMBER</p>
			<input type="number" min="2" max="50" name="group_memeber" id="input_deco">	
			<p id="contents-title">DESCRIPTION</p>
			<input type="text"  name="group_descr" id="input_deco">	
			<p></p>
			<a onclick="groupCreate()" id="a-deco">
			<i class="fas fa-plus-square">&nbsp;Create Group&nbsp;</i></a>
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