package com.br.geld.security;

import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

import java.util.NoSuchElementException;

public interface KeyVaultClient {

    SecretClient getSecretClient();

    default KeyVaultSecret getSecret(String key) {
        KeyVaultSecret secret;
        try {
            secret = getSecretClient().getSecret(key);
        } catch (Exception ex) {
            throw new NoSuchElementException("nable to retrieve secret");
        }
        return secret;
    }
}
