package com.example.projetora2arvorebinaria;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VisualisarArvore extends Application {

    private ArvoreBinaria arvore = new ArvoreBinaria();

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Visualizador de Árvore Binária (CSV)");

        arvore.carregarCSV();

        int altura = getHeight(arvore.getRoot());

        int canvasWidth = (int) Math.pow(2, altura) * 40;
        int canvasHeight = 100 + altura * 120;

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);

        drawTree(canvas);

        //Scroll na janela
        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();

        scrollPane.setContent(canvas);

        //arrastar com o mouse
        scrollPane.setPannable(true);

        scrollPane.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);

        //Tamanho da janela
        Scene scene = new Scene(scrollPane, 1200, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //altura da arvore
    private int getHeight(No no) {
        if (no == null) return 0;
        return 1 + Math.max(getHeight(no.esquerda), getHeight(no.direita));
    }
    //desenha a arvore na janela
    private void drawTree(Canvas canvas) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.5);

        drawNode(gc,
                arvore.getRoot(),
                canvas.getWidth() / 2,
                40,
                canvas.getWidth() / 4,
                1);
    }

    //desenho dos nós
    private void drawNode(GraphicsContext gc, No no,
                          double x, double y, double xOffset, int level) {

        if (no == null) return;
        gc.strokeOval(x - 18, y - 18, 36, 36);

        //centralizar texto
        String textoNome = no.jogador.getNome();
        String textoRank = String.valueOf(no.jogador.getRank());

        gc.strokeText(textoNome, x - (textoNome.length() * 3), y - 2);
        gc.strokeText("R:" + textoRank, x - 10, y + 10);

        if (no.esquerda != null) {
            double newX = x - xOffset;
            double newY = y + 120;
            gc.strokeLine(x, y + 18, newX, newY - 18);

            drawNode(gc, no.esquerda, newX, newY, xOffset / 2, level + 1);
        }

        if (no.direita != null) {
            double newX = x + xOffset;
            double newY = y + 120;
            gc.strokeLine(x, y + 18, newX, newY - 18);

            drawNode(gc, no.direita, newX, newY, xOffset / 2, level + 1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}