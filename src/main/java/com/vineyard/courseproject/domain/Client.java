package com.vineyard.courseproject.domain;

import com.vineyard.courseproject.hashing.PasswordHash;

import javax.persistence.*;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @NotNull
//    @Email
    @Column(unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (!email.equals(client.email)) return false;

        boolean equals = true;
        try {
            Map<String, String> hashData = new HashMap<>();
            hashData.put("salt", client.salt);
            hashData.put("hash", client.password);

            equals = PasswordHash.validatePassword(password, hashData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return equals;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    public Client() { }

    public Client(int id) {
        this.id = id;
    }
}
