<%@ page import="ru.javawebinar.basejava.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="static ru.javawebinar.basejava.model.ContactType.EMAIL" %><%--
  Created by IntelliJ IDEA.
  Date: 20/09/2019
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../css/style.css">
    <title>Resume List</title>
</head>
<body>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th></th>
            <th></th>
        </tr>
        <%
            for (Resume resume: (List<Resume>) request.getAttribute("resumes") ){
        %>

        <tr>
            <td><a href="resume?uuid=<%=resume.getUuid()%>"/><%=resume.getFullName()%> </td>
            <td><%=resume.getContact(EMAIL)%></td>
        </tr>
        <%
            }
        %>
    </table>
</section>
</body>
</html>
