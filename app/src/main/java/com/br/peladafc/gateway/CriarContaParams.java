package com.br.peladafc.gateway;

/**
 * Created by alan on 18/11/2016.
 */

public final class CriarContaParams {

    public final String nomeCompleto;
    public final byte[] foto;

    public CriarContaParams(String nomeCompleto)
    {
        this.nomeCompleto = nomeCompleto;
        this.foto = null;
    }

    public CriarContaParams(String nomeCompleto, byte[] foto)
    {
        this.nomeCompleto = nomeCompleto;
        this.foto = foto;
    }

}
