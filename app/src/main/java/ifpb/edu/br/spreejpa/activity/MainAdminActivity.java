package ifpb.edu.br.spreejpa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ifpb.edu.br.spreejpa.R;

public class MainAdminActivity extends AppCompatActivity {
    private Button btnCadastroEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        this.btnCadastroEvento = (Button) findViewById(R.id.btnCadastroEvento);

        this.btnCadastroEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, CadastroEventoActivity.class);
                startActivity(intent);
            }
        });
    }
}
