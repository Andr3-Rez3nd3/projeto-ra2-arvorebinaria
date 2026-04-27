package com.example.projetora2arvorebinaria;

import java.io.*;
import java.util.*;

public class CSVService {

    public List<Jogador> carregar() {
        List<Jogador> lista = new ArrayList<>();

        try (InputStream is = getClass().getResourceAsStream("/players.csv")) {

            if (is == null) {
                System.out.println("CSV não encontrado!");
                return lista;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linha = br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(",");

                String nome = p[0];
                int rank = Integer.parseInt(p[1]);

                lista.add(new Jogador(nome, rank));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    public void salvar(List<Jogador> jogadores) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("players.csv"))) {

            bw.write("nickname,ranking");

            for (Jogador j : jogadores) {
                bw.write("\n" + j.getNome() + "," + j.getRank());
            }

            System.out.println("CSV atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}