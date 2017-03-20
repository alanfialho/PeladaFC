package com.br.peladafc.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.br.peladafc.R;
import com.br.peladafc.controller.AutenticarContaController;
import com.br.peladafc.controller.CriarContaController;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;
import static eu.inmite.android.lib.validations.form.annotations.RegExp.EMAIL;

public class AutenticarContaActivity extends BaseActivity {

    @NotEmpty (messageId= R.string.validation_required, order= 1)
    @RegExp(value = EMAIL, messageId = R.string.validation_email, order = 2)
    private EditText etEmail;

    @NotEmpty (messageId= R.string.validation_required, order= 3)
    private EditText etSenha;

    private AutenticarContaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticar_conta);
        this.etEmail = (EditText)findViewById(R.id.etEmail);
        this.etSenha = (EditText)findViewById(R.id.etSenha);
        this.controller = new AutenticarContaController(this);
    }

    public String getEmail()
    {
        return etEmail.getText().toString();
    }

    public String getSenha()
    {
        return etSenha.getText().toString();
    }

    protected void btEntrarOnClick(View view){

        if(FormValidator.validate(this, new SimpleErrorPopupCallback(this, true))){
            controller.ExecutarAutenticarConta();
        }
    }

    protected void tvCriarContaOnClick(View view){

        Intent intent = new Intent(this, CriarContaActivity.class);
        startActivity(intent);
    }
}
