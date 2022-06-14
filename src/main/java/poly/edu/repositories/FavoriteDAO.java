package poly.edu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.models.Category;
import poly.edu.models.Favorite;
import poly.edu.models.Product;

public interface FavoriteDAO extends JpaRepository<Favorite, Integer> {
	@Query(name = "Favorite.LikeByUserId")
	List<Favorite> findByUserId(String username);
	
	@Query(name = "Favorite.FavByUserIdAndProductId")
	Favorite findByUserIdAndProductId(String username, int id);
	
	@Query(name = "Favorite.findCategoryByUserId")
	List<Category> findCategoryByUserId(String username);
	
	@Query(name = "Favorite.findProductByCIAndUserId")
	List<Product> findProductByCIAndUserId(String username, Category category);
}
