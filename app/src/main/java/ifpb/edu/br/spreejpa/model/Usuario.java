package ifpb.edu.br.spreejpa.model;

import android.support.test.espresso.core.deps.guava.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Created by Windows 10 on 07/02/2017.
 */

public class Usuario {
    private int id;
    private boolean admin;
    private String nome;
    private String login;
    private String senha;

    public Usuario(){

    }

    public Usuario(String login, String nome){
        // Removi a senha do construtor para colocar senha atraves do metodo.
        this.admin=false;
        this.nome = nome;
        this.login=login;
    }

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenhaHash(String senha) {
        // Mudança no metodo para para ser salvo o hash como String
        this.senha = Hashing.sha256()
                .hashString(senha, StandardCharsets.UTF_8)
                .toString();
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
