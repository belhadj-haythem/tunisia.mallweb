package tunisia.mall.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tunisia.mall.persistance.News;
import tunisia.mall.persistance.Shop;

@Local
public interface NewsServiceLocal {
	public boolean addNews(News n );
	public void updateNews(News n );
	public News findNews(int id );
	public void removeNews(News n );
	public List<News> list();
	public List<News> searchNewsByDate(Date d);
	public News searchNewsByName(String title);
	public List<News> searchNewsByDateOrName(String title,Date date);
	public List<News> findLastNews();
	public Shop findShop(int id);
}
