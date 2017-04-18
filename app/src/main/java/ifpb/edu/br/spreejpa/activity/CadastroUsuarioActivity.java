package ifpb.edu.br.spreejpa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ifpb.edu.br.spreejpa.R;
import ifpb.edu.br.spreejpa.dao.UsuarioDAO;
import ifpb.edu.br.spreejpa.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {
    EditText edtNome, edtEmail, edtSenha;
    Button btnCadastrar;
    private UsuarioDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        this.edtNome = (EditText) findViewById(R.id.nomeUser);
        this.edtEmail = (EditText) findViewById(R.id.emailUser);
        this.edtSenha = (EditText) findViewById(R.id.senhaUser);

        this.btnCadastrar = (Button) findViewById(R.id.registerUser);

        this.userDAO = new UsuarioDAO(this);

        this.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, email, password;
                name = CadastroUsuarioActivity.this.edtNome.getText().toString();
                email = CadastroUsuarioActivity.this.edtEmail.getText().toString();
                password = CadastroUsuarioActivity.this.edtSenha.getText().toString();

                Usuario user = new Usuario(email, name);
                user.setSenhaHash(password);
                CadastroUsuarioActivity.this.userDAO.insert(user);
                Log.i("IFPB", "Usuario cadastrado!");
                Intent intent = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
