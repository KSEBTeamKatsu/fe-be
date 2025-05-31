package Katsu.Katsu_spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("Katsu.Katsu_spring.repository")
public class KatsuSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(KatsuSpringApplication.class, args);
	}

}
