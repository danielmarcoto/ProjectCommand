package br.com.poo.projeto_command.domain;

import br.com.poo.projeto_command.interfaces.Command;
import br.com.poo.projeto_command.models.Light;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class LightOnComand implements Command {

    Light light;

    public LightOnComand(Light light){
        this.light=light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }

}
