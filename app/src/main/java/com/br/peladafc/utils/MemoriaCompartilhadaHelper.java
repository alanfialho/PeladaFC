package com.br.peladafc.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alan on 09/11/2016.
 */

public final class MemoriaCompartilhadaHelper {

    public final Map<String,Object> dados;
    private static MemoriaCompartilhadaHelper memoriaCompartilhadaHelper;

    private MemoriaCompartilhadaHelper(){
        dados = new HashMap<String, Object>();
    }

    public static MemoriaCompartilhadaHelper getInstance(){
        if(memoriaCompartilhadaHelper == null)
            memoriaCompartilhadaHelper = new MemoriaCompartilhadaHelper();

        return memoriaCompartilhadaHelper;
    }
}
