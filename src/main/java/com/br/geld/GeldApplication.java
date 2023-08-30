package com.br.geld;

import com.br.geld.security.KeyVaultAutoconfiguredClient;
import com.br.geld.security.KeyVaultClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeldApplication {
	private final KeyVaultClient keyVaultClient;

	public GeldApplication(@Qualifier(value = "KeyVaultAutoconfiguredClient") KeyVaultAutoconfiguredClient keyVaultAutoconfiguredClient) {
		this.keyVaultClient = keyVaultAutoconfiguredClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(GeldApplication.class, args);
	}
}
