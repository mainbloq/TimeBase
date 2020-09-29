<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.*"%>
<%@ page import="java.lang.management.ThreadMXBean" %>
<%@ page import="java.lang.management.ManagementFactory" %>
<%@ page import="java.lang.management.ThreadInfo" %>
<%@ page import="deltix.util.lang.Util"%>

<html>
<head>
    <title>Thread Management</title>
    <link rel='stylesheet' type='text/css' href='../style.css'>
</head>
<style>
    table.list
    {
        border-width: 1px 1px 0px 1px;
        border-style: solid;
        border-color: #000000;
    }
    td.line
    {
        margin: 0;
        padding: 2px;
        border-width: 1px 0px 1px 0px;
        border-style: solid;
        border-color: #000000;
    }
</style>
<body>

<h1>Thread Management</h1>

<%
Map<Thread, ThreadInfo> threads = Util.getAllStackTraces ();
int id = request.getParameter("itn") != null ? Integer.parseInt(request.getParameter("itn")) : -1;

for (Thread th : threads.keySet ()) {
    if (th.getId() == id) {
        pageContext.setAttribute ("interruptedThread", th);
        th.interrupt();
    }
}
pageContext.setAttribute ("threads", threads);

%>

<script>
function inter (name, form) {
    form.itn.value=name;
    form.submit ();
}
</script>

<c:if test="${interruptedThread != null}">
    <p>
        <font color="red">
            <%=new Date ()%>: Interrupted thread <code><c:out value="${interruptedThread.name}"/></code>.
        </font>
    </p>
</c:if>

<form>
    <input type=hidden name="itn" value="">
    
    <table class="list" border="0" cellpadding="2" cellspacing="0" width="95%">
        <c:forEach items="${threads}" var="entry">
            <tr bgcolor="#FDCF46">
                <td><b><c:out value="${entry.key.name}"/></b></td>
                <td align=left><c:out value="${entry.key.state}"/></td>
                <td align="center">
                    <button onClick='inter ("<c:out value="${entry.key.id}"/>", form)'>Interrupt</button>
                </td>
            </tr>
            <tr>
                <td colspan=3 class="line">
                    <c:set var="th" value="${entry.key}"/>
                    <c:set var="info" value="${entry.value}"/>
                    <pre><%=Util.getThreadStackTrace( (Thread) pageContext.getAttribute("th"),
                        (ThreadInfo) pageContext.getAttribute("info"))%></pre>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>

</body>
</html>
