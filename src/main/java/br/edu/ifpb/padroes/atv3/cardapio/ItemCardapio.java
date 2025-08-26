package br.edu.ifpb.padroes.atv3.cardapio;

public interface ItemCardapio {
    
    /**
     * Obtém o nome/descrição do item
     */
    String getNome();
    
    /**
     * Obtém o preço do item
     */
    double getPreco();
    
    /**
     * Exibe a descrição completa do item com indentação
     */
    String exibir(int nivelIndentacao);
    
    /**
     * Adiciona um item ao combo (apenas para combos)
     */
    default void adicionarItem(ItemCardapio item) {
        throw new UnsupportedOperationException("Este item não suporta adição de subitens");
    }
    
    /**
     * Remove um item do combo (apenas para combos)
     */
    default void removerItem(ItemCardapio item) {
        throw new UnsupportedOperationException("Este item não suporta remoção de subitens");
    }
    
    /**
     * Obtém os itens do combo (apenas para combos)
     */
    default java.util.List<ItemCardapio> getItens() {
        throw new UnsupportedOperationException("Este item não possui subitens");
    }
}
