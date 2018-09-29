package peumconf.springbootapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OPERATORS")
public class Operator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "OPERATOR_NAME")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "operator")
	private List<Incidence> incidences;

	
	public Operator() {
		
	}
	public Operator(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Incidence> getIncidences() {
		return incidences;
	}

	public void setIncidences(List<Incidence> incidences) {
		this.incidences = incidences;
	}

}
