package com.example.medfounder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtConvenio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // seta qual xml vai ser exibido

        Button btnListaBD = findViewById(R.id.btnListaBD);

        // opções de convenios
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.convenios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    // método que envia os dados da página para página de busca
    public void enviarDados (View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String convenioSelecionado = spinner.getSelectedItem().toString();

        // intent que envia qual foi o item selecionado
        Intent intentEnviadora = new Intent(getApplicationContext(), busca.class);
        Bundle parametros = new Bundle();
        // parametros.putTIPO("chave_qualquer", nomedavariavel)
        parametros.putString("chave_convenio", convenioSelecionado);

        intentEnviadora.putExtras(parametros);
        startActivity(intentEnviadora);
    }

    public void entrarListaBD (View view) {
        Intent it = new Intent(getApplicationContext(), listabd.class);
        startActivity(it);
    }






}
