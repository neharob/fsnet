<%-- 
		 Author : BOURAGBA Mohamed
		
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="../../WEB-INF/ili.tld" prefix="ili"%>

<h3>
	<bean:message key="groups.title" />
</h3>
<c:choose>
	<c:when test="${ socialGroup != null }">
		<div>
			<c:forEach var="group" items="${antecedantsOfGroup}">
				<html:link action="/DisplayInformationGroup">
					<html:param name="idGroup" value="${ group.id }" />
					${group.name}
				</html:link>
				<bean:message key="groups.addGroups" />
			</c:forEach>
			<html:link action="/DisplayInformationGroup">
				<html:param name="idGroup" value="${ socialGroup.id }" />
				${ socialGroup.name }
			</html:link>
		</div>

		<h3>
			<bean:message key="groups.description.message" />
		</h3>
		<p>${ socialGroup.description }</p>

		<h3>
			<bean:message key="groups.listGroup" />
		</h3>
		<c:forEach items="${childsOfGroup}" var="cGroup" varStatus="status">
			<html:link action="/DisplayInformationGroup">
				<html:param name="idGroup" value="${ cGroup.id }" />
				${ cGroup.name }
			</html:link>
			<c:if test="${status.count < fn:length(childsOfGroup)}"> | </c:if>
		</c:forEach>

		<h3>
			<bean:message key="groups.listMember" />
		</h3>
		<c:forEach var="member" items="${allMembers}">
			<a href="/fsnetWeb/DisplayProfile.do?id=${member.id}"
				class="miniature"> <img
				title="${member.name} ${member.firstName}"
				src="miniature/${member.id}.png" />
			</a>
		</c:forEach>

		<br/>
		<br/>

		<c:if test="${ sessionScope.isMasterGroup }">
			<h3>
				<bean:message key="groups.rights.title" />
			</h3>
			<c:choose>
				<c:when test="${ fn:length(socialGroup.rights) != 0 }">
					<table class="inLineTable">
						<c:forEach var="right" items="${ socialGroup.rights }">
							<tr>
								<td><bean:message key="groups.rights.${ right }" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<p>
						<bean:message key="groups.noRights.title" />
					</p>
				</c:otherwise>
			</c:choose>
		</c:if>
	</c:when>
	<c:otherwise>
		<p>
			<bean:message key="groups.noGroup" />
		</p>
	</c:otherwise>
</c:choose>
