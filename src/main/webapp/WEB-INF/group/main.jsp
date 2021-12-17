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
<link rel="stylesheet" href="<c:url value='/css/group/groupMain.css' />"
	type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>

$(function(){
    $("#challTable tr").slice(0, 3).show(); // select the first ten
    $("#load").click(function(e){ // click event for load more
        e.preventDefault();
        if($("#challTable tr:hidden").length == 0){ // check if any hidden divs still exist
            alert("챌린지 게시물을 모두 불러왔습니다."); // alert if there are none left
        }
        $("#challTable tr:hidden").slice(0, 3).show(); // select next 10 hidden divs and show them
    });
});

</script>
</head>
<body>
	<div class="page-wrapper">
		<nav class="nav-bar">
			<div class="nav-logo">
				<a href="<c:url value='/main'/>" id="text-deco">&lt;HBTI/&gt;</a>
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
				<a href="<c:url value='/user/logout'/>" id="text-deco">Logout</a>
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
							</c:url>">그룹정보
					수정하기</a>
			</c:if>
		</div>
		<!-- 그룹원 정보 출력 -->
		<div class="contents-split">
			<p id="contents-title">GROUP MEMBER</p>
			<p id="intro">그룹원들의 목록을 확인할 수 있습니다.</p>

		<table>
            <c:forEach var="user" items="${userList}" varStatus="status">
               <c:choose>
                  <c:when test="${status.first || status.index % 5 == 0}">
                     <tr>
                       
                              <c:if test="${user.image == null }">
                                 <td>
                                 <img class="member-img"
                                       src="<c:url value='/images/mypage_img/profile-image.jpg'/>" />
                                 <br>${user.name}
                                 </td>
                              </c:if>
                              <c:if test="${user.image != null}" >
                                 <td>
                                 <img class="member-img"
                                       src="<c:url value='/upload/${user.image }' />">
                                 <br>${user.name}
                                 </td>
                              </c:if>
                       
                  </c:when>
                  <c:when test="${status.last || status.index % 4 == 0}">
                       <c:if test="${user.image == null }">
                                 <td>
                                 <img class="member-img"
                                       src="<c:url value='/images/mypage_img/profile-image.jpg'/>" />
                                 <br>${user.name}
                                 </td>
                              </c:if>
                              <c:if test="${user.image != null}" >
                                 <td>
                                 <img class="member-img"
                                       src="<c:url value='/upload/${user.image }' />">
                                 <br>${user.name}
                                 </td>
                              </c:if>
                     </tr>
                  </c:when>
                  <c:otherwise>
                          <c:if test="${user.image == null }">
                                 <td>
                                 <img class="member-img"
                                       src="<c:url value='/images/mypage_img/profile-image.jpg'/>" />
                                 <br>${user.name}
                                 </td>
                              </c:if>
                              <c:if test="${user.image != null}" >
                                 <td>
                                 <img class="member-img"
                                       src="<c:url value='/upload/${user.image }' />">
                                 <br>${user.name}
                                 </td>
                              </c:if>
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
				오늘의 챌린지를 완료해서 HBTI 랭킹 1등을 차지하세요!<br> 게시물을 클릭해 상세 내용을 확인할 수
				있습니다.
			</p>
			<div class="challenge-list">
				<table id="challTable" class="challenge-table">
					<c:forEach var="post" items="${postList}" varStatus="status">
						<tr id="list"
							onClick="location.href='<c:url value='/challenge/view'>
												<c:param name="writer_id" value='${post.writer_id}'/>
												</c:url>'">
							<td><img class="challenge-img"
								src="<c:url value='/upload/${post.image}' />"></td>
							<td>이름 : ${post.writer_name}
								<p>${post.content}</p>
							</td>
							<td><a href="<c:url value='/challenge/updateLike_btn'>
									<c:param name="post_id" value='${post.post_id}' />
									<c:param name="writer_id" value='${post.writer_id}' />
									</c:url>" id="like-btn"><i class="fas fa-heart"></i></a>&nbsp;${post.like_btn}</td>
						</tr>
					</c:forEach>
				</table>
				<a href="#" id="load">more</a>
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