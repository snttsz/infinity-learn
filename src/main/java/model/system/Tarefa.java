package model.system;

import java.util.ArrayList;

public class Tarefa 
{

    /* CONSTRUTORES */
    public Tarefa(){};

    public Tarefa(double notaMaxima, int idAula) 
    {
        this.notaMaxima = notaMaxima;
        this.idAula = idAula;
    }

    /* FUNÇÕES GERAIS */
    public static void printarTarefa(Tarefa tarefa)
    {
        System.out.println("ID: " + tarefa.getId());
        System.out.println("Id Aula:" + tarefa.idAula);
        System.out.println("Nota Maximo: " + tarefa.getNotaMaxima());
        System.out.println("QUESTÕES: \n");
        for(Questao q : tarefa.getQuestoes())
        {
            Questao.printarquestao(q);
        }
        System.out.println("\n");
    }
    // Getters e Setters
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public double getNotaMaxima() 
    {
        return notaMaxima;
    }

    public void setNotaMaxima(double notaMaxima) 
    {
        this.notaMaxima = notaMaxima;
    }

    /*Trazer da classe aula */
    public int getIdAula() 
    {
        return idAula;
    }

    public void setIdAula(int idAula) 
    {
        this.idAula = idAula;
    }

    public ArrayList<Questao> getQuestoes() 
    {
        return questoes;
    }

    public void setQuestoes(ArrayList<Questao> questoes) 
    {
        this.questoes = questoes;
    }

    //ENUMS 
    public static enum Coluna
    {
        ID("id"),
        NOTAMAXIMA("nota_maxima"),
        AULAID("Aula_idAula");

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
    double notaMaxima;
    int idAula;
    ArrayList<Questao> questoes;

}