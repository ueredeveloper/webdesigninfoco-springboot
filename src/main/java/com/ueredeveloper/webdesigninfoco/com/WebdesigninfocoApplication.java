package com.ueredeveloper.webdesigninfoco.com;

import java.sql.Timestamp;
import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ueredeveloper.webdesigninfoco.com.models.User;
import com.ueredeveloper.webdesigninfoco.com.repository.UserRepository;



@SpringBootApplication
@EntityScan("com.ueredeveloper.webdesigninfoco.com.models")
@EnableJpaRepositories("com.ueredeveloper.webdesigninfoco.com.repository")
public class WebdesigninfocoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebdesigninfocoApplication.class, args);
		
	}

	@Bean
    CommandLineRunner init(UserRepository repository) {
        return args -> {
        	//repository.deleteAll();
            LongStream.range(1, 1)
                    .mapToObj(i -> {
                        User u = new User();
                        u.setName("User " + i);
                        u.setPassword("pass" + i);
                        u.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                        u.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                        return u;
                    })
                    .map(v -> repository.save(v))
                    .forEach(System.out::println);
        };
    }

}
