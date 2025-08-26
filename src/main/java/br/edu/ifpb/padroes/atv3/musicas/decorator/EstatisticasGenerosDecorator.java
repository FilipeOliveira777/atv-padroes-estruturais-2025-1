package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaInterface;

import java.util.HashMap;
import java.util.Map;

public class EstatisticasGenerosDecorator extends TocadorMusicaDecorator {
    
    private static final Map<String, Integer> contadorGeneros = new HashMap<>();
    
    public EstatisticasGenerosDecorator(TocadorMusicaDecorator proximoDecorator) {
        super(proximoDecorator);
    }
    
    @Override
    protected void executarAntesDeTocar(MusicaInterface musica) {
        // Nada a fazer antes
    }
    
    @Override
    protected void executarDepoisDeTocar(MusicaInterface musica) {
        String genero = musica.getGenero();
        contadorGeneros.put(genero, contadorGeneros.getOrDefault(genero, 0) + 1);
        
        System.out.println("🎭 Gênero: " + genero + " - Tocou " + contadorGeneros.get(genero) + " vez(es)");
    }
    
    public static Map<String, Integer> getEstatisticasGeneros() {
        return new HashMap<>(contadorGeneros);
    }
    
    public static String getGeneroMaisTocado() {
        if (contadorGeneros.isEmpty()) {
            return "Nenhum gênero tocado ainda";
        }
        
        return contadorGeneros.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("N/A");
    }
    
    public static void resetarEstatisticas() {
        contadorGeneros.clear();
    }
}
