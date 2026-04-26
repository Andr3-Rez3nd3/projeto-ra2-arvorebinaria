package com.example.projetora2arvorebinaria;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ArvoreBinaria {

    private No root;

    //Carregar CSV
    public void carregarCSV() {
        try {
            InputStream is = getClass().getResourceAsStream("/com/example/projetora2arvorebinaria/players.csv");

            if (is == null) {
                System.out.println("Arquivo CSV não encontrado!");
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {

                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] partes = linha.split(",");

                String nome = partes[0];
                int rank = Integer.parseInt(partes[1]);

                Jogador jogador = new Jogador(nome, rank);
                inserir(jogador);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ABB
    private void inserir(Jogador jogador) {
        root = inserirRec(root, jogador);
    }

    private No inserirRec(No atual, Jogador jogador) {

        if (atual == null) {
            return new No(jogador);
        }

        //menor vai pra esquerda
        if (jogador.getRank() < atual.jogador.getRank()) {
            atual.esquerda = inserirRec(atual.esquerda, jogador);
        }
        //maior vai pra direita
        else if (jogador.getRank() > atual.jogador.getRank()) {
            atual.direita = inserirRec(atual.direita, jogador);
        }

        return atual;
    }

    public No getRoot() {
        return root;
    }
}