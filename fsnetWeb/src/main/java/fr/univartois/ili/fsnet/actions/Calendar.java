package fr.univartois.ili.fsnet.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import fr.univartois.ili.fsnet.actions.utils.UserUtils;
import fr.univartois.ili.fsnet.commons.utils.DateUtils;
import fr.univartois.ili.fsnet.commons.utils.PersistenceProvider;
import fr.univartois.ili.fsnet.entities.Meeting;
import fr.univartois.ili.fsnet.entities.SocialEntity;
import fr.univartois.ili.fsnet.facade.MeetingFacade;


public class Calendar extends MappingDispatchAction {

	/**
	 * List that contains meeting/events for calendar view
	 */
	private List<String> events;

	/**
	 * Action that create a list of all events/meetings in json format
	 * Used for Full Calendar jquery plugin
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		EntityManager em = PersistenceProvider.createEntityManager();
		SocialEntity user = UserUtils.getAuthenticatedUser(request, em);

		em.getTransaction().begin();

		MeetingFacade meetingFacade = new MeetingFacade(em);
		List<Meeting> results = meetingFacade.getAllUserMeeting(user);
		
		events = new ArrayList<String>();
		for (Meeting m : results) {
			String startDate = DateUtils.renderDateForFullCalendar(m.getStartDate());
			String endDate = DateUtils.renderDateForFullCalendar(m.getEndDate());
			events.add(m.getTitle()+","+startDate+","+endDate+","+"false"+","+m.getContent()+","+m.getId());
		}

		JSONArray jsonArray = JSONArray.fromObject(events);
		JSONObject obj = new JSONObject();
		obj.put("events", jsonArray);

		response.setHeader("X-JSON", obj.toString());
		response.setContentType("text/html");

		return mapping.findForward("success");

	}

	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}

}