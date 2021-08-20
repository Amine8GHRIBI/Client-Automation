package tn.mpsoft.Client.Modele;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Paths {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String methode ;
	
    @OneToMany(mappedBy="path" ,cascade = CascadeType.ALL )
	private List<Controllers> controllers;
	
    
	@ManyToOne
	@JsonIgnore
	private WebServiceEntity webservices;
     
}
