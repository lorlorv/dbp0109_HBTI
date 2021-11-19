<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HBTI TEST</title>
<link rel="stylesheet" href="<c:url value='/css/hbtiTestForm.css' />"
	type="text/css">

<!-- contents 스타일 시트 -->
<link rel="stylesheet" href="<c:url value='/css/contents.css' />"
	type="text/css">
	
	<link rel="stylesheet" href="<c:url value='/css/mainContainer.css' />"
	type="text/css">
	

<!-- 아이콘 -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">

<script>
	function userTest() {
		if (form.a1.value == "") {
			alert("답안을 선택해주세요.");
			form.a1.focus();
			return false;
		}
		if (form.a2.value == "") {
			alert("답안을 선택해주세요.");
			form.a2.focus();
			return false;
		}
		if (form.a3.value == "") {
			alert("답안을 선택해주세요.");
			form.a3.focus();
			return false;
		}
		if (form.a4.value == "") {
			alert("답안을 선택해주세요.");
			form.a4.focus();
			return false;
		}
		if (form.a5.value == "") {
			alert("답안을 선택해주세요.");
			form.a5.focus();
			return false;
		}
		if (form.a6.value == "") {
			alert("답안을 선택해주세요.");
			form.a6.focus();
			return false;
		}
		if (form.a7.value == "") {
			alert("답안을 선택해주세요.");
			form.a7.focus();
			return false;
		}
		if (form.a8.value == "") {
			alert("답안을 선택해주세요.");
			form.a8.focus();
			return false;
		}
		if (form.a9.value == "") {
			alert("답안을 선택해주세요.");
			form.a9.focus();
			return false;
		}
		if (form.a10.value == "") {
			alert("답안을 선택해주세요.");
			form.a10.focus();
			return false;
		}
		if (form.a11.value == "") {
			alert("답안을 선택해주세요.");
			form.a11.focus();
			return false;
		}
		if (form.a12.value == "") {
			alert("답안을 선택해주세요.");
			form.a12.focus();
			return false;
		}
		
		if (confirm("정말 제출하시겠습니까?") == true){ 
			 document.form.submit();
		 }else{   //취소
		     return false;
		 }
		form.submit();
	}
/* 	function jbFunc() {
		document.getElementById('jb').value += '10';
	} */
</script>
</head>
<body>
	<div class="test-container">
		<p id="test-title">HBTI TEST</p>
		
		<!-- 프로그레스 바 추후 구현 -->
