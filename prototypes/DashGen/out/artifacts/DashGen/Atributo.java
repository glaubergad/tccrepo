package dashboard;

public class Atributo {
    private int index;
    private String nome;
    private String tipoDado;

    public Atributo(int index, String nome, String tipoDado) {
        this.index = index;
        this.nome = nome;
        this.tipoDado = tipoDado;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(String tipoDado) {
        this.tipoDado = tipoDado;
    }
}
