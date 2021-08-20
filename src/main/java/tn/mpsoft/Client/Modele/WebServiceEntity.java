package tn.mpsoft.Client.Modele;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class WebServiceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private String nom;
	
	private String host ;
	
	private String port ;
	
	@OneToMany(mappedBy="webservices" ,  cascade = CascadeType.ALL)
	private List<Paths> paths;

}
