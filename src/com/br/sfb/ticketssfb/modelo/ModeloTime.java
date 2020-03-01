/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@NamedQuery(name = "ModeloEmail.getTime", query = "SELECT l FROM ModeloTime l WHERE l.emails=:emails"),
    })    
@Table(name="time")
public class ModeloTime implements Serializable, IModelo<Long>{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long codigo;
@Column
String nome;
@Column
String mail;
 
@ManyToMany(fetch = FetchType.LAZY, mappedBy = "time")
private Set<ModeloTicket> emails = new HashSet<>();


@ManyToMany(fetch = FetchType.LAZY, mappedBy = "time")
private Set<ModeloUsuario> usuario = new HashSet<>();


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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }



}
