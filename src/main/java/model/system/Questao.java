package model.system;

public class Questao 
{
    /* CONSTRUTORES */
    public Questao(){}

    public Questao(String texto, int idTarefa) 
    {
        this.idTarefa = idTarefa;
        this.texto = texto;
    }

    /* FUNÇÕES GERAIS */
    public static void printarquestao(Questao questao)
    {
        System.out.println("ID: " + questao.getId());
        System.out.println("Id tarefa:" + questao.getIdTarefa());
        System.out.println("Texto: " + questao.getTexto());
        System.out.println("\n");
    }
    // Getters e setters
    public int getId() 
    {
        return id;
    }
    public void setId(int id) 
    {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIdTarefa() 
    {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) 
    {
        this.idTarefa = idTarefa;
    }

    //ENUMS 
    public static enum Coluna
    {
        ID("id"),
        ID_TAREFA("Tarefa_idTarefa"),
        TEXTO("texto");

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
     int idTarefa;
     String texto;

}

