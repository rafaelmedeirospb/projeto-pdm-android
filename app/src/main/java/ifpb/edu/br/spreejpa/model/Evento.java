package ifpb.edu.br.spreejpa.model;

import java.util.Calendar;

import ifpb.edu.br.spreejpa.R;

/**
 * Created by Windows 10 on 07/02/2017.
 */

public class Evento {
    private int id;
    private int Icon= R.drawable.img1;
    private String nome;
    private String telefone;
    private String endereco;
    private int categoriaid;
    private Calendar data;

    public Evento(String nome,String telefone,String endereco, long data, int categoriaid){
        this.nome = nome;
        this.endereco = endereco;
        this.data = Calendar.getInstance();
        this.data.setTimeInMillis(data);
        this.telefone = telefone;
        this.categoriaid = categoriaid;
    }

    public int getId() {
        return id;
    }
    public int getIcon() {
        return Icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(int categoriaid) {
        this.categoriaid = categoriaid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData(){
        return String.format("%d/%d/%d", this.data.get(Calendar.DAY_OF_MONTH),
                this.data.get(Calendar.MONTH) + 1,
                this.data.get(Calendar.YEAR));
    }

    public long getDataLong(){
        return this.data.getTimeInMillis();
    }

    public void setData(Calendar data) {
        this.data = data;
    }
}
