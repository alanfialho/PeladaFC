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

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

import static eu.inmite.android.lib.validations.form.annotations.RegExp.EMAIL;


public class CriarContaActivity extends BaseActivity{

    @NotEmpty(messageId= R.string.validation_required, order= 1)
    private EditText etNomeCompleto;

    @NotEmpty(messageId= R.string.validation_required, order= 2)
    @RegExp(value = EMAIL, messageId = R.string.validation_email, order = 3)
    private EditText etEmail;

    @NotEmpty(messageId= R.string.validation_required, order= 4)
    @MinLength(value = 6, messageId = R.string.validation_min_lenght_6, order = 5)
    private EditText etSenha;

    @NotEmpty(messageId= R.string.validation_required, order= 6)
    @MinLength(value = 6, messageId = R.string.validation_min_lenght_6, order = 7)
    private EditText etConfirmarSenha;

    private CriarContaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        etNomeCompleto = (EditText)findViewById(R.id.etNomeCompleto);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etSenha = (EditText)findViewById(R.id.etSenha);
        etConfirmarSenha = (EditText)findViewById(R.id.etConfirmarSenha);
        this.controller = new CriarContaController(this);
    }

    public String getNomeCompleto(){
        return etNomeCompleto.getText().toString();
    }

    public ImageSelector getIsProfile(){
        return (ImageSelector) getFragmentManager().findFragmentById(R.id.isProfile);
    }

    public String getEmail()
    {
        return etEmail.getText().toString();
    }

    public String getSenha()
    {
        return etSenha.getText().toString();
    }

    public String getConfirmarSenha()
    {
        return etConfirmarSenha.getText().toString();
    }

    protected void criarContaOnClick(View view){

        if(FormValidator.validate(this, new SimpleErrorPopupCallback(this, true))){
            controller.ExecutarCriarConta();
        }
    }
}
