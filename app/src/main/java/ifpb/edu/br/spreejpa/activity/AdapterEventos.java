package ifpb.edu.br.spreejpa.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import ifpb.edu.br.spreejpa.R;
import ifpb.edu.br.spreejpa.dao.EventoDAO;
import ifpb.edu.br.spreejpa.model.Evento;

/**
 * Created by Windows 10 on 19/02/2017.
 */

public class AdapterEventos extends BaseAdapter implements Serializable{
    private Context context;
    private ArrayList<Evento> eventos;


    public AdapterEventos(Context context, ArrayList<Evento> eventos){
        this.context=context;
        this.eventos=eventos;
    }
    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int position) {
        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view= inflater.inflate(R.layout.evento_list_item,null);

        if(view !=null){

            ImageView img= (ImageView) view.findViewById(R.id.imageView);
            TextView titulo= (TextView) view.findViewById(R.id.titulo);
            TextView data= (TextView) view.findViewById(R.id.data);
            Button btnLocalization= (Button) view.findViewById(R.id.btnLocalization);

            btnLocalization.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    Intent i=new Intent(context ,MapsActivity.class);
                    i.putExtra("endereco", eventos.get(position).getEndereco());

                    context.startActivity(i);
                }


            });


            Evento evento = eventos.get(position);
//            img.setImageResource(evento.getIcon());
            titulo.setText(evento.getNome());
            data.setText(evento.getData());

        }

        return view;
    }
}
