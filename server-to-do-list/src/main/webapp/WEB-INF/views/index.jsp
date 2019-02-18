<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Test JSP</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
To Do List
    <p/>
      <form action="login" method="post">
        <table>
          <tr><td>Login:</td>
              <td><input type="text" name="userlogin"/></td></tr>
          <tr><td>Password:</td>
              <td><input password="text" name="password"/></td></tr>
        </table>
        <input type="submit" value="Ok" />
      </form>
</body>
</html>