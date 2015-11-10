package br.com.poo.projeto_command.models;

import br.com.poo.projeto_command.domain.Status;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class TV {

    Status s = Status.getInstance();

    public void on(){
        s.addMensagem("TV ligada");
    }

    public void off(){
        s.addMensagem("TV desligada");
    }

    public void setVolume(int vol){
        s.addMensagem("Volume alterado: "+vol);
    }

    public void setCanal(int canal){
        s.addMensagem("Canal sintonizado: "+canal);
    }
}
