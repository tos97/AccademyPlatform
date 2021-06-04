package com.tosiani.models;

public class RicercaEbay {

    private String nome,prezzo,subtitle,link;

    public RicercaEbay(String nome,String subtitle,String prezzo,String link) {
        this.nome = nome;
        this.subtitle = subtitle;
        this.prezzo = prezzo;
        this.link = link;
    }

    public String getNome() {
        return nome;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public String getLink() {
        return link;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void stampa(){
        System.out.println(getNome());
        System.out.println(getSubtitle());
        System.out.println(getPrezzo());
        System.out.println(getLink());
    }
}
