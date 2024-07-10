package model.DAO;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.sqlite.SQLiteConnection;

import model.SQLiteConnectionManager;
import model.system.Curso;
import model.system.Mensagem;
import model.system.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.system.Chat;;

public class ChatDAO extends DAO<Chat>
{
    @Override
    public void delete(int id)
    {

    }

    public void insert(Chat chat) throws SQLException
    {
        Connection conexao = null;
        PreparedStatement stmt_first = null;

        try 
        {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection(URL);

            String instrucao = "INSERT INTO " + nomeTabela +
                    "(id_usuario_um, id_usuario_dois) " +
                    "VALUES (?, ?)";

            stmt_first = conexao.prepareStatement(instrucao);
            stmt_first.setInt(1, chat.getId_usuario_1());
            stmt_first.setInt(2, chat.getId_usuario_2());

            int linhasAfetadas = stmt_first.executeUpdate();

            if (linhasAfetadas == 0) 
            {
                throw new SQLException("Inserção de chat falhou, nenhum chat adicionado.");
            }

            if (!chat.getMensagens().isEmpty())
            {
                if (!chat.getMensagens().isEmpty()) 
                {
                    for (Mensagem mensagem : chat.getMensagens()) 
                    {
                        String instrucaoMensagem = "INSERT INTO " + nomeTabelaMensagem + " " +
                                                   "(id_autor, id_receptor, texto) " +
                                                   "VALUES (?, ?, ?)";
                
                        try (PreparedStatement stmt = conexao.prepareStatement(instrucaoMensagem, Statement.RETURN_GENERATED_KEYS)) 
                        {
                            stmt.setInt(1, mensagem.getId_autor());
                            stmt.setInt(2, mensagem.getId_receptor());
                            stmt.setString(2, mensagem.getTexto());
                
                            int linhasAfetadasMensagem = stmt.executeUpdate();
                
                            if (linhasAfetadasMensagem == 0) 
                            {
                                throw new SQLException("Inserção de mensagem falhou, nenhuma mensagem adicionada.");
                            }
                
                            ResultSet generatedKeys = stmt.getGeneratedKeys();
                
                            if (generatedKeys.next()) 
                            {
                                int idMensagemInserida = generatedKeys.getInt(1);
                
                                String instrucaoChatMensagem = "INSERT INTO chat_has_mensagem " +
                                                               "(chat_id, mensagem_id) " +
                                                               "VALUES (?, ?)";
                
                                try (PreparedStatement stmtChatMensagem = conexao.prepareStatement(instrucaoChatMensagem)) 
                                {
                                    stmtChatMensagem.setInt(1, chat.getId_chat());
                                    stmtChatMensagem.setInt(2, idMensagemInserida);
                
                                    stmtChatMensagem.executeUpdate();
                                }
                            } 
                            else 
                            {
                                throw new SQLException("Falha ao obter ID da mensagem inserida.");
                            }
                        }

                    }
                }
                
            }
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            SQLiteConnectionManager.desconectar();
            throw new SQLException("Erro ao inserir chat: " + e.getMessage());
        } 

        SQLiteConnectionManager.desconectar();
    }

    @Override
    public ArrayList<Chat> selectAll() 
    {
        return null;
    }

    @Override
    public Chat selectById(int id) 
    {
        return null;
    }

    @Override
    public void update(Chat chat) 
    {

    }

    public Chat getChatByUserId(int userId) throws SQLException
    {
        Chat chat = null;

        Connection conexao = null;
        PreparedStatement stmt = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection(URL);

            // String instrucao = "SELECT * FROM " + 
        }
        catch (Exception e)
        {
            SQLiteConnectionManager.desconectar();
            throw new SQLException("Erro em getChatByUserId -> " + e.getMessage());
        }

        SQLiteConnectionManager.desconectar();

        return chat;
    }

    public static final String nomeTabela = "chat";
    public static final String nomeTabelaMensagem = "mensagem";
    public static final String nomeTabelaHas = "chat_has_mensagem";

    private static final String URL = "jdbc:sqlite:src/main/resources/database/database.db";
}
