package com.example.projetora2arvorebinaria;

import java.io.*;

public class ArvoreBinaria {
    private No root;
    private final String caminho = "src/main/resources/players.csv";

    public void insert(int valor){
        Jogador j = buscarNoCSV(valor);
        if(j != null){
            root = insertN(root, j);
        }
    }

    public boolean search(int valor){
        return searchN(root, valor) != null;
    }

    public Jogador remove(int valor){
        Jogador j = buscarNoCSV(valor);
        if(j != null){
            root = removeN(root, valor);
            removerDoCSV(valor);
        }
        return j;
    }

    private No insertN(No atual, Jogador jogador){
        if(atual == null){
            adicionarNoCSV(jogador);
            return new No(jogador);
        }

        if(jogador.getRank() < atual.jogador.getRank()){
            atual.esquerda = insertN(atual.esquerda, jogador);
        } else if(jogador.getRank() > atual.jogador.getRank()){
            atual.direita = insertN(atual.direita, jogador);
        }

        return atual;
    }

    private No searchN(No atual, int valor){
        if(atual == null) return null;

        if(valor == atual.jogador.getRank()) return atual;

        if(valor < atual.jogador.getRank()){
            return searchN(atual.esquerda, valor);
        } else {
            return searchN(atual.direita, valor);
        }
    }

    private No removeN(No atual, int valor){
        if(atual == null) return null;

        if(valor < atual.jogador.getRank()){
            atual.esquerda = removeN(atual.esquerda, valor);
        } else if(valor > atual.jogador.getRank()){
            atual.direita = removeN(atual.direita, valor);
        } else {

            if(atual.esquerda == null && atual.direita == null){
                return null;
            }

            if(atual.esquerda == null){
                return atual.direita;
            }

            if(atual.direita == null){
                return atual.esquerda;
            }

            int sucessor = sucessor(atual.direita);
            atual.jogador = buscarNoCSV(sucessor);
            atual.direita = removeN(atual.direita, sucessor);
        }

        return atual;
    }

    private int sucessor(No no){
        if(no.esquerda == null){
            return no.jogador.getRank();
        }
        return sucessor(no.esquerda);
    }

    private int anterior(No no){
        if(no.direita == null){
            return no.jogador.getRank();
        }
        return anterior(no.direita);
    }

    private Jogador buscarNoCSV(int rank){
        try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
            String linha = br.readLine();
            while((linha = br.readLine()) != null){
                String[] p = linha.split(",");
                if(Integer.parseInt(p[1]) == rank){
                    return new Jogador(p[0], Integer.parseInt(p[1]));
                }
            }
        } catch(Exception e){}
        return null;
    }

    private void adicionarNoCSV(Jogador j){
        try(FileWriter fw = new FileWriter(caminho, true);
            BufferedWriter bw = new BufferedWriter(fw)){
            bw.write("\n" + j.getNome() + "," + j.getRank());
        } catch(Exception e){}
    }

    private void removerDoCSV(int rank){
        File input = new File(caminho);
        File temp = new File("temp.csv");

        try(BufferedReader br = new BufferedReader(new FileReader(input));
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp))){

            String linha = br.readLine();
            bw.write(linha);

            while((linha = br.readLine()) != null){
                String[] p = linha.split(",");
                if(Integer.parseInt(p[1]) != rank){
                    bw.write("\n" + linha);
                }
            }

        } catch(Exception e){}

        input.delete();
        temp.renameTo(input);
    }

    public void carregarCSV(){
        try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
            String linha = br.readLine();
            while((linha = br.readLine()) != null){
                String[] p = linha.split(",");
                Jogador j = new Jogador(p[0], Integer.parseInt(p[1]));
                root = insertN(root, j);
            }
        } catch(Exception e){}
    }

    public No getRoot(){
        return root;
    }
}