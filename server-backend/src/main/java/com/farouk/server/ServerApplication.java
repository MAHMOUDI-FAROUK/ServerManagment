package com.farouk.server;

import com.farouk.server.Model.Server;
import com.farouk.server.enumeration.Status;
import com.farouk.server.repo.ServerRepos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.farouk.server.enumeration.Status.SERVER_DOWN;
import static com.farouk.server.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run (ServerRepos serverRepos){
		return args -> {
			serverRepos.save(new Server(null, "192.168.1.1" , "Microsoft" , "16 GB" , "Personal PC" ,
					"http://localhost:8080/server/image/server1.png" , SERVER_DOWN));
			serverRepos.save(new Server(null, "192.168.1.2" , "Ubunti" , "32 GB" , "Farouk PC" ,
					"http://localhost:8080/server/image/server2.png" , SERVER_UP));
			serverRepos.save(new Server(null, "192.168.1.3" , "Lunix" , "64 GB" , "Ahlem PC" ,
					"http://localhost:8080/server/image/server3.png" , SERVER_DOWN));
		};
	}

}
