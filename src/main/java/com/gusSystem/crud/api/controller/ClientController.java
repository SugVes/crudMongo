package com.gusSystem.crud.api.controller;

import com.gusSystem.crud.api.documents.Client;
import com.gusSystem.crud.api.response.Response;
import com.gusSystem.crud.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Response<List<Client>>> findAll(){
        return ResponseEntity.ok(new Response<>(clientService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Client>> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(new Response<>(clientService.findById(id)));
    }

    @PostMapping("/")
    public ResponseEntity<Response<Client>> create(@Valid @RequestBody Client client, BindingResult result){
        ResponseEntity<Response<Client>> errors = getErrors(result);
        if (errors != null) return errors;
        return ResponseEntity.ok(new Response<>(clientService.create(client)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Client>> update(@PathVariable("id") String id, @Valid @RequestBody Client client, BindingResult result){

        ResponseEntity<Response<Client>> errors = getErrors(result);
        if (errors != null) return errors;

        client.setId(id);
        return ResponseEntity.ok(new Response<>(clientService.update(client)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response<Client>> saveManager(@PathVariable("id") String id, @RequestBody Map<Object, Object> fields){
        Client client = clientService.findById(id);

       fields.forEach((k, v) ->{
           Field field = ReflectionUtils.findField(Client.class, (String) k);
           field.setAccessible(true);
           ReflectionUtils.setField(field, client, v);
               });
        return ResponseEntity.ok(new Response<>(clientService.update(client)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Integer>> remove(@PathVariable("id") String id){
         clientService.remove(id);
         return ResponseEntity.ok(new Response<>(1));
    }

    private ResponseEntity<Response<Client>> getErrors(BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<>(errors));
        }
        return null;
    }
}
