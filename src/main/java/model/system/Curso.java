package model.system;



public class Curso {

    public Curso(){}

    public Curso(String titulo) {
        this.titulo = titulo;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    //ENUMS 
    public static enum Coluna
    {
        ID("id"),
        TITULO("titulo");

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
     String titulo;
}

