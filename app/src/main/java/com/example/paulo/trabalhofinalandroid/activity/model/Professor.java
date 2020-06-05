package com.example.paulo.trabalhofinalandroid.activity.model;

import java.io.Serializable;
import java.util.Date;

public class Professor implements Serializable {

    //region Variables

    private int id;
    private byte[] image;
    private String nome;
    private String dataDeNascimento;
    private String localDeNascimento;
    private String endereco;
    private String email;
    private String telefone;
    private String formacoes;

    //endregion

    //region Constructors

    public Professor() {
    }

    public Professor(int id, byte[] image, String nome, String dataDeNascimento, String localDeNascimento, String endereco, String email, String telefone, String formacoes) {
        this.id = id;
        this.image = image;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.localDeNascimento = localDeNascimento;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.formacoes = formacoes;
    }

    //endregion

    //region Getters and Setters

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getLocalDeNascimento() {
        return localDeNascimento;
    }

    public void setLocalDeNascimento(String localDeNascimento) {
        this.localDeNascimento = localDeNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(String formacoes) {
        this.formacoes = formacoes;
    }

    //endregion

    //region toString method

    @Override
    public String toString() {
        return nome;
    }

    //endregion
}