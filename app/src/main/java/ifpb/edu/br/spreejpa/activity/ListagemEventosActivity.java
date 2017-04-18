package ifpb.edu.br.spreejpa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ifpb.edu.br.spreejpa.dao.CategoriaDAO;
import ifpb.edu.br.spreejpa.dao.EventoDAO;
import ifpb.edu.br.spreejpa.R;
import ifpb.edu.br.spreejpa.model.Categoria;
import ifpb.edu.br.spreejpa.model.Evento;


public class ListagemEventosActivity extends AppCompatActivity implements Serializable {
    Button btnLocalization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_eventos);

        ListView listViewEventos = (ListView) findViewById(R.id.listViewEventos);

        ArrayList<Evento> eventos = null;
        EventoDAO daoE = new EventoDAO(this);
//        Evento e1=new Evento("Virgens de Mangabeira","2424242424","Av. Epitacio Pèssoa",1000000,2);
//        Evento e2=new Evento("Muriçoquinhas","11111111111","Av. Epitacio Pèssoa",200000,2);
//        Evento e3=new Evento("Grito Rock","33333333333","Av. Epitacio Pèssoa",300000,2);
//        daoE.insert(e1);
//        daoE.insert(e2);
//        daoE.insert(e3);

        Integer categoriaid = (Integer) getIntent().getSerializableExtra("idcategoria");

        eventos = (ArrayList<Evento>) daoE.EventosPorCategoria(categoriaid);

        AdapterEventos adapterEventos = new AdapterEventos(this, eventos);
        listViewEventos.setAdapter(adapterEventos);
    }

}

//    private class OnClickList implements AdapterView.OnItemClickListener{
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//
//            ArrayList<Evento> eventos = null;
//            EventoDAO daoE= new EventoDAO();
//            Integer categoriaid = (Integer) getIntent().getSerializableExtra("idcategoria");
//            eventos = (ArrayList<Evento>) daoE.EventosPorCategoria(categoriaid);
//
//            Intent i = new Intent(ListagemEventosActivity.this, MapsActivity.class);
//            i.putExtra("endereco", eventos.get(position).getEndereco());
//
//            startActivity(i);
//        }
//    }

