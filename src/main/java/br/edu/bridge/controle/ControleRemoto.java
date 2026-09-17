package br.edu.bridge.controle;

import br.edu.bridge.dispositivo.Dispositivo;
import java.util.Objects;

/** Abstraction: oferece operações ao usuário e delega ao Implementor. */
public class ControleRemoto {
    // Esta referência é a ponte entre as duas hierarquias.
    protected final Dispositivo dispositivo;

    public ControleRemoto(Dispositivo dispositivo) {
        this.dispositivo = Objects.requireNonNull(dispositivo, "O dispositivo é obrigatório.");
    }

    public void alternarEnergia() {
        if (dispositivo.estaLigado()) {
            dispositivo.desligar();
        } else {
            dispositivo.ligar();
        }
    }

    public void aumentarVolume() {
        dispositivo.setVolume(dispositivo.getVolume() + 10);
    }

    public void diminuirVolume() {
        dispositivo.setVolume(dispositivo.getVolume() - 10);
    }
}

