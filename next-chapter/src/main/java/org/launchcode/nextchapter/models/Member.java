package org.launchcode.nextchapter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends AbstractEntity{

    private String email;

    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @ManyToMany(mappedBy = "members")
    private List<Club> clubs = new ArrayList<>();
    //need to set up DTO, chapter 18.5

    public Member(String email, String displayName, String password) {
        this.email = email;
        this.setDisplayName(displayName);
        this.pwHash = encoder.encode(password);
    }

    public Member() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwHash() {
        return pwHash;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

   public List<Club> getClubs() {
       return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

}