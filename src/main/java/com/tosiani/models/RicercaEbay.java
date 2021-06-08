package com.tosiani.models;

import java.util.ArrayList;

public class RicercaEbay {

    private String nome,prezzo,subtitle,link;

    public RicercaEbay(String nome,String subtitle,String prezzo,String link) {
        this.nome = nome;
        this.subtitle = subtitle;
        this.prezzo = prezzo;
        this.link = link;
    }

    public RicercaEbay(String nome,String prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public RicercaEbay(ArrayList<RicercaEbay> ricercaEbays, int i){
        this.nome = ricercaEbays.get(i).getNome();
        this.prezzo = ricercaEbays.get(i).getPrezzo();
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

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public void stampa(){
        System.out.println(getNome());
        System.out.println(getSubtitle());
        System.out.println(getPrezzo());
        System.out.println(getLink());
    }
}
