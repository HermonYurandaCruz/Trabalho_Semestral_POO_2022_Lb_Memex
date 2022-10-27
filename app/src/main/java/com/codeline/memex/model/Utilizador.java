package com.codeline.memex.model;

import java.util.List;

public class Utilizador {

    protected String id_do_usuario;
    protected String token;
    protected String nome_usuario;
    protected String email;
    protected String url_foto_perfil;
    protected List<Publicacao> publicacoes;
    protected List<Seguidor> seguidores;
    protected List<Seguindo> seguindo;

    public Utilizador() {
    }

    public String getId_do_usuario() {
        return id_do_usuario;
    }

    public void setId_do_usuario(String id_do_usuario) {
        this.id_do_usuario = id_do_usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl_foto_perfil() {
        return url_foto_perfil;
    }

    public void setUrl_foto_perfil(String url_foto_perfil) {
        this.url_foto_perfil = url_foto_perfil;
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    public List<Seguidor> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Seguidor> seguidores) {
        this.seguidores = seguidores;
    }

    public List<Seguindo> getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(List<Seguindo> seguindo) {
        this.seguindo = seguindo;
    }
}
