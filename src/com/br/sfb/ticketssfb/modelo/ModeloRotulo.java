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
    @NamedQuery(name = "ModeloEmail.getRotulo", query = "SELECT l FROM ModeloRotulo l WHERE l.rotulo=:rotulo"),
    })    
@Table(name="rotulo")
public class ModeloRotulo implements Serializable, IModelo<Long>{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long codigo;
@Column  
private String rotulo; 

@ManyToMany(fetch = FetchType.LAZY, mappedBy = "rotulo")
private Set<ModeloTicket> ticket = new HashSet<>();


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Set<ModeloTicket> getTicket() {
        return ticket;
    }

    public void setTicket(Set<ModeloTicket> ticket) {
        this.ticket = ticket;
    }

 

 
}
