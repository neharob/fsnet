package fr.univartois.ili.fsnet.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import fr.univartois.ili.fsnet.actions.utils.UserUtils;
import fr.univartois.ili.fsnet.commons.utils.PersistenceProvider;
import fr.univartois.ili.fsnet.entities.Interest;
import fr.univartois.ili.fsnet.entities.PrivateMessage;
import fr.univartois.ili.fsnet.entities.ProfileVisite;
import fr.univartois.ili.fsnet.entities.SocialEntity;
import fr.univartois.ili.fsnet.facade.InteractionFacade;
import fr.univartois.ili.fsnet.facade.InterestFacade;
import fr.univartois.ili.fsnet.facade.ProfileVisiteFacade;
import fr.univartois.ili.fsnet.facade.SocialEntityFacade;
import fr.univartois.ili.fsnet.facade.InteractionFacade.Triple;
import fr.univartois.ili.fsnet.facade.SocialEntityFacade.SearchResult;
import fr.univartois.ili.fsnet.facade.SocialGroupFacade;

public class Home extends MappingDispatchAction {

	private void lastVisits(ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, EntityManager em,
			SocialEntity authenticatedUser) throws IOException,
			ServletException {
		ProfileVisiteFacade pvf = new ProfileVisiteFacade(em);
		List<ProfileVisite> visitors = pvf.getLastVisitor(authenticatedUser);
		request.setAttribute("visitors", visitors);
	}

	private void lastInteractions(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response,
			EntityManager em)
			throws IOException, ServletException {
		SocialEntity connectedUser = UserUtils.getAuthenticatedUser(request, em);
		InteractionFacade facade = new InteractionFacade(em);
		List<Triple> result = facade
				.getLastInteractions(connectedUser);		
		
		request.setAttribute("lastInteractions", result);
	}

	private void lastMessages(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response,
			EntityManager em, SocialEntity authenticatedUser)
			throws IOException, ServletException {
		List<PrivateMessage> userMessages = new ArrayList<PrivateMessage>(
				authenticatedUser.getReceivedPrivateMessages());
		Collections.reverse(userMessages);
		request.setAttribute("messages", userMessages);
	}

	private void getInterestProposals(ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, EntityManager em,
			SocialEntity authenticatedUser) throws IOException,
			ServletException {
		InterestFacade facade = new InterestFacade(em);
		List<Interest> interestProposals = facade.getOtherInterests(authenticatedUser);
		if(interestProposals.size()==0){
			interestProposals = facade.getNonAssociatedInterests(authenticatedUser);
			Collections.shuffle(interestProposals);
		}
		if(interestProposals.size()>7){
			interestProposals = interestProposals.subList(0,7);
		}
				
		request.setAttribute("interests", interestProposals);
	
	}
	
	private void getContactProposals(ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, EntityManager em,
			SocialEntity authenticatedUser) throws IOException,
			ServletException {
		HttpSession session = request.getSession();
		Integer numNewContactRequests = (Integer) session.getAttribute("numNewContactsRequests");
		if (numNewContactRequests!=null && numNewContactRequests > 0) {
			SocialEntity user = UserUtils.getAuthenticatedUser(request, em);
			List<SocialEntity> socialEntities;
			if (user.getAsked().size() > 5) {
				socialEntities = new ArrayList<SocialEntity>(5);
				socialEntities = user.getAsked().subList(0, 5);
			} else {
				socialEntities = user.getAsked();
			}
			session.setAttribute("contactsAsked", socialEntities);
		} else {
			SocialEntityFacade facade = new SocialEntityFacade(em);
			Set<SocialEntity> setContacts = facade.searchSocialEntity("",authenticatedUser).get(SearchResult.Others);
			List<SocialEntity> contacts = new ArrayList<SocialEntity>( setContacts );
			Collections.shuffle(contacts);
			if(contacts.size()>5){
				contacts = contacts.subList(0,5);
			}
			request.setAttribute("contacts", contacts);
		}
		
	}

	public ActionForward doDashboard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		EntityManager em = PersistenceProvider.createEntityManager();
		SocialGroupFacade sgf = new SocialGroupFacade(em);
		SocialEntity authenticatedUser = UserUtils.getAuthenticatedUser(
				request, em);

		lastVisits(mapping, request, response, em, authenticatedUser);
		lastInteractions(mapping, request, response, em);
		lastMessages(mapping, request, response, em, authenticatedUser);
		getContactProposals(mapping, request, response, em, authenticatedUser);
		getInterestProposals(mapping, request, response, em, authenticatedUser);
		
		request.getSession().setAttribute("isMasterGroup",sgf.isMasterGroup(authenticatedUser));

		em.close();

		return mapping.findForward("success");
	}

}
