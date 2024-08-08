package com.m4technology.busvans.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.m4technology.busvans.domain.enums.PerfilEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String nome;
    private String senha;
    @Enumerated(EnumType.STRING)
    private PerfilEnum perfil;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ID_VEICULO")
    private Veiculo veiculo;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ID_EMPRESA")
    private Empresa empresa;
    @CreationTimestamp
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;
    @Transient
    private Long idVeiculo;
    @Transient
    private Long idEmpresa;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
