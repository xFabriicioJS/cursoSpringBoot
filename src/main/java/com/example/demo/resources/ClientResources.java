
package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Client;
import com.example.demo.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResources {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>>findAll(){
        List<Client> lista = clientService.findAll();
        
        return ResponseEntity.ok().body(lista);
        
    }

    @GetMapping(value = "/{id}")
        public ResponseEntity<Client> findById(@PathVariable Long id){
            Client client = clientService.findById(id);
            return ResponseEntity.ok().body(client);

        }
}
