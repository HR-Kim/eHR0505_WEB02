<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.ehr.code.service.Code"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.ehr.cmn.StringUtil"%>
<%@page import="kr.co.ehr.user.service.Search"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%
    /** 페이지 사이즈 */
    String pageSize   = "10"  ;     
    /** 페이지 번호 */
    String pageNum    = "1"  ;  
    /** 검색조건 */
    String searchDiv  = "" ;
    /** 검색어 */
    String searchWord = "" ;
    /** 확장자 */
    String ext = "xls" ;    
    
    Search vo = (Search)request.getAttribute("vo");
    if(null !=vo){
        pageSize = StringUtil.nvl(vo.getPageSize()+"","10");
        pageNum = StringUtil.nvl(vo.getPageNum()+"","1");
        searchDiv = StringUtil.nvl(vo.getSearchDiv(),"");
        searchWord = StringUtil.nvl(vo.getSearchWord(),"");
        
    }else{
        pageSize = "10";
        pageNum  = "1";
        searchDiv = "";
        searchWord = "";
    }
    
    String extParam = (String)request.getAttribute("ext");
    if(extParam !=null) ext = extParam;
    
    
    //pageCode  
    List<Code> codeList = (request.getAttribute("codeList")==null)?(List<Code>)new ArrayList<Code>():(List<Code>)request.getAttribute("codeList");
            
    //userSearch    
    List<Code> codeSearchList = 
    (request.getAttribute("codeSearchList")==null)?
            (List<Code>)new ArrayList<Code>():
                (List<Code>)request.getAttribute("codeSearchList");     
    
    //pageCode  
    List<Code> excelList = (request.getAttribute("excelList")==null)?(List<Code>)new ArrayList<Code>():(List<Code>)request.getAttribute("excelList");
                        
    int maxNum      = 0;
    int bottomCount = 10;
    int currPageNo  = 1; //pageNum
    int rowPerPage  = 10;//pageSize
    String url      = request.getContextPath()+"/board/get_retrieve.do";
    String scriptName = "search_page";
    
    String iTotalCnt =(request.getAttribute("totalCnt")==null)?"0":request.getAttribute("totalCnt").toString();
    
    maxNum = Integer.parseInt(iTotalCnt);
    currPageNo = Integer.parseInt(pageNum);
    rowPerPage = Integer.parseInt(pageSize);
            
            

%>
<%--
  /**
  * @Class Name : board_list.jsp
  * @Description : bootstrap list
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
<title>부트스트랩 HR_LIST 템플릿</title>

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
			<h1><spring:message code="message.board.title"/></h1>
		</div>
		<!--// div title -->

		<!-- 검색영역 -->
		<div class="row">
			<div class="col-md-12 text-right">
				<form class="form-inline" name="frm" id="frm">
				    <input type="hidden" name="pageNum" id="pageNum" value="${vo.pageNum }">
				    <input type="hidden" name="boardId" id="boardId" >
					<div class="form-group">
						<%=StringUtil.makeSelectBox(codeList, "pageSize", pageSize, false) %>
                        <%=StringUtil.makeSelectBox(codeSearchList, "searchDiv", searchDiv, true) %>
                        <input type="text" class="form-control input-sm" id="searchWord"
							name="searchWord" placeholder="검색어" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-default btn-sm"
							id="doRetrieve">조회</button>
						<%=StringUtil.makeSelectBox(excelList, "ext", ext, false) %>	
						<button type="button" class="btn btn-default btn-sm" 
						id="doExcel">엑셀다운</button>
					</div>
				</form>
			</div>
		</div>
		<!--// 검색영역 -->

		<!-- Grid영역 --> 
		<div class="table-responsive">
			<table class="table  table-striped table-bordered table-hover" id="listTable">
				<thead class="bg-primary">
				    <th class="text-center col-md-1 col-xs-1" style="display:none;">ID</th>
					<th class="text-center col-md-1 col-xs-1"><spring:message code="message.board.num"/></th>
					<th class="text-center col-md-9 col-xs-6 "><spring:message code="message.board.title"/></th>
					<th class="text-center col-md-1 hidden-sm hidden-xs"><spring:message code="message.board.reg_id"/></th>
					<th class="text-center col-md-1 hidden-sm hidden-xs"><spring:message code="message.board.read_cnt"/></th>
					<th class="text-center col-md-1 col-xs-2"><spring:message code="message.board.reg_dt"/></th>
				</thead>

				<tbody>
				<c:choose>
				    <c:when test="${list.size()>0 }">
                            <c:forEach  var="vo"  items="${list}">
							<tr>
							    <td style="display:none;"><c:out value="${vo.boardId }"/></td>
								<td class="text-center"><c:out value="${vo.num }"/></td>
								<td class="text-left"><c:out value="${vo.title }"/></td>
								<td class="text-center  hidden-sm hidden-xs"><c:out value="${vo.regId }"/></td>
								<td class="text-right  hidden-sm hidden-xs"><c:out value="${vo.readCnt }"/></td>
								<td class="text-center"><c:out value="${vo.regDt }"/></td>
							</tr>
					       </c:forEach>
					</c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="99">등록된 게시글이 없습니다.</td>
                            </tr>
                        </c:otherwise>  					       
                </c:choose>
				</tbody>
			</table>
		</div>
		<!--// Grid영역 -->

        <!-- pagenation -->
        <div class="text-center">
            <%=StringUtil.renderPaing(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
        </div>
        <!--// pagenation -->

	</div>
	<!--// div container -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	    //그리드 클릭 : 아이디 단건조회
	    //listTable
	    $("#listTable>tbody").on("click","tr",function(){
	        console.log("#listTable>tbody");
	        var tr  = $(this);
	        var td  =  tr.children();
	        console.log("td:"+td.text());
	        var boardId = td.eq(0).text();
	        console.log("boardId:"+boardId);
	        
	        var frm = document.frm;
	        frm.boardId.value =boardId;
	        frm.action = "${context}/board/get_select_one.do";
	        frm.submit();
	    });	
	    //paging이동
	    function search_page(url,pageNum){
	        //alert(url+"|"+pageNum);
	        var frm = document.frm;
	        frm.pageNum.value = pageNum;
	        frm.action = url;
	        frm.submit();
	    }
	    $("#doExcel").on("click",function(){
	    	//alert("doRetrieve");
	    	if(false==confirm("엑셀저장 하시겠습니까?"))return;
	    	doExcelDown();
	    });
	    
        $("#doRetrieve").on("click",function(){
            //alert("doRetrieve");
            doRetrieve();
        });	    
	    
        function doExcelDown(){
            var frm = document.frm;
            frm.action = "${context}/board/exceldown.do";
            frm.submit();
        } 
       
        function doRetrieve(){
            var frm = document.frm;
            frm.pageNum.value= 1;
            frm.action = "${context}/board/get_retrieve.do";
            frm.submit();
        }
        
		$(document).ready(function() {
			//alert("ready");
		});
	</script>
</body>
</html>