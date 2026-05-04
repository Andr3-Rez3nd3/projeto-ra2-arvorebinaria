package com.example.projetora2arvorebinaria;

public class Fila<T> {

    private NoList<T> inicio;
    private NoList<T> fim;

    public void enqueue(T valor) {
        NoList<T> novo = new NoList<>(valor);

        if (fim == null) {
            inicio = fim = novo;
        } else {
            fim.proximo = novo;
            fim = novo;
        }
    }

    public T dequeue() {
        if (inicio == null) return null;

        T valor = inicio.valor;
        inicio = inicio.proximo;

        if (inicio == null) fim = null;

        return valor;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}