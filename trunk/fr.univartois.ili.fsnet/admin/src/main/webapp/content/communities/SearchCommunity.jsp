<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<h3><bean:message key="communities.search"/></h3>
<html:form action="SearchCommunity">
    <div id="SearchCommunity">
        <html:text property="searchText" />
        <html:submit styleClass="button"><bean:message key="communities.searchButton"/></html:submit>
    </div>
</html:form>

<h3><bean:message key="communities.listCommunities"/></h3>

<c:choose>
<c:when test="${! empty communitiesResult}">
	<table  class="inLineTable">
        <c:forEach var="community" items="${communitiesResult}">
            <tr class="content">
                <td>${community.title}      
                </td>
            </tr>
        </c:forEach>
    </table>
</c:when>
<c:otherwise>
	<bean:message key="communities.noResult"/>
</c:otherwise>
</c:choose>