package tunisia.mall.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tunisia.mall.interfaces.SurveyServiceLocal;
import tunisia.mall.interfaces.SurveyServiceRemote;
import tunisia.mall.persistance.News;
import tunisia.mall.persistance.Survey;

/**
 * Session Bean implementation class SurveyService
 */
@Stateless
@LocalBean
public class SurveyService implements SurveyServiceRemote, SurveyServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext(name = "ejb-sample")
	EntityManager em;
    public SurveyService() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public void addSurvey(Survey n) {
		// TODO Auto-generated method stub
		em.persist(n);
		
	}
	@Override
	public List<Survey> list() {
		return em.createQuery("select n from Survey n").getResultList();
	}
	@Override
	public List<String> listr1() {
		return em.createQuery("select n.r1 from Survey n").getResultList();
		
	}
	@Override
	public List<String> listr2() {
		return em.createQuery("select n.r2 from Survey n").getResultList();
	}
	@Override
	public List<String> listr3() {
		return em.createQuery("select n.r3 from Survey n").getResultList();
	}
	@Override
	public List<String> listr4() {
		return em.createQuery("select n.r4 from Survey n").getResultList();
	}
	@Override
	public List<String> listr5() {
		return em.createQuery("select n.r5 from Survey n").getResultList();
	}
	@Override
	public List<String> listr6() {
		return em.createQuery("select n.r6 from Survey n").getResultList();
	}
	

}
