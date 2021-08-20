package tn.mpsoft.Client.service.invoke;

import java.util.List;



import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import tn.mpsoft.Client.Modele.Controllers;
import tn.mpsoft.Client.Modele.Paths;
import tn.mpsoft.Client.Modele.WebServiceEntity;
import tn.mpsoft.Client.Repositories.ControllersRepository;
import tn.mpsoft.Client.Repositories.PathsRepository;
import tn.mpsoft.Client.Repositories.WebServiceRepository;


@Service
public class RestClientService {

	@Autowired
	private WebServiceRepository wsRepos;
	
	@Autowired 
	private PathsRepository pathRepos;
	
	@Autowired
	private ControllersRepository conRepos;

	
	private RestTemplate restTemplate = new RestTemplate();
   
	Logger logger = LoggerFactory.getLogger(RestClientService.class);


    

	// AJOUTER WS 
	public WebServiceEntity ajouterWebService( WebServiceEntity requestws){
		
		WebServiceEntity wsInBase = wsRepos.save(requestws);
		List<Paths> pathInBase = wsInBase.getPaths();
		
		
		for( Paths  p : pathInBase) {
			p.setWebservices(wsInBase);
			Paths newpath = pathRepos.save(p);
			for ( Controllers c : p.getControllers()) {
				
				 c.setPath(newpath);
				 
				 conRepos.save(c);
				
			}

		}
		
	 return requestws; 
		
	 }
	
	public List<WebServiceEntity> getAllWs(){
		List<WebServiceEntity> ws = wsRepos.findAll();
		
		return ws;
	}
	
	public WebServiceEntity getWs(int id) {
		Optional<WebServiceEntity> ws = wsRepos.findById(id);
		return ws.get();
		
	}
	
	
	/*
	public Object Geturl() {
		  
	   	 String URL = "http://localhost:8080/api/get";

		// call of the service
		
		   Object response = restTemplate.getForObject(URL, Object.class );
			
			return response;
	}
	*/
	/*
	public Object GetService( int var1 , int var2 ) {
		  String URL2 =  wsRepos.getWebService(var1,var2);
	        URL2 = URL2.replace(",", "/"); 

		  logger.warn("This is INFO"+ URL2);
		   System.out.println(URL2);
		// call of the service
		
		   Object response = restTemplate.getForObject(URL2, Object.class );
			
			return response;
	}*/
	
	public Object Get(  Map<String, Object> vars , int wsid , int pathid ) {
		  String URL2 =  wsRepos.getWebService(wsid, pathid);
	        URL2 = URL2.replace(",", "/"); 

		  logger.warn("This is INFO"+ URL2);
		   System.out.println(URL2);
		// call of the service
	    Optional<Paths> path = pathRepos.findById(pathid);
	    
	    Object response = restTemplate.getForObject(URL2, Object.class ,  vars);
	    
	    /*
	    if(path.get().equals("GET")){
			Object response = restTemplate.getForObject(URL2, Object.class ,  vars);

	    	
	    }
	    else if(path.get().equals("POST")) {
			Object response = restTemplate.postForObject(URL, request, Object.class , vars);
	
	    }*/
        		
		return response;
	}
	
	public Object Post(Object request ) {
		// call of the service
	 String URL = "http://localhost:8080/api/get-status";

		Object response = restTemplate.postForObject(URL, request, Object.class );
		
		return response;
	}

	public Object Delete ( Object request , Map<String, String> vars) {
		// call of the service
			String	URL = "http://localhost:8080/api/get-status";
		Object response = restTemplate.postForObject(URL, request, Object.class , vars);
		
		return response;

	}
	
    public Object Put ( Object request , Map<String, String> vars) {
    	// call of the service
           String	URL = "http://localhost:8080/api/get-status";
		Object response = restTemplate.postForObject(URL, request, Object.class , vars);
		
		return response;

	}

	
	
	
}
