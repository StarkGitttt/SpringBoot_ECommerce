package poly.edu.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQueries({
    @NamedQuery(
        name = "Product.countLikeOfProduct", 
        query = "SELECT new Report(f.product, COUNT(f.product)) FROM Favorite f " +
        "WHERE f.product.available = TRUE AND f.isLike = TRUE " +
        "GROUP BY f.product"
    ),
    @NamedQuery(
    			name = "Favorite.LikeByUserId",
    			query = "SELECT f FROM Favorite f WHERE f.account.username = ?1 AND f.isLike = TRUE"
    ),
    @NamedQuery(
    			name = "Favorite.FavByUserIdAndProductId",
    			query = "SELECT f FROM Favorite f WHERE f.account.username = ?1"
    					+ " AND f.product.id = ?2"
    		),
    @NamedQuery(
    			name = "Favorite.findCategoryByUserId",
    			query = "SELECT DISTINCT f.product.category FROM Favorite f "
    					+ "WHERE f.account.username = ?1 AND f.product.available =TRUE"
    					+ " AND f.isLike = TRUE"
    		),
    @NamedQuery(
    			name = "Favorite.findProductByCIAndUserId",
    			query = "SELECT f.product FROM Favorite f "
    					+ "WHERE f.account.username = ?1 AND f.product.category = ?2 "
    					+ "AND f.product.available =TRUE AND f.isLike = TRUE"
    		)

})

@Data
@NoArgsConstructor
@Entity
@Table(name = "Favorites")
public class Favorite implements Serializable {
    private static final long serialVersionUID = 1L;
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFav")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "Username")
    private Account account;

    @Temporal(TemporalType.DATE)
    @Column(name = "DateLike", columnDefinition = "DATE DEFAULT GETDATE()")
    private Date dateLike;

    @Column(name = "IsLike", columnDefinition = "BIT DEFAULT 1")
    private Boolean isLike;
    
    public Favorite(Account account, Product product, Date dateLike, Boolean isLike) {
    	this.account = account;
    	this.product = product;
    	this.dateLike = dateLike;
    	this.isLike = isLike;
    }
}
