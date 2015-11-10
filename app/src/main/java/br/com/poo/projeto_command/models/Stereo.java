package br.com.poo.projeto_command.models;

import br.com.poo.projeto_command.domain.Status;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class Stereo {
    Status s = Status.getInstance();

    public void on(){
        s.addMensagem("Radio ligado");
    }

    public void setCD(){
        s.addMensagem("CD selecionado");
    }

    public void setVolume(int vol){
        s.addMensagem("Volume alterado: "+vol);
    }

    public void off(){
        s.addMensagem("Radio desligado");
    }
}
