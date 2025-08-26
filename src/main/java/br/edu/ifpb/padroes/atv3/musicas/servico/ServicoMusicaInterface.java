package br.edu.ifpb.padroes.atv3.musicas.servico;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;
import java.util.List;

public interface ServicoMusicaInterface {
    List<MusicaInterface> listarMusicas();
    List<MusicaInterface> buscarPorArtista(String artista);
    List<MusicaInterface> buscarPorGenero(String genero);
    MusicaInterface buscarPorTitulo(String titulo);
}
