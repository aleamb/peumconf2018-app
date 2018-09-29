package peumconf.springbootapp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INCIDENCES")
public class Incidence {

	public static String[] types = { "INFO", "ERROR", "WARNING" };

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "INCIDENCE_TITLE")
	private String title;

	@ManyToOne
	@JoinColumn(name = "OPERATOR_ID")
	private Operator operator;

	@Column(name = "INCIDENCE_START")
	private LocalDateTime start;

	@Column(name = "INCIDENCE_TYPE")
	private String type;

	public Incidence() {

	}

	public Incidence(String title, Operator operator, LocalDateTime start, String type) {
		super();
		this.title = title;
		this.operator = operator;
		this.start = start;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
