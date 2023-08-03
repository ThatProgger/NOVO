package com.novo.model.user;

import com.novo.model.employee.Employee;
import com.novo.model.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The class describes the attributes of the model as a user
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirm;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date created;

    @Column(name = "isEnabled", nullable = false)
    private boolean isEnabled;

    @Column(name = "isAccountNonExpired", nullable = false)
    private boolean isAccountNonExpired;

    @Column(name = "isAccountNonLocked", nullable = false)
    private boolean isAccountNonLocked;

    @Column(name = "isCredentialsNonExpired", nullable = false)
    private boolean isCredentialsNonExpired;


    @Column(name = "position", nullable = false)
    private String position;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Employee> employeeList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


}
