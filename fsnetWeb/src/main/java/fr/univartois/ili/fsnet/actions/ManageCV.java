package fr.univartois.ili.fsnet.actions;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.MappingDispatchAction;

import fr.univartois.ili.fsnet.actions.utils.UserUtils;
import fr.univartois.ili.fsnet.commons.utils.PersistenceProvider;
import fr.univartois.ili.fsnet.entities.AssociationDateDegreeCV;
import fr.univartois.ili.fsnet.entities.AssociationDateFormationCV;
import fr.univartois.ili.fsnet.entities.AssociationDateTrainingCV;
import fr.univartois.ili.fsnet.entities.Curriculum;
import fr.univartois.ili.fsnet.entities.DegreeCV;
import fr.univartois.ili.fsnet.entities.EstablishmentCV;
import fr.univartois.ili.fsnet.entities.FormationCV;
import fr.univartois.ili.fsnet.entities.HobbiesCV;
import fr.univartois.ili.fsnet.entities.Interaction;
import fr.univartois.ili.fsnet.entities.Meeting;
import fr.univartois.ili.fsnet.entities.MemberCV;
import fr.univartois.ili.fsnet.entities.Right;
import fr.univartois.ili.fsnet.entities.SocialEntity;
import fr.univartois.ili.fsnet.entities.TrainingCV;
import fr.univartois.ili.fsnet.facade.CvFacade;
import fr.univartois.ili.fsnet.facade.InteractionFacade;

/**
 * @author fsnet
 * 
 */
public class ManageCV extends MappingDispatchAction {

	public static final String SUCCESS_ACTION_NAME = "success";
	public static final String UNAUTHORIZED_ACTION_NAME = "unauthorized";
	
	public static SimpleDateFormat formatter = new SimpleDateFormat(
			"dd/MM/yyyy");

	/**
	 * @param sDate
	 * @return
	 * @throws ParseException
	 */
	public static java.util.Date stringToDate(String sDate)
			throws ParseException {
		return formatter.parse(sDate);
	}

