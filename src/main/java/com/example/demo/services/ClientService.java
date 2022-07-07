package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Client;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.services.exceptions.DatabaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service    
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(Long Id){
        Optional <Client> obj = clientRepository.findById(Id);

        return obj.orElseThrow(()-> new ResourceNotFoundException(Id));
    }

    public Client insert(Client obj){
        return clientRepository.save(obj);
    }

    public void delete(Long id){
        try{
        clientRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException error){
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException dataError){
            throw new DatabaseException(dataError.getMessage());
        }
        
    }

    public Client update(Long id, Client obj){

       try{ Client entity = clientRepository.getReferenceById(id);
        updateData(entity, obj);
        return clientRepository.save(entity);
       }
       catch(EntityNotFoundException error){
          
           throw new ResourceNotFoundException(id);
       }
    }

    private void updateData(Client entity, Client obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

}
