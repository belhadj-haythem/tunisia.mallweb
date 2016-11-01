package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Local;

import tunisia.mall.persistance.ProductCategorie;

@Local
public interface CategorieLocal {
	public boolean addCategorie(ProductCategorie c);
	public boolean deleteCategorie(ProductCategorie c);
	public ProductCategorie findCategorie(int id);
	public List<ProductCategorie> listCategorie();
	public ProductCategorie findCategorieByName(String nom);
}
