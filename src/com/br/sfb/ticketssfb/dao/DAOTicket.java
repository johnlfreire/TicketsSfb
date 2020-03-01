package com.br.sfb.ticketssfb.dao;

import com.br.sfb.ticketssfb.enumerator.Atribuicoes;
import com.br.sfb.ticketssfb.modelo.IModelo;
import com.br.sfb.ticketssfb.modelo.ModeloTicket;
import java.util.List;

public class DAOTicket extends DAOGenerico {
    @Override
    public void adicionar(IModelo m){
        super.adicionar(m);
    }
 
     public List<ModeloTicket> pesquisarAtribuicao(Atribuicoes atribuicao) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloTicket> emails = session.getNamedQuery("ModeloTicket.getAtribuicao")
                    .setParameter("atribuicao",atribuicao).list();
            getSession().getTransaction().commit();
            return emails;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
     
     public List<ModeloTicket> pesquisarticketUsuario(Atribuicoes atribuicao,Long codigo) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloTicket> emails = session.getNamedQuery("ModeloTicket.getTicketUsuario")
                    .setParameter("atribuicao",atribuicao).setParameter("codigo", codigo).list();
            getSession().getTransaction().commit();
            return emails;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
     
     public List<ModeloTicket> pesquisarTicketTime(Atribuicoes atribuicao,Long codigo)  {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloTicket> emails = session.getNamedQuery("ModeloTicket.getTicketTime")
                    .setParameter("atribuicao",atribuicao).setParameter("codigo", codigo).list();
            getSession().getTransaction().commit();
            return emails;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
     
     public List<ModeloTicket> pesquisarTicketRotulo(Atribuicoes atribuicao,Long codigo)  {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloTicket> emails = session.getNamedQuery("ModeloTicket.getTicketRotulo")
                    .setParameter("atribuicao",atribuicao).setParameter("codigo", codigo).list();
            getSession().getTransaction().commit();
            return emails;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
}
