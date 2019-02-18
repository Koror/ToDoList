<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Test JSP</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
</head>
<body>
To Do List: Edit Task
<br>

<form:form action="edit-task" method="post" modelAttribute="task">
     <form:input path="name"/>
     <form:hidden path="id"/>
     <form:hidden path="creator"/>
     <input name="groupId" type="hidden" value="${groupId}"/>
    <input type="submit" value="Edit" />
</form:form>


</body>
</html>