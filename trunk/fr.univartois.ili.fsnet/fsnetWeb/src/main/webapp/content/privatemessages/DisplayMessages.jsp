<%-- 
    Document   : DisplayInbox
    Created on : 2 févr. 2010, 18:29:45
    Author     : Matthieu Proucelle <matthieu.proucelle at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<h3>Boite de reception</h3>
<logic:empty name="messages">
    zdzdzdzd
</logic:empty>
<table class="inLineTable">
    <c:forEach items="${requestScope.messages}" var="message">
        <c:if test="${not message.reed}">
            <tr class="notReed">
                <td>
                    <input type="checkbox"/>
                </td>
                <td style="width: 25%">
                    <html:link action="/DisplayMessage">
                        <html:param name="messageId" value="${message.id}"/>
                        ${message.from.firstName} ${message.from.name}
                    </html:link>
                </td>
                <td style="width: 50%">
                    <html:link action="/DisplayMessage">
                        <html:param name="messageId" value="${message.id}"/>
                        <span>${fn:substring(message.subject, 0,20)} : </span>
                        <span style="color: gray">${fn:escapeXml(fn:substring(message.body, 0, 20))}</span>
                    </html:link>
                </td>
            </c:if>
            <c:if test="${message.reed}">
            <tr>
                <td>
                    <input type="checkbox"/>
                </td>
                <td style="width: 25%">
                    <html:link action="/DisplayMessage">
                        <html:param name="messageId" value="${message.id}"/>
                        ${message.from.firstName} ${message.from.name}
                    </html:link>
                </td>
                <td style="width: 50%">

                    <html:link action="/DisplayMessage">
                        <html:param name="messageId" value="${message.id}"/>
                        <span>${fn:substring(message.subject, 0,20)} : </span>
                        <span style="color: gray">${fn:substring(message.body, 0, 20)}</span>
                    </html:link>
                </td>
            </c:if>
            <td class="alignRight">
                <bean:write name="message" property="creationDate" format="dd/MM/yyyy HH:mm"/>
            </td>
        </tr>
    </c:forEach>
</table>