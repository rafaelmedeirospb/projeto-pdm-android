package ifpb.edu.br.spreejpa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 27/01/17.
 */
public class BancoHelper extends SQLiteOpenHelper
{
    public BancoHelper(Context context) {
        super(context, "spreeDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Usuario(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR(100), " +
                "login VARCHAR(100),"+
                "senha VARCHAR(100),"+
                "admin BOOLEAN);");

        db.execSQL("CREATE TABLE IF NOT EXISTS Categoria(" +
                "categoriaId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR(100));");

        db.execSQL("CREATE TABLE IF NOT EXISTS Evento(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR(100), " +
                "telefone VARCHAR(100), "+
                "endereco VARCHAR(100), "+
                "data DATETIME, "+
                "categoria INTEGER, FOREIGN KEY (categoria) REFERENCES Categoria(categoriaId));");
//                "FOREIGN KEY REFERENCES Categoria(categoriaId));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table Usuario");
        db.execSQL("drop table Evento");
        db.execSQL("drop table Categoria");
        this.onCreate(db);
    }
}