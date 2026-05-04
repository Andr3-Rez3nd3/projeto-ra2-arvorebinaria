package com.example.projetora2arvorebinaria;

public class NoList<T> {
    public T valor;
    public NoList<T> proximo;

    public NoList(T valor) {
        this.valor = valor;
    }
}