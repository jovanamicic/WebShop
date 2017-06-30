package drools.spring.example.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@DiscriminatorValue("SELLER")
public class Seller extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Seller(){
		super();
	}

}
