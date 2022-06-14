package poly.edu.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
@NamedQueries({
	@NamedQuery(
			name = "Size.findByIdProduct",
			query = "SELECT s FROM Size s WHERE s.product.id = ?1"
			)
})
@Data
@Entity
@Table(name = "Size", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ProductId", "Size"})
})
public class Size implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Product product;

    @Column(name = "Size")
    private String size;
}
