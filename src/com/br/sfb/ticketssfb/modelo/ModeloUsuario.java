/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author johnpc
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ModeloUsuario.getUsuario", query = "SELECT l FROM ModeloUsuario l WHERE l.email=:email"),
    })             
@Table(name="usuario")
public class ModeloUsuario implements Serializable, IModelo<Long>{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long codigo;

@Column
String nome;
@Column
String email;
@Column
String password;
@Column
String assinatura;  
@Column(columnDefinition="blob")
private byte[] avatar;

@ManyToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
Set<ModeloTicket> ticket = new HashSet<>();

@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
@JoinTable(name="usuario_time", joinColumns={@JoinColumn(name="usuario_codigo", nullable=false, updatable=true)}, inverseJoinColumns={@JoinColumn(name="time_codigo", nullable=false, updatable=true)})
Set<ModeloTime> time = new HashSet<>(); 

@Override
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }



    public Set<ModeloTime> getTime() {
        return time;
    }

    public void setTime(Set<ModeloTime> time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ModeloTicket> getTicket() {
        return ticket;
    }

    public void setTicket(Set<ModeloTicket> ticket) {
        this.ticket = ticket;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }


        
}
