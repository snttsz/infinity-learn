package spring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.utils.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/files")
public class FileUploadController 
{
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload/profilepictures")
    public ResponseEntity<String> handleUploadProfilePic(@RequestParam("file") MultipartFile file, int user_id) 
    {
        try
        {
            String filename = user_id + "_" + file.getOriginalFilename();

            this.fileStorageService.storeFile(file, filename, "profilepictures");
            
            String message = "Arquivo recebido e salvo com sucesso: " + file.getOriginalFilename() + "_" + user_id;
            return ResponseEntity.ok(message);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao salvar o arquivo: " + e.getMessage());
        }
    }
}
