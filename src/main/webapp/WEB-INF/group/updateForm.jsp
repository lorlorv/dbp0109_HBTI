<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>HBTI 그룹 수정하기</title>
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/groupCreate.css' />" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/groupUpdate.css' />" type="text/css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">

<script>
function groupUpdate() {
	if(form.icon.value == "") {
		alert("iCON을 선택해주세요!");
		form.icon.focus();
		return false;
	}
	if(form.name.value=="") {
		alert("그룹 이름을 입력하세요!");
		form.name.focus();
		return false;
	}
	
	if(form.descr.value=="") {
		alert("그룹 소개를 입력해주세요!");
		form.descr.focus();
		return false;
	}
	if(form.limit.value=="") {
		alert("그룹 인원을 입력하세요!");
		form.limit.focus();
		return false;
	}
	form.submit();
}
function groupDelete(targetURI) {
	 if (confirm("${groupInfo.name}을(를) 정말 삭제하시겠습니까??") == true){
		 form.action = targetURI;
		form.submit();
	 } else{
	     return false;
	 }	
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
					<li class="menu-li"><a href="<c:url value='/group/main' />" id="text-deco">Group</a></li>
					<li class="menu-li"><a href="#" id="text-deco">MyPage</a></li>
				</ul>
			</div>
			<div class="nav-logout">
				<a href="#" id="text-deco">Logout</a>
			</div>
		</nav>
	</div>

	<!-- 그룹 정원을 잘못 입력하면 경고창 -->
		<c:if test="${updateFailed}">
		<script>
			alert('${Exception.getMessage()}');
		</script>
	</c:if>
	<!-- 그룹장을 강퇴하려하면 경고창 -->
	<c:if test="${quitFailed}">
		<script>
			alert('${Exception.getMessage()}');
		</script>
	</c:if>
	
	<div class="contents">
		<p id="sub-title">HBTI GROUP UPDATE</p>
		<div class="contents-split">
		<p id="contents-title">그룹 멤버 관리</p>
		<table>
			<tr>
				<td>그룹원 이름</td>
				<td>최근접속날짜</td>
				<td> </td>
			</tr>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.name}</td>
					<td>${user.login_date}</td>
					<td><a href="<c:url value='/group/manageUser'>
							<c:param name="user_id" value='${user.user_id}'/>
							<c:param name="group_id" value='${groupInfo.group_id}' />
					</c:url>" onclick="alert('강퇴하시겠습니까?');">강퇴</a> </td>
				</tr>
			</c:forEach>
		</table>
		</div>
		
		<div class="contents-split">
		<form name="form" method="POST" action="<c:url value='/group/update' >
											<c:param name="group_id" value='${groupInfo.group_id}' />
											</c:url>">
			<p id="contents-title">GROUP ICON</p>
			<p id="intro">
				우리 그룹을 나타낼 아이콘을 골라보세요.</p>
			<input type="radio" id="icon_1" name="icon" value='fas fa-running'>
			<label for="icon_1"><i class="fas fa-running"></i></label>
			<input type="radio" id="icon_2" name="icon" value='fas fa-swimmer'>
			<label for="icon_2"><i class="fas fa-swimmer"></i></label>
			<input type="radio" id="icon_3" name="icon" value='fas fa-skiing'>
			<label for="icon_3"><i class="fas fa-skiing"></i></label>
			<input type="radio" id="icon_4" name="icon" value='fas fa-dumbbell'>
			<label for="icon_4"><i class="fas fa-dumbbell"></i></label>
			<input type="radio" id="icon_5" name="icon" value='fas fa-table-tennis'>
			<label for="icon_5"><i class="fas fa-table-tennis"></i></label>
			<input type="radio" id="icon_6" name="icon" value='far fa-futbol'>
			<label for="icon_6"><i class="far fa-futbol"></i></label>
			<input type="radio" id="icon_7" name="icon" value='fas fa-cat'>
			<label for="icon_7"><i class="fas fa-cat"></i></label>
			<input type="radio" id="icon_8" name="icon" value='far fa-kiss-wink-heart'>
			<label for="icon_8"><i class="far fa-kiss-wink-heart"></i></label>
			
			<p id="contents-title">GROUP NAME</p>
			<p id="intro">
				그룹의 이름을 지어주세요.<br>
				관심있는 운동이나 취향이 드러나면 더욱 좋습니다. 
			</p>
			<input type="text" name="name" maxlength="10" value="${groupInfo.name}">	
			<p id="contents-title">NUMBER OF MEMBER</p>
			<p id="intro">
				우리 그룹이 가입할 수 있는 정원을 입력해주세요.<br>
				최소 2명에서 30명까지 가능합니다. 
			</p>
			
			<input type="text" name="limit" value="${groupInfo.numberOfMem}" required>	
			<p id="contents-title">DESCRIPTION</p>
			<p id="intro">
				우리 그룹을 재치있게 한마디로 소개해주세요!<br> 
				이 설명을 통해 HBTI 친구들이 가입할 수 있습니다.
			</p>
			<input type="text" name="descr" value="${groupInfo.descr}">	
			<div id="len"></div>
			<span><a onclick="groupUpdate()" id="a-deco">Update Group</a></span>
			<span><a onclick="groupDelete('<c:url value='/group/delete'>
											<c:param name="group_id" value='${groupInfo.group_id}'/></c:url>')"
											 id="a-deco2">Delete Group</a></sapn>
			
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