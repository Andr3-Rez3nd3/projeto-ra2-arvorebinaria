package com.example.projetora2arvorebinaria;

public class ArvoreBinaria {
    private No root;
    public void insert(int valor){
        root = inserir(root, valor);
    }
    public boolean search(int valor) {
        return busca(root, valor) != null;
    }
    public Jogador remover(int valor){
        No removido = busca(root, valor);
        if (removido != null){
            root = remover(root, valor);
            return removido.jogador;
        }
        return null;
    }
    private No inserir(No atual, int valor){
        if (atual == null){
            return new No(new Jogador("Jogador" + valor, valor));
        }
        if (valor < atual.jogador.getRank()){
            atual.direita = inserir(atual.direita, valor);
        }
        else if (valor > atual.jogador.getRank()){
            atual.direita = inserir(atual.direita, valor);
        }
        return atual;
    }
    private No busca(No atual, int valor){
        if (atual == null) return null;

        if (valor == atual.jogador.getRank()) {
            return atual;
        }

        if (valor < atual.jogador.getRank()) {
            return busca(atual.esquerda, valor);
        } else {
            return busca(atual.direita, valor);
        }
    }
    private No remover(No atual, int valor){
        if (atual == null) return null;

        if (valor < atual.jogador.getRank()) {
            atual.esquerda = remover(atual.esquerda, valor);
        } else if (valor > atual.jogador.getRank()) {
            atual.direita = remover(atual.direita, valor);
        } else {
            // CASO 1: sem filhos
            if (atual.esquerda == null && atual.direita == null) {
                return null;
            }

            // CASO 2: um filho
            if (atual.esquerda == null) {
                return atual.direita;
            }

            if (atual.direita == null) {
                return atual.esquerda;
            }

            // CASO 3: dois filhos
            No sucessor = menorValor(atual.direita);
            atual.jogador = sucessor.jogador;
            atual.direita = remover(atual.direita, sucessor.jogador.getRank());
        }

        return atual;
    }
    private No menorValor(No no){
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }
    public No getRoot() {
        return root;
    }
    public void emOrdem(){
        emOrdemRec(root);
    }

    private void emOrdemRec(No atual){
        if (atual != null) {
            emOrdemRec(atual.esquerda);
            System.out.println("x" + atual.jogador.getNome());
            emOrdemRec(atual.direita);
        }
    }
}