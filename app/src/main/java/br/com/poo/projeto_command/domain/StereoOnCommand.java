package br.com.poo.projeto_command.domain;

import br.com.poo.projeto_command.interfaces.Command;
import br.com.poo.projeto_command.models.Stereo;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class StereoOnCommand implements Command{
    Stereo stereo;

    public StereoOnCommand(Stereo stero) {
        this.stereo = stero;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }

    @Override
    public void undo() {
        stereo.off();
    }
}
