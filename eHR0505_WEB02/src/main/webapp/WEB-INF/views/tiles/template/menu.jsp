<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="context"   value="${pageContext.request.contextPath}"/>    
   
<table>
    <tr>
        <td><a href="${context}/user/do_login.do">로그인</a></td>
    </tr>
    <tr>
        <td><a href="${context}/user/get_retrieve.do">사용자관리</a></td>
    </tr>
    <tr>
        <td><a href="${context}/board/get_retrieve.do">게시판</a></td>
    </tr>
</table>