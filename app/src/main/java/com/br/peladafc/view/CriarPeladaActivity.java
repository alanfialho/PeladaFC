package com.br.peladafc.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.peladafc.R;

import eu.inmite.android.lib.validations.form.annotations.NotEmpty;


public class CriarPeladaActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    @NotEmpty(messageId= R.string.validation_required, order= 1)
    private EditText etNome;

    @NotEmpty(messageId= R.string.validation_required, order= 2)
    private EditText etLocalizacao;

    private Spinner spnNotificacao;

    private DatePicker dpDataInicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_pelada);
        spnNotificacao = (Spinner) findViewById(R.id.spnNotificacao);
        spnNotificacao.setOnItemSelectedListener(this);
        etNome = (EditText) findViewById(R.id.etNome);
        etLocalizacao = (EditText) findViewById(R.id.etLocalizacao);
        dpDataInicio = (DatePicker) findViewById(R.id.dpDataInicio);
        dpDataInicio.setMinDate(System.currentTimeMillis() - 1000);
    }

    protected void btConcluirOnClick(View view){


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position == 2){//semanal
            final Dialog dialog = new Dialog(this);
            LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = li.inflate(R.layout.activity_dias_semana, null, false);
            dialog.setContentView(v);

            dialog.findViewById(R.id.btCancelar).setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
                }
            );


            dialog.show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
