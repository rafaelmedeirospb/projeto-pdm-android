package ifpb.edu.br.spreejpa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import ifpb.edu.br.spreejpa.dao.EventoDAO;
import ifpb.edu.br.spreejpa.R;
import ifpb.edu.br.spreejpa.model.Evento;

public class CadastroEventoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private EventoDAO eventoDAO;

    private EditText eventoNome, eventoTelefone, eventoEndereco, eventoData;
    private Calendar calendario;
    private Button btnEnviar;
    private RadioGroup categoriaEvento;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        this.eventoDAO = new EventoDAO(this);

        // Informações de tipo texto
        this.eventoNome = (EditText) findViewById(R.id.nomeEvento);
        this.eventoTelefone = (EditText) findViewById(R.id.telefoneEvento);
        this.eventoEndereco = (EditText) findViewById(R.id.enderecoEvento);

        // Tratar o campo de tipo Data
        this.eventoData = (EditText) findViewById(R.id.dataEvento);
        this.eventoData.setOnFocusChangeListener(new onPickDate());
        this.calendario  = Calendar.getInstance();

        // Tratar campo para categoria do evento
        this.categoriaEvento = (RadioGroup) findViewById(R.id.radioCateg);

        this.btnEnviar = (Button) findViewById(R.id.salvarEvento);
        this.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = CadastroEventoActivity.this.eventoNome.getText().toString();
                String telefone = CadastroEventoActivity.this.eventoTelefone.getText().toString();
                String endereco = CadastroEventoActivity.this.eventoEndereco.getText().toString();
                // Tratar Data
                long data = CadastroEventoActivity.this.calendario.getTimeInMillis();
                // Tratar categoria do evento
                int id = CadastroEventoActivity.this.categoriaEvento.getCheckedRadioButtonId();
                View rb = (RadioButton)findViewById(id);
                int categoria = CadastroEventoActivity.this.categoriaEvento.indexOfChild(rb);
                categoria++;

                Evento evento = new Evento(nome, telefone, endereco, data, categoria);

                if(CadastroEventoActivity.this.eventoDAO.jaExiste(evento)){
                    Toast.makeText(getApplicationContext(), "Evento já existe! ", Toast.LENGTH_SHORT).show();
                    Log.i("IFPB", "Não pode cadastrar essa evento!");
                    Intent intent = new Intent(CadastroEventoActivity.this, MainAdminActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Evento cadastrado! ", Toast.LENGTH_SHORT).show();
                    Log.i("IFPB", "Pode cadastrar esse evento!");
                    CadastroEventoActivity.this.eventoDAO.insert(evento);
                    Intent intent = new Intent(CadastroEventoActivity.this, MainAdminActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    // Retornar para o usuario a data que ele informou
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        CadastroEventoActivity.this.calendario.set(year,(monthOfYear+1), dayOfMonth);
        eventoData.setText(date);
    }

    // Classe para renderizar o Calendario com material design
    class onPickDate implements View.OnFocusChangeListener{
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus){
                Calendar evData = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        (DatePickerDialog.OnDateSetListener) CadastroEventoActivity.this,
                        evData.get(Calendar.YEAR),
                        evData.get(Calendar.MONTH),
                        evData.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        }
    }
}
