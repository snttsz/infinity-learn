package model.system;

public class Questao {
    public Questao(){}

    public Questao(String texto) {
        this.texto = texto;
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

    //ENUMS 
    public static enum Coluna
    {
        ID("id"),
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
     String texto;
}

