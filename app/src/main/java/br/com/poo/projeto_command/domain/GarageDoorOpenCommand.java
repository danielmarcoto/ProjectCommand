package br.com.poo.projeto_command.domain;

import br.com.poo.projeto_command.interfaces.Command;
import br.com.poo.projeto_command.models.GarageDoor;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class GarageDoorOpenCommand implements Command {
    GarageDoor garage;

    public GarageDoorOpenCommand(GarageDoor garage){
        this.garage=garage;
    }

    @Override
    public void execute() {
        garage.open();
    }

    @Override
    public void undo() {
        garage.open();
    }
}
