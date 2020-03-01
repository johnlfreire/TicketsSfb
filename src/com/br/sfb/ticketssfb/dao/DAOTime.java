package com.br.sfb.ticketssfb.dao;

import static com.br.sfb.ticketssfb.dao.DAOGenerico.abrirSessao;
import static com.br.sfb.ticketssfb.dao.DAOGenerico.getSession;
import static com.br.sfb.ticketssfb.dao.DAOGenerico.session;
import com.br.sfb.ticketssfb.modelo.IModelo;
import com.br.sfb.ticketssfb.modelo.ModeloTime;
import com.br.sfb.ticketssfb.modelo.ModeloUsuario;
import java.util.List;

public class DAOTime extends DAOGenerico {
    @Override
    public void adicionar(IModelo m){
        super.adicionar(m); //To change body of generated methods, choose Tools | Templates.
    }
 public List<ModeloTime> pesquisarUsuario(String mail) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloTime> time = session.getNamedQuery("ModeloTime.getUsuario")
                    .setParameter("mail",mail).list();
            getSession().getTransaction().commit();
            return time;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
     
}
