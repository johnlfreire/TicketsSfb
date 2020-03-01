/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sfb.ticketssfb.enumerator;

/**
 *
 * @author johnpc
 */
public enum Atribuicoes {

   RESOLVIDO("Resolvido"),RASCUNHO("Rascunho"),RECEBIDO("Recebido"),  SPAM("Spam"),  ARQUIVADO("Arquivado"),  RESOLVENDO("Resolvendo ");
  
  String atribuicoes;
  
  private Atribuicoes(String atribuicoes)
  {
    this.atribuicoes = atribuicoes;
  }
  
  public String toString()
  {
    return this.atribuicoes;
  }
}
