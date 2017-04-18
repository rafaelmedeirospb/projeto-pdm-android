package ifpb.edu.br.spreejpa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ifpb.edu.br.spreejpa.database.BancoHelper;
import ifpb.edu.br.spreejpa.model.Categoria;

/**
 * Created by Windows 10 on 08/02/2017.
 */

public class CategoriaDAO {

    private SQLiteDatabase banco;

    public CategoriaDAO(Context context){
        this.banco = new BancoHelper(context).getWritableDatabase();
    }


    public void insert(Categoria c){
        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        this.banco.insert("Categoria", null, cv);
    }

    public List<Categoria> get(){
        List<Categoria> lista = new ArrayList<Categoria>();
        String colunas[] = {"categoriaId","nome"};
        Cursor c = this.banco.query("Categoria", colunas, null, null, null, null, "nome");
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                int id = c.getInt(c.getColumnIndex("categoriaId"));
                String nome = c.getString(c.getColumnIndex("nome"));
                Categoria cat = new Categoria(nome);
                cat.setId(id);
                lista.add(cat);
            } while (c.moveToNext());
        }
        return lista;
    }

    // Metodo verifica se já existe uma categoria com o nome informado
    // compara os nomes ambos em minusculo.
    public boolean jaExiste(Categoria categoria) {
        List<Categoria> lista = new ArrayList<Categoria>();
        String colunas[] = {"nome"};
        Cursor c = this.banco.query("Categoria", colunas, null, null, null, null, "nome");
        String nomeCategoria = categoria.getNome().toLowerCase();

        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String nome = c.getString(c.getColumnIndex("nome"));
                if (nome.toLowerCase().equals(nomeCategoria)) {
                    return true;
                }
            } while (c.moveToNext());
        }
        return false;
    }
}
