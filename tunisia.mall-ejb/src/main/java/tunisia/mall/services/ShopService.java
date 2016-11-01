package tunisia.mall.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tunisia.mall.interfaces.ShopServiceLocal;
import tunisia.mall.interfaces.ShopServiceRemote;
import tunisia.mall.persistance.ProductCategorie;
import tunisia.mall.persistance.Shop;

/**
 * Session Bean implementation class ShopService
 */
@Stateless
@LocalBean
public class ShopService implements ShopServiceRemote, ShopServiceLocal {

	@PersistenceContext(name="ejb-sample")
    EntityManager entitymanager;
    
    public ShopService() {
        
    }
    @Override
    public boolean addShop(Shop shop){
    	shop.setEnabled(true);
    	entitymanager.persist(shop);
    	return true;
    }
    @Override
    public boolean deleteShop(Shop shop){
    	entitymanager.remove(entitymanager.merge(shop));
    	return true;
    }
    @Override
    public Shop findShopById(int id){
    	return entitymanager.find(Shop.class, id);
    }
    @Override
    public List<Shop> listShop() {
    	return entitymanager.createQuery("select c from Shop c",
				Shop.class).getResultList();
	}
    @Override
    public boolean updateShop(Shop shop){
    	entitymanager.merge(shop);
    	return true;
    }
    
    public byte[] extractBytes (String ImageName) throws IOException {
    	ByteArrayOutputStream baos = null;
    	byte[] imageInByte=null;
    	try {
    	    BufferedImage originalImage = ImageIO.read(new File(ImageName));
    	    baos = new ByteArrayOutputStream();
    	    ImageIO.write(originalImage, "jpg", baos);
    	    baos.flush();
    	    imageInByte = baos.toByteArray();
    	    
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}finally{
    	    try {
    		baos.close();
    	    } catch (IOException e) {
    		e.printStackTrace();
    	    }
    	}
    	return imageInByte;

    }
    @Override
    public Shop searchShopByName(String nameShop) {
		String requete="select i from Shop i where i.nameShop=:r";
		Shop shop=null;
		Query query=entitymanager.createQuery(requete).setParameter("r", nameShop);
		shop =(Shop) query.getSingleResult();
		return shop;
	}
    
    @Override
	public List<Shop> listCat1() {
    	List<Shop> shops = new ArrayList<Shop>();
		String requete="select i from Shop i where i.categorie.id=:r";
		Shop shop=null;
		Query query=entitymanager.createQuery(requete).setParameter("r", 1);
		shops = query.getResultList();
		return shops;
	}
    
    @Override
	public List<Shop> listCat2() {
    	List<Shop> shops = new ArrayList<Shop>();
		String requete="select i from Shop i where i.categorie.id=:r";
		Shop shop=null;
		Query query=entitymanager.createQuery(requete).setParameter("r", 2);
		shops = query.getResultList();
		return shops;
	}
    
    @Override
	public List<Shop> listCat3() {
    	List<Shop> shops = new ArrayList<Shop>();
		String requete="select i from Shop i where i.categorie.id=:r";
		Shop shop=null;
		Query query=entitymanager.createQuery(requete).setParameter("r", 3);
		shops = query.getResultList();
		return shops;
	}
    
    @Override
	public List<Shop> listCat4() {
    	List<Shop> shops = new ArrayList<Shop>();
		String requete="select i from Shop i where i.categorie.id=:r";
		Shop shop=null;
		Query query=entitymanager.createQuery(requete).setParameter("r", 4);
		shops = query.getResultList();
		return shops;
	}
    
    @Override
	public List<Shop> listCat5() {
    	List<Shop> shops = new ArrayList<Shop>();
		String requete="select i from Shop i where i.categorie.id=:r";
		Shop shop=null;
		Query query=entitymanager.createQuery(requete).setParameter("r", 5);
		shops = query.getResultList();
		return shops;
	}
    
    @Override
	public List<Shop> listCat6() {
    	List<Shop> shops = new ArrayList<Shop>();
		String requete="select i from Shop i where i.categorie.id=:r";
		Shop shop=null;
		Query query=entitymanager.createQuery(requete).setParameter("r", 6);
		shops = query.getResultList();
		return shops;
	}
    

}

