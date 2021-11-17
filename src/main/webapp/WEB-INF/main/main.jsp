<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>


<!--  MainContainer 스타일시트 -->
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />"
	type="text/css">
<!--  Main 스타일시트 -->
<link rel="stylesheet" href="<c:url value='/css/main.css' />"
	type="text/css">
<!-- Contents 스타일시트 -->
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">
<!-- snow 스타일 시트 -->
<link rel="stylesheet" href="<c:url value='/css/snow_style.css' />"
	type="text/css">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">

</head>
<body>
	<div class="page-wrapper">
		<nav class="nav-bar">
			<div class="nav-logo">
				<a href="<c:url value='/main'/>" id="text-deco">&lt;HBTI/&gt;</a>
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

	<!-- HBTI INFO -->
	<div class="contents">
		<p id="sub-title">👻WELCOME TO HBTI!👻</p>
		<div class="contents-split">

		<!-- 그룹에 가입되지 않았을 때  -->
			<c:if test="${user.group_id eq 0 }">
				<p id=intro>
				<p style="font-size: 30px;">
					<i class="fas fa-quote-left"></i>&nbsp;HBTI에 오신 것을 환영합니다!&nbsp; <i
						class="fas fa-quote-right"></i>
				</p>
				<br> ${user.name }님의 HBTI 유형 검사 결과와 함께 다양한 컨텐츠들이 준비되어 있습니다.<br>
				<br> 잠깐 가시기 전에! 하단에 위치한 랭킹도 확인해보세요. <br> 지금 당장이라도
			${user.name }님과 비슷한 유형의 그룹에 가입하고 싶은 마음이 들지 않나요?<br>
				<br>
			그룹에 가입한 뒤 오늘의 챌린지를 인증하면 ${user.name }님이 속한 HBTI의 랭킹이 쭉쭉 올라갑니다! <br>
			그럼, HBTI를 자유롭게 즐겨보세요!
			</p>
				<span style="color: #dfdfdf"> <i class="fas fa-plane-departure fa-3x"></i></i>
				</span>
			</c:if>
			
			
		<!-- 그룹에 가입되어 있을 때 -->
		<c:if test="${user.group_id ne 0 }">
				<p id=intro>
				<p style="font-size: 30px;">
					<i class="fas fa-quote-left"></i>&nbsp;HBTI에 다시 오신 것을 환영합니다!&nbsp; <i
						class="fas fa-quote-right"></i>
				</p>
				<br> ${user.name }님, 오늘의 해야할 일이나 형성하고 싶은 습관을 TODO에 기록해보세요.<br>
				<br> 혹시라도, HBTI를 다시 검사하고 싶거나 개인정보를 수정해야할 일이 있을 땐 MYPAGE가 있답니다.<br>
				<br>
			오늘의 챌린지를 꾸준히 인증해주세요! ${user.name }님이 속한 HBTI의 랭킹이 쭉쭉 올라갑니다! <br>
			앞으로도 HBTI를 자유롭게 즐겨주세요!
			</p>
				<span style="color: #dfdfdf"> <i class="fab fa-fort-awesome fa-3x"></i>
				</span>
			</c:if>
			
		</div>
		<div class="contents-split">
			<div class="hbti-info">
				<p id="contents-title">My HBTI</p>
				<img id="hbti_icon"
					src="<c:url value='/images/hbti_img/${hbti.icon}.JPG'/>" />

				<p id="user-name">${user.name }님의HBTI</p>
				<p id="hbti-name">
					<i class="fas fa-quote-left"></i>&nbsp;${hbti.name}&nbsp;<i
						class="fas fa-quote-right"></i>
				</p>
				<p class="contents-info">${hbti.name }&nbsp;찰떡운동</p>
				<p id="hbti-exercise">${hbti.exercise}</p>
				<p class="contents-info">${hbti.name }&nbsp;특징</p>
				<p id="hbti-goodDescr">${hbti.good_descr}</p>
				<p class="contents-info">${hbti.name }&nbsp;유의점</p>
				<p id="hbti-badDescr">${hbti.bad_descr}</p>

				<div class="otherHBTI">
					<div class="Good">
						<p class="goodbad-info">GOOD</p>
						<p id="goodHBTI">${goodName}</p>
					</div>
					<div class="Bad">
						<p class="goodbad-info">BAD</p>
						<p id="badHBTI">${badName}</p>
					</div>
				</div>

				<p id="info-desc"></p>
			</div>
		</div>
	</div>

	<!-- 랭킹 -->
	<div class="contents">
		<p id="sub-title">🏆RANKING🏆</p>
		<div class="contents-split">
			<!-- 1, 2, 3등 이미지 구현 -->
			<table class="table1">
				<thead class="thead1">
					<tr class="tr1">
						<td class="td1" style="font-size: 40px; color: silver;"><i
							class="fas fa-award"></i></td>
						<td class="td1" style="font-size: 40px; color: gold;"><i
							class="fas fa-award"></i></td>
						<td class="td1" style="font-size: 40px; color: bronze;"><i
							class="fas fa-award"></i></td>
					</tr>
				</thead>
				<tr class="tr1">
					<c:forEach var="img" items="${rank_img }" varStatus="status">
						<c:if test="${status.index ne 1 }">
							<td class="td1"><img id="ranking-image"
								style="width: 150px;"
								src="<c:url value='/images/hbti_img/${img.value}.JPG'/>" /></td>
						</c:if>
						<c:if test="${status.index eq 1  }">
							<td class="td1"><img id="ranking-image"
								src="<c:url value='/images/hbti_img/${img.value}.JPG'/>" /></td>
						</c:if>
					</c:forEach>
				</tr>
				<tr class="tr1">
					<c:forEach var="img" items="${rank_img }" varStatus="status">
						<c:if test="${status.index ne 1 }">
							<td style="font-size: 15px;">${img.key }</td>
						</c:if>
						<c:if test="${status.index eq 1  }">
							<td style="font-size: 15px;">${img.key }</td>
						</c:if>
					</c:forEach>
				</tr>
			</table>

			<div class="progress-bar">
				<c:forEach var="rank" items="${rank}" varStatus="status">
					<div class="box">
						<table class="table2">
							<tr class="tr2">
								<td class="td2">
									<!-- 1등 --> <c:if test="${status.index eq 0}">
										<p class="ranking" style="color: gold; font-size: 20px;">
											<i class="fas fa-medal"></i> ${status.index + 1}등
										</p>
										<p style="font-size: 20px;">
											<i class="fas fa-quote-left"></i>&nbsp;${rank.key}&nbsp;<i
												class="fas fa-quote-right"></i>
										</p>
									</c:if> <!-- 2등 --> <c:if test="${status.index eq 1}">
										<p class="ranking" style="color: silver; font-size: 20px;">
											<i class="fas fa-medal"></i> ${status.index + 1}등
										</p>
										<p style="font-size: 20px;">
											<i class="fas fa-quote-left"></i>&nbsp;${rank.key}&nbsp;<i
												class="fas fa-quote-right"></i>
										</p>
									</c:if> <!-- 3등 --> <c:if test="${status.index eq 2}">
										<p class="ranking" style="color: bronze; font-size: 20px;">
											<i class="fas fa-medal"></i> ${status.index + 1}등
										</p>
										<p style="font-size: 20px;">
											<i class="fas fa-quote-left"></i>&nbsp;${rank.key}&nbsp;<i
												class="fas fa-quote-right"></i>
										</p>
									</c:if> <c:if
										test="${status.index ne 0 && status.index ne 1 && status.index ne 2}">
										<p class="ranking" style="font-size: 17px;">
											${status.index + 1}등
											<!-- <i class="fas fa-quote-left"></i>&nbsp; -->${rank.key}&nbsp;<!-- <i
												class="fas fa-quote-right"></i> -->
										</p>
									</c:if>

								</td>
								<td class="td2"><progress value="${rank.value}" max="100"
										class="progress"></progress></td>
								<td class="td2"><p class="percentage">
										<fmt:formatNumber value="${rank.value}" pattern="0.00" />
										&nbsp;<i class="fas fa-percentage"></i>
									</p></td>
							</tr>
						</table>
					</div>
				</c:forEach>
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