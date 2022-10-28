package com.codeline.memex.model;

public class Publicacao {

    private String nome_usuario;
    private String url_imagem;
    private String url_foto_usuario;
    private String texto_publicacao;
    private String id_post;
    private String id_usuario;
    private String data_publicacao;
    private String hora_publicacao;

    public Publicacao() {
    }

    public Publicacao(String nome_usuario, String url_imagem, String url_foto_usuario, String texto_publicacao, String id_post, String id_usuario, String data_publicacao, String hora_publicacao) {
        this.nome_usuario = nome_usuario;
        this.url_imagem = url_imagem;
        this.url_foto_usuario = url_foto_usuario;
        this.texto_publicacao = texto_publicacao;
        this.id_post = id_post;
        this.id_usuario = id_usuario;
        this.data_publicacao = data_publicacao;
        this.hora_publicacao = hora_publicacao;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }


    public String getUrl_foto_usuario() {
        return url_foto_usuario;
    }

    public void setUrl_foto_usuario(String url_foto_usuario) {
        this.url_foto_usuario = url_foto_usuario;
    }

    public String getTexto_publicacao() {
        return texto_publicacao;
    }

    public void setTexto_publicacao(String texto_publicacao) {
        this.texto_publicacao = texto_publicacao;
    }

    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(String data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public String getHora_publicacao() {
        return hora_publicacao;
    }

    public void setHora_publicacao(String hora_publicacao) {
        this.hora_publicacao = hora_publicacao;
    }
}
