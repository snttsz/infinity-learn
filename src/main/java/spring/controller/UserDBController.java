package spring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/database/user/")
public class UserDBController 
{

    @PostMapping("/registerUser")
    public ResponseEntity<String> handleRegisterUserDB(@RequestParam String user, @RequestParam String password, @RequestParam String fullName, @RequestParam String nickName, @RequestParam String email)
    {
        // TODO -> registrar usuario no banco de dados
        String userId = "1";
        return ResponseEntity.ok(userId);
    }

    @PostMapping("/updateProfilePic")
    public ResponseEntity<String> handleUpdateUserProfilePic(@RequestParam int user_id, @RequestParam String newFile)
    {
        String response = "1";

        // TODO -> update da foto no banco de dados

        return ResponseEntity.ok(response);
    }

}
