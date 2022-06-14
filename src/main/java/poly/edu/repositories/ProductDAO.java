package poly.edu.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.edu.models.Category;
import poly.edu.models.Product;
import poly.edu.models.Report;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query(name = "Product.findProductByCategoryId")
	Page<Product> findProductByCategoryId(@Param("cid") String categoryId, Pageable pageable);
	/* Tìm kiếm sản phẩm và nhóm cản sản phẩm
	    được tìm thấy vào Category ( danh mục ) tương ứng
	*/
	@Query(name = "Category.findCategoryByProductName")
	List<Category> findCategoryByProductName(String keyword);
	
	@Query(name = "Product.findProductByCIAndPN")
	List<Product> findProductByCIAndPN(String categoryId, String keyword);
	
	@Query(name = "Product.countLikeOfProduct")
	List<Report> countLikeOfProduct();
	
	@Query(name = "Product.suggestProduct")
	List<Product> getSuggestProduct(String categoryId, int idProduct);
	
	@Query(name = "Product.getNewProducts")
	Page<Product> getNewProducts(Pageable pageable);
	
	@Query(name = "Product.findCategoryByProductsSale")
	List<Category> findCategoryByProductsSale();
	
	@Query(name = "Product.findProductsSale")
	List<Product> findProductsSale(String id);
	
	Product findByNameEquals(String name);
	
	List<Product> findByAvailableEquals(boolean isExist);
	
	@Query
	List<Category> findCategoryInProducts();
	
	@Query
	List<Product> findProductsInCategory(Category category);
	
}
