package com.br.peladafc.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by alan on 22/02/2017.
 */

public class BaseActivity extends AppCompatActivity{

        private ProgressDialog dialog;

        public void mostrarMensagem(final String mensagem){
            if(dialog.isShowing())
                esconderMensagemDeEspera();

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }

        public void mostrarMensagemDeEspera(){
            dialog = ProgressDialog.show(this, "Executando", "Aguarde...", true);
        }

        public void esconderMensagemDeEspera(){
            dialog.dismiss();
        }


}
