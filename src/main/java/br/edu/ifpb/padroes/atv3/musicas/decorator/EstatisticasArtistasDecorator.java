package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EstatisticasArtistasDecorator extends TocadorMusicaDecorator {
    
    private static final Map<String, Integer> contadorArtistas = new HashMap<>();
    
    public EstatisticasArtistasDecorator(TocadorMusicaDecorator proximoDecorator) {
        super(proximoDecorator);
    }
    
    @Override
    protected void executarAntesDeTocar(MusicaInterface musica) {
        // Nada a fazer antes
    }
    
    @Override
    protected void executarDepoisDeTocar(MusicaInterface musica) {
        String artista = musica.getArtista();
        contadorArtistas.put(artista, contadorArtistas.getOrDefault(artista, 0) + 1);
        
        System.out.println("🎤 Artista: " + artista + " - Tocou " + contadorArtistas.get(artista) + " vez(es)");
    }
    
    public static Map<String, Integer> getEstatisticasArtistas() {
        return new HashMap<>(contadorArtistas);
    }
    
    public static String getArtistaMaisTocado() {
        if (contadorArtistas.isEmpty()) {
            return "Nenhum artista tocado ainda";
        }
        
        return contadorArtistas.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("N/A");
    }
    
    public static void resetarEstatisticas() {
        contadorArtistas.clear();
    }
}
