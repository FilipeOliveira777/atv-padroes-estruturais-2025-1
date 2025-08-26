package br.edu.ifpb.padroes.atv3.cardapio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cardapio {
    
    private final List<ItemCardapio> itens;
    private final String nomeRestaurante;
    
    public Cardapio(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
        this.itens = new ArrayList<>();
    }
    
    public Cardapio() {
        this("Restaurante");
    }
    
    /**
     * Adiciona um item ao cardápio
     */
    public void adicionarItem(ItemCardapio item) {
        if (item != null) {
            itens.add(item);
        }
    }
    
    /**
     * Remove um item do cardápio
     */
    public void removerItem(ItemCardapio item) {
        itens.remove(item);
    }
    
    /**
     * Busca um item por nome
     */
    public Optional<ItemCardapio> buscarPorNome(String nome) {
        return itens.stream()
            .filter(item -> item.getNome().equalsIgnoreCase(nome))
            .findFirst();
    }
    
    /**
     * Busca itens por categoria
     */
    public List<ItemCardapio> buscarPorCategoria(String categoria) {
        List<ItemCardapio> resultado = new ArrayList<>();
        
        for (ItemCardapio item : itens) {
            if (item instanceof ItemIndividual) {
                ItemIndividual individual = (ItemIndividual) item;
                if (individual.getCategoria().equalsIgnoreCase(categoria)) {
                    resultado.add(item);
                }
            }
        }
        
        return resultado;
    }
    
    /**
     * Obtém todos os itens do cardápio
     */
    public List<ItemCardapio> getItens() {
        return new ArrayList<>(itens);
    }
    
    /**
     * Obtém apenas os itens individuais
     */
    public List<ItemIndividual> getItensIndividuais() {
        List<ItemIndividual> resultado = new ArrayList<>();
        
        for (ItemCardapio item : itens) {
            if (item instanceof ItemIndividual) {
                resultado.add((ItemIndividual) item);
            }
        }
        
        return resultado;
    }
    
    /**
     * Obtém apenas os combos
     */
    public List<Combo> getCombos() {
        List<Combo> resultado = new ArrayList<>();
        
        for (ItemCardapio item : itens) {
            if (item instanceof Combo) {
                resultado.add((Combo) item);
            }
        }
        
        return resultado;
    }
    
    /**
     * Calcula o preço total de todos os itens
     */
    public double getPrecoTotal() {
        return itens.stream()
            .mapToDouble(ItemCardapio::getPreco)
            .sum();
    }
    
    /**
     * Exibe todo o cardápio com formatação
     */
    public String exibirCardapio() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("🍽️  ").append(nomeRestaurante.toUpperCase()).append(" 🍽️\n");
        sb.append("=".repeat(nomeRestaurante.length() + 8)).append("\n\n");
        
        if (itens.isEmpty()) {
            sb.append("Cardápio vazio.\n");
            return sb.toString();
        }
        
        // Exibe itens individuais primeiro
        List<ItemIndividual> itensIndividuais = getItensIndividuais();
        if (!itensIndividuais.isEmpty()) {
            sb.append("📋 ITENS INDIVIDUAIS:\n");
            sb.append("-".repeat(20)).append("\n");
            
            for (ItemIndividual item : itensIndividuais) {
                sb.append(item.exibir(0)).append("\n");
            }
            sb.append("\n");
        }
        
        // Exibe combos
        List<Combo> combos = getCombos();
        if (!combos.isEmpty()) {
            sb.append("🍱 COMBOS:\n");
            sb.append("-".repeat(20)).append("\n");
            
            for (Combo combo : combos) {
                sb.append(combo.exibir(0)).append("\n\n");
            }
        }
        
        // Resumo final
        sb.append("💰 RESUMO:\n");
        sb.append("-".repeat(20)).append("\n");
        sb.append("Total de itens: ").append(itens.size()).append("\n");
        sb.append("Itens individuais: ").append(getItensIndividuais().size()).append("\n");
        sb.append("Combos: ").append(getCombos().size()).append("\n");
        sb.append("Preço total: R$ ").append(String.format("%.2f", getPrecoTotal())).append("\n");
        
        return sb.toString();
    }
    
    /**
     * Exibe o cardápio de forma compacta
     */
    public String exibirCardapioCompacto() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("🍽️  ").append(nomeRestaurante).append(" - Cardápio Compacto\n");
        sb.append("-".repeat(nomeRestaurante.length() + 25)).append("\n");
        
        for (ItemCardapio item : itens) {
            sb.append(item.toString()).append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Limpa todo o cardápio
     */
    public void limparCardapio() {
        itens.clear();
    }
    
    /**
     * Obtém o nome do restaurante
     */
    public String getNomeRestaurante() {
        return nomeRestaurante;
    }
}
