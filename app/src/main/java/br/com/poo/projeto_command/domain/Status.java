package br.com.poo.projeto_command.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 05/11/2015.
 */
public class Status {

    private static Status instance;
    private List<String> listaMensagem = new ArrayList<>();


    public static Status getInstance(){
        if (instance==null)
            instance=new Status();

        return instance;
    }

    public void addMensagem(String texto){
        listaMensagem.add(texto);
    }


}
