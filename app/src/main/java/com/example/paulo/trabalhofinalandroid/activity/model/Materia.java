package com.example.paulo.trabalhofinalandroid.activity.model;

public class Materia {

    //region Variables

    private int id;
    private String nome;
    private String area;
    private String cargaHoraria;
    private String campus;
    private String professor;

    //endregion

    //region Constructions

    public Materia() {
    }

    public Materia(int id, String nome, String area, String cargaHoraria, String campus, String professor) {
        this.id = id;
        this.nome = nome;
        this.area = area;
        this.cargaHoraria = cargaHoraria;
        this.campus = campus;
        this.professor = professor;
    }

    //endregion

    //region Getters and Setters

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    //endregion

    //region toString method

    @Override
    public String toString() {
        return nome;
    }

    //endregion
}