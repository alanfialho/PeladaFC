package com.br.peladafc.gateway;

/**
 * Created by alan on 18/11/2016.
 */

public final class CriarContaParams {

    public final String nomeCompleto;
    public final String email;
    public final String senha;
    public final byte[] foto;

    public CriarContaParams(String nomeCompleto, String email, String senha)
    {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.foto = null;
    }

    public CriarContaParams(String nomeCompleto, String email, String senha, byte[] foto)
    {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }

}
