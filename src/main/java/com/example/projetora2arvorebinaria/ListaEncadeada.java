package com.example.projetora2arvorebinaria;

public class ListaEncadeada {

    private NoLista inicio;
    private NoLista fim;

    public void adicionar(Jogador valor) {
        NoLista novo = new NoLista(valor);

        if (inicio == null) {
            inicio = novo;
            fim = novo;
        } else {
            fim.proximo = novo;
            fim = novo;
        }
    }

    public NoLista getInicio() {
        return inicio;
    }
}