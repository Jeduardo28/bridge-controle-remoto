package br.edu.bridge;

import br.edu.bridge.controle.ControleRemoto;
import br.edu.bridge.controle.ControleRemotoAvancado;
import br.edu.bridge.dispositivo.Dispositivo;
import br.edu.bridge.dispositivo.Radio;
import br.edu.bridge.dispositivo.Televisao;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

class BridgeTest {
    static Stream<Dispositivo> dispositivos() {
        return Stream.of(new Televisao(), new Radio());
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> combinacoes() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(new Televisao(), false),
            org.junit.jupiter.params.provider.Arguments.of(new Televisao(), true),
            org.junit.jupiter.params.provider.Arguments.of(new Radio(), false),
            org.junit.jupiter.params.provider.Arguments.of(new Radio(), true));
    }

    @ParameterizedTest
    @MethodSource("combinacoes")
    void controlesOperamQualquerDispositivo(Dispositivo dispositivo, boolean avancado) {
        ControleRemoto controle = avancado
                ? new ControleRemotoAvancado(dispositivo) : new ControleRemoto(dispositivo);
        assertFalse(dispositivo.estaLigado());
        assertEquals(30, dispositivo.getVolume());
        controle.alternarEnergia();
        assertTrue(dispositivo.estaLigado());
        controle.aumentarVolume();
        assertEquals(40, dispositivo.getVolume());
        controle.diminuirVolume();
        assertEquals(30, dispositivo.getVolume());
        controle.alternarEnergia();
        assertFalse(dispositivo.estaLigado());
        assertEquals(30, dispositivo.getVolume());
    }

    @ParameterizedTest
    @MethodSource("dispositivos")
    void controleRespeitaLimitesDeVolume(Dispositivo dispositivo) {
        ControleRemoto controle = new ControleRemoto(dispositivo);
        dispositivo.setVolume(95);
        controle.aumentarVolume();
        assertEquals(100, dispositivo.getVolume());
        controle.aumentarVolume();
        assertEquals(100, dispositivo.getVolume());
        dispositivo.setVolume(5);
        controle.diminuirVolume();
        assertEquals(0, dispositivo.getVolume());
        controle.diminuirVolume();
        assertEquals(0, dispositivo.getVolume());
    }

    @ParameterizedTest
    @MethodSource("dispositivos")
    void dispositivoLimitaValoresForaDaFaixa(Dispositivo dispositivo) {
        dispositivo.setVolume(Integer.MAX_VALUE);
        assertEquals(100, dispositivo.getVolume());
        dispositivo.setVolume(Integer.MIN_VALUE);
        assertEquals(0, dispositivo.getVolume());
    }

    @ParameterizedTest
    @MethodSource("dispositivos")
    void silenciarZeraVolumeSemDesligar(Dispositivo dispositivo) {
        dispositivo.ligar();
        ControleRemotoAvancado controle = new ControleRemotoAvancado(dispositivo);
        controle.silenciar();
        controle.silenciar();
        assertEquals(0, dispositivo.getVolume());
        assertTrue(dispositivo.estaLigado());
        controle.aumentarVolume();
        assertEquals(10, dispositivo.getVolume());
    }

    @ParameterizedTest
    @MethodSource("dispositivos")
    void permiteAjustarVolumeDesligado(Dispositivo dispositivo) {
        new ControleRemoto(dispositivo).aumentarVolume();
        assertEquals(40, dispositivo.getVolume());
        assertFalse(dispositivo.estaLigado());
    }

    @Test
    void aparelhosMantemEstadosIndependentes() {
        Dispositivo tv = new Televisao();
        Dispositivo radio = new Radio();
        ControleRemoto controle = new ControleRemoto(tv);
        controle.alternarEnergia();
        controle.aumentarVolume();
        assertFalse(radio.estaLigado());
        assertEquals(30, radio.getVolume());
    }

    @Test
    void controlesPodemCompartilharMesmoAparelho() {
        Dispositivo tv = new Televisao();
        ControleRemoto basico = new ControleRemoto(tv);
        ControleRemotoAvancado avancado = new ControleRemotoAvancado(tv);
        basico.aumentarVolume();
        avancado.silenciar();
        basico.aumentarVolume();
        assertEquals(10, tv.getVolume());
    }

    @Test
    void rejeitaDispositivoNulo() {
        assertThrows(NullPointerException.class, () -> new ControleRemoto(null));
        assertThrows(NullPointerException.class, () -> new ControleRemotoAvancado(null));
    }
}

