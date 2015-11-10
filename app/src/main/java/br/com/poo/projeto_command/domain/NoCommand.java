package br.com.poo.projeto_command.domain;

import br.com.poo.projeto_command.interfaces.Command;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class NoCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
