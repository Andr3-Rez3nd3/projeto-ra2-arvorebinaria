package com.example.projetora2arvorebinaria;
import java.io.*;

public class CSVService {

    public Fila<Jogador> carregar() {
        Fila<Jogador> fila = new Fila<>();

        try (InputStream is = getClass().getResourceAsStream("/players.csv")) {

            if (is == null) {
                System.out.println("CSV não encontrado!");
                return fila;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linha = br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(",");

                String nome = p[0];
                int rank = Integer.parseInt(p[1]);

                fila.enqueue(new Jogador(nome, rank));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fila;
    }

    public void salvar(Fila<Jogador> fila) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("players.csv"))) {

            bw.write("nickname,ranking");

            while (!fila.estaVazia()) {
                Jogador j = fila.dequeue();
                bw.write("\n" + j.getNome() + "," + j.getRank());
            }

            System.out.println("CSV atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}