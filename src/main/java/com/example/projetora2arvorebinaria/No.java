package com.example.projetora2arvorebinaria;

public class No<T> {
    public T valor;
    public No<T> proximo;

    public No(T valor) {
        this.valor = valor;
    }
}