package com.example.dncinema.model;

import com.example.dncinema.model.Roles;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name_account"})
})
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_account", columnDefinition = "varchar(255)")
    private String nameAccount;
    @Column(name = "password_account", columnDefinition = "varchar(255)")
    private String passwordAccount;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Roles> roles = new HashSet<>();

    public AccountUser() {
    }

    public AccountUser(int id, String nameAccount, String passwordAccount, Set<Roles> roles) {
        this.id = id;
        this.nameAccount = nameAccount;
        this.passwordAccount = passwordAccount;
        this.roles = roles;
    }

    public AccountUser(String username, String encode) {
        this.nameAccount = username;
        this.passwordAccount = encode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "AccountUser{" +
                "id=" + id +
                ", nameAccount='" + nameAccount + '\'' +
                ", passwordAccount='" + passwordAccount + '\'' +
                ", roles=" + roles +
                '}';
    }
}
