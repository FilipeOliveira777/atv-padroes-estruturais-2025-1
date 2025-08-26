package br.edu.ifpb.padroes.atv3.cardapio;

public class ItemIndividual implements ItemCardapio {
    
    private final String nome;
    private final double preco;
    private final String categoria;
    private final String descricao;
    
    public ItemIndividual(String nome, double preco, String categoria, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.descricao = descricao;
    }
    
    public ItemIndividual(String nome, double preco, String categoria) {
        this(nome, preco, categoria, "");
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public double getPreco() {
        return preco;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String exibir(int nivelIndentacao) {
        StringBuilder sb = new StringBuilder();
        
        // Adiciona indentação
        for (int i = 0; i < nivelIndentacao; i++) {
            sb.append("  ");
        }
        
        // Formata o item
        sb.append("• ").append(nome);
        
        if (!descricao.isEmpty()) {
            sb.append(" - ").append(descricao);
        }
        
        sb.append(" (").append(categoria).append(")");
        sb.append(" - R$ ").append(String.format("%.2f", preco));
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", preco);
    }
}
