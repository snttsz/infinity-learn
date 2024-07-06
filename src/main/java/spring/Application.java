package spring;

import org.springframework.context.annotation.ComponentScan;

import model.SQLiteConnectionManager;
import model.SQLiteTableManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan(basePackages = {"spring.controller", "spring.config"})
public class Application 
{
	public static void main(String[] args) 
	{
		//SpringApplication.run(Application.class, args);

		SQLiteTableManager sqLiteTableManager = new SQLiteTableManager();
		
		

 

	}
}