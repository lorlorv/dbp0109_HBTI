<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="EUC-KR">
<title>HBTI 그룹 메인 페이지</title>
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />"
	type="text/css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/groupMain.css' />"
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
					<li class="menu-li"><a href="#" id="text-deco">ToDo</a></li>
					<li class="menu-li"><a href="<c:url value='/group/main' />"
						id="text-deco">Group</a></li>
					<li class="menu-li"><a href="#" id="/user/myPage">MyPage</a></li>
				</ul>
			</div>
			<div class="nav-logout">
				<a href="#" id="text-deco">Logout</a>
			</div>
		</nav>
	</div>
	<div class="contents">
		<p id="sub-title">HBTI GROUP</p>
		<!-- 그룹의 정보 출력 -->
		<div class="contents-split">
			<p id="contents-title">${groupInfo.name}</p>
			<table>
				<tr>
					<td id="icon" rowspan="3"><i class="${groupInfo.icon}"></i></td>
					<td>그룹장 : ${groupInfo.leader_name}</td>
				</tr>
				<tr>
					<td>그룹인원 : ${groupInfo.numberOfMem} 명
						(${groupInfo.numberOfMem}/ ${groupInfo.limitation})</td>
				</tr>
				<tr>
					<td>한줄 소개 : ${groupInfo.descr}</td>
				</tr>
			</table>
			<!-- 그룹장일 때만 그룹 정보 수정 버튼이 나타난다. -->
			<c:if test="${isLeader}">
				<a id="a-deco"
					href="<c:url value='/group/updateForm'>
							<c:param name="group_id" value='${groupInfo.group_id}' />
							<c:param name="leader_id" value='${groupInfo.leader_id}' />
							</c:url>">그룹정보 수정하기</a>
			</c:if>
		</div>
		<!-- 그룹원 정보 출력 -->
		<div class="contents-split">
			<p id="contents-title">GROUP MEMBER</p>
			<p id="intro">그룹원들의 목록을 확인할 수 있습니다.</p>

			<table>
				<c:forEach var="user" items="${userList}" varStatus="status">
					<c:choose>
						<c:when test="${status.first || status.index % 6 == 0}">
							<tr>
								<td><img class="member-img"
									src="<c:url value='/images/img.png' />"><br>${user.name}</td>
						</c:when>
						<c:when test="${status.last || status.index % 5 == 0}">
							<td><img class="member-img"
								src="<c:url value='/images/img.png' />"><br>${user.name}</td>
							</tr>
						</c:when>
						<c:otherwise>
							<td><img class="member-img"
								src="<c:url value='/images/img.png' />"><br>${user.name}</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</div>
		<!-- 챌린지 정보 -->
		<div class="contents-split">
			<p id="contents-title">TODAY CHALLENGE</p>
			<div class="challenge-box">
				${groupInfo.challengeContent}
				<p>
					<a id="a-deco" href="<c:url value='/challenge/addForm' />">미션
						인증하기</a>
			</div>
			<p id="contents-title">CHALLENGE POST</p>
			<p id="intro">
				오늘의 챌린지를 완료해서 HBTI 랭킹 1등을 차지하세요!<br>
			</p>
			<div class="challenge-list">
				<table class="challenge-table">
					<c:forEach var="post" items="${postList}">
						<tr
							onClick="location.href='<c:url value='/challenge/view'>
												<c:param name="postInfo" value='${post}'/>
												</c:url>'">
							<td><img class="challenge-img"
								src="<c:url value='/upload/${post.image}' />"></td>
							<td>이름 : ${post.writer_name}
								<p>${post.content}</p>
							</td>
							<td><a href="<c:url value='/challenge/updateLike_btn'>
									<c:param name="post_id" value='${post.post_id}' />
									</c:url>" id="like-btn"><i class="fas fa-heart"></i></a>&nbsp;${post.like_btn}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div style="cursor: pointer;" onclick="window.scrollTo(0,0);">
				<i class="fas fa-caret-square-up fa-3x"></i>
			</div>
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