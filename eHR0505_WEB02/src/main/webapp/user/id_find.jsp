<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%--
  /**
  * @Class Name : ID확인
  * @Description : 회원로그인
  * @Modification Information
  * 
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018.09.26            최초 생성
  *
  * author SIST 개발팀
  * since 2018.09.26
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>

<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>아이디확인</title>

<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/util.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/main.css">
<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
				<form class="login100-form validate-form" action="${context}/user/do_login.do"  name="frm" id="frm" method="post">
					<span class="login100-form-title p-b-33"> 아이디 찾기 </span>
					<div class="wrap-input100 validate-input"
						data-validate="Valid 아이디 is required">
						<input class="input100" type="text" name="u_id" id="u_id"
							placeholder="아이디"> <span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>
					<div class="wrap-input100 rs1 validate-input"
						data-validate="Password is required">
						<input class="input100" type="text" name="name" id="name"
							placeholder="이름"> <span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>
				</form>	
					<div class="container-login100-form-btn m-t-20">
						<button class="login100-form-btn" id="idFind">아이디 찾기</button>
					</div>
					<div class="text-center p-t-45 p-b-4">
						<span class="txt1"> Forgot </span> <a href="#" class="txt2 hov1">
							Username / Password? </a>
					</div>
					<div class="text-center">
						<span class="txt1"> Create an account? </span> <a href="#"
							class="txt2 hov1"> Sign up </a>
					</div>
				
			</div>
		</div>
	</div>
	<!--// div container -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
    
	
	    //mail로 ID전송
	    $("#idFind").on("click",function(){
	    	//alert("signIn");
	    	if(false==confirm("ID를 확인 하시겠습니까?"))return;
	        
	        $.ajax({
	            type:"POST",
	            url:"${context}/user/do_login.do",
	            dataType:"html",// JSON
	            data:{
	                "u_id": $("#u_id").val(),
	                "passwd": $("#passwd").val()
	            },
	            success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
	                console.log(data);
	                //{"msgId":"1","msgMsg":"삭제 되었습니다.","totalCnt":0,"num":0}
	                var parseData = $.parseJSON(data);
	                if(parseData.msgId=="1"){
	                    alert(parseData.msgMsg);	
	                    location.href="${context}/main/main.jsp";

	                }else{
	                    alert(parseData.msgMsg);
	                }
	                
	            },
	            complete: function(data){//무조건 수행
	             
	            },
	            error: function(xhr,status,error){
	            	 console.log(error);
	            }
	        });      	    	
	    });
	
		$(document).ready(function() {
			//alert("ready");
		});
	</script>
</body>
</html>