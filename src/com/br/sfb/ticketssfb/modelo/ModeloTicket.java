package com.br.sfb.ticketssfb.modelo;

import com.br.sfb.ticketssfb.enumerator.Atribuicoes;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;

import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.type.LobType;


@Entity
@NamedQueries({
    @NamedQuery(name = "ModeloTicket.getAtribuicao", query = "SELECT l FROM ModeloTicket l WHERE l.atribuicao=:atribuicao"),
     @NamedQuery(name = "ModeloTicket.getTicketUsuario", query = "SELECT l FROM ModeloTicket as l inner join l.usuario as usuario WHERE l.atribuicao=:atribuicao and usuario.codigo =:codigo"),
      @NamedQuery(name = "ModeloTicket.getTicketRotulo", query = "SELECT l FROM ModeloTicket as l inner join l.time as time WHERE l.atribuicao=:atribuicao and time.codigo =:codigo"),
       @NamedQuery(name = "ModeloTicket.getTicketTime", query = "SELECT l FROM ModeloTicket as l inner join l.rotulo as rotulo WHERE l.atribuicao=:atribuicao and rotulo.codigo =:codigo"),
})        
@Table(name="ticket")
public class ModeloTicket implements Serializable, IModelo<Long>{
 /**
 * 
*/
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long codigo;
@Column
private String remetente; 
@Column
private String destinatarios; 
@Column
private String assunto; 
@Column(length = 100000)
private String conteudo; 
@Column
private Date dataDeEnvio; 
@Column
@Enumerated(EnumType.STRING)
private Atribuicoes atribuicao;
 
  @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinTable(name="ticket_time", joinColumns={@JoinColumn(name="email_codigo", nullable=false, updatable=true)}, inverseJoinColumns={@JoinColumn(name="time_codigo", nullable=false, updatable=true)})
  Set<ModeloTime> time = new HashSet<>(); 
  
  @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinTable(name="ticket_usuario", joinColumns={@JoinColumn(name="email_codigo", nullable=false, updatable=true)}, inverseJoinColumns={@JoinColumn(name="usuario_codigo", nullable=false, updatable=true)})
  Set<ModeloUsuario> usuario = new HashSet<>();
  
  @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinTable(name="ticket_rotulo", joinColumns={@JoinColumn(name="email_codigo", nullable=false, updatable=true)}, inverseJoinColumns={@JoinColumn(name="rotulo_codigo", nullable=false, updatable=true)})
  Set<ModeloUsuario> rotulo = new HashSet<>();
 
  
  
@Override
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataDeEnvio() {
        return dataDeEnvio;
    }

    public void setDataDeEnvio(Date dataDeEnvio) {
        this.dataDeEnvio = dataDeEnvio;
    }

    public Atribuicoes getAtribuicao() {
        return atribuicao;
    }

    public void setAtribuicao(Atribuicoes atribuicao) {
        this.atribuicao = atribuicao;
    }

    public Set<ModeloTime> getTime() {
        return time;
    }

    public void setTimes(Set<ModeloTime> times) {
        this.time = times;
    }

    public Set<ModeloUsuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(Set<ModeloUsuario> usuario) {
        this.usuario = usuario;
    }

    public Set<ModeloUsuario> getRotulo() {
        return rotulo;
    }

    public void setRotulo(Set<ModeloUsuario> rotulo) {
        this.rotulo = rotulo;
    }
}