package peumconf.springbootapp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INCIDENCES")
public class Incidence {

	public final static String[] types = { "INFO", "ERROR", "WARNING" };

	@Id
	@Column(name = "INCIDENCE_ID")
	private String id;

	@Column(name = "INCIDENCE_TITLE")
	private String title;

	@ManyToOne
	@JoinColumn(name = "OPERATOR_ID")
	private Operator operator;

	@Column(name = "INCIDENCE_START")
	private LocalDateTime start;

	@Column(name = "INCIDENCE_TYPE")
	private String type;

	@Column(name = "RESOLVED")
	private boolean resolved;

	public Incidence() {

	}

	public Incidence(String title, Operator operator, LocalDateTime start, String type) {
		super();
		this.title = title;
		this.operator = operator;
		this.start = start;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

}
