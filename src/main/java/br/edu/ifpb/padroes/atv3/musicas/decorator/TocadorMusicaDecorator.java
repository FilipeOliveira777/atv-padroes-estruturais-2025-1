package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;

public abstract class TocadorMusicaDecorator {
    
    protected TocadorMusicaDecorator proximoDecorator;
    
    public TocadorMusicaDecorator(TocadorMusicaDecorator proximoDecorator) {
        this.proximoDecorator = proximoDecorator;
    }
    
    public void tocarMusica(MusicaInterface musica) {
        executarAntesDeTocar(musica);
        
        if (proximoDecorator != null) {
            proximoDecorator.tocarMusica(musica);
        }
        
        executarDepoisDeTocar(musica);
    }
    
    protected abstract void executarAntesDeTocar(MusicaInterface musica);
    protected abstract void executarDepoisDeTocar(MusicaInterface musica);
}
