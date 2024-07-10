package model.system;



public class Curso {

    public Curso(){}

    public Curso(String titulo, int professor_id) {
        this.titulo = titulo;
        this.professor_id = professor_id;
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
        TITULO("titulo"),
        PROFESSOR_ID("professor_id");

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

     public int getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(int professor_id) {
        this.professor_id = professor_id;
    }

    /* ATRIBUTOS */
    int id;
    int professor_id;
    String titulo;
}

