package com.example.medfounder.objetos;

// Objeto dos Hospitais que vão ser exibidos na tela de busca
public class Hospital {

    private String nome;
    private int imagem;
    private String endereco;

    public Hospital (String nome, String endereco, int imagem) {
        this.nome = nome;
        this.endereco = endereco;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
