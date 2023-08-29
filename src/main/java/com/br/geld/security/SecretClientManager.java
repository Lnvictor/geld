package com.br.geld.security;


import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.br.geld.configuration.DatabaseProperties;
import com.br.geld.configuration.KeyVaultProperties;
import org.springframework.stereotype.Component;

@Component("SecretClientManager")
public class SecretClientManager {
    private KeyVaultProperties properties;
    private DatabaseProperties databaseProperties;
    private SecretClient client;

    public void configure(KeyVaultProperties properties, DatabaseProperties databaseProperties) {
        this.setProperties(properties);
        this.setDatabaseProperties(databaseProperties);

        this.client = new SecretClientBuilder()
                .vaultUrl(this.properties.getVaultUrl())
                .credential(new ClientSecretCredentialBuilder()
                        .tenantId(this.properties.getTenantId())
                        .clientId(this.properties.getClientId())
                        .clientSecret(this.properties.getClientSecret())
                        .build()).buildClient();
    }

    public KeyVaultProperties getProperties() {
        return properties;
    }

    private void setProperties(KeyVaultProperties properties) {
        this.properties = properties;
    }

    private void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public void getVaultCredentials(){
        if (this.client == null){
            throw new NullPointerException("Inner attribute is null");
        }
        this.databaseProperties.setUrl(this.client.getSecret("gelddb-server").getValue());
        this.databaseProperties.setUsername(this.client.getSecret("geld-db-user").getValue());
        this.databaseProperties.setPassword(this.client.getSecret("gelddb-pwd").getValue());
    }
}
