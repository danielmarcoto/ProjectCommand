package br.com.poo.projeto_command.models;

import br.com.poo.projeto_command.domain.Status;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class GarageDoor {
    Status s = Status.getInstance();


    public void open(){
        s.addMensagem("Porta da garagem aberta");
    }

    public void close(){
        s.addMensagem("Porta da garagem fechada");
    }
}
