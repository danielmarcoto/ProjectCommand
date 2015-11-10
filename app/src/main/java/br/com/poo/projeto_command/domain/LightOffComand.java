package br.com.poo.projeto_command.domain;

import br.com.poo.projeto_command.interfaces.Command;
import br.com.poo.projeto_command.models.Light;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class LightOffComand implements Command {
    Light light;

    public LightOffComand(Light light){
        this.light=light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
