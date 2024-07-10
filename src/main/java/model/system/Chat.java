package model.system;

import java.util.ArrayList;

import model.system.Mensagem;

public class Chat 
{
    private int id_usuario_1;
    private int id_usuario_2;
    private int id_chat;
    
    ArrayList<Mensagem> mensagens;

    public Chat(int id_usuario_1, int id_usuario_2, int id_chat)
    {
        this.id_usuario_1 = id_usuario_1;
        this.id_usuario_2 = id_usuario_2;
        this.id_chat = id_chat;

        this.mensagens = new ArrayList<Mensagem>();
    }

    public Chat(int id_usuario_1, int id_usuario_2)
    {
        this.id_usuario_1 = id_usuario_1;
        this.id_usuario_2 = id_usuario_2;

        this.mensagens = new ArrayList<Mensagem>();
    }   

    public Chat() {}

    public void adicionarMensagem(Mensagem mensagem)
    {
        this.mensagens.add(mensagem);
    }

    public int getId_usuario_1() 
    {
        return id_usuario_1;
    }

    public void setId_usuario_1(int id_usuario_1) 
    {
        this.id_usuario_1 = id_usuario_1;
    }

    public int getId_usuario_2() 
    {
        return id_usuario_2;
    }

    public void setId_usuario_2(int id_usuario_2) 
    {
        this.id_usuario_2 = id_usuario_2;
    }

    public int getId_chat() 
    {
        return id_chat;
    }

    public void setId_chat(int id_chat) 
    {
        this.id_chat = id_chat;
    }

    public ArrayList<Mensagem> getMensagens() 
    {
        return mensagens;
    }

    public void setMensagens(ArrayList<Mensagem> mensagens) 
    {
        this.mensagens = mensagens;
    }

    public static enum Coluna
    {
        ID("id"),
        ID_USUARIO_1("id_usuario_um"),
        ID_USUARIO_2("id_usuario_dois");

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

    public static enum ColunaHas
    {
        
        CHAT_ID("chat_id"),
        MENSAGEM_ID("mensagem_id");

        public final String nomeColuna;

        ColunaHas(String nomeColuna)
        {
            this.nomeColuna = nomeColuna;
        }

        public String getNomeColuna()
        {
            return this.nomeColuna;
        }
    }
    
}
