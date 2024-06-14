package br.com.luscadevs.msprocessos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MsProcessosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProcessosApplication.class, args);
	}

}
