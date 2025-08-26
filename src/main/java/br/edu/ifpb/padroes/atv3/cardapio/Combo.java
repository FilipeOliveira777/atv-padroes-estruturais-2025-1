package br.edu.ifpb.padroes.atv3.cardapio;

import java.util.ArrayList;
import java.util.List;

public class Combo implements ItemCardapio {
    
    private final String nome;
    private final String descricao;
    private final double descontoPercentual;
    private final List<ItemCardapio> itens;
    
    public Combo(String nome, String descricao, double descontoPercentual) {
        this.nome = nome;
        this.descricao = descricao;
        this.descontoPercentual = Math.max(0, Math.min(100, descontoPercentual)); // Limita entre 0 e 100%
        this.itens = new ArrayList<>();
    }
    
    public Combo(String nome, String descricao) {
        this(nome, descricao, 0.0);
    }
    
    public Combo(String nome) {
        this(nome, "", 0.0);
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public double getPreco() {
        double precoTotal = itens.stream()
            .mapToDouble(ItemCardapio::getPreco)
            .sum();
        
        if (descontoPercentual > 0) {
            double desconto = precoTotal * (descontoPercentual / 100.0);
            precoTotal -= desconto;
        }
        
        return precoTotal;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public double getDescontoPercentual() {
        return descontoPercentual;
    }
    
    public double getPrecoSemDesconto() {
        return itens.stream()
            .mapToDouble(ItemCardapio::getPreco)
            .sum();
    }
    
    @Override
    public void adicionarItem(ItemCardapio item) {
        if (item != null) {
            itens.add(item);
        }
    }
    
    @Override
    public void removerItem(ItemCardapio item) {
        itens.remove(item);
    }
    
    @Override
    public List<ItemCardapio> getItens() {
        return new ArrayList<>(itens);
    }
    
    public int getQuantidadeItens() {
        return itens.size();
    }
    
    @Override
    public String exibir(int nivelIndentacao) {
        StringBuilder sb = new StringBuilder();
        
        // Adiciona indentação
        for (int i = 0; i < nivelIndentacao; i++) {
            sb.append("  ");
        }
        
        // Formata o combo
        sb.append("🍽️  ").append(nome);
        
        if (!descricao.isEmpty()) {
            sb.append(" - ").append(descricao);
        }
        
        sb.append(" (Combo)");
        
        if (descontoPercentual > 0) {
            sb.append(" - Desconto: ").append(String.format("%.1f", descontoPercentual)).append("%");
        }
        
        sb.append(" - R$ ").append(String.format("%.2f", getPreco()));
        
        if (descontoPercentual > 0) {
            sb.append(" (Economia: R$ ").append(String.format("%.2f", getPrecoSemDesconto() - getPreco())).append(")");
        }
        
        sb.append("\n");
        
        // Exibe os itens do combo com indentação adicional
        for (ItemCardapio item : itens) {
            sb.append(item.exibir(nivelIndentacao + 1)).append("\n");
        }
        
        return sb.toString().trim();
    }
    
    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", getPreco()) + " (" + getQuantidadeItens() + " itens)";
    }
}
