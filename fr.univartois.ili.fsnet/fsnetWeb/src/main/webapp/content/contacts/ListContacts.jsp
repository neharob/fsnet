<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

Liste de vos contacts:
<c:forEach var="contact" items="${requestScope.listContacts.contacts}">
    -${contact.nom}
</c:forEach>