<!-- 		<div class="progress-bar">
			<progress value="20" max="100" id="jb"></progress>
		</div> -->
		
		<div class="contents-split">
			<p id=intro>
				회원 가입의 마지막 단계, 자신의 운동/습관 유형을 알아볼 수 있는 HBTI TEST입니다. <br> 문제를
				정확히 읽고 자신에게 더 맞다고 생각하는 답안을 A와 B중에 골라 선택해주세요!<br> 준비 되셨나요?
				시작합니다. <br> <span style="color: #dfdfdf"> <i class="fas fa-running"></i><i
					class="fas fa-golf-ball"></i> <i class="fas fa-skating"></i><i
					class="fas fa-swimmer"></i> <i class="fas fa-skiing"></i><i
					class="fas fa-mountain"></i></span>

			</p>
		</div>
		<form name="form" action="<c:url value='/user/hbtiTestResult'/>"
			method="POST">
			<!-- 1번 -->
			<%-- <c:forEach var="q" items="${qnaList}" varStatus="status"> --%>
			<p class="q-no">01.</p>

			<div class="q-container">

				<div class="q-box">

					<%-- <p class="q-desc">${q.quesition}</p> --%>
					<p class="q-desc">Q : 새해를 맞은 당신! 야심차게 운동을 시작해보려고 한다. 당신의 선택은?</p>

				</div>
				<div class="a-box">
					<label class="box-radio-input"><input type="radio"
						name="a1" value="A" onclick="jbFunc()"><span>A :
							혼자하면 재미없지, 무조건 그룹으로 진행되는 운동을 신청한다.</span></label> <label class="box-radio-input"><input
						type="radio" name="a1" value="B" onclick="jbFunc()"><span>B
							: 운동은 자고로 나에게 집중해야 하는 법! 홈트로 시작한다.</span></label>
				</div>

			</div>
			<%-- 			</c:forEach> --%>


			<!-- 2번 -->

			<p class="q-no">02.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 운동을 꾸준히 했더니 근육이 좀 생긴 것 같다. SNS에 자랑 좀 해볼까?</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a2" value="A"><span>A : 당장 올리자! 뭐라고 올려야 멋있다고
							소문날지 고민한다.</span></label> <label class="box-radio-input"><input
						type="radio" name="a2" value="B"><span>B : 절대 안됨!
							혼자만 보면서 뿌듯해한다.</span></label>
				</div>
			</div>

			<!-- 3번 -->
			<p class="q-no">03.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 헬스장에 처음 간 당신, 아무것도 모르겠다. 이럴 땐?</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a3" value="A"><span>A : 다른 사람들에게 먼저 말을 걸어
							물어본다.</span></label> <label class="box-radio-input"><input type="radio"
						name="a3" value="B"><span>B : 그저 주변 기구만 보며 서성인다…</span></label>
				</div>
			</div>

			<!-- 4번 -->
			<p class="q-no">04.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 헬스장에 있다가 갑자기 이상한 소리가 들렸을 때 당신은?</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a4" value="A"><span>A : 에이 뭐 별거 아니겠지~ 현재에
							집중한다.</span></label> <label class="box-radio-input"><input type="radio"
						name="a4" value="B"><span>B : 지진 난 거 아니야? 아니면 전쟁???
							전쟁 나면 당장 어떻게 탈출하지? 탈출하면 내 소지품들은? 꼬리에 꼬리를 물어 최악의 상황까지 생각한다.</span></label>
				</div>
			</div>

			<!-- 5번 -->
			<p class="q-no">05.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 운동할 때 당신의 머릿속에는?</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a5" value="A"><span>A : 아무 생각도 하지 않고 지금 하는
							자세에 집중한다.</span></label> <label class="box-radio-input"><input
						type="radio" name="a5" value="B"><span>B : 미래의 몸짱인
							나를 머릿속에 그려본다.</span></label>
				</div>
			</div>

			<!-- 6번 -->
			<p class="q-no">06.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 한 번도 해보지 않은 운동을 해보라고 추천받았을 때?</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a6" value="A"><span>A : 전에 경험해본 적이 없었으므로 보류하고
							전문적으로 배우기를 기다린다.</span></label> <label class="box-radio-input"><input
						type="radio" name="a6" value="B"><span>B : 자신의
							운동능력을 믿고 시도해본다.</span></label>
				</div>
			</div>

			<!-- 7번 -->
			<p class="q-no">07.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 다이어트 식단 관리로 인해 힘들다는 친구가 있을 때, 당신은?</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a7" value="A"><span>A : 어떤 식단하고 있는데? 물어본다.</span></label> <label
						class="box-radio-input"><input type="radio" name="a7"
						value="B"><span>B : 헐 다이어트 진짜 힘들겠다... ㅠㅠ 위로해준다.</span></label>
				</div>
			</div>

			<!-- 8번 -->
			<p class="q-no">08.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 다이어트 중 맛있는 음식이 눈에 들어올 때, 당신은?</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a8" value="A"><span>A : 오늘을 미루면 내일도 미룬다.
							참아본다.</span></label> <label class="box-radio-input"><input type="radio"
						name="a8" value="B"><span>B : 이따 저녁은 조금만 먹어도 되니까...
							괜찮겠지? 먹는다.</span></label>
				</div>
			</div>

			<!-- 9번 -->
			<p class="q-no">09.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 운동 중 나의 몸매를 보고 친구의 지적을 듣는다면?</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a9" value="A"><span>A : 어디가 더 운동이 필요할까? 어디
							부분? 물어본다.</span></label> <label class="box-radio-input"><input
						type="radio" name="a9" value="B"><span>B : 뭐..? 정말
							열심히했는데.... 슬퍼한다.</span></label>
				</div>
			</div>

			<!-- 10번 -->
			<p class="q-no">10.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 아무리 열심히 일을 해도 할 일이 태산이다. 점점 운동갈 시간은
						가까워지는데.. 이때 당신이라면??</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a10" value="A"><span>A : 일단 운동을 갔다오고 나서 다시
							한다.</span></label> <label class="box-radio-input"><input type="radio"
						name="a10" value="B"><span>B : 어떡하지.. 아 머리아파 몰라 안해~
					</span></label>
				</div>
			</div>


			<!-- 11번 -->
			<p class="q-no">11.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 이제 운동을 시작해보려 하는데 무슨 운동을 해야할 지 모르겠다! 당신이 더
						끌리는 운동은?</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a11" value="A"><span>A : 월요일은 등, 화요일은 하체.. 내
							입맛대로 계획해서 운동할 수 있는 헬스장으로 가자!</span></label> <label class="box-radio-input"><input
						type="radio" name="a11" value="B"><span>B : 나는야
							자유로운 영혼. 산도 가고 바다도 가서 그때그때 끌리는 운동이 더 재밌다! </span></label>
				</div>
			</div>

			<!-- 12번 -->
			<p class="q-no">12.</p>
			<div class="q-container">
				<div class="q-box">
					<p class="q-desc">Q : 올해 큰맘먹고 계획했던 다이어트가 목표를 달성하지 못했다. 이때 당신은?</p>
				</div>
				<div class="a-box">

					<label class="box-radio-input"><input type="radio"
						name="a12" value="A"><span>A : 너무 수고했지만, 아쉽다. 내년에는
							더 체계적으로 계획해서 꼭 성공할거야!</span></label> <label class="box-radio-input"><input
						type="radio" name="a12" value="B"><span>B : 오히려 좋아.
							이제 실패에 대한 변수 파악했다. 다이어트 넌 끝이다. </span></label>
				</div>
			</div>

			<input type="button" value="결과 제출" onClick="userTest()">

		</form>
	</div>
</body>
</html>