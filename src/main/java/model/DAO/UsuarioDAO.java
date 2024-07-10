package model.DAO;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.SQLiteConnectionManager;
import model.system.Usuario;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO extends DAO<Usuario>
{
    ResultSet generatedKeys = null;

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

            SQLiteConnectionManager.desconectar();
            
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
    public void insert(Usuario usuario) throws SQLException
    {
        try
        {
            Usuario.printarUsuario(usuario);

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
            Usuario.Coluna.NOMECOMPLETO.getNomeColuna() +
            ")" +
            "VALUES(?,?,?,?,?,?);";
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getApelido());
            stmt.setString(5, usuario.getLinkFoto());
            stmt.setString(6, usuario.getNomeCompleto());


            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) 
            {
                generatedKeys.close();
                SQLiteConnectionManager.desconectar();

                throw new SQLException("Inserção falhou, nenhum usuário adicionado.");
            }
            
            generatedKeys = stmt.getGeneratedKeys();
            
            if (generatedKeys.next()) 
            {
                this.user_id = generatedKeys.getInt(1);
            } else 
            {
                generatedKeys.close();
                SQLiteConnectionManager.desconectar();
                
                throw new SQLException("Falha ao obter ID do usuário inserido.");
            }

            try 
            {
                generatedKeys.close();
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }

            SQLiteConnectionManager.desconectar();
            
        }
        catch(Exception e)
        {
            // System.out.println("Erro em inserir usuário: " + e.getMessage());
            throw new SQLException("Inserção falhou, nenhum usuário adicionado.");
        }
        finally
        {
            if (generatedKeys != null) 
            {
                try 
                {
                    generatedKeys.close();
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            }

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
                String nome_completo = resultSet.getString(Usuario.Coluna.NOMECOMPLETO.getNomeColuna());

                
                Usuario usuario = new Usuario(nome,senha,email,nome_completo,apelido);
                usuario.setLinkFoto(url_foto);
                usuario.setId(id);

                usuarios.add(usuario);
            }

            SQLiteConnectionManager.desconectar();

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
            Usuario.Coluna.NOMECOMPLETO.getNomeColuna() +
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
                String nome_completo = resultSet.getString(Usuario.Coluna.NOMECOMPLETO.getNomeColuna());

                
                Usuario usuario = new Usuario(nome,senha,email,nome_completo,apelido);
                usuario.setLinkFoto(url_foto);
                usuario.setId(id);

                SQLiteConnectionManager.desconectar();
                return usuario;
            }

            SQLiteConnectionManager.desconectar();

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
            Usuario.Coluna.NOMECOMPLETO.getNomeColuna() + " = ? " +
            
            "WHERE id = ?";
            
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getApelido());
            stmt.setString(5, usuario.getLinkFoto());
            stmt.setString(6, usuario.getNomeCompleto());
            stmt.setInt(7, usuario.getId());

            stmt.executeUpdate();

            SQLiteConnectionManager.desconectar();
            
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

    public void updateTableInfo(String attribute, String newValue, String user) throws SQLException
    {
        try
        {
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            Connection conexao = DriverManager.getConnection(url);

            String instrucao = "UPDATE " + nomeTabela + " SET " + attribute + " = ? " + 
            "WHERE " + Usuario.Coluna.NOME.getNomeColuna() + " = ?";

            PreparedStatement stmt = conexao.prepareStatement(instrucao);

            stmt.setString(1, newValue);
            stmt.setString(2, user);

            stmt.executeUpdate();
        }
        catch (Exception e)
        {
            SQLiteConnectionManager.desconectar();
            throw new SQLException("Falha em updateTableInfo -> " + e.getMessage());
        }

        SQLiteConnectionManager.desconectar();
    }

    public void updateProfilePic(int usuarioId, String newProfilePicPath) throws SQLException
    {
        try
        {
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            Connection conexao = DriverManager.getConnection(url);

            String instrucao = "UPDATE " + UsuarioDAO.nomeTabela + " SET " +
            Usuario.Coluna.URL_FOTO.getNomeColuna() + " = ? " + 
            
            " WHERE id = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);

            stmt.setString(1, newProfilePicPath);
            stmt.setInt(2, usuarioId);

            stmt.executeUpdate();

        }
        catch(Exception e)
        {
            SQLiteConnectionManager.desconectar();
            throw new SQLException("Falha em updateProfilePic -> " + e.getMessage());
        }

        SQLiteConnectionManager.desconectar();
    }

    public boolean doesUserExists(String user) throws SQLException
    {
        boolean result = false;

        try
        {
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = " SELECT " + Usuario.Coluna.ID + " FROM " + nomeTabela + 
            " WHERE " + Usuario.Coluna.NOME.getNomeColuna() + " = ? ";

            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            stmt.setString(1, user);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next())
            {
                result = true;
            }
        }
        catch (Exception e)
        {
            SQLiteConnectionManager.desconectar();

            throw new SQLException("Falha em doesUserExists -> " + e.getMessage());
        }

        SQLiteConnectionManager.desconectar();

        return result;
    }

    public Usuario authenticate(String usuario, String senha) throws SQLException 
    {
        Usuario usuarioAutenticado = null;

        try 
        {
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            Connection conexao = DriverManager.getConnection(url);

            String instrucao = "SELECT * FROM " + nomeTabela +
                    " WHERE " + Usuario.Coluna.NOME.getNomeColuna() + " = ? AND " +
                    Usuario.Coluna.SENHA.getNomeColuna() + " = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) 
            {
                usuarioAutenticado = new Usuario();
                usuarioAutenticado.setId(resultado.getInt(Usuario.Coluna.ID.getNomeColuna()));
                usuarioAutenticado.setNome(resultado.getString(Usuario.Coluna.NOME.getNomeColuna()));
                usuarioAutenticado.setSenha(resultado.getString(Usuario.Coluna.SENHA.getNomeColuna()));
                usuarioAutenticado.setEmail(resultado.getString(Usuario.Coluna.EMAIL.getNomeColuna()));
                usuarioAutenticado.setApelido(resultado.getString(Usuario.Coluna.APELIDO.getNomeColuna()));
                usuarioAutenticado.setLinkFoto(resultado.getString(Usuario.Coluna.URL_FOTO.getNomeColuna()));
                usuarioAutenticado.setNomeCompleto(resultado.getString(Usuario.Coluna.NOMECOMPLETO.getNomeColuna()));
            }
            else
            {
                throw new SQLException("FALHA!");
            }

            SQLiteConnectionManager.desconectar();
        } 
        catch (Exception e) 
        {
            SQLiteConnectionManager.desconectar();
            throw new SQLException("FALHA!");
            // System.out.println("Erro ao autenticar usuário: " + e.getMessage());
        }

        return usuarioAutenticado;
    }

    public int getUser_id() 
    {
        return user_id;
    }

    public void setUser_id(int user_id) 
    {
        this.user_id = user_id;
    }

    public static final String nomeTabela = "usuario";

    private int user_id = 0;
}
