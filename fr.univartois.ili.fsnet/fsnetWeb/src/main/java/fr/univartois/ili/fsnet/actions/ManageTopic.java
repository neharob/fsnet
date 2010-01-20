/*
 *  Copyright (C) 2010 Matthieu Proucelle <matthieu.proucelle at gmail.com>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.univartois.ili.fsnet.actions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.MappingDispatchAction;

import fr.univartois.ili.fsnet.entities.EntiteSociale;
import fr.univartois.ili.fsnet.entities.Hub;
import fr.univartois.ili.fsnet.entities.Message;
import fr.univartois.ili.fsnet.entities.Topic;

/**
 *
 * @author 
 */
public class ManageTopic extends MappingDispatchAction implements CrudAction {

	private static EntityManagerFactory factory = Persistence
	.createEntityManagerFactory("fsnetjpa");

	private static final Logger logger = Logger.getAnonymousLogger();
	
    @Override
    public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	EntityManager em = factory.createEntityManager();
		DynaActionForm dynaForm = (DynaActionForm) form;
		String topicSujet = (String) dynaForm.get("topicSujet");
		Hub entiteHub = (Hub) dynaForm.get("entityHub");
		Date date = new Date();
		List<Message> lesMsgs = (List<Message>) dynaForm.get("listMessages");
		EntiteSociale entiteSociale = (EntiteSociale) dynaForm.get("entitySociale");
		Topic topic = new Topic(topicSujet,date,lesMsgs,entiteHub,entiteSociale);
        em.getTransaction().begin();
        em.persist(topic);
	    em.getTransaction().commit();
	    em.close();
		return mapping.findForward("success");
    }

    @Override
    public ActionForward modify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	EntityManager em = factory.createEntityManager();
		DynaActionForm dynaForm = (DynaActionForm) form;
		int topicId = (Integer)dynaForm.get("topicId");
		String topicSujet = (String) dynaForm.get("topicSujet");
		Hub entityHub = (Hub) dynaForm.get("entityHub");
		List<Message> lesMsgs = (List<Message>) dynaForm.get("listMessages");
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Topic SET sujet = :sujet, hub = :hub, lesMessages = :lesMsgs WHERE id = ?topicId");
	    	query.setParameter("sujet", topicSujet);
	    	query.setParameter("hub", entityHub);
	    	query.setParameter("lesMsgs",lesMsgs);
	    	query.setParameter("topicId",topicId);
	    	query.executeUpdate();
        em.getTransaction().commit();
        em.close();
		return mapping.findForward("success");
    }

    @Override
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	EntityManager em = factory.createEntityManager();
    	if (request.getParameterMap().containsKey("topicId")) {
			int topicId = Integer.valueOf(request.getParameter("topicId"));
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Topic WHERE id = ?topicId");
            	query.setParameter("topicId", topicId);
            	query.executeUpdate();
            em.getTransaction().commit();
	        em.close();
		}
		
		return mapping.findForward("success");
    }

    @Override
    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	EntityManager em = factory.createEntityManager();
		DynaActionForm dynaForm = (DynaActionForm) form;
		String topicSujet = (String) dynaForm.get("topicSujet");
        Query query = em.createQuery("SELECT OBJECT(topic) FROM Topic topic WHERE topic.sujet LIKE '%:topicSujet%' ");
        	query.setParameter("topicSujet", topicSujet);
        List<Topic> result =query.getResultList();
        request.setAttribute("listEntityTopic", result);
		return mapping.findForward("success");
    }

    @Override
    public ActionForward display(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	EntityManager em = factory.createEntityManager();
    	List<Topic> result = null;
    	result = em.createQuery("SELECT OBJECT(topic) FROM Topic topic order by sujet").getResultList();
        request.getSession().setAttribute("listTopics", result);
    	return mapping.findForward("success");
    }
}
