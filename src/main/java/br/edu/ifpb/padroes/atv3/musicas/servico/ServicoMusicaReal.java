package br.edu.ifpb.padroes.atv3.musicas.servico;

import br.edu.ifpb.padroes.atv3.musicas.abcd.ClienteHttpABCD;
import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;
import br.edu.ifpb.padroes.atv3.musicas.adapter.AdapterABCD;
import br.edu.ifpb.padroes.atv3.musicas.adapter.AdapterXPTO;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;
import br.edu.ifpb.padroes.atv3.musicas.xpto.ClientHttpXPTO;
import br.edu.ifpb.padroes.atv3.musicas.xpto.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoMusicaReal implements ServicoMusicaInterface {
    
    private final ClienteHttpABCD clienteABCD;
    private final ClientHttpXPTO clienteXPTO;
    
    public ServicoMusicaReal() {
        this.clienteABCD = new ClienteHttpABCD();
        this.clienteXPTO = new ClientHttpXPTO();
    }
    
    @Override
    public List<MusicaInterface> listarMusicas() {
        List<MusicaInterface> musicas = new ArrayList<>();
        
        // Buscar músicas do serviço ABCD
        try {
            List<Musica> musicasABCD = clienteABCD.listarMusicas();
            musicas.addAll(musicasABCD.stream()
                .map(AdapterABCD::adaptar)
                .collect(Collectors.toList()));
        } catch (Exception e) {
            System.err.println("Erro ao buscar músicas do serviço ABCD: " + e.getMessage());
        }
        
        // Buscar músicas do serviço XPTO
        try {
            List<Song> songsXPTO = clienteXPTO.findAll();
            musicas.addAll(songsXPTO.stream()
                .map(AdapterXPTO::adaptar)
                .collect(Collectors.toList()));
        } catch (Exception e) {
            System.err.println("Erro ao buscar músicas do serviço XPTO: " + e.getMessage());
        }
        
        return musicas;
    }
    
    @Override
    public List<MusicaInterface> buscarPorArtista(String artista) {
        return listarMusicas().stream()
            .filter(musica -> musica.getArtista().toLowerCase().contains(artista.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    @Override
    public List<MusicaInterface> buscarPorGenero(String genero) {
        return listarMusicas().stream()
            .filter(musica -> musica.getGenero().toLowerCase().contains(genero.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    @Override
    public MusicaInterface buscarPorTitulo(String titulo) {
        return listarMusicas().stream()
            .filter(musica -> musica.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
            .findFirst()
            .orElse(null);
    }
}
