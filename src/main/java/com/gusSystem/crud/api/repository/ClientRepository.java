package com.gusSystem.crud.api.repository;

import com.gusSystem.crud.api.documents.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {

//    @Query()
//    List<Object> findByTelephones(String id);
}
