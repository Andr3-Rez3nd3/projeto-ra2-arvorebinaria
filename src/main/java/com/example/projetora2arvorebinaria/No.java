package com.example.projetora2arvorebinaria;

public class No<T> {
    public Jogador jogador;
    public No<T> esquerda;
    public No<T> direita;

    public No(Jogador jogador){
        this.jogador = jogador;
    }
}