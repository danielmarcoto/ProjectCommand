package br.com.poo.projeto_command.domain;

import br.com.poo.projeto_command.interfaces.Command;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class ControleRemoto {

    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;
    String[] nomes;

    public ControleRemoto() {

        onCommands = new Command[7];
        offCommands = new Command[7];
        nomes = new String[7];

        Command noCommand = new NoCommand();

        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand, String nome) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
        nomes[slot] = nome;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

    public Command getCommand(int slot) {
        return onCommands[slot];
    }

    public String getNome(int slot) {
        return nomes[slot];
    }

}

