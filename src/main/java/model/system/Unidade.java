package model.system;

public class Unidade {
    public Unidade(){}

    public Unidade(int numero) {
        this.numero = numero;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    //ENUMS 
    public static enum Coluna
    {
        ID("id"),
        NUMERO("numero");

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
     int numero;
}
