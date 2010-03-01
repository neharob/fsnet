package fr.univartois.ili.fsnet.actions.ajax;

import java.io.Writer;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import fr.univartois.ili.fsnet.commons.utils.PersistenceProvider;
import fr.univartois.ili.fsnet.entities.SocialEntity;
import fr.univartois.ili.fsnet.facade.SocialEntityFacade;

/**
 *
 * @author Matthieu Proucelle <matthieu.proucelle at gmail.com>
 */
public class Members extends Action {
	
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (form != null) {

            DynaActionForm theform = (DynaActionForm) form; // NOSONAR
            String searchText = (String) theform.get("searchText");
            EntityManager em = PersistenceProvider.createEntityManager();
            SocialEntityFacade sef = new SocialEntityFacade(em);
            List<SocialEntity> listSE = sef.searchSocialEntity(searchText);

            Writer out = response.getWriter();
            response.setContentType("text/xml");

            out.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            out.append("<root>");
            for (SocialEntity se : listSE) {
                out.append("<item id='" + se.getId() + "' label='" + se.getEmail() + "'/>");
            }
            out.append("</root>");
            out.flush();
            out.close();
        }
        return null;
    }
}