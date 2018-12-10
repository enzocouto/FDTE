package br.com.ecouto.fdte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.ecouto.fdte.service.CompromissoRecorrenteService;


@EnableScheduling
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Autowired
	CompromissoRecorrenteService service;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
      

    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(getClass());
    }	
}
