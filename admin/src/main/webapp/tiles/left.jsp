<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<ul>
    <li>
        <html:link action="/Members">
			<bean:message key="left.2"/>
        </html:link>
    </li>
    
    
    
    <li>
        <html:link action="/Groups">
			<bean:message key="left.4"/>
        </html:link>
    </li>
    
    <li>
        <html:link action="/DisplayForModifyGroup">
			<bean:message key="left.test"/>
        </html:link>
    </li>
    
</ul>