package fr.univartois.ili.fsnet.facade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.fsnet.entities.Curriculum;


/**
 * 
 * @author Ayoub AICH
 *
 */


public class CvFacade {
	
	 private final EntityManager em;
	
	 public CvFacade(EntityManager em) {
	        this.em = em;
	    }
	 
	/**
	 * List all the resumes
	 * @return List<Curriculum> the list containing all the resumes
	 */
	public final List<Curriculum> listAllCv(int id) {
		List<Curriculum> results;
		final TypedQuery<Curriculum> query;
		query = em
		.createQuery(
				"SELECT e FROM Curriculum e WHERE e.userId= :idC",
				Curriculum.class); 
		query.setParameter("idC",id);
		results=query.getResultList();
		return results;
	}
	
	/**
    *
    * @param curriculumId
    * @return
    */
   public final Curriculum getCurriculum(long curriculumId) {
       return em.find(Curriculum.class, curriculumId);
   }
   
   
}
