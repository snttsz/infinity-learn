package model.system;

public class Mensagem 
{
    private int id;
    private String texto;
    private int id_autor;
    private int id_receptor;

    public int getId_autor() 
    {
        return id_autor;
    }

    public void setId_autor(int id_autor) 
    {
        this.id_autor = id_autor;
    }

    public int getId_receptor() 
    {
        return id_receptor;
    }

    public void setId_receptor(int id_receptor) 
    {
        this.id_receptor = id_receptor;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getTexto() 
    {
        return texto;
    }

    public void setTexto(String texto) 
    {
        this.texto = texto;
    }

    public static enum Coluna
    {
        ID("id"),
        ID_AUTOR("id_usuario_um"),
        ID_RECEPTOR("id_usuario_dois"),
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
}