	/**
	 * @param sDate
	 * @return
	 * @throws ParseException
	 */
	public static Date toDBDateFormat(String sDate) throws ParseException {
		return new Date(stringToDate(sDate).getTime());
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward Cree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return mapping.findForward("success");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward displayProfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession mysession = request.getSession();
		DynaActionForm dynaForm = (DynaActionForm) form; // NOSONAR
		String cvTitle = (String) dynaForm.get("CvTitle");
		String cvNom = (String) dynaForm.get("CvNom");
		String cvPrenom = (String) dynaForm.get("CvPrenom");
		String cvAdresse = (String) dynaForm.get("CvAdresse");
		String cvVille = (String) dynaForm.get("CvVille");
		String cvPortable = (String) dynaForm.get("CvTel");
		String cvCp = (String) dynaForm.get("CvCp");
		String cvPays = (String) dynaForm.get("CvPays");
		String cvContact = (String) dynaForm.get("CvContact");
		String birthDay = (String) dynaForm.get("CvBirthDay");

		mysession.setAttribute("CvTitle", cvTitle);
		mysession.setAttribute("CvNom", cvNom);
		mysession.setAttribute("CvPrenom", cvPrenom);
		mysession.setAttribute("CvAdresse", cvAdresse);
		mysession.setAttribute("CvVille", cvVille);
		mysession.setAttribute("CvTel", cvPortable);
		mysession.setAttribute("CvCp", cvCp);
		mysession.setAttribute("CvPays", cvPays);
		mysession.setAttribute("CvContact", cvContact);
		mysession.setAttribute("CvBirthDay", birthDay);
		mysession
				.setAttribute("CvSituation", request.getParameter("situation"));
		mysession.setAttribute("CvSexe", request.getParameter("sexe"));

		ActionErrors errors = new ActionErrors();
		int erreur = 0;

		if ("".equals(cvTitle)) {
			errors.add("CvTitle", new ActionMessage("error.titre"));
			saveErrors(request, errors);
			erreur = 1;
		}

		if ("".equals(cvPortable)) {
			errors.add("CvTel", new ActionMessage("error.portable"));
			saveErrors(request, errors);
			erreur = 1;
		}
		
		if ("".equals(cvNom)) {
			errors.add("CvNom", new ActionMessage("error.nom"));
			saveErrors(request, errors);
			erreur = 1;
		}
		
		if ("".equals(cvPrenom)) {
			errors.add("CvPrenom", new ActionMessage("error.prenom"));
			saveErrors(request, errors);
			erreur = 1;
		}
		
		if ("".equals(cvAdresse)) {
			errors.add("CvAdresse", new ActionMessage("error.adresse"));
			saveErrors(request, errors);
			erreur = 1;
		}
		
		if ("".equals(cvVille)) {
			errors.add("CvVille", new ActionMessage("error.ville"));
			saveErrors(request, errors);
			erreur = 1;
		}
		if ("".equals(birthDay)) {
			errors.add("CvBirthDay", new ActionMessage("error.birthDay"));
			saveErrors(request, errors);
			erreur = 1;
		}

		if ("".equals(cvPays)) {
			errors.add("CvPays", new ActionMessage("error.pays"));
			saveErrors(request, errors);
			erreur = 1;
		}
		
		if ("".equals(cvContact)) {
			errors.add("CvContact", new ActionMessage("error.contact"));
			saveErrors(request, errors);
			erreur = 1;
		}
		
		if ("".equals(cvCp)) {
			errors.add("CvCp", new ActionMessage("error.cp"));
			saveErrors(request, errors);
			erreur = 1;
		}
		
		if (!"".equals(cvCp)) {
			try {
				Integer.parseInt(cvCp);
			} catch (Exception e) {
				errors.add("CvCp", new ActionMessage("error.cpInt"));
				saveErrors(request, errors);
				erreur = 1;
			}
		}
		
		try {
			toDBDateFormat(birthDay);
		} catch (Exception e) {
			errors.add("CvBirthDay", new ActionMessage(
					"error.birthDayValue"));
			saveErrors(request, errors);
			erreur = 1;
		}

		if (erreur == 1) {
			return mapping.findForward(UNAUTHORIZED_ACTION_NAME);
		} else {
			return mapping.findForward(SUCCESS_ACTION_NAME);
		}
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	public ActionForward displayExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		try {
			int nbExp = Integer.parseInt(request.getParameter("nbexp"));
			int nbfrom = Integer.parseInt(request.getParameter("nbform"));
			int nblangue = Integer.parseInt(request.getParameter("nblangue"));
			int nbloisir = Integer.parseInt(request.getParameter("nbloisir"));
			int nbdip = Integer.parseInt(request.getParameter("nbdip"));

			Curriculum curriculum = new Curriculum();

			EntityManager em = PersistenceProvider.createEntityManager();

			HttpSession mysession = request.getSession();

			// Insert Membe
			MemberCV member = new MemberCV();
			try {
				member.setBirthDate((Date) toDBDateFormat((String) mysession
						.getAttribute("CvBirthDay")));
			} catch (Exception e) {

			}
			
			member.setTown((String) mysession.getAttribute("CvPays"));
			member.setAdress((String) mysession.getAttribute("CvAdresse"));
			member.setFirstName((String) mysession.getAttribute("CvNom"));
			member.setMail((String) mysession.getAttribute("CvContact"));
			member.setNumberPhone((String) mysession.getAttribute("CvTel"));
			member.setSurname((String) mysession.getAttribute("CvPrenom"));
			member.setPostCode(Integer.parseInt((String) mysession
					.getAttribute("CvCp")));
			member.setSituationFamilly((String) mysession
					.getAttribute("CvSituation"));
			member.setSex((String) mysession.getAttribute("CvSexe"));
			int lang = 0;
			HashMap<String, String> languages = new HashMap<String, String>();
			while (lang < nblangue) {

				String cVLangue = request.getParameter("CVLangue" + lang);
				String niveaux = request.getParameter("niveaux" + lang);
				languages.put(cVLangue, niveaux);
				lang++;
			}
			member.setLanguages(languages);
			em.getTransaction().begin();
			em.persist(member);
			curriculum.setMember(member);
			curriculum.setTitleCv((String) mysession.getAttribute("CvTitle"));

			// Insert experiences
			int i = 0;

			while (i < nbExp) {
				TrainingCV training = new TrainingCV();

				AssociationDateTrainingCV dateTaining = new AssociationDateTrainingCV();
				EstablishmentCV etab = new EstablishmentCV();
				String nomEntreprise = request
						.getParameter("NomEntreprise" + i);

				String cvPoste = request.getParameter("CvPoste" + i);
				try {
					Date expBeginDate = toDBDateFormat(request
							.getParameter("expBeginDate" + i));
					Date expEndDate = toDBDateFormat(request
							.getParameter("expEndDate" + i));

					dateTaining.setStartDate(expBeginDate);
					dateTaining.setEndDate(expEndDate);
				} catch (Exception e) {

				}
				String cvSecteur = request.getParameter("CvSecteur" + i);
				etab.setName(nomEntreprise);
				etab.setLand("CvPaysExp" + i);
				etab.setTown("CvVilleExp" + i);

				training.setName(cvPoste);
				training.setSpeciality(cvSecteur);

				training.getAssociationDateTrainingCV().add(dateTaining);
				training.setAssociationDateTrainingCV(training
						.getAssociationDateTrainingCV());
				training.setMyEst(etab);

				curriculum.getTrains().add(dateTaining);
				curriculum.setTrains(curriculum.getTrains());

				dateTaining.setCurriculum(curriculum);
				dateTaining.setTraining(training);
				em.persist(dateTaining);

				em.persist(training);
				em.persist(etab);

				i++;
			}

			// Insert formation
			int f = 0;
			while (f < nbfrom) {

				AssociationDateFormationCV dateFormation = new AssociationDateFormationCV();
				FormationCV formation = new FormationCV();
				EstablishmentCV etab = new EstablishmentCV();
				String cvFormation = request.getParameter("CvFormation" + f);
				String cvEtablissmentform = request
						.getParameter("CvEtablissmentform" + f);
				String cvFormPays = request.getParameter("CvFormPays" + f);
				try {
					Date dateObtention = toDBDateFormat(request
							.getParameter("DateObtention" + f));
					dateFormation.setObtainedDate(dateObtention);
				} catch (Exception e) {

				}
				String cvFormVille = request.getParameter("CvFormVille" + f);

				etab.setName(cvEtablissmentform);
				etab.setTown(cvFormVille);
				etab.setLand(cvFormPays);

				formation.getAssociationDateFormationCV().add(dateFormation);
				formation.setAssociationDateFormationCV(formation
						.getAssociationDateFormationCV());
				formation.setName(cvFormation);
				formation.setEts(etab);
				curriculum.getMyFormations().add(dateFormation);
				curriculum.setMyFormations(curriculum.getMyFormations());
				dateFormation.setIdCurriculum(curriculum);
				dateFormation.setIdFormation(formation);

				em.persist(dateFormation);
				em.persist(formation);
				em.persist(etab);

				f++;
			}

			// Insert degree
			int d = 0;
			while (d < nbdip) {
				DegreeCV degree = new DegreeCV();
				AssociationDateDegreeCV dateDegreeCV = new AssociationDateDegreeCV();
				EstablishmentCV etabCv = new EstablishmentCV();
				String cvEtude = request.getParameter("CvEtude" + d);
				String cvEtudeDom = request.getParameter("CvEtudeDom" + d);
				String cvEtablissment = request.getParameter("CvEtablissment"
						+ d);
				String cvEtudePays = request.getParameter("CvEtudePays" + d);
				String cvEtudeVille = request.getParameter("CvEtudeVille" + d);
				try {
					Date etudBeginDate = toDBDateFormat(request
							.getParameter("etudBeginDate" + d));
					Date etudEndDate = toDBDateFormat(request
							.getParameter("etudEndDate" + d));

					dateDegreeCV.setStartDate(etudBeginDate);
					dateDegreeCV.setEndDate(etudEndDate);
				} catch (Exception e) {

				}
				degree.setStudiesLevel(cvEtude);
				degree.setDomain(cvEtudeDom);
				etabCv.setName(cvEtablissment);
				etabCv.setTown(cvEtudeVille);
				etabCv.setLand(cvEtudePays);

				degree.getAssociationDateDegreeCV().add(dateDegreeCV);
				degree.setAssociationDateDegreeCV(degree
						.getAssociationDateDegreeCV());
				degree.setEts(etabCv);
				curriculum.getDegs().add(dateDegreeCV);
				curriculum.setDegs(curriculum.getDegs());
				dateDegreeCV.setCurriculum(curriculum);
				dateDegreeCV.setDegree(degree);

				em.persist(dateDegreeCV);
				em.persist(degree);
				em.persist(etabCv);

				d++;
			}

			int l = 0;
			while (l < nbloisir) {
				HobbiesCV loisir = new HobbiesCV();
				String cvNomLoisir = request.getParameter("CvNomLoisir" + l);
				loisir.setName(cvNomLoisir);
				em.persist(loisir);
				curriculum.getHobs().add(loisir);
				curriculum.setHobs(curriculum.getHobs());
				l++;
			}

			em.persist(curriculum);

			em.getTransaction().commit();
			em.close();

			//

			return mapping.findForward(SUCCESS_ACTION_NAME);
		} catch (NumberFormatException e) {
			return mapping.findForward(SUCCESS_ACTION_NAME);
		}
	}

