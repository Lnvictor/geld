package com.br.geld;

import com.br.geld.configuration.DatabaseProperties;
import com.br.geld.configuration.KeyVaultProperties;
import com.br.geld.security.SecretClientManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({KeyVaultProperties.class, DatabaseProperties.class})
public class GeldApplication implements CommandLineRunner {

	private KeyVaultProperties properties;
	private DatabaseProperties databaseProperties;

	public GeldApplication(KeyVaultProperties properties, DatabaseProperties databaseProperties){
		this.properties = properties;
		this.databaseProperties = databaseProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(GeldApplication.class, args);
	}

	@Override
	public void run(String[] args){
		if (System.getenv("APP_ENV") != null){
			SecretClientManager clientManager = new SecretClientManager();
			clientManager.configure(this.properties, this.databaseProperties);
			clientManager.getVaultCredentials();
		}
	}
}
