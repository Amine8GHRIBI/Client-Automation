package tn.mpsoft.Client.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.mpsoft.Client.Modele.Paths;
import tn.mpsoft.Client.Modele.RequestParametre;
import tn.mpsoft.Client.Modele.WebServiceEntity;
import tn.mpsoft.Client.Repositories.PathsRepository;
import tn.mpsoft.Client.Repositories.WebServiceRepository;
import tn.mpsoft.Client.service.invoke.RestClientService;

@RestController
@RequestMapping("/api")
public class WebServiceController {
	
	@Autowired
	private WebServiceRepository wsRepos;
	
	@Autowired 
	private PathsRepository pathRepos;

	@Autowired
	private RestClientService service;

	
	
	
	@PostMapping("/add")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

	public ResponseEntity<WebServiceEntity> ajouterWebService(@RequestBody WebServiceEntity requestws) {

	   	WebServiceEntity ws = service.ajouterWebService(requestws);
    	 return new ResponseEntity (ws ,  HttpStatus.OK);
		
	 }
	//consume ws
	@PostMapping("/get/{wsid}/{pathid}")
	public ResponseEntity<Object> ConsummeGet(@RequestBody List<RequestParametre> request , @PathVariable("wsid") int wsid , @PathVariable("pathid") int pathid){
		Map<String, Object> params = new HashMap<>();
		for ( RequestParametre p : request) {
			params.put( p.getNom() , p.getValeur());
		}
	// for GET 	
		
		Object ob = service.Get(params , wsid  , pathid );
		
		return new ResponseEntity(ob ,HttpStatus.OK );

	}
	//test 
	/*@GetMapping("get/test/{var1}/{var2}")
	public 	Object get ( @PathVariable("var1") int var1 , @PathVariable("var2") int var2) {
		return service.GetService(var1 , var2); 
		//pc.paths_id= idp , paths_controllers pc
	}*/

}
