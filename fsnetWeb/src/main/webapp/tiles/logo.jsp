<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="logo2">
	<img src="images/logo.png" />

</div>
<html:link action="/DisplayProfile" styleId="userPicture">
	<img src="avatar/${sessionScope.userId}.png" />
</html:link>

<div class="group">
	<c:choose>
	<c:when test="${sessionScope.hisGroup != null}">
	<bean:message key="avatar.groups" /> 
	<html:link action="/DisplayInformationGroup">
		<html:param name="idGroup" value="${sessionScope.hisGroup.id}"/>
		${sessionScope.hisGroup.name}
	</html:link>
	</c:when>
	<c:otherwise>
		<bean:message key="avatar.member.no.group" /> 
	</c:otherwise>
	</c:choose>
<br>
<c:if test="${isMasterGroup}">
<html:link action="DisplayProfile.do" styleId="">
	<bean:message key="left.group.manager"/>
</html:link>
</c:if>
</div>

<style>
.group {
	float : left;	
	clear: left ;

}
</style>

