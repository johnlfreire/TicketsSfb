/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.controle;

import java.util.ArrayList;

/**
 *
 * @author johnpc
 */
public class SingletonTicket {
    private ArrayList<Long> listTicket = new ArrayList();

    public ArrayList<Long> getListTicket() {
        return listTicket;
    }

    public void setListTicket(ArrayList<Long> listTicket) {
        this.listTicket = listTicket;
    }
    
    private SingletonTicket() {
    }
    
    public static SingletonTicket getInstance() {
        return SingletonTicketHolder.INSTANCE;
    }
    
    private static class SingletonTicketHolder {

        private static final SingletonTicket INSTANCE = new SingletonTicket();
    }
    
}
