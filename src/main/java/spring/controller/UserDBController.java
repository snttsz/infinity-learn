package spring.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.ResponseEntity;

import model.DAO.UsuarioDAO;
import model.system.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/database/user/")
public class UserDBController 
{

    @Autowired
    UsuarioDAO usuarioDao;
    

    @PutMapping("/registerUser")
    public ResponseEntity<String> handleRegisterUserDB(@RequestParam String user, @RequestParam String password, @RequestParam String fullName, @RequestParam String nickName, @RequestParam String email)
    {
        Usuario usuario = new Usuario(
            user,
            password, 
            email,
            fullName,
            nickName
        );

        try
        {
            this.usuarioDao.insert(usuario);
            
            String userId = String.valueOf(this.usuarioDao.getUser_id());
            // System.out.println("USUARIO ---------------------> " + userId);
            return ResponseEntity.ok(userId);
        }
        catch(SQLException e)
        {
            return ResponseEntity.ok("0");
        }        
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> handleUpdateUpdatePassword(@RequestParam String userName, @RequestParam String newPassword)
    {
        try
        {
            usuarioDao.updateTableInfo(Usuario.Coluna.SENHA.getNomeColuna(), newPassword, userName);
            
            return ResponseEntity.ok("1");
        }
        catch (Exception e)
        {
            return ResponseEntity.ok("0");
        }
    }

    @PutMapping("/updateProfilePic")
    public ResponseEntity<String> handleUpdateUserProfilePic(@RequestParam int user_id, @RequestParam String newFile)
    {
        try
        {
            String filename = String.valueOf(user_id) + "_" + newFile;

            usuarioDao.updateProfilePic(user_id, filename);

            return ResponseEntity.ok("1");
        }
        catch(Exception e)
        {
            return ResponseEntity.ok("0");
        }
    }

    @PutMapping("/doesUserExists")
    public ResponseEntity<String> handleCheckUserExists(@RequestParam String user)
    {
        String response = "0";

        try
        {
            if (this.usuarioDao.doesUserExists(user))
            {
                response = "1";
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/checkCredentials")
    public ResponseEntity<String> handleCheckCredentials(@RequestParam String user, @RequestParam String password)
    {   
        String response;

        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            Usuario usuario = this.usuarioDao.authenticate(user, password);
            
            response = objectMapper.writeValueAsString(usuario);

            return ResponseEntity.ok(response);
        }
        catch (SQLException e)
        {
            return ResponseEntity.ok("null");
        }
        catch (IOException e)
        {
            return ResponseEntity.ok("null");
        }
    }   

}
