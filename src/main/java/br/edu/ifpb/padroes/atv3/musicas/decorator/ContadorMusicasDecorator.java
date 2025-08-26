package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;

public class ContadorMusicasDecorator extends TocadorMusicaDecorator {
    
    private static int totalMusicasTocadas = 0;
    
    public ContadorMusicasDecorator(TocadorMusicaDecorator proximoDecorator) {
        super(proximoDecorator);
    }
    
    @Override
    protected void executarAntesDeTocar(MusicaInterface musica) {
        // Nada a fazer antes
    }
    
    @Override
    protected void executarDepoisDeTocar(MusicaInterface musica) {
        totalMusicasTocadas++;
        System.out.println("🎵 Música tocada: " + musica.getTitulo() + " - Total de músicas tocadas: " + totalMusicasTocadas);
    }
    
    public static int getTotalMusicasTocadas() {
        return totalMusicasTocadas;
    }
    
    public static void resetarContador() {
        totalMusicasTocadas = 0;
    }
}
