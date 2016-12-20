package com.br.peladafc.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.br.peladafc.gateway.CriarContaParams;
import com.br.peladafc.gateway.GatewayOperationException;
import com.br.peladafc.gateway.PeladaFCWsGateway;
import com.br.peladafc.utils.MemoriaCompartilhadaHelper;
import com.br.peladafc.view.CriarContaActivity;

import java.util.UUID;

/**
 * Created by alan on 03/11/2016.
 */

public class CriarContaController {

    private CriarContaActivity activity;

    public CriarContaController(CriarContaActivity activity){
        this.activity = activity;
    }

    public void ExecutarCriarConta(){
        activity.mostrarMensagemDeEspera();
        CriarContaTask task = new CriarContaTask();

        CriarContaParams params = activity.getIsProfile().wasDefaultImageChanged() ?
                new CriarContaParams(activity.getNomeCompleto(),
                        activity.getEmail(),
                        activity.getSenha(),
                        activity.getIsProfile().getImage()) :

                new CriarContaParams(activity.getNomeCompleto(),
                        activity.getEmail(),
                        activity.getSenha());

        task.execute(params);
    }

    private class CriarContaTask extends AsyncTask<CriarContaParams, Void, UUID>{

        @Override
        protected UUID doInBackground(CriarContaParams... params) {

            try {
                if(activity.getSenha().equals(activity.getConfirmarSenha()))
                    return PeladaFCWsGateway.CriarConta(params[0]);
                else
                    activity.mostrarMensagem("A senha digitada não é igual a senha de confirmação");
            } catch (GatewayOperationException e) {
                activity.mostrarMensagem(e.getMessage());
            }catch (Exception e){
                activity.mostrarMensagem("Oops! houve um problema ao criar sua conta tente novamente mais tarde");
                Log.e("Generic Error", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(UUID contaId){
            activity.esconderMensagemDeEspera();

            if(contaId != null){
                activity.mostrarMensagem("Sua conta foi criada com sucesso!");
                MemoriaCompartilhadaHelper.getInstance().dados.put("contaId", contaId);
            }
        }
    }
}
