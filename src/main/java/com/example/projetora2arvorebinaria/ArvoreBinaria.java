package com.example.projetora2arvorebinaria;

public class ArvoreBinaria {

    private No root;

    public void insert(Jogador jogador) {
        root = insertN(root, jogador);
    }

    private No insertN(No atual, Jogador jogador) {
        if (atual == null) {
            return new No(jogador);
        }

        if (jogador.getRank() < atual.jogador.getRank()) {
            atual.esquerda = insertN(atual.esquerda, jogador);

        } else if (jogador.getRank() > atual.jogador.getRank()) {
            atual.direita = insertN(atual.direita, jogador);

        } else {
            atual.jogador = jogador;
        }

        return atual;
    }

    public Jogador search(int rank) {
        No no = searchN(root, rank);
        return (no != null) ? no.jogador : null;
    }

    private No searchN(No atual, int valor) {
        if (atual == null) return null;

        if (valor == atual.jogador.getRank()) return atual;

        if (valor < atual.jogador.getRank()) {
            return searchN(atual.esquerda, valor);
        } else {
            return searchN(atual.direita, valor);
        }
    }

    public void remove(int rank) {
        root = removeN(root, rank);
    }

    private No removeN(No atual, int valor) {
        if (atual == null) return null;

        if (valor < atual.jogador.getRank()) {
            atual.esquerda = removeN(atual.esquerda, valor);
        } else if (valor > atual.jogador.getRank()) {
            atual.direita = removeN(atual.direita, valor);
        } else {

            if (atual.esquerda == null) return atual.direita;
            if (atual.direita == null) return atual.esquerda;

            No sucessor = menor(atual.direita);
            atual.jogador = sucessor.jogador;
            atual.direita = removeN(atual.direita, sucessor.jogador.getRank());
        }

        return atual;
    }

    private No menor(No no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }

    public No getRoot() {
        return root;
    }
}