package com.example.projetora2arvorebinaria;

public class No {
    Jogador jogador;
    public No esquerda;
    public No direita;

    public No(Jogador jogador){
        this.jogador = jogador;
        this.esquerda = null;
        this.direita = null;
    }
}