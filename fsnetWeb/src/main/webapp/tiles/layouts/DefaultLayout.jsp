<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle">
	<tiles:getAsString name="title" />
</c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html:html xhtml="true" lang="true">
<head>

<meta http-equiv="content-type"
	content="application/xhtml+xml; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />

<title><bean:message key="${pageTitle}" /></title>

<link rel="shortcut icon" href="images/Favicon.ico"
	type="image/vnd.microsoft.icon" />

<link rel="stylesheet" type="text/css" media="screen"
	href="css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="css/fsnet-custom.css" />
<link type="text/css" href="css/jquery-tablesorter-custom.css"
	rel="stylesheet" />
<link type="text/css" rel="stylesheet" media="all" href="css/chat.css" />
<link type="text/css" rel="stylesheet" media="all" href="css/screen.css" />

<link type="text/css" href="css/cupertino/jquery-ui-1.8.18.custom.css"
	rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-i18n.min.js"></script>
<link type="text/css" rel="stylesheet" media="all"
	href="css/jquery-ui-timepicker-addon.css" />
<!-- DO NOT GET THIS LIBRARY HERE !!! -->
<!--<script type="text/javascript" src="js/jquery-ui-timepicker-addon.js"></script>-->
<script type="text/javascript" src="js/functions.js"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="js/geolocalisation.js"></script>
<script type="text/javascript" src="js/jquery.simplemodal-1.4.2.js"></script>

<script type="text/javascript" src="js/talkUtils.js"></script>
<script type="text/javascript" src="js/chat.js"></script>
<script class="jsbin" type="text/javascript"
	src="js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="css/skeleton.css" />

<link rel="stylesheet" media="screen" type="text/css"
	href="css/colorpicker.css" />
<script type="text/javascript" src="js/colorpicker.js"></script>
<script type="text/javascript" src="js/eye.js"></script>

<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/layout.js?ver=1.0.2"></script>

<script>
	$('#colorpickerField1').ColorPicker({
		onSubmit : function(hsb, hex, rgb, el) {
			$(el).val(hex);
			$(el).ColorPickerHide();
		},
		onBeforeShow : function() {
			$(this).ColorPickerSetColor(this.value);
		}
	}).bind('keyup', function() {
		$(this).ColorPickerSetColor(this.value);
	});
</script>

<!-- Custom color style by group-->
<style type="text/css">
body {
	background: -webkit-gradient(linear, left top, left bottom, from(#${color}),to(white)) no-repeat;
	background: -webkit-linear-gradient(top,#${color},white) no-repeat;
	background: -moz-linear-gradient(top,#${color},white) no-repeat;
	background: -o-linear-gradient(top,#${color},white) no-repeat;
	background: linear-gradient(top,#${color},white) no-repeat;	
}
</style>



</head>
<body>
	<div id="wrapHeader">
		<div id="header">
			<tiles:useAttribute name="currentMenu" scope="request" ignore="true" />
			<tiles:insert attribute="menu" />
		</div>
	</div>
	<div id="wrapBody">
		<div id="placeHeader"></div>
		<div id="leftMenu">
			<div id="logo">
				<tiles:insert attribute="logo" />
			</div>
			<div id="left">
				<h2>
					<a> <bean:message key="${pageTitle}" />
					</a>
				</h2>
				<tiles:insert attribute="left" />
				<tiles:insert attribute="loggedUsers" />
			</div>
		</div>

		<div id="body-content">
			<tiles:insert attribute="body-content" />
		</div>
		<div style="clear: both"></div>

		<div id="placeFooterStyle"></div>
	</div>
	<div id="wrapFooterStyle">
		<div id="footerStyle">
			<tiles:insert attribute="footer" />
		</div>
	</div>

</body>
</html:html>