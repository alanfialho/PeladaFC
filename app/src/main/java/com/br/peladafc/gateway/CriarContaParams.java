package com.br.peladafc.gateway;

/**
 * Created by alan on 18/11/2016.
 */

public final class CriarContaParams {

    public final String nomeCompleto;
    public final String email;
    public final String hashSenha;
    public final byte[] foto;

    public CriarContaParams(String nomeCompleto, String email, String hashSenha)
    {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.hashSenha = hashSenha;
        this.foto = null;
    }

    public CriarContaParams(String nomeCompleto, String email, String hashSenha, byte[] foto)
    {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.hashSenha = hashSenha;
        this.foto = foto;
    }

}
