/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Amade
 */
public class Estudante implements Serializable{
    
    private static final long serialVersionUID = -299482035708790407L;
    
    String nome;
    String curso;
    String dataDeNascimento;
    Date dataCadastro = new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
    int numeroDoEstudante;
    boolean statusRemovido = false;

    public Estudante() {
    }

    public Estudante(String nome, String curso, String dataDeNascimento, int numeroDoEstudante) {
        this.nome = nome;
        this.curso = curso;
        this.dataDeNascimento = dataDeNascimento;
        this.numeroDoEstudante = numeroDoEstudante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public int getNumeroDoEstudante() {
        return numeroDoEstudante;
    }

    public void setNumeroDoEstudante(int numeroDoEstudante) {
        this.numeroDoEstudante = numeroDoEstudante;
    }

    public boolean isStatusRemovido() {
        return statusRemovido;
    }

    public void setStatusRemovido(boolean statusRemovido) {
        this.statusRemovido = statusRemovido;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }
    
    
    @Override
    public String toString() {
        return "Estudante {" + "nome=" + nome + ", numeroDoEst=" + numeroDoEstudante + ", DataNasc=" + dataDeNascimento + '}';
    }
    
    
}
