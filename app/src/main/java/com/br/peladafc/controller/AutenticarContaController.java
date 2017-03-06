package com.br.peladafc.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.br.peladafc.gateway.AutenticarContaParams;
import com.br.peladafc.gateway.GatewayOperationException;
import com.br.peladafc.gateway.PeladaFCWsGateway;
import com.br.peladafc.utils.CryptoHelper;
import com.br.peladafc.utils.MemoriaCompartilhadaHelper;
import com.br.peladafc.view.AutenticarContaActivity;

import java.util.UUID;

/**
 * Created by alan on 22/02/2017.
 */

public class AutenticarContaController {

    private AutenticarContaActivity activity;

    public AutenticarContaController(AutenticarContaActivity activity){

        this.activity = activity;
    }

    public void ExecutarAutenticarConta() {

        activity.mostrarMensagemDeEspera();
        String hash = CryptoHelper.GenerateSHA256(activity.getSenha());
        AutenticarContaTask task = new AutenticarContaTask();
        AutenticarContaParams params = new AutenticarContaParams(activity.getEmail(), hash);
        task.execute(params);
    }

    private class AutenticarContaTask extends AsyncTask<AutenticarContaParams, Void, UUID> {

        @Override
        protected UUID doInBackground(AutenticarContaParams... params) {

            try {
                return PeladaFCWsGateway.AutenticarConta(params[0]);
            } catch (GatewayOperationException e) {
                activity.mostrarMensagem(e.getMessage());
            }catch (Exception e){
                activity.mostrarMensagem("Oops! houve um problema ao entrar tente novamente mais tarde");
                Log.e("Generic Error", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(UUID contaId){
            activity.esconderMensagemDeEspera();

            if(contaId != null){
                //TODO:redirecionar para tela home
                MemoriaCompartilhadaHelper.getInstance().dados.put("contaId", contaId);
                activity.mostrarMensagem("entrou!");
            }
        }
    }
}
