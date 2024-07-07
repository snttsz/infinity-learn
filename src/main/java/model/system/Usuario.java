package model.system;

public class Usuario 
{
    
    /* CONSTRUTORES */
    public Usuario(String nome, String senha, String email, String apelido, String linkFoto, String tipo) 
    {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.apelido = apelido;
        this.linkFoto = linkFoto;
        this.tipo = tipo;
    }

    /* FUNÇÕES GERAIS */
    public static void printarUsuario(Usuario usuario)
    {
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome:" + usuario.getNome());
        System.out.println("E-mail: " + usuario.getEmail());
        System.out.println("Senha: " + usuario.getSenha());
        System.out.println("Url_foto: " + usuario.getLinkFoto());
        System.out.println("\n");
    }

    public Usuario(){};
    
    /* GETTERS E SETTERS */
    public int getId() 
    {
        return id;
    }
    public void setId(int id) 
    {
        this.id = id;
    }
    public String getNome() 
    {
        return nome;
    }
    public void setNome(String nome) 
    {
        this.nome = nome;
    }
    public String getSenha() 
    {
        return senha;
    }
    public void setSenha(String senha) 
    {
        this.senha = senha;
    }
    public String getEmail() 
    {
        return email;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }
    public String getApelido() 
    {
        return apelido;
    }
    public void setApelido(String apelido) 
    {
        this.apelido = apelido;
    }
    public String getLinkFoto() 
    {
        return linkFoto;
    }
    public void setLinkFoto(String linkFoto) 
    {
        this.linkFoto = linkFoto;
    }
    public String getTipo() 
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    /* 
     * Enum com as tabelas da classe
     */
    public static enum Coluna
    {
        ID("id"),
        NOME("nome"),
        SENHA("senha"),
        EMAIL("email"),
        APELIDO("apelido"),
        URL_FOTO("url_foto"),
        TIPO("tipo");

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
    


    /* 
     * Enum com os tipos possíveis de usuários
     */
    public static enum TipoUsuario
    {
        ALUNO("aluno"),
        PROFESSOR("professor");

        public final String tipoUsuario;

        TipoUsuario(String nomeDoTipo)
        {
            this.tipoUsuario = nomeDoTipo;
        }

        public String getNomeDoTIpo()
        {
            return this.tipoUsuario;
        }
    }


    /* ATRIBUTOS */
    int id;
    String nome;
    String senha;
    String email;
    String apelido;
    String linkFoto;
    String tipo;

    
}
