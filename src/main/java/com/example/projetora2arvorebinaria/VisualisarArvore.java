package com.example.projetora2arvorebinaria;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VisualisarArvore extends Application {

    private ArvoreBinaria arvore = new ArvoreBinaria();

    @Override
    public void start(Stage stage){

        arvore.carregarCSV();

        if(arvore.getRoot() == null){
            System.out.println("Árvore vazia - CSV não carregado");
        }

        int altura = getAltura(arvore.getRoot());

        int largura = Math.max(1200, (int)Math.pow(2, altura) * 50);
        int alturaTela = Math.max(800, altura * 120);

        Canvas canvas = new Canvas(largura, alturaTela);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, largura, alturaTela);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        desenhar(gc, arvore.getRoot(), largura / 2, 60, largura / 4);

        Group root = new Group(canvas);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Árvore Binária");
        stage.show();
    }

    private void desenhar(GraphicsContext gc, No no, double x, double y, double offset){
        if(no == null) return;

        gc.strokeOval(x - 20, y - 20, 40, 40);
        gc.strokeText("x " + no.jogador.getNome(), x - 30, y + 5);

        if(no.esquerda != null){
            double novoX = x - offset;
            double novoY = y + 100;

            gc.strokeLine(x, y + 20, novoX, novoY - 20);
            desenhar(gc, no.esquerda, novoX, novoY, offset / 2);
        }

        if(no.direita != null){
            double novoX = x + offset;
            double novoY = y + 100;

            gc.strokeLine(x, y + 20, novoX, novoY - 20);
            desenhar(gc, no.direita, novoX, novoY, offset / 2);
        }
    }

    private int getAltura(No no){
        if(no == null) return 0;

        int esq = getAltura(no.esquerda);
        int dir = getAltura(no.direita);

        return 1 + (esq > dir ? esq : dir);
    }

    public static void main(String[] args){
        launch();
    }
}