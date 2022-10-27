package com.codeline.memex;

public class Usuario {

    private String nome;
    private String url_image;


    public Usuario(String nome, String url_image) {
        this.nome = nome;
        this.url_image = url_image;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
