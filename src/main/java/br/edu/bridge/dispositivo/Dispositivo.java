package br.edu.bridge.dispositivo;

/** Implementor: contrato usado pelos controles, sem depender de aparelhos concretos. */
public interface Dispositivo {
    String getNome();
    boolean estaLigado();
    void ligar();
    void desligar();
    int getVolume();

    /** Ajusta o volume para a faixa de 0 a 100, limitando valores fora dela. */
    void setVolume(int volume);
}

