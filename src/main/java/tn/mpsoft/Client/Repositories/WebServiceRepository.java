package tn.mpsoft.Client.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.mpsoft.Client.Modele.WebServiceEntity;


public interface WebServiceRepository extends JpaRepository<WebServiceEntity, Integer> {

   @Query( "select  CONCAT('http://', host ,':' , port ,'/' , GROUP_CONCAT( param ) )"
   		+ " from WebServiceEntity ws , Paths p , Controllers c "
   		+ " where (ws.id = :idws) and (p.id = :idp and webservices_id=:idws ) and (path_id=:idp) Group By path_id" )
    String getWebService(@Param("idws") int idws , @Param("idp") int idp);
  
    
    
    
    
}
