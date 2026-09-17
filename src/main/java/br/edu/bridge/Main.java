package br.edu.bridge;

import br.edu.bridge.controle.ControleRemoto;
import br.edu.bridge.controle.ControleRemotoAvancado;
import br.edu.bridge.dispositivo.Dispositivo;
import br.edu.bridge.dispositivo.Radio;
import br.edu.bridge.dispositivo.Televisao;

public class Main {
    public static void main(String[] args) {
        demonstrar(new Televisao());
        demonstrar(new Radio());
    }

    private static void demonstrar(Dispositivo dispositivo) {
        System.out.println("\n=== " + dispositivo.getNome() + " ===");
        ControleRemoto basico = new ControleRemoto(dispositivo);
        basico.alternarEnergia();
        basico.aumentarVolume();
        mostrarEstado("Controle básico: ligar e aumentar volume", dispositivo);

        ControleRemotoAvancado avancado = new ControleRemotoAvancado(dispositivo);
        avancado.diminuirVolume();
        mostrarEstado("Controle avançado: diminuir volume", dispositivo);
        avancado.silenciar();
        mostrarEstado("Controle avançado: silenciar", dispositivo);
        avancado.alternarEnergia();
        mostrarEstado("Controle avançado: desligar", dispositivo);
    }

    private static void mostrarEstado(String acao, Dispositivo dispositivo) {
        System.out.printf("%s -> ligado=%s, volume=%d%n",
                acao, dispositivo.estaLigado(), dispositivo.getVolume());
    }
}

