package com.gusSystem.crud.api.service;

import com.gusSystem.crud.api.documents.Client;
import com.gusSystem.crud.api.documents.Telephone;
import com.gusSystem.crud.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client findById(String id) {
        return this.clientRepository.findById(id).get();
    }

    @Override
    public Client create(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public void remove(String id) {
         this.clientRepository.deleteById(id);
    }

    @Override
    public List<Telephone> findByTelephonesClientId(String id) {

        Query find = new Query( Criteria.where("id").is(id) );  //To find matching documents
        find.fields().include("telephones");

        return mongoTemplate.find(find, Telephone.class);
    }
}
