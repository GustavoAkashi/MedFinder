package com.example.medfounder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.medfounder.objetos.Hospital;
import com.example.medfounder.objetos.hospBD;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class busca extends AppCompatActivity {

    private TextView edtparaConv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        edtparaConv = (TextView) findViewById(R.id.paraConv); // declara o lugar que vai ficar escrito o convenio

        // intent que recebe os dados enviados da main
        Intent intentRecebedora = getIntent();
        Bundle parametros = intentRecebedora.getExtras();
        String convenio = parametros.getString("chave_convenio");
        edtparaConv.setText("Para o conveio: "+convenio);

        // declara a lista de hospitais que vai ser exibida
        ListView lista = (ListView) findViewById(R.id.lvHosp);
        ArrayAdapter adapter = new HospitalAdapter(this, adicionarHospitais());
        lista.setAdapter(adapter);

        
    }

    // array dos hospitais que aparecerão
    private ArrayList<Hospital> adicionarHospitais() {
        ArrayList<Hospital> hosptais = new ArrayList<Hospital>();
        Intent intentRecebedora = getIntent();
        Bundle parametros = intentRecebedora.getExtras();

        String convenioSelecionado = parametros.getString("chave_convenio"); // pega qual convenio foi selecionado

        // Se o convenio selecionado for algo, retorna uma lista de hospitais daquele convenio
        if (convenioSelecionado.equals("Amil")) {
            DAO dao = new DAO(getApplicationContext()); // chama o banco de dados
            List<hospBD> hospnome = dao.buscaHospInf("Amil"); // usa o método buscanomeHosp para pegar as informações do hospital (nome, endereço) daquele convenio

            for (hospBD nomeBuscado : hospnome) { // percorre todos os hospitais que aceita aquele convenio
                String nome = nomeBuscado.getNome();
                String end = nomeBuscado.getEndereco();
                Hospital h = new Hospital(nome, end, R.drawable.alberteinsteinlogo ); // precisa arrumar qual logo irá pegar (VOLTAR AQUI)
                hosptais.add(h);
            }

        } else if (convenioSelecionado.equals("Itaúde")) {
            DAO dao = new DAO(getApplicationContext());
            List<hospBD> hospnome = dao.buscaHospInf("Itaúde");

            for (hospBD nomeBuscado : hospnome) {
                String nome = nomeBuscado.getNome();
                String end = nomeBuscado.getEndereco();
                Hospital h = new Hospital(nome, end, R.drawable.alberteinsteinlogo);
                hosptais.add(h);
            }

        } else if (convenioSelecionado.equals("Saúde Bradesco")) {
            DAO dao = new DAO(getApplicationContext());
            List<hospBD> hospnome = dao.buscaHospInf("Saúde Bradesco");

            for (hospBD nomeBuscado : hospnome) {
                String nome = nomeBuscado.getNome();
                String end = nomeBuscado.getEndereco();
                Hospital h = new Hospital(nome, end, R.drawable.alberteinsteinlogo);
                hosptais.add(h);
            }

        }

        return hosptais;
    }

    // método que volta pra página inicial
    public void voltarHome(View view) {
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class); // retorna pra página da MainActivity
        startActivity(intent2);
    }
}
