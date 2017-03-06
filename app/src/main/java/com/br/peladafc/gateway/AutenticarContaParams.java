package com.br.peladafc.gateway;

/**
 * Created by alan on 22/02/2017.
 */

public class AutenticarContaParams {

    public final String email;
    public final String hashSenha;

    public AutenticarContaParams(String email, String hashSenha){

        this.email = email;
        this.hashSenha = hashSenha;
    }

}
