package br.edu.ifpb.padroes.atv3.musicas.adapter;

import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;

public class AdapterABCD {
    
    public static MusicaInterface adaptar(Musica musicaABCD) {
        return new MusicaPadrao(
            musicaABCD.id(),
            musicaABCD.titulo(),
            musicaABCD.artista(),
            musicaABCD.ano(),
            musicaABCD.genero()
        );
    }
}
