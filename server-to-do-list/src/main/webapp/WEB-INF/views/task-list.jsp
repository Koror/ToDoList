<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Test JSP</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
</head>
<body>
To Do List: <c:out value="${username}" />
<form action= "link-to-group" method="POST">
     <input value="Back" type="submit">
</form>
<p/>
Group: ${groupName}
<p/>
<form action= "link-add-task" method="POST">
     <input name="groupId" type="hidden" value="${groupId}"/>
     <input value="Add task" type="submit">
</form>
<p/>
<table style="width: 40%; height: 5px; table-layout: fixed;" border="1">
<tr>
<td width="30%" align="center">Name task</td>
<td width="15%" align="center">Edit task</td>
<td width="15%" align="center">Delete task</td>
</tr>
<c:forEach items="${taskList}" var="item" varStatus="loop">
<tr>
<td width="30%" align="center">
${item}
</td>
<td width="15%" align="center">
<form action= "link-edit-task" method="POST">
         <input name="taskId" type="hidden" value="${item.id}"/>
         <input name="groupId" type="hidden" value="${groupId}"/>
         <input value="Edit" type="submit">
</form>
</td>
<td width="15%" align="center">
<form action= "delete-task" method="POST">
         <input name="taskId" type="hidden" value="${item.id}"/>
         <input name="groupId" type="hidden" value="${groupId}"/>
         <input value="Delete" type="submit">
</form>
</td>
</c:forEach>
</tr>
</table>
</body>
</html>