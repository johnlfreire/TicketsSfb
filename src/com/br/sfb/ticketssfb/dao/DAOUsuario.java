package com.br.sfb.ticketssfb.dao;

import static com.br.sfb.ticketssfb.dao.DAOGenerico.abrirSessao;
import static com.br.sfb.ticketssfb.dao.DAOGenerico.getSession;
import static com.br.sfb.ticketssfb.dao.DAOGenerico.session;
import com.br.sfb.ticketssfb.modelo.IModelo;
import com.br.sfb.ticketssfb.modelo.ModeloUsuario;
import java.util.List;

public class DAOUsuario extends DAOGenerico {
    @Override
    public void adicionar(IModelo m){
        super.adicionar(m); //To change body of generated methods, choose Tools | Templates.
    }
 public List<ModeloUsuario> pesquisarUsuario(String email) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            List<ModeloUsuario> usuario = session.getNamedQuery("ModeloUsuario.getUsuario")
                    .setParameter("email",email).list();
            getSession().getTransaction().commit();
            return usuario;
        } catch (RuntimeException ex) {
            getSession().getTransaction().rollback();
            throw ex;
        }
    }
     
}
