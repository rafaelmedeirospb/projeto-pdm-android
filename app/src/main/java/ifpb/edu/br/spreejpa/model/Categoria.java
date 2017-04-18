package ifpb.edu.br.spreejpa.model;

import static android.R.attr.id;

/**
 * Created by Windows 10 on 08/02/2017.
 */

public class Categoria {

    private int categoriaid;
    private String nome;


    public Categoria(String nome){
        this.nome=nome;
    }

    public Categoria(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return categoriaid;
    }

    public void setId(int categoriaid) {
        this.categoriaid = categoriaid;
    }
}
