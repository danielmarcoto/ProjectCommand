package br.com.poo.projeto_command.models;

import br.com.poo.projeto_command.domain.Status;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class Light {
    Status s = Status.getInstance();

    public void on(){
        s.addMensagem("Luz acesa");
    }

    public void off(){
        s.addMensagem("Luz apagada");
    }

}
