package br.edu.ifpb.padroes.atv3.musicas.facade;

import br.edu.ifpb.padroes.atv3.musicas.decorator.*;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;
import br.edu.ifpb.padroes.atv3.musicas.proxy.ServicoMusicaProxy;
import br.edu.ifpb.padroes.atv3.musicas.servico.ServicoMusicaInterface;

import java.util.List;
import java.util.Map;

public class SistemaStreamMusicaFacade {
    
    private final ServicoMusicaInterface servicoMusica;
    private final TocadorMusicaDecorator tocadorMusica;
    
    public SistemaStreamMusicaFacade() {
        this.servicoMusica = new ServicoMusicaProxy();
        
        // Configurando a cadeia de decorators
        TocadorMusicaDecorator tocadorBase = new TocadorBaseDecorator();
        TocadorMusicaDecorator contador = new ContadorMusicasDecorator(tocadorBase);
        TocadorMusicaDecorator estatisticasArtistas = new EstatisticasArtistasDecorator(contador);
        TocadorMusicaDecorator estatisticasGeneros = new EstatisticasGenerosDecorator(estatisticasArtistas);
        
        this.tocadorMusica = estatisticasGeneros;
    }
    
    // Métodos para busca de músicas
    public List<MusicaInterface> listarTodasMusicas() {
        return servicoMusica.listarMusicas();
    }
    
    public List<MusicaInterface> buscarPorArtista(String artista) {
        return servicoMusica.buscarPorArtista(artista);
    }
    
    public List<MusicaInterface> buscarPorGenero(String genero) {
        return servicoMusica.buscarPorGenero(genero);
    }
    
    public MusicaInterface buscarPorTitulo(String titulo) {
        return servicoMusica.buscarPorTitulo(titulo);
    }
    
    // Método para tocar música
    public void tocarMusica(MusicaInterface musica) {
        if (musica == null) {
            System.out.println("❌ Música não encontrada!");
            return;
        }
        
        tocadorMusica.tocarMusica(musica);
    }
    
    // Métodos para estatísticas
    public int getTotalMusicasTocadas() {
        return ContadorMusicasDecorator.getTotalMusicasTocadas();
    }
    
    public String getArtistaMaisTocado() {
        return EstatisticasArtistasDecorator.getArtistaMaisTocado();
    }
    
    public String getGeneroMaisTocado() {
        return EstatisticasGenerosDecorator.getGeneroMaisTocado();
    }
    
    public Map<String, Integer> getEstatisticasArtistas() {
        return EstatisticasArtistasDecorator.getEstatisticasArtistas();
    }
    
    public Map<String, Integer> getEstatisticasGeneros() {
        return EstatisticasGenerosDecorator.getEstatisticasGeneros();
    }
    
    // Métodos de controle
    public void limparCache() {
        if (servicoMusica instanceof br.edu.ifpb.padroes.atv3.musicas.proxy.ServicoMusicaProxy) {
            ((br.edu.ifpb.padroes.atv3.musicas.proxy.ServicoMusicaProxy) servicoMusica).limparCache();
        }
    }
    
    public void resetarEstatisticas() {
        ContadorMusicasDecorator.resetarContador();
        EstatisticasArtistasDecorator.resetarEstatisticas();
        EstatisticasGenerosDecorator.resetarEstatisticas();
        System.out.println("🔄 Todas as estatísticas foram resetadas");
    }
}
