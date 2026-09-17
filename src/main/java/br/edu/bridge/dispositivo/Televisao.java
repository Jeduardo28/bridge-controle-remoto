package br.edu.bridge.dispositivo;

/** ConcreteImplementor: simula o estado de uma televisão. */
public class Televisao implements Dispositivo {
    private boolean ligado;
    private int volume = 30;

    @Override
    public String getNome() {
        return "Televisao";
    }

    @Override
    public boolean estaLigado() {
        return ligado;
    }

    @Override
    public void ligar() {
        ligado = true;
    }

    @Override
    public void desligar() {
        ligado = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }
}

