package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Local;

import tunisia.mall.persistance.SubCategorie;

@Local
public interface ScategorieServiceLocal {
	public boolean addSubCategorie(SubCategorie s);
	public boolean updateSubCategorie(SubCategorie s);
	public boolean removeSubCategorie(SubCategorie s);
	public List<SubCategorie> listSubCategorie();
	public SubCategorie findSubCategorieByName(String name);
	public List<SubCategorie> listSubCategorieByCategorie(String categorieName);
	public SubCategorie findSubCategorieById(int id);
}
