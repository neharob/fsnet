<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html"     prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic"    prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="../../WEB-INF/ili.tld" prefix="ili"%>

<h3>
    <c:import url="/FavoriteFragment.do">
        <c:param name="interactionId" value="${event.id}"/>
    </c:import>
    ${event.title}
</h3>

<div class="interactionDisplay">
    <table>
        <tr class="authorDate">
            <td>
                <bean:message key="events.5"/>
                <ili:getSocialEntityInfos socialEntity="${event.creator}"/>
                , 
                <bean:message key="events.willoccur"/>
                <bean:write name="event" property="startDate" format="dd/MM/yyyy" />
                <bean:message key="events.to"/>
                <bean:write name="event" property="endDate" format="dd/MM/yyyy" />
                
                <c:if test="${not empty event.address.address or not empty event.address.city}">
                	<bean:message key="events.in"/>
                	${event.address.address} ${event.address.city}
                </c:if>
                
                <c:if test="${subscriber}">,&nbsp;&nbsp;"<bean:message key="events.19"/>"</c:if>
            </td>
        </tr>
        <tr>
            <td>
                ${event.content}
            </td>
        </tr>
        <tr>
            <td  class="alignRight">
            	<ili:interactionFilter user="${ socialEntity }" right="${ rightRegisterEvent }">
               	 	<c:if test="${not subscriber}">
	                	<html:link  action="/SubscribeEvent" styleClass="button">
	                    	<html:param name="eventId" value="${event.id}"/>
	                    	<bean:message key="events.17"/>
	                	</html:link>
            		</c:if>
            		<c:if test="${subscriber}">
                		<html:link  action="/UnsubscribeEvent" styleClass="button">
                    		<html:param name="eventId" value="${event.id}"/>
                    		<bean:message key="events.18"/>
                		</html:link>
            		</c:if>
            	</ili:interactionFilter>
                <c:if test="${userId eq event.creator.id}">
                    <html:form  action="/DeleteEvent" method="post" styleId="eventid${event.id}" styleClass="deleteEventForm" >
                        <html:hidden property="eventId" value="${event.id}"/>
                        <span class="button" onclick="confirmDelete2('eventid${event.id}')">
                        	<bean:message key="events.7"/>
                        </span>
                    </html:form>
                </c:if>
            </td>
        </tr>
    </table>
</div>
<c:set var="theInteraction" value="${event}" scope="request"/>
<jsp:include page="/content/interactions/InteractionInfo.jsp" />
<c:if test="${not empty event.address.city}">
   <jsp:include page="/content/geolocalisation/GeolocalisationWidget.jsp" />
</c:if>
<div class="clear"></div>


<c:if test="${fn:length(subscribers) gt 0}">
<h3>
	<bean:message key="events.22"/> : 
</h3>
<logic:iterate id="subscriber" collection="${subscribers}"> 
	<span class="tagSE"> 
		<ili:getMiniature socialEntity="${subscriber}"/>
	    <ili:getSocialEntityInfos socialEntity="${subscriber}"/>
	</span> 
</logic:iterate>
</c:if>
