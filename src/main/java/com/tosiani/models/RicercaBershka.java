package com.tosiani.models;

public class RicercaBershka {
    private String titolo;
    private float prezzo;

    public RicercaBershka(String titolo, float prezzo) {
        this.titolo = titolo;
        this.prezzo = prezzo;
    }

    public String getTitolo() {
        return titolo;
    }

    public float getPrezzo() {
        return prezzo;
    }
}
