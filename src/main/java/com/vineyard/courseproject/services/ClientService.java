package com.vineyard.courseproject.services;

import com.vineyard.courseproject.domain.Client;
import com.vineyard.courseproject.hashing.PasswordHash;
import com.vineyard.courseproject.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public boolean clientExists(Client client) {
        return clientRepository.findFirstByEmail(client.getEmail()) != null;
    }

    public void addClient(Client client) {

        try {
            Map<String, String> hashData = PasswordHash.createHash(client.getPassword());
            client.setPassword(hashData.get("hash"));
            client.setSalt(hashData.get("salt"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        clientRepository.save(client);
    }

    public Optional<Client> getClientByEmail(Client client) {
        return Optional.ofNullable(clientRepository.getClientByEmail(client.getEmail()));
    }
    /*
    *  public ResponseEntity<Recipes> create(
                @RequestBody Recipes recipes) {
            Recipes saved= recipesService.add(recipes);
            return new ResponseEntity<Recipes>(saved, HttpStatus.CREATED);
        }
    * */

}
