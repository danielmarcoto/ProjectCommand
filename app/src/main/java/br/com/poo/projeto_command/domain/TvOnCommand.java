package br.com.poo.projeto_command.domain;

import br.com.poo.projeto_command.interfaces.Command;
import br.com.poo.projeto_command.models.TV;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class TvOnCommand implements Command{

    TV tv;

    public TvOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
        tv.setCanal(5);
        tv.setVolume(9);
    }

    @Override
    public void undo() {
        tv.off();
    }
}
