package br.edu.ifpb.padroes.atv3;

import br.edu.ifpb.padroes.atv3.cardapio.*;

public class DemoCardapio {
    
    public static void main(String[] args) {
        System.out.println("🍽️  SISTEMA DE CARDÁPIO - DEMONSTRAÇÃO 🍽️");
        System.out.println("=".repeat(50));
        
        // Criando o cardápio do restaurante
        Cardapio cardapio = new Cardapio("Sabor Brasileiro");
        
        // Criando itens individuais
        System.out.println("\n📝 CRIANDO ITENS INDIVIDUAIS...");
        
        ItemIndividual feijoada = new ItemIndividual("Feijoada", 25.90, "Prato Principal", "Feijão preto com carnes e acompanhamentos");
        ItemIndividual churrasco = new ItemIndividual("Churrasco", 35.50, "Prato Principal", "Carne assada na brasa com farofa");
        ItemIndividual moqueca = new ItemIndividual("Moqueca de Peixe", 28.75, "Prato Principal", "Peixe cozido no leite de coco e dendê");
        
        ItemIndividual caipirinha = new ItemIndividual("Caipirinha", 12.00, "Bebida", "Cachaça, limão, açúcar e gelo");
        ItemIndividual sucoLaranja = new ItemIndividual("Suco de Laranja", 8.50, "Bebida", "Suco natural de laranja");
        ItemIndividual refrigerante = new ItemIndividual("Refrigerante", 6.00, "Bebida", "Coca-Cola, Sprite ou Fanta");
        
        ItemIndividual brigadeiro = new ItemIndividual("Brigadeiro", 5.50, "Sobremesa", "Doce de chocolate com granulado");
        ItemIndividual pudim = new ItemIndividual("Pudim de Leite", 7.00, "Sobremesa", "Pudim tradicional com calda de caramelo");
        
        // Adicionando itens ao cardápio
        cardapio.adicionarItem(feijoada);
        cardapio.adicionarItem(churrasco);
        cardapio.adicionarItem(moqueca);
        cardapio.adicionarItem(caipirinha);
        cardapio.adicionarItem(sucoLaranja);
        cardapio.adicionarItem(refrigerante);
        cardapio.adicionarItem(brigadeiro);
        cardapio.adicionarItem(pudim);
        
        System.out.println("✅ " + cardapio.getItensIndividuais().size() + " itens individuais criados!");
        
        // Criando combos simples
        System.out.println("\n🍱 CRIANDO COMBOS SIMPLES...");
        
        Combo comboFeijoada = new Combo("Combo Feijoada", "Feijoada completa com bebida e sobremesa", 15.0);
        comboFeijoada.adicionarItem(feijoada);
        comboFeijoada.adicionarItem(caipirinha);
        comboFeijoada.adicionarItem(brigadeiro);
        
        Combo comboChurrasco = new Combo("Combo Churrasco", "Churrasco com bebida e sobremesa", 10.0);
        comboChurrasco.adicionarItem(churrasco);
        comboChurrasco.adicionarItem(refrigerante);
        comboChurrasco.adicionarItem(pudim);
        
        // Adicionando combos ao cardápio
        cardapio.adicionarItem(comboFeijoada);
        cardapio.adicionarItem(comboChurrasco);
        
        System.out.println("✅ " + cardapio.getCombos().size() + " combos simples criados!");
        
        // Criando combo complexo (combo de combos)
        System.out.println("\n🎯 CRIANDO COMBO COMPLEXO (COMBO DE COMBOS)...");
        
        Combo megaCombo = new Combo("Mega Combo Família", "Combo completo para toda a família", 20.0);
        megaCombo.adicionarItem(comboFeijoada);
        megaCombo.adicionarItem(comboChurrasco);
        megaCombo.adicionarItem(moqueca);
        megaCombo.adicionarItem(sucoLaranja);
        megaCombo.adicionarItem(brigadeiro);
        megaCombo.adicionarItem(pudim);
        
        cardapio.adicionarItem(megaCombo);
        
        System.out.println("✅ Combo complexo criado com " + megaCombo.getQuantidadeItens() + " itens!");
        
        // Exibindo o cardápio completo
        System.out.println("\n" + "=".repeat(60));
        System.out.println(cardapio.exibirCardapio());
        
        // Demonstrações adicionais
        System.out.println("\n🔍 DEMONSTRAÇÕES ADICIONAIS:");
        System.out.println("-".repeat(30));
        
        // Busca por categoria
        System.out.println("\n📋 Itens da categoria 'Prato Principal':");
        cardapio.buscarPorCategoria("Prato Principal").forEach(item -> 
            System.out.println("  • " + item.getNome() + " - R$ " + String.format("%.2f", item.getPreco()))
        );
        
        // Busca por nome
        System.out.println("\n🔍 Buscando por 'Feijoada':");
        cardapio.buscarPorNome("Feijoada").ifPresent(item -> 
            System.out.println("  • Encontrado: " + item.getNome() + " - R$ " + String.format("%.2f", item.getPreco()))
        );
        
        // Análise de preços
        System.out.println("\n💰 ANÁLISE DE PREÇOS:");
        System.out.println("-".repeat(20));
        
        System.out.println("Preço do Combo Feijoada: R$ " + String.format("%.2f", comboFeijoada.getPreco()));
        System.out.println("Preço sem desconto: R$ " + String.format("%.2f", comboFeijoada.getPrecoSemDesconto()));
        System.out.println("Desconto aplicado: " + String.format("%.1f", comboFeijoada.getDescontoPercentual()) + "%");
        System.out.println("Economia: R$ " + String.format("%.2f", comboFeijoada.getPrecoSemDesconto() - comboFeijoada.getPreco()));
        
        System.out.println("\nPreço do Mega Combo Família: R$ " + String.format("%.2f", megaCombo.getPreco()));
        System.out.println("Preço sem desconto: R$ " + String.format("%.2f", megaCombo.getPrecoSemDesconto()));
        System.out.println("Desconto aplicado: " + String.format("%.1f", megaCombo.getDescontoPercentual()) + "%");
        System.out.println("Economia: R$ " + String.format("%.2f", megaCombo.getPrecoSemDesconto() - megaCombo.getPreco()));
        
        // Exibindo cardápio compacto
        System.out.println("\n📋 CARDÁPIO COMPACTO:");
        System.out.println("-".repeat(25));
        System.out.println(cardapio.exibirCardapioCompacto());
        
        System.out.println("\n✅ DEMONSTRAÇÃO CONCLUÍDA COM SUCESSO!");
    
    }
}
