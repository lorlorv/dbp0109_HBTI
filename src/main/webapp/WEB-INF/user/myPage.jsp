<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPage</title>

<!--  MainContainer 스타일시트 -->
<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />"
	type="text/css">

<!-- Contents 스타일시트 -->
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">

<!-- MyPage 스타일 시트 -->
<link rel="stylesheet" href="<c:url value='/css/myPage.css' />"
	type="text/css">

<!-- snow 스타일 시트 -->
<link rel="stylesheet" href="<c:url value='/css/snow_style.css' />"
	type="text/css">

<!-- 아이콘 -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">

<script>
	function userUpdate() {
		form.submit();
	}
	function userQuit(targetUri) {
		form.action = targetUri;
		form.submit();
	}
	function HBTIreTest(targetUri) {
		form.action = targetUri;
		form.submit();
	}
	function groupQuit(targetUri) {
		form.action = targetUri;
		form.submit();
	}
	
	var todoDate = "";
	var today = new Date(); //오늘 날짜
    var date = new Date();//today의 Date를 세어주는 역할
    function prevCalendar() {//이전 달
    	todoDate = "";
     today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
     buildCalendar(); //달력 cell 만들어 출력 
    }

    function nextCalendar() {//다음 달
    	todoDate = "";
         today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
         buildCalendar();//달력 cell 만들어 출력
    }
    function buildCalendar(){//현재 달 달력 만들기
    	todoDate="";
        var doMonth = new Date(today.getFullYear(),today.getMonth(),1);
        //이번 달의 첫째 날
        
        var lastDate = new Date(today.getFullYear(),today.getMonth()+1,0);
        //이번 달의 마지막 날
       
        var tbCalendar = document.getElementById("calendar");
        //날짜를 찍을 테이블 변수 만듬, 일 까지 다 찍힘
        
        var tbCalendarYM = document.getElementById("tbCalendarYM");
        //테이블에 정확한 날짜 찍는 변수
        
         tbCalendarYM.innerHTML = today.getFullYear() + " " + (today.getMonth() + 1) + " "; 
         todoDate += today.getFullYear() + "/";
         todoDate += today.getMonth() + 1 + "/";

         /*while은 이번달이 끝나면 다음달로 넘겨주는 역할*/
        while (tbCalendar.rows.length > 2) {
        //열을 지워줌
              tbCalendar.deleteRow(tbCalendar.rows.length-1);
         }
         var row = null;
         row = tbCalendar.insertRow();
         //테이블에 새로운 열 삽입
         var cnt = 0;// count, 셀의 갯수를 세어주는 역할
        // 1일이 시작되는 칸을 맞추어 줌
         for (i=0; i<doMonth.getDay(); i++) {
         /*이번달의 day만큼 돌림*/
              cell = row.insertCell();
              cnt = cnt + 1;
         }
        /*달력 출력*/
        var todoDate2 = "";
		for (i = 1; i <= lastDate.getDate(); i++) {
			todoDate2 = todoDate; // yyyy/mm/ 까지만
			if(i < 10)
				todoDate2 += "0" + i;
			else
   				todoDate2 += i; //yyyy/mm/ + i -> yyyy/mm/dd
   			
         	//1일부터 마지막 일까지 돌림
            cell = row.insertCell();
   			//foreach문 안에서는 break, continue 사용불가능
   			
           <c:forEach items="${isTodo}" var="isTodo">
				if(todoDate2 == "${isTodo}"){
					
					cell.innerHTML = i + "⭐";
					cnt++;
					
					if (cnt % 7 == 0){/*토요일 */
						cell.innerHTML = "<font color=#3F72AF>" + i + "⭐";
			            row = calendar.insertRow();
			          }
					if (cnt % 7 == 1){/*일요일 */
						cell.innerHTML = "<font color=#FD5E53>" + i + "⭐";
			          }
					
					 /*오늘 날짜*/
			          if (today.getFullYear() == date.getFullYear()
			             && today.getMonth() == date.getMonth()
			             && i == date.getDate()) {
			            cell.bgColor = "#DBE2EF";
			           }
					 continue;
					}
				else{
	            	cell.innerHTML = i;            	
				}
				
            </c:forEach>
           
            
            cell.innerHTML = i;
            cnt++;
   			
            if (cnt % 7 == 1) {/*일요일 계산*/			
		            cell.innerHTML = "<font color=#FD5E53>" + i;
	        }   
	          
	          if (cnt % 7 == 0){/*토요일 */
					cell.innerHTML = "<font color=#3F72AF>" + i;	          
	               row = calendar.insertRow();
	          }
	          
	          /*오늘 날짜*/
	          if (today.getFullYear() == date.getFullYear()
	             && today.getMonth() == date.getMonth()
	             && i == date.getDate()) {
	            cell.bgColor = "#DBE2EF";
	           }
         }
    }
</script>

