package br.edu.ifpb.padroes.atv3.musicas.model;

public class MusicaPadrao implements MusicaInterface {
    private final String id;
    private final String titulo;
    private final String artista;
    private final Long ano;
    private final String genero;

    public MusicaPadrao(String id, String titulo, String artista, Long ano, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.ano = ano;
        this.genero = genero;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getArtista() {
        return artista;
    }

    @Override
    public Long getAno() {
        return ano;
    }

    @Override
    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "MusicaPadrao{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", ano=" + ano +
                ", genero='" + genero + '\'' +
                '}';
    }
}
