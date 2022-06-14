package poly.edu.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQuery(
		name = "Visitor.viewVisitorToday",
		query = "SELECT v FROM Visitor v WHERE v.dateVisit = ?1"
		)


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Visitor")
public class Visitor {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "DateVisit")
	@Temporal(TemporalType.DATE)
	private Date dateVisit;
	
	@ManyToOne
	@JoinColumn(name = "Username")
	private Account account;
	
	public Visitor(Date dateVisit, Account account) {
		this.dateVisit = dateVisit;
		this.account = account;
	};
}
