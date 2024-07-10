package model.DAO;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.SQLiteConnectionManager;
import model.system.Usuario;


import java.sql.Connection;
import java.sql.ResultSet;


public class UsuarioDAO extends DAO<Usuario>
{

    @Override
    public void delete(int id) 
    {
      try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "DELETE FROM " + UsuarioDAO.nomeTabela +  
            " WHERE id = ?;"; 
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setInt(1, id);

            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            System.out.println("Erro em deletar usuário: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }        
    }

    @Override
    public void insert(Usuario usuario) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "INSERT INTO " + UsuarioDAO.nomeTabela +  
            "(" + 
            Usuario.Coluna.NOME.getNomeColuna() + "," +
            Usuario.Coluna.SENHA.getNomeColuna() + "," +
            Usuario.Coluna.EMAIL.getNomeColuna() + "," +
            Usuario.Coluna.APELIDO.getNomeColuna() + "," +
            Usuario.Coluna.URL_FOTO.getNomeColuna() + "," +
            Usuario.Coluna.TIPO.getNomeColuna() +
            ")" +
            "VALUES(?,?,?,?,?,?);";
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getApelido());
            stmt.setString(5, usuario.getLinkFoto());
            stmt.setString(6, usuario.getTipo());


            stmt.executeUpdate();
            

        }
        catch(Exception e)
        {
            System.out.println("Erro em inserir usuário: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
    }

    @Override
    public ArrayList<Usuario> selectAll() 
    {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try
        {  
            Class.forName("org.sqlite.JDBC");
                
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "SELECT * FROM " + UsuarioDAO.nomeTabela + ";";
        
            PreparedStatement stmt = conexao.prepareStatement(instrucao);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next())
            {
                int id = resultSet.getInt(Usuario.Coluna.ID.getNomeColuna());
                String nome = resultSet.getString(Usuario.Coluna.NOME.getNomeColuna());
                String senha = resultSet.getString(Usuario.Coluna.SENHA.getNomeColuna());
                String email = resultSet.getString(Usuario.Coluna.EMAIL.getNomeColuna());
                String apelido = resultSet.getString(Usuario.Coluna.APELIDO.getNomeColuna());
                String url_foto = resultSet.getString(Usuario.Coluna.URL_FOTO.getNomeColuna());
                String tipoUsuario = resultSet.getString(Usuario.Coluna.TIPO.getNomeColuna());

                
                Usuario usuario = new Usuario(nome,senha,email,apelido,url_foto, tipoUsuario);
                usuario.setId(id);

                usuarios.add(usuario);
            }

            return usuarios;

        }
        catch (Exception e) 
        {
            System.out.println("Erro em selecionar todos os usuários: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return null;
    }

    @Override
    public Usuario selectById(int id) 
    {
        try
        {  
            Class.forName("org.sqlite.JDBC");
                
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "SELECT " +
            Usuario.Coluna.ID.getNomeColuna() + "," +
            Usuario.Coluna.NOME.getNomeColuna() + "," +
            Usuario.Coluna.SENHA.getNomeColuna() + "," +
            Usuario.Coluna.EMAIL.getNomeColuna() + "," +
            Usuario.Coluna.APELIDO.getNomeColuna() + "," +
            Usuario.Coluna.URL_FOTO.getNomeColuna() + "," +
            Usuario.Coluna.TIPO.getNomeColuna() +
            " FROM " + UsuarioDAO.nomeTabela + " WHERE id = ?;";
        
            PreparedStatement stmt = conexao.prepareStatement(instrucao);

            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next())
            {
                String nome = resultSet.getString(Usuario.Coluna.NOME.getNomeColuna());
                String senha = resultSet.getString(Usuario.Coluna.SENHA.getNomeColuna());
                String email = resultSet.getString(Usuario.Coluna.EMAIL.getNomeColuna());
                String apelido = resultSet.getString(Usuario.Coluna.APELIDO.getNomeColuna());
                String url_foto = resultSet.getString(Usuario.Coluna.URL_FOTO.getNomeColuna());
                String tipoUsuario = resultSet.getString(Usuario.Coluna.TIPO.getNomeColuna());

                
                Usuario usuario = new Usuario(nome,senha,email,apelido,url_foto, tipoUsuario);
                usuario.setId(id);

                return usuario;
            }
        }
        catch (Exception e) 
        {
            System.out.println("Erro em selecionar Usuário: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
        return null;
    }

    @Override
    public void update(Usuario usuario) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "UPDATE " + UsuarioDAO.nomeTabela + " SET " +
            Usuario.Coluna.NOME.getNomeColuna() + " = ?, " +
            Usuario.Coluna.SENHA.getNomeColuna() + " = ?, " +
            Usuario.Coluna.EMAIL.getNomeColuna() + " = ?, " +
            Usuario.Coluna.APELIDO.getNomeColuna() + " = ?, " +
            Usuario.Coluna.URL_FOTO.getNomeColuna() + " = ?, " +
            Usuario.Coluna.TIPO.getNomeColuna() + " = ? " +
            " WHERE id = ?";
            
            
            System.out.println(instrucao);
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getApelido());
            stmt.setString(5, usuario.getLinkFoto());
            stmt.setString(6, usuario.getTipo());
            stmt.setInt(7, usuario.getId());

            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            System.out.println("Erro em atualizar Usuário: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }        
    }

    public static final String nomeTabela = "usuario";

}
