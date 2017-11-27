package me.afua.thymeleafsecdemo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String role;

    @ManyToMany(mappedBy = "roles",fetch=FetchType.LAZY)
    private Set<UserData> users;

    public UserRole(String role) {
        this.role = role;
    }

    public UserRole() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<UserData> getUsers() {
        return users;
    }

    public void setUsers(Set<UserData> users) {
        this.users = users;
    }
}
