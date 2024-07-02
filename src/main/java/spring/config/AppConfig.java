package spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.utils.FileStorageService;

@Configuration
public class AppConfig 
{
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Bean
    public FileStorageService fileStorageService() 
    {
        return new FileStorageService();
    }
}
