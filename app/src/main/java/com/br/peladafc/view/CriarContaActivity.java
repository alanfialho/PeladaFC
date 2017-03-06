package com.br.peladafc.view;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.br.peladafc.R;
import com.br.peladafc.controller.CriarContaController;
import com.br.peladafc.view.components.ImageSelector;


public class CriarContaActivity extends BaseActivity{

    private CriarContaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        this.controller = new CriarContaController(this);
    }

    public String getNomeCompleto(){
        EditText editText   = (EditText)findViewById(R.id.etNomeCompleto);
        return editText.getText().toString();
    }

    public ImageSelector getIsProfile(){
        return (ImageSelector) getFragmentManager().findFragmentById(R.id.isProfile);
    }

    public String getEmail()
    {
        EditText editText   = (EditText)findViewById(R.id.etEmail);
        return editText.getText().toString();
    }

    public String getSenha()
    {
        EditText editText   = (EditText)findViewById(R.id.etSenha);
        return editText.getText().toString();
    }

    public String getConfirmarSenha()
    {
        EditText editText   = (EditText)findViewById(R.id.etConfirmarSenha);
        return editText.getText().toString();
    }

    protected void criarContaOnClick(View view){
        controller.ExecutarCriarConta();
    }
}
