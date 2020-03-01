package com.br.sfb.ticketssfb;

import com.br.sfb.ticketssfb.dao.DAOTicket;
import com.br.sfb.ticketssfb.dao.DAOUsuario;
import com.br.sfb.ticketssfb.enumerator.Atribuicoes;
import com.br.sfb.ticketssfb.modelo.ModeloTicket;
import com.br.sfb.ticketssfb.modelo.ModeloUsuario;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class New {
public static void main(String[] args) {
	ModeloTicket em = new ModeloTicket();
        em.setAssunto("Youtube");
        em.setRemetente("");
       em.setDestinatarios("");
       em.setAtribuicao(Atribuicoes.RECEBIDO);
       ModeloUsuario user = new ModeloUsuario();
        user.setAssinatura("john");
       user.setNome("freire");
       user.setEmail("nova@nova.com");       
       user.setPassword("90274");
       DAOUsuario userdao = new DAOUsuario();
       userdao.adicionar(user);
      //  DAOEmail dao = new DAOEmail();
	//dao.adicionar(em);
        
         DAOTicket dao = new DAOTicket();
         dao.adicionar(em);
	//em.setNome(entrada.nextLine());
	 Set<ModeloUsuario> models = new HashSet<>() ;
       
List<ModeloUsuario> model = userdao.pesquisarUsuario("nova@nova.com");

        
        for (ModeloUsuario c : model) {
           models.add(c);
            System.out.println(c.getNome());
    }
      List<ModeloTicket> model2 = dao.pesquisarticketUsuario(Atribuicoes.RECEBIDO,model.get(0).getCodigo());
          for (ModeloTicket c : model2) 
            System.out.println(c.getAssunto());
  }
//em.setAssunto("Youtube");
       //em.setRemetente("john-jtw@hotmail.com");
       // em.setDestinatarios("fansubsbrasil@gmail.com");
       // em.setAtribuicao(Atribuicoes.RECEBIDO);
       // ModeloUsuario user = new ModeloUsuario();
       // user.setAssinatura("john");
      //  user.setNome("freire");
      //  user.setMail("nova@nova.com");
      //  user.setPassword("90274");
        //em.setUsuario(models);
      // DAOTicket daos = new DAOTicket();
//       daos.adicionar(em);
        

}

