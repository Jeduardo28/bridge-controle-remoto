package br.edu.bridge.controle;

import br.edu.bridge.dispositivo.Dispositivo;

/** RefinedAbstraction: acrescenta uma operação válida para qualquer dispositivo. */
public class ControleRemotoAvancado extends ControleRemoto {
    public ControleRemotoAvancado(Dispositivo dispositivo) {
        super(dispositivo);
    }

    public void silenciar() {
        dispositivo.setVolume(0);
    }
}