<!-- <script type="text/javascipt" src=<c:url value='/js/Calendar.js'/>></script> -->

</head>
<body>
	<div class="page-wrapper">
		<nav class="nav-bar">
			<div class="nav-logo">
				<a href="#" id="text-deco">HBTI</a>
			</div>
			<div class="nav-menu">
				<ul class="menu-ul">
					<li class="menu-li"><a href="" id="text-deco">ToDo</a></li>
					<li class="menu-li"><a href="#" id="/group/main">Group</a></li>
					<li class="menu-li"><a href=<c:url value='/user/myPage'/>
						id="text-deco">MyPage</a></li>
				</ul>
			</div>
			<div class="nav-logout">
				<a href="<c:url value='/user/logout'/>" id="text-deco">Logout</a>
			</div>
		</nav>
	</div>
	<!-- contents 페이지 <My Page> -->
	<div class="contents">
		<div class="contents-split">
			<p id=intro>
				HBTI의 놀라운 기능들을 체험해보셨나요?<br>이곳에서는 자신의 정보를 확인할 수 있으며 수정/탈퇴/변경이
				가능합니다!<br>혹시, 그룹에 속해있지 않으시다면 다양한 그룹들을 구경해보세요!
			</p>
			<span style="color: #dfdfdf"> <i
				class="far fa-check-circle fa-3x"></i>
			</span>
		</div>
		<p id="sub-title">MyProfile</p>
		<form name=form action="<c:url value='/user/update'/>">
			<div class="contents-split">
				<div class="box">
					<div class="info-img">
						<c:if test="${user.image eq null}">
							<img id="user_image"
								src="<c:url value='/images/mypage_img/profile-image.jpg'/>" />
						</c:if>
						<c:if test="${user.image ne null}">
							<img id="user_image" src="<c:url value='/upload/${user.image}'/>" />
						</c:if>
					</div>
					<div class="info-text">
						<div class="info-my">
							<p id="contents-title">MY</p>
							<p id="info-name">${user.name}님</p>
							<p id="info-HBTI">${hbti_name}</p>
							<p id="info-desc">
								<c:if test="${user.descr eq null}">
							한줄 소개가 없습니다. 정보 수정에서 한줄 소개를 등록해보세요!
							</c:if>
								<c:if test="${user.descr ne null}">
							${user.descr}
							</c:if>
							</p>
						</div>


						<div class="my-btn">

							<button id="info-ud" type="button" onClick="userUpdate()">회원
								정보 수정</button>
							<button id="reMBTI" type="button"
								onClick="HBTIreTest('<c:url value='/user/hbtiTest' />')">HBTI
								다시 검사</button>
							<button id="member-quit" type="button"
								onClick="userQuit('<c:url value='/user/quit' />')">회원
								탈퇴</button>
						</div>

						<div class="info-group">
							<p id="contents-title">GROUP</p>
							<c:if test="${user.group_id eq 0}">
								<p id="group-name">
									가입된 그룹이 없습니다. <br> ${groupNum}개의 그룹들이 ${user.name}님을 기다리고
									있습니다!
								</p>
								<button id="group-recommend" type="button" onClick="">그룹
									구경하러 가기</button>
							</c:if>
							<c:if test="${user.group_id ne 0}">
								<p id="group-name">${group_name}</p>
								<button id="group-quit" type="button"
									onClick="groupQuit('<c:url value='/user/group/quit' />')">그룹
									탈퇴</button>
							</c:if>

						</div>

					</div>
				</div>

			</div>
		</form>

		<!-- Calendar  -->
		<p id="sub-title">MyCalendar</p>
		<div class="contents" style="margin: -150px 0 0 0;">
			<div class="contents-split">
				<p id="contents-title" style="padding: 10px 30px;">My TODO Calendar</p>
				<table id="calendar">
					<tr>
						<!-- label은 마우스로 클릭을 편하게 해줌 -->
						<td><label onclick="prevCalendar()"><i
								class="fas fa-chevron-left"></i></label></td>
						<td align="center" id="tbCalendarYM" colspan="5">yyyy년 m월</td>
						<td><label onclick="nextCalendar()"><i
								class="fas fa-chevron-right"></i></label></td>
					</tr>
					<tr>
						<td align="center"><font color="#FD5E53">일</font></td>
						<td align="center">월</td>
						<td align="center">화</td>
						<td align="center">수</td>
						<td align="center">목</td>
						<td align="center">금</td>
						<td align="center"><font color="#3F72AF">토</font></td>
					</tr>
				</table>
				<script>
		    buildCalendar();
			</script>
			</div>
		</div>
	</div>
</body>
<footer>
	<ul>
		<li><p>copyright ⓒ 2021 All rights reserved by 코딩궁딩.</p></li>
		<li><a class="text-deco" href="mailto:leuns36@naver.com"
			target="_top"> ✉Mail me : leuns36@naver.com </a></li>
	</ul>
</footer>

</html>