<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%--
  /**
  * @Class Name : form_template.jsp
  * @Description : Sample Register 화면
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018.04.26            최초 생성
  *
  * author SIST 개발팀
  * since 2018.04.26
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
<title>게시관리</title>

<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
	<!-- div container -->
	<div class="container">
		<!-- div title -->
		<div class="page-header">
			<h1>게시관리</h1>
		</div>
		<!--// div title -->
		<!-- Button Area -->
         <div class="row">
             <div class="col-lg-10 col-sm-10 col-xs-10">
                 <div class="text-right">
                     <button type="button" class="btn btn-default btn-sm" id="doInit">초기화</button>
                     <button type="button" class="btn btn-default btn-sm" id="doRetrieve">목록</button>
                     <button type="button" class="btn btn-default btn-sm" id="doSave">등록</button>
                     <button type="button" class="btn btn-default btn-sm" id="doUpdate">수정</button>
                     <button type="button" class="btn btn-default btn-sm" id="doDelete">삭제</button>
                 </div>
             </div>
         </div>
         <div class="col-lg-12"></div>
		<!-- div title -->
		<form action="do_update.do" name="frmEdit" id="frmEdit" method="post" class="form-horizontal">
		    <input type="hidden"   class="form-control input-sm" id="boardId"  name="boardId" value="${vo.boardId }" />
			<div class="form-group">
				<label for="title" class="col-sm-2 control-label">제목</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="title" name="title" placeholder="제목" value="${vo.title }">
				</div>
			</div>
            <div class="form-group">
                <label for="regId" class="col-sm-2 control-label">등록자</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="regId" name="regId" placeholder="등록자" value="${vo.regId }">
                </div>
            </div>			
            <div class="form-group">
                <label for="regDt" class="col-sm-2 control-label">등록일</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="regDt" name="regDt" placeholder="등록일" value="${vo.regDt }">
                </div>
             
                <label for="readCnt" class="col-sm-2 control-label">조회수</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="readCnt" name="readCnt" placeholder="제목" value="${vo.readCnt }">
                </div>               
            </div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">내용</label>
				<div class="col-sm-8">
					<textarea name="contents" id="contents" class="form-control" rows="8" placeholder="내용">${vo.contents }</textarea>
				</div>
			</div>
		</form>
	</div>
	<!--// div container -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
    function doRetrieve(){
        var frm = document.frmEdit;
        frm.action = "${context}/board/get_retrieve.do";
        frm.method = 'GET';
        frm.submit();
    }
    $("#doRetrieve").on("click",function(){
    	doRetrieve();
    });
    
    //저장
    $("#doSave").on("click",function(){
        
        if(false==confirm("저장 하시겠습니까?"))return;
        
        $.ajax({
            type:"POST",
            url:"${context}/board/do_save.do",
            dataType:"html",// JSON
            data:{
                "title": $("#title").val(),
                "contents": $("#contents").val()
            },
            success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
                //console.log(data);
                //{"msgId":"1","msgMsg":"삭제 되었습니다.","totalCnt":0,"num":0}
                var parseData = $.parseJSON(data);
                if(parseData.msgId=="1"){
                    alert(parseData.msgMsg);
                    doRetrieve();
                }else{
                    alert(parseData.msgMsg);
                }
                
            },
            complete: function(data){//무조건 수행
             
            },
            error: function(xhr,status,error){
             
            }
        });             
    });   
    
    //수정
    $("#doUpdate").on("click",function(){
        
        if(false==confirm("수정 하시겠습니까?"))return;
        
        $.ajax({
            type:"POST",
            url:"${context}/board/do_update.do",
            dataType:"html",// JSON
            data:{
                "boardId": $("#boardId").val(),
                "title": $("#title").val(),
                "contents": $("#contents").val(),
                "regId": $("#regId").val()
            },
            success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
                //console.log(data);
                //{"msgId":"1","msgMsg":"삭제 되었습니다.","totalCnt":0,"num":0}
                var parseData = $.parseJSON(data);
                if(parseData.msgId=="1"){
                    alert(parseData.msgMsg);
                    doRetrieve();
                }else{
                    alert(parseData.msgMsg);
                }
                

                
            },
            complete: function(data){//무조건 수행
             
            },
            error: function(xhr,status,error){
             
            }
        });             
    });
    
    //삭제
    $("#doDelete").on("click",function(){
        console.log($("#boardId").val());
        
        
        if(""==$("#boardId").val() || null == $("#boardId").val()){
            alert("삭제할 데이터를 선택 하세요.");
            return;
        }
        
        if(confirm("삭제 하시겠습니까?") == false)return;
        
        

        $.ajax({
            type:"POST",
            url:"${context}/board/do_delete.do",
            dataType:"html",// JSON
            data:{
                "boardId": $("#boardId").val()
            },
            success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
                //console.log(data);
                //{"msgId":"1","msgMsg":"삭제 되었습니다.","totalCnt":0,"num":0}
                var parseData = $.parseJSON(data);
                if(parseData.msgId=="1"){
                    alert(parseData.msgMsg);
                    doRetrieve();
                }else{
                    alert(parseData.msgMsg);
                }
                

                
            },
            complete: function(data){//무조건 수행
             
            },
            error: function(xhr,status,error){
             
            }
        });                     
    });	
	    //초기화
	    $("#doInit").on("click",function(){
	        $("#boardId").val("");
	        $("#title").val("");
	        $("#readCnt").val("");
	        $("#contents").val("");
	        $("#regId").val("");
	        $("#regDt").val("");
	        
	        $("#boardId").prop("disabled",true);
	        $("#readCnt").prop("disabled",true);
	        $("#regId").prop("disabled",true);
	        $("#regDt").prop("disabled",true);
	        
	        $("#doSave").prop("disabled",false);
	        $("#doUpdate").prop("disabled",true);
	    });
		$(document).ready(function() {
			//alert("ready");
		});
	</script>
</body>
</html>