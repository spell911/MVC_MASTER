<%-- 
    Document   : _headr
    Created on : Apr 13, 2017, 2:43:50 PM
    Author     : Khun_Phichz
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>My Site</h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">
        <!-- User store in session with attribute: loginedUser -->
        Hello <b>${loginedUser.userName}</b>
        <c:if test="${not empty loginedUser.userName}">
            <a href="logout">Logout</a>
        </c:if>
        <br/>
        Search <input name="search">
    </div>
</div>
