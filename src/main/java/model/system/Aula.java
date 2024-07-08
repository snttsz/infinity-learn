package model.system;

import java.util.ArrayList;

public class Aula
{
    /* CONSTRUTORES */
    public Aula(){};

    
    public Aula(ArrayList<Tarefa> tarefas, int idCurso)
    {
        this.tarefas = tarefas;
        this.idCurso = idCurso;
    }


    /* GETTERS AND SETTERS */
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public ArrayList<Tarefa> getTarefas() 
    {
        return tarefas;
    }

    public void setTarefas(ArrayList<Tarefa> tarefas) 
    {
        this.tarefas = tarefas;
    }
    
    public int getIdCurso() 
    {
        return idCurso;
    }

    public void setIdCurso(int idCurso) 
    {
        this.idCurso = idCurso;
    }
    
    /* ENUM */
    public static enum Coluna
    {
        ID("id"),
        CURSO_idCurso("Curso_idCurso");

        public final String nomeColuna;

        Coluna(String nomeColuna)
        {
            this.nomeColuna = nomeColuna;
        }

        public String getNomeColuna()
        {
            return this.nomeColuna;
        }
    }

    /* ATRIBUTOS */
    int id;
    int idCurso;
    ArrayList<Tarefa> tarefas;



}
