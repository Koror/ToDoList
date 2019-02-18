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
<form action= "logout" method="POST">
     <input value="Logout" type="submit">
</form>
<p/>
<form action= "link-add-group" method="POST">
     <input value="Add group" type="submit">
</form>
<p/>
<table style="width: 40%; height: 5px; table-layout: fixed;" border="1"">
<tr>
<td width="30%" align="center">Name group</td>
<td width="15%" align="center">Edit group</td>
<td width="15%" align="center">Delete group</td>
</tr>
<c:forEach items="${groupList}" var="item" varStatus="loop">
<tr>
<td width="30%" align="center">
  <form action= "link-to-task" method="POST">
    <input name="groupId" type="hidden" value="${item.id}"/>
    <input value="${item}" type="submit">
  </form>
</td>
<td width="15%" align="center">
<form action= "link-edit-group" method="POST">
         <input name="groupId" type="hidden" value="${item.id}"/>
         <input value="Edit" type="submit">
</form>
</td>
<td width="15%" align="center">
<form action= "delete-group" method="POST">
         <input name="groupid" type="hidden" value="${item.id}"/>
         <input value="Delete" type="submit">
</form>
</td>
 </tr>
</c:forEach>
</table>
</body>
</html>