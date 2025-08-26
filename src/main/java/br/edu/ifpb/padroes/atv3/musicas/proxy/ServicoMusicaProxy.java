package br.edu.ifpb.padroes.atv3.musicas.proxy;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;
import br.edu.ifpb.padroes.atv3.musicas.servico.ServicoMusicaInterface;
import br.edu.ifpb.padroes.atv3.musicas.servico.ServicoMusicaReal;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class ServicoMusicaProxy implements ServicoMusicaInterface {
    
    private final ServicoMusicaReal servicoReal;
    private List<MusicaInterface> cacheMusicas;
    private long ultimaAtualizacao;
    private static final long TEMPO_CACHE = TimeUnit.MINUTES.toMillis(5); // Cache por 5 minutos
    
    public ServicoMusicaProxy() {
        this.servicoReal = new ServicoMusicaReal();
        this.cacheMusicas = null;
        this.ultimaAtualizacao = 0;
    }
    
    private boolean cacheExpirado() {
        return System.currentTimeMillis() - ultimaAtualizacao > TEMPO_CACHE;
    }
    
    private void atualizarCache() {
        if (cacheMusicas == null || cacheExpirado()) {
            System.out.println("Atualizando cache de músicas...");
            this.cacheMusicas = servicoReal.listarMusicas();
            this.ultimaAtualizacao = System.currentTimeMillis();
            System.out.println("Cache atualizado com " + cacheMusicas.size() + " músicas");
        }
    }
    
    @Override
    public List<MusicaInterface> listarMusicas() {
        atualizarCache();
        return new ArrayList<>(cacheMusicas);
    }
    
    @Override
    public List<MusicaInterface> buscarPorArtista(String artista) {
        atualizarCache();
        return cacheMusicas.stream()
            .filter(musica -> musica.getArtista().toLowerCase().contains(artista.toLowerCase()))
            .collect(java.util.stream.Collectors.toList());
    }
    
    @Override
    public List<MusicaInterface> buscarPorGenero(String genero) {
        atualizarCache();
        return cacheMusicas.stream()
            .filter(musica -> musica.getGenero().toLowerCase().contains(genero.toLowerCase()))
            .collect(java.util.stream.Collectors.toList());
    }
    
    @Override
    public MusicaInterface buscarPorTitulo(String titulo) {
        atualizarCache();
        return cacheMusicas.stream()
            .filter(musica -> musica.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
            .findFirst()
            .orElse(null);
    }
    
    public void limparCache() {
        this.cacheMusicas = null;
        this.ultimaAtualizacao = 0;
        System.out.println("Cache limpo");
    }
}
