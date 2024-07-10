package model.DAO;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;


import model.SQLiteConnectionManager;
import model.system.Curso;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CursoDAO extends DAO<Curso>{

    @Override
    public void delete(int id) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM " + CursoDAO.nomeTabela + " WHERE id = ?");
            
            stmt.setInt(1, id);

            stmt.executeUpdate();

        }
        catch(Exception e)
        {
            System.out.println("Erro ao deletar Curso: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
    }

    @Override
    public void insert(Curso curso) throws SQLException
    {
        try
        {
            Class.forName("org.sqlite.JDBC");   
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   
            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "INSERT INTO " + CursoDAO.nomeTabela +  
            "(" + 
            Curso.Coluna.TITULO.getNomeColuna() + ", " +
            Curso.Coluna.PROFESSOR_ID.getNomeColuna() +
            ")" +
            "VALUES(?, ?);";

            PreparedStatement stmt = conexao.prepareStatement(instrucao);            
            stmt.setString(1, curso.getTitulo());
            stmt.setInt(2, curso.getProfessor_id());
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            throw new SQLException("Falha em insert do Curso -> " + e.getMessage());
            // System.out.println("Erro em inserir Usuário: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }
        
    }

    @Override
    public ArrayList<Curso> selectAll() {
        ResultSet rs = null;
        ArrayList<Curso> cursos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/database/database.db";
            conexao = DriverManager.getConnection(url);
    
            String instrucao = "SELECT * FROM " + CursoDAO.nomeTabela; 
    
            stmt = conexao.prepareStatement(instrucao);
            rs = stmt.executeQuery();
    
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setTitulo(rs.getString("titulo"));
                cursos.add(curso);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao executar consulta: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        
        return cursos;
    }
    
    @Override
public Curso selectById(int id) {
    Curso curso = null;
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:src/main/resources/database/database.db";
        conexao = DriverManager.getConnection(url);

        String instrucao = "SELECT * FROM curso WHERE id = ?";

        stmt = conexao.prepareStatement(instrucao);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            curso = new Curso();
            curso.setId(rs.getInt("id"));
            curso.setTitulo(rs.getString("titulo"));
        }
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Erro ao buscar curso por ID: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
    
    return curso;
}


    @Override
    public void update(Curso curso) 
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);
            
            String instrucao = "UPDATE " + CursoDAO.nomeTabela + " SET " +
            Curso.Coluna.TITULO.getNomeColuna() + " = ?, " +
            "WHERE id = ?";
            
            
            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setString(1, curso.getTitulo());
            stmt.setInt(2, curso.getId());

            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            System.out.println("Erro em atualizar o Curso: " + e.getMessage());
        }
        finally
        {
            SQLiteConnectionManager.desconectar();
        }        
    }

    public ArrayList<Curso> getAllFromProfessor(int user_id) throws SQLException
    {
        ArrayList<Curso> cursos = new ArrayList<>();

        try
        {
            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:src/main/resources/database/database.db";   

            Connection conexao = DriverManager.getConnection(url);

            String instrucao = "SELECT * FROM " + nomeTabela + " WHERE " + Curso.Coluna.PROFESSOR_ID.getNomeColuna() + " = ?";

            PreparedStatement stmt = conexao.prepareStatement(instrucao);
            
            stmt.setInt(1, user_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) 
            {
                int id = rs.getInt(Curso.Coluna.ID.getNomeColuna());
                String titulo = rs.getString(Curso.Coluna.TITULO.getNomeColuna());
                int professorId = rs.getInt(Curso.Coluna.PROFESSOR_ID.getNomeColuna());

                Curso curso = new Curso(titulo, professorId);
                curso.setId(id);

                cursos.add(curso);
            }


        }
        catch(Exception e)
        {
            SQLiteConnectionManager.desconectar();
            throw new SQLException("Falha em insert do Curso -> " + e.getMessage());
        }

        SQLiteConnectionManager.desconectar();

        return cursos;
    }


    private static final String nomeTabela = "curso";
}
