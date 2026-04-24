package com.example.projetora2arvorebinaria;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;

public class VisualisarArvore extends Application {

    private ArvoreBinaria arvore = new ArvoreBinaria();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visualizador de Árvore Binária - Ranking");

        carregarCSV();

        if (arvore.getRoot() == null) {
            System.out.println("Árvore está vazia!");
        }

        int height = getHeight(arvore.getRoot());
        int canvasHeight = 100 + height * 120;
        int canvasWidth = (int) Math.pow(2, height) * 60;

        if (canvasWidth < 800) canvasWidth = 800;

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        drawTree(canvas);

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, canvasWidth, canvasHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Leitura do CSV
    private void carregarCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("players (1).csv"))) {

            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {

                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] partes = linha.split(",");

                String nickname = partes[0];
                int ranking = Integer.parseInt(partes[1]);

                System.out.println("Inserindo: " + nickname + " - " + ranking);

                arvore.insert(ranking);
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler CSV:");
            e.printStackTrace();
        }
    }

    //Desenha a árvore inteira
    private void drawTree(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // DEBUG visual
        gc.setStroke(Color.GRAY);
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawNode(gc, arvore.getRoot(), canvas.getWidth() / 2, 40, canvas.getWidth() / 4);
    }

    //Desenha cada nó
    private void drawNode(GraphicsContext gc, No node, double x, double y, double offset) {
        if (node == null) return;

        System.out.println("Desenhando nó: " + node.jogador.getNome());

        //círculo
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 20, y - 20, 40, 40);

        //texto
        gc.strokeText("x" + node.jogador.getNome(), x - 15, y + 5);

        // esquerda
        if (node.esquerda != null) {
            double newX = x - offset;
            double newY = y + 100;

            gc.strokeLine(x, y + 20, newX, newY - 20);

            drawNode(gc, node.esquerda, newX, newY, offset / 2);
        }

        //direita
        if (node.direita != null) {
            double newX = x + offset;
            double newY = y + 100;

            gc.strokeLine(x, y + 20, newX, newY - 20);

            drawNode(gc, node.direita, newX, newY, offset / 2);
        }
    }

    //Altura da árvore
    private int getHeight(No node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.esquerda), getHeight(node.direita));
    }

    public static void main(String[] args) {
        launch(args);
    }
}