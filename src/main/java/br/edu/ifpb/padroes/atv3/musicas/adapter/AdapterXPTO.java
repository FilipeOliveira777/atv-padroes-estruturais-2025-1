package br.edu.ifpb.padroes.atv3.musicas.adapter;

import br.edu.ifpb.padroes.atv3.musicas.xpto.Song;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;

public class AdapterXPTO {
    
    public static MusicaInterface adaptar(Song songXPTO) {
        return new MusicaPadrao(
            songXPTO.id(),
            songXPTO.title(),
            songXPTO.artist(),
            songXPTO.year(),
            songXPTO.genre()
        );
    }
}
