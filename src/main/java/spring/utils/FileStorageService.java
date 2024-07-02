package spring.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService 
{
    @Value("${file.upload-dir}")
    private String uploadDir;

    public void storeFile(MultipartFile file, String filename, String type) throws Exception 
    {
        Path directoryPath = Paths.get(uploadDir + type);
        Path filePath = directoryPath.resolve(filename);

        if (Files.exists(filePath)) 
        {
            Files.delete(filePath);
        }

        Files.createDirectories(directoryPath);
        file.transferTo(filePath);
    }
}

