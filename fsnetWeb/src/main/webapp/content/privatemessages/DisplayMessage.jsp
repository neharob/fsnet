<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="../../WEB-INF/ili.tld" prefix="ili"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="js/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="js/mceTextArea.js"></script>


<fieldset class="fieldsetCadre">
	<legend>${theMessage.subject}</legend>

	<c:choose>
		<c:when test="${empty requestScope.conversationMessages.resultList}">
			<s:form action="/DeleteMultiMessages2?fromPage=in">
				<table class="topicTable inLineTable tableStyle">
					<c:forEach items="${requestScope.conversationMessages1.resultList}"
						var="message">
						<tr class="topicHeader">
							<td><s:checkbox name="selectedMessages"
									value="%{message.id}" /></td>
							<td><s:text name="privatemessages.from" /> : |<ili:getSocialEntityInfos
									socialEntity="${message.from}" />| <span style="float: right">
									<s:property value="message" />
							</span></td>
						</tr>
						<tr>
							<td class="topicHeader"></td>
							<td>${message.body}</td>
						</tr>
					</c:forEach>
				</table>
				<s:submit styleClass="btn btn-inverse">
					<s:text name="privatemessages.delete" />
				</s:submit>
			</s:form>

			<ili:interactionFilter user="${ socialEntity }"
				right="${ rightAnswerMessage }">
				<a class="button"
					onclick="document.getElementById('quickResponse').style.display='table'">
					<img src="images/quickResponse.png" style="vertical-align: bottom"
					alt="Quick response" /> <s:text
						name="privatemessages.Quickresponse" />
				</a>
			</ili:interactionFilter>




			<s:form action="/CreatePrivateMessage">
				<s:hidden name="messageTo" value="%{theMessage.from.email}" />
				<table id="quickResponse tableStyle"
					style="width: 100%; display: none; margin-top: 10px;">
					<tr>
						<td><label for="messageTo"> <s:text
									name="privatemessages.to" /> :
						</label></td>
						<td><s:text
								name="%{theMessage.from.name} %{theMessage.from.firstName}" /></td>
					</tr>
					<tr>
						<td><label for="messageSubject"> <s:text
									name="privatemessages.subject" /> :
						</label></td>
						<td><s:text name="RE: %{theMessage.subject}" /></td>
					</tr>
					<tr>
						<td colspan="2"><s:textarea property="messageBody"
								styleClass="mceTextArea" style="width:100%">
							</s:textarea></td>
					</tr>

					<tr>
						<td colspan="2" class="alignRight"><s:submit
								styleClass="btn btn-inverse"
								onclick="this.disabled=true; this.value='Sending Message'; this.form.submit();">
								<s:text name="privatemessages.send" />
							</s:submit></td>
					</tr>
				</table>
			</s:form>
		</c:when>
		<c:otherwise>

			<s:form action="/DeleteMultiMessages2?fromPage=in">
				<table class="topicTable inLineTable">
					<c:forEach items="${requestScope.conversationMessages.resultList}"
						var="message">
						<tr class="topicHeader">
							<td><s:checkbox name="selectedMessages"
									value="%{message.id}" /></td>
							<td><s:text name="privatemessages.from" /> : |<ili:getSocialEntityInfos
									socialEntity="${message.from}" />| <span style="float: right">
									<s:property value="message" />
							</span></td>
						</tr>
						<tr>
							<td class="topicHeader"></td>
							<td>${message.body}</td>
						</tr>
					</c:forEach>
				</table>
				<s:submit styleClass="btn btn-inverse">
					<s:text name="privatemessages.delete" />
				</s:submit>
			</s:form>

			<ili:interactionFilter user="${ socialEntity }"
				right="${ rightAnswerMessage }">
				<a class="btn btn-inverse"
					onclick="document.getElementById('quickResponse').style.display='table'">
					<img src="images/quickResponse.png" style="vertical-align: bottom"
					alt="Quick response" /> <s:text
						name="privatemessages.Quickresponse" />
				</a>
			</ili:interactionFilter>

			<s:form action="/CreatePrivateMessage">
				<s:hidden name="messageTo" value="%{theMessage.from.email}" />
				<table id="quickResponse"
					style="width: 100%; display: none; margin-top: 10px;">
					<tr>
						<td><label for="messageTo"> <s:text
									name="privatemessages.to" /> :
						</label></td>
						<td><s:text
								name="%{theMessage.from.name} %{theMessage.from.firstName}" /></td>
					</tr>
					<tr>
						<td><label for="messageSubject"> <s:text
									name="privatemessages.subject" /> :
						</label></td>
						<td><s:text name="RE: %{theMessage.subject}" /></td>
					</tr>
					<tr>
						<td colspan="2"><s:textarea property="messageBody"
								styleClass="mceTextArea" style="width:100%">
							</s:textarea></td>
					</tr>

					<tr>
						<td colspan="2" class="alignRight"><s:submit
								styleClass="btn btn-inverse"
								onclick="this.disabled=true; this.value='Sending Message'; this.form.submit();">
								<s:text name="privatemessages.send" />
							</s:submit></td>
					</tr>
				</table>
			</s:form>

		</c:otherwise>
	</c:choose>
</fieldset>