	/**
	 * @param request
	 */
	private void addRightToRequest(HttpServletRequest request) {
		SocialEntity socialEntity = UserUtils.getAuthenticatedUser(request);
		Right rightAddEvent = Right.ADD_EVENT;
		Right rightRegisterEvent = Right.REGISTER_EVENT;
		request.setAttribute("rightAddEvent", rightAddEvent);
		request.setAttribute("rightRegisterEvent", rightRegisterEvent);
		request.setAttribute("socialEntity", socialEntity);
	}

	/**
	 * @param request
	 * @param em
	 */
	public static final void refreshNumNewEvents(HttpServletRequest request,
			EntityManager em) {
		HttpSession session = request.getSession();
		SocialEntity user = UserUtils.getAuthenticatedUser(request, em);
		InteractionFacade inf = new InteractionFacade(em);
		List<Interaction> list = inf.getUnreadInteractionsForSocialEntity(user);
		int numNonReedEvents = Interaction.filter(list, Meeting.class).size();
		session.setAttribute("numNonReedEvents", numNonReedEvents);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward displayCv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		EntityManager em = PersistenceProvider.createEntityManager();
		addRightToRequest(request);

		em.getTransaction().begin();

		CvFacade cvfacade = new CvFacade(em);
		List<Curriculum> result = cvfacade.listAllCv();

		em.close();

		request.setAttribute("CVsList", result);

		return mapping.findForward(SUCCESS_ACTION_NAME);
	}
}
