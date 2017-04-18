package ifpb.edu.br.spreejpa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ifpb.edu.br.spreejpa.dao.CategoriaDAO;
import ifpb.edu.br.spreejpa.R;
import ifpb.edu.br.spreejpa.model.Categoria;

public class CadastroCategoriaActivity extends AppCompatActivity {
    Button btnEnviar;
    EditText CategoriaNome;
    CategoriaDAO categoriaDAO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);

        this.categoriaDAO = new CategoriaDAO(this);

        this.CategoriaNome = (EditText) findViewById(R.id.nomeCategoria);

        this.btnEnviar = (Button) findViewById(R.id.salvarCategoria);
        this.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = CadastroCategoriaActivity.this.CategoriaNome.getText().toString();
                Categoria categoria = new Categoria(nome);
                if(CadastroCategoriaActivity.this.categoriaDAO.jaExiste(categoria)){
                    Toast.makeText(getApplicationContext(), "Categoria já existe! ", Toast.LENGTH_SHORT).show();
                    Log.i("IFPB", "Não pode cadastra essa categoria!");
                    Intent intent = new Intent(CadastroCategoriaActivity.this, MainAdminActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Categoria cadastrada! ", Toast.LENGTH_SHORT).show();
                    Log.i("IFPB", "Pode cadastra esse evento!");
                    CadastroCategoriaActivity.this.categoriaDAO.insert(categoria);
                    Intent intent = new Intent(CadastroCategoriaActivity.this, MainAdminActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
