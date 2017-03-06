package com.br.peladafc.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.br.peladafc.R;
import com.br.peladafc.controller.AutenticarContaController;

public class AutenticarContaActivity extends BaseActivity {

    AutenticarContaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticar_conta);
        this.controller = new AutenticarContaController(this);
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

    protected void entrarOnClick(View view){
        controller.ExecutarAutenticarConta();
    }
}
