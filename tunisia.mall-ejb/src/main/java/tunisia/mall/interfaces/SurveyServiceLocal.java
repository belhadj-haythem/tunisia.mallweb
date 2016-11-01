package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Local;

import tunisia.mall.persistance.Survey;

@Local
public interface SurveyServiceLocal {
	public void addSurvey(Survey n);
	public List<Survey> list();
	public List<String> listr1();
	public List<String> listr2();
	public List<String> listr3();
	public List<String> listr4();
	public List<String> listr5();
	public List<String> listr6();
}
