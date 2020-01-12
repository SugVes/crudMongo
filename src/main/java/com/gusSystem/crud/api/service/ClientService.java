package com.gusSystem.crud.api.service;

import com.gusSystem.crud.api.documents.Client;
import com.gusSystem.crud.api.documents.Telephone;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findById(String id);

    Client create(Client client);

    Client update(Client client);

    void remove(String id);

    List<Telephone> findByTelephonesClientId(String id);
}
