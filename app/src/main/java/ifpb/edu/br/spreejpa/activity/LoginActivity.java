package ifpb.edu.br.spreejpa.activity;

import android.content.Intent;
import android.support.test.espresso.core.deps.guava.hash.Hashing;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import ifpb.edu.br.spreejpa.R;
import ifpb.edu.br.spreejpa.dao.UsuarioDAO;
import ifpb.edu.br.spreejpa.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText login, senha;
    private Button btnRegister, btnLogin;
    private UsuarioDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.userDAO = new UsuarioDAO(this);
//         Usuario us1=new Usuario("admin","admin"
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
// ;
//         us1.setAdmin(true);
//         us1.setSenha("123");
//         userDAO.insert(us1);

        this.login = (EditText) findViewById(R.id.emailUser);
        this.senha = (EditText) findViewById(R.id.senhaUser);

        this.btnLogin = (Button) findViewById(R.id.login);
        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String loginField = LoginActivity.this.login.getText().toString();
                    String senhaField = Hashing.sha256().hashString(LoginActivity.this.senha.getText().toString(), StandardCharsets.UTF_8).toString();
                    if (userDAO.validaLogin(loginField, senhaField)){
                        if (userDAO.isAdmin(loginField, senhaField)){
                            Log.i("IFPB", "USER É ADMIN!");
                            Intent intent = new Intent(LoginActivity.this, MainAdminActivity.class);
                            startActivity(intent);
                        }else{
                            Log.i("IFPB", "DEU CERTO!");
                            Intent intent = new Intent(LoginActivity.this, ListagemCategoriaActivity.class);
                            startActivity(intent);
                        }

                    }else {
                        Log.i("IFPB", "DEU ERRADO!");
                        Toast.makeText(getApplicationContext(), "Valores para Email ou Senha invalidos! ", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        this.btnRegister = (Button) findViewById(R.id.signup);
        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
