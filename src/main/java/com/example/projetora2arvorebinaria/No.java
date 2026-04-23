package com.example.projetora2arvorebinaria;

public class No<T> {
    Jogador jogador;
    public T valor;
    public No<T> esquerda;
    public No<T> direita;

    public No(T valor) {
        this.valor = valor;
    }
    public No(Jogador jogador){
        this.jogador = jogador;
    }
}