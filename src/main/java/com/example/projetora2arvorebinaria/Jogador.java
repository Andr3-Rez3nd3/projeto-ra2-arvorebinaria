package com.example.projetora2arvorebinaria;

public class Jogador {
    public String nome;
    public int rank;

    public Jogador(String nome, int rank){
        this.nome = nome;
        this.rank = rank;
    }

    public String getNome() {
        return nome;
    }

    public int getRank() {
        return rank;
    }
}

