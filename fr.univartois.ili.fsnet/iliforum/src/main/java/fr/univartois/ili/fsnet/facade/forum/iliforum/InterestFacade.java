package fr.univartois.ili.fsnet.facade.forum.iliforum;

import java.util.List;

import javax.persistence.EntityManager;

import fr.univartois.ili.fsnet.entities.Interest;

/**
 *
 * @author BEN ABDESSALEM Mehdi <mehdi.benabdessalem at gmail.com>
 */
public class InterestFacade {

	private final EntityManager em;

	public InterestFacade(EntityManager em){
		this.em = em;
	}

	/**
	 * create a new interest
	 * @param interestName the name of the interest
	 * @return the interest created
	 */
	public final Interest createInterest(String interestName){
		if(interestName == null) throw new IllegalArgumentException();
		Interest interest = new Interest(interestName);
		em.persist(interest);
		return interest;
	}

	/**
	 * 
	 * @param interestId the id of the interest we search
	 * @return the interest we search
	 */
	public final Interest getInterest(int interestId){
		return em.find(Interest.class, interestId);
	}

	/**
	 * modify an interest name
	 * @param interestName the new interest name
	 * @param interest the interest to modify
	 */
	public final void modifyInterest(String interestName, Interest interest){
        if(interestName == null || interest == null) throw new IllegalArgumentException();
		interest.setName(interestName);	
	}

	/**
	 * delete an interest 
	 * @param the interest to delete
	 */
	public final void deleteInterest(Interest interest){
		if(interest == null) throw new IllegalArgumentException();
		em.remove(interest);
		em.flush();
	}

	/**
	 * 
	 * @param interestName the name of the interest we search
	 * @return the list of interests having name like interestName
	 */
	public final List<Interest> searchInterest(String interestName){
		if(interestName == null) throw new IllegalArgumentException();
		List<Interest> result = em.createQuery(
				"SELECT interest FROM Interest interest "
				+ "WHERE interest.name LIKE :interestName ",
				Interest.class).setParameter("interestName",
						'%' + interestName + '%').getResultList();
		return result;
	}
	
	/**
	 * 
	 * @param interestName the name of the interest we search
	 * @param begin point of beginning for the research
	 * @param number how many by result
	 * @return the list of interests having name like interestName
	 * 
	 * @author Alexandre Lohez <alexandre.lohez at gmail.com>
	 */
	public final List<Interest> advancedSearchInterest(String interestName, int begin, int number){
		List<Interest> result = em.createQuery(
				"SELECT interest FROM Interest interest "
				+ "WHERE interest.name LIKE :interestName ",
				Interest.class).setParameter("interestName",
						'%' + interestName + '%').setFirstResult(begin).setMaxResults(number).getResultList();
		return result;
	}

	/**
	 * 
	 * @return the list of all interests
	 */
	public final List<Interest> getInterests(){
		List<Interest> listAllInterests = em.createQuery(
				"SELECT interest FROM Interest interest", Interest.class).getResultList();
		return listAllInterests;
	}
	
	

}
