package peumconf.springbootapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPERATORS")
public class Operator {

	@Id
	private String id;

	@Column(name = "OPERATOR_NAME")
	private String name;

	public Operator() {

	}

	public Operator(String name) {
		super();
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
