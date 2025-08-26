package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;

public class TocadorBaseDecorator extends TocadorMusicaDecorator {
    
    public TocadorBaseDecorator() {
        super(null);
    }
    
    @Override
    protected void executarAntesDeTocar(MusicaInterface musica) {
        System.out.println("🎵 Tocando música: " + musica.getTitulo() + " - " + musica.getArtista());
    }
    
    @Override
    protected void executarDepoisDeTocar(MusicaInterface musica) {
        System.out.println("✅ Música finalizada: " + musica.getTitulo());
    }
}
