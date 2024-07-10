package spring.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mediafiles/")
public class MediaFilesController 
{   
    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/foto-perfil/{nomeArquivo:.+}")
    public ResponseEntity<byte[]> obterFotoPerfil(@PathVariable String nomeArquivo) throws IOException 
    {
        String diretorioFotos = uploadDir + "/profilepictures/";

        Path caminhoArquivo = Paths.get(diretorioFotos, nomeArquivo);
        byte[] bytes = Files.readAllBytes(caminhoArquivo);

        String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1);
        MediaType mediaType = MediaType.IMAGE_JPEG; 

        if (extensao.equalsIgnoreCase("png")) 
        {
            mediaType = MediaType.IMAGE_PNG;
        } 
        else if (extensao.equalsIgnoreCase("gif")) 
        {
            mediaType = MediaType.IMAGE_GIF;
        }
        else if (extensao.equalsIgnoreCase("jgp") || extensao.equalsIgnoreCase("jpeg"))
        {
            mediaType = MediaType.IMAGE_JPEG;
        }

        return ResponseEntity.ok().contentType(mediaType).body(bytes);
    }
}
