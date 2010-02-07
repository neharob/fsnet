package fr.univartois.ili.fsnet.admin.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.MappingDispatchAction;
import org.eclipse.persistence.exceptions.DatabaseException;

import fr.univartois.ili.fsnet.entities.Interaction;
import fr.univartois.ili.fsnet.entities.Interest;
import fr.univartois.ili.fsnet.facade.forum.iliforum.InterestFacade;

/**
 * Execute CRUD Actions (and more) for the entity interet
 * 
 * @author Alexandre Lohez <alexandre.lohez at gmail.com>
 */
public class ManageInterests extends MappingDispatchAction implements
		CrudAction {

	private static EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("fsnetjpa");
	private static final Logger logger = Logger.getAnonymousLogger();

	@Override
	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		EntityManager em = factory.createEntityManager();
		DynaActionForm dynaForm = (DynaActionForm) form; // NOSONAR
		InterestFacade facade = new InterestFacade(em);

		String interestName = (String) dynaForm.get("createdInterestName");
		int parentInterestId = Integer.valueOf((String) dynaForm.get("parentInterestId"));
		
		logger.info("new interest: " + interestName);

		try {
			em.getTransaction().begin();
			if (parentInterestId != 0) {
				facade.createInterest(interestName, parentInterestId);
			} else {
				facade.createInterest(interestName);
			}
			em.getTransaction().commit();
		} catch (RollbackException ex) {
			ActionErrors actionErrors = new ActionErrors();
			ActionMessage msg = new ActionMessage("interest.alreadyExists");
			actionErrors.add("createdInterestName", msg);
			saveErrors(request, actionErrors);
		}

		em.close();

		return mapping.findForward("success");
	}

	@Override
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		EntityManager em = factory.createEntityManager();
		DynaActionForm dynaForm = (DynaActionForm) form;// NOSONAR
		int interestId = Integer.valueOf((String) dynaForm
				.get("modifiedInterestId"));
		String interestName = (String) dynaForm.get("modifiedInterestName");
		int parentInterestId = Integer.valueOf((String) dynaForm.get("modifiedParentInterstId"));
		InterestFacade facade = new InterestFacade(em);

		Interest interest = facade.getInterest(interestId);

		
		if (interest != null) {
			logger.info("interest modification: " + interestName);

			try {
				em.getTransaction().begin();
				facade.modifyInterest(interestName, interest);
				em.getTransaction().commit();
			} catch (DatabaseException ex) {
				ActionErrors actionErrors = new ActionErrors();
				ActionMessage msg = new ActionMessage("interest.alreadyExists");
				actionErrors.add("modifiedInterestName", msg);
				saveErrors(request, actionErrors);
			}
		}

		em.close();

		return mapping.findForward("success");
	}

	@Override
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		EntityManager em = factory.createEntityManager();

		// TODO verify if user has the right to do delete

		int interestId = Integer.valueOf(request
				.getParameter("deletedInterestId"));
		InterestFacade facade = new InterestFacade(em);
		Interest interest = facade.getInterest(interestId);

		if (interest != null) {
			logger.info("interest deleted: id=" + interestId);

			try {
				em.getTransaction().begin();
				facade.deleteInterest(interest);
				em.getTransaction().commit();
			} catch (RollbackException ex) {
				ActionErrors actionErrors = new ActionErrors();
				ActionMessage msg = new ActionMessage("interest.notExists");
				actionErrors.add("error.interest.delete", msg);
				saveErrors(request, actionErrors);
			}
		}
		em.close();

		return mapping.findForward("success");
	}

	@Override
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		EntityManager em = factory.createEntityManager();
		DynaActionForm dynaForm = (DynaActionForm) form;// NOSONAR
		String interestName = "";

		if (dynaForm.get("searchInterestName") != null) {
			interestName = (String) dynaForm.get("searchInterestName");
		}
		InterestFacade facade = new InterestFacade(em);
		logger.info("search interest: " + interestName);

		List<Interest> result = facade.searchInterest(interestName);
		em.close();

		request.setAttribute("interestResult", result);

		return mapping.findForward("success");
	}

	@Override
	public ActionForward display(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		EntityManager em = factory.createEntityManager();
		InterestFacade facade = new InterestFacade(em);
		logger.info("Displaying interests");

		List<Interest> listAllInterests = facade.getInterests();
		em.close();
		request.setAttribute("allInterests", listAllInterests);

		return mapping.findForward("success");
	}
	
	public ActionForward informations(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		EntityManager em = factory.createEntityManager();
		InterestFacade facade = new InterestFacade(em);
		logger.info("Displaying interest's informations");

		int interestId;
		if (request.getParameterMap().containsKey("interestId")) {
			try {
				interestId = Integer.valueOf(request.getParameter("interestId"));
			} catch (NumberFormatException e) {
				interestId = 0;
			}

			Interest interest = facade.getInterest(interestId);
			HashMap<String, List<Interaction>> resultMap = facade.getInteractions(interestId);
			em.close();
			
			if (interest != null) {
				request.setAttribute("interest", interest);
				for (String interactionClass : resultMap.keySet()) {
					request.setAttribute(interactionClass, resultMap.get(interactionClass));
				}
			}
		}
		return mapping.findForward("success");
	}
}