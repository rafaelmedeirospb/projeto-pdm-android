package ifpb.edu.br.spreejpa.dao;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

        import java.util.ArrayList;
        import java.util.List;

        import ifpb.edu.br.spreejpa.database.BancoHelper;
        import ifpb.edu.br.spreejpa.model.Usuario;

/**
 * Created by admin on 27/01/17.
 */
public class   UsuarioDAO {
    private SQLiteDatabase banco;

    public UsuarioDAO(Context context){
        this.banco = new BancoHelper(context).getWritableDatabase();
    }

    public void insert(Usuario p){
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("login", p.getLogin());
        cv.put("senha", p.getSenha());
        cv.put("admin",p.isAdmin());
        this.banco.insert("Usuario", null, cv);
    }

    public List<Usuario> get(){
        List<Usuario> lista = new ArrayList<Usuario>();
        String colunas[] = {"nome", "login", "senha"};
        Cursor c = this.banco.query("usuario", colunas, null, null, null, null, "nome");
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                int id = c.getInt(c.getColumnIndex("id"));
                String nome = c.getString(c.getColumnIndex("nome"));
                String login = c.getString(c.getColumnIndex("login"));
                String senha = c.getString(c.getColumnIndex("senha"));
                Usuario user = new Usuario(nome,login);
                user.setSenhaHash(senha);
                lista.add(user);
            } while (c.moveToNext());
        }
        return lista;
    }

    public Usuario montaUsuario(Cursor cursor) {
        if (cursor.getCount() < 1) {
            return null;
        }
        Integer id = cursor.getInt(cursor.getColumnIndex("id"));
        String nome = cursor.getString(cursor.getColumnIndex("nome"));
        String email = cursor.getString(cursor.getColumnIndex("login"));
        String senha = cursor.getString(cursor.getColumnIndex("senha"));
        Usuario u = new Usuario(email, nome);
        u.setId(id);
        u.setSenha(senha);
        return u;
    }

    public Usuario findByLogin(String email, String senha) {
        String sql = "SELECT * FROM Usuario WHERE login = ? AND senha = ?";
        String[] selectionArgs = new String[] { email, senha };
        Cursor cursor = this.banco.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        return montaUsuario(cursor);
    }

    public boolean validaLogin(String login, String senha) throws Exception {
        Usuario user = findByLogin(login, senha);
        if (user == null || user.getLogin() == null || user.getSenha() == null) {
            return false;
        }
        String informado = login + senha;
        String esperado = user.getLogin() + user.getSenha();
        if (informado.equals(esperado)) {
            return true;
        }
        return false;
    }
    public boolean isAdmin(String email, String senha) {
        String sql = "SELECT * FROM Usuario WHERE login = ? AND senha = ? AND admin = 1";
        String[] selectionArgs = new String[] { email, senha };
        Cursor cursor = this.banco.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();

        // Retorna se usuario é admin ou não
        if (cursor.getCount() < 1) {
            return false;
        }
        return true;
    }
}
