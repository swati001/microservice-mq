package com.app.enquiry.service;

import com.app.enquiry.model.SecretData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

import java.net.URISyntaxException;

public class SecretDataService {

    @Autowired
    private VaultTemplate vaultTemplate;


    public void secureCredentials(SecretData credentials) throws URISyntaxException {
        vaultTemplate.write("secretdata/myapp", credentials);
    }


    public SecretData accessCredentials() throws URISyntaxException {
        VaultResponseSupport<SecretData> response = vaultTemplate.read("secretdata/myapp", SecretData.class);
        return response.getData();
    }
}
