package br.edu.ifpb.padroes.atv3;

import br.edu.ifpb.padroes.atv3.musicas.facade.SistemaStreamMusicaFacade;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;

import java.util.List;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("🎵 SISTEMA DE STREAM DE MÚSICA 🎵");
        System.out.println("=====================================\n");
        
        // Criando a Facade do sistema
        SistemaStreamMusicaFacade sistema = new SistemaStreamMusicaFacade();
        
        // Demonstração das funcionalidades
        System.out.println("📋 LISTANDO TODAS AS MÚSICAS:");
        List<MusicaInterface> todasMusicas = sistema.listarTodasMusicas();
        todasMusicas.forEach(musica -> 
            System.out.println("  • " + musica.getTitulo() + " - " + musica.getArtista() + " (" + musica.getGenero() + ")")
        );
        
        System.out.println("\n🔍 BUSCANDO POR ARTISTA (Tom Jobim):");
        List<MusicaInterface> musicasTomJobim = sistema.buscarPorArtista("Tom Jobim");
        musicasTomJobim.forEach(musica -> 
            System.out.println("  • " + musica.getTitulo() + " - " + musica.getArtista())
        );
        
        System.out.println("\n🔍 BUSCANDO POR GÊNERO (Rock):");
        List<MusicaInterface> musicasRock = sistema.buscarPorGenero("Rock");
        musicasRock.forEach(musica -> 
            System.out.println("  • " + musica.getTitulo() + " - " + musica.getArtista())
        );
        
        System.out.println("\n🎵 TOCANDO ALGUMAS MÚSICAS:");
        // Tocando algumas músicas para demonstrar os decorators
        if (!todasMusicas.isEmpty()) {
            sistema.tocarMusica(todasMusicas.get(0)); // Primeira música
            if (todasMusicas.size() > 1) {
                sistema.tocarMusica(todasMusicas.get(1)); // Segunda música
            }
            if (todasMusicas.size() > 2) {
                sistema.tocarMusica(todasMusicas.get(2)); // Terceira música
            }
        }
        
        System.out.println("\n📊 ESTATÍSTICAS:");
        System.out.println("  • Total de músicas tocadas: " + sistema.getTotalMusicasTocadas());
        System.out.println("  • Artista mais tocado: " + sistema.getArtistaMaisTocado());
        System.out.println("  • Gênero mais tocado: " + sistema.getGeneroMaisTocado());
        
        System.out.println("\n📈 ESTATÍSTICAS DETALHADAS:");
        Map<String, Integer> estatisticasArtistas = sistema.getEstatisticasArtistas();
        System.out.println("  • Artistas:");
        estatisticasArtistas.forEach((artista, contagem) -> 
            System.out.println("    - " + artista + ": " + contagem + " vez(es)")
        );
        
        Map<String, Integer> estatisticasGeneros = sistema.getEstatisticasGeneros();
        System.out.println("  • Gêneros:");
        estatisticasGeneros.forEach((genero, contagem) -> 
            System.out.println("    - " + genero + ": " + contagem + " vez(es)")
        );
        
        System.out.println("\n🔄 DEMONSTRANDO CACHE:");
        System.out.println("  • Buscando músicas novamente (deve usar cache):");
        List<MusicaInterface> musicasCache = sistema.listarTodasMusicas();
        System.out.println("  • Total de músicas em cache: " + musicasCache.size());
        
        System.out.println("\n🧹 LIMPANDO CACHE E ESTATÍSTICAS:");
        sistema.limparCache();
        sistema.resetarEstatisticas();
        
        System.out.println("\n✅ SISTEMA DEMONSTRADO COM SUCESSO!");
    }
}
