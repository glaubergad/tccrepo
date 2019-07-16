package dashgen;

public class Grafico {
    private String atributoX;
    //private String atributoY;
    private String nome;
    private String tipo;
    public static final String TIPO_BARRAS = "dc.barChart";
    public static final String TIPO_BARRASV = "dc.rowChart";
    public static final String TIPO_PIZZA = "dc.pieChart";
    public static final String TIPO_TABELA = "dc.dataTable";

    public Grafico() {
    }

    public Grafico(String atributoX, String nome, String tipo) {
        this.atributoX = atributoX;
        this.nome = nome;
        this.tipo = tipo;
    }

    /*public dashgen.Grafico(String atributoX, String atributoY, String nome, String tipo) {
        this.atributoX = atributoX;
        this.atributoY = atributoY;
        this.nome = nome;
        this.tipo = tipo;
    }*/

    public String getAtributoX() {
        return atributoX;
    }

    public void setAtributoX(String atributoX) {
        this.atributoX = atributoX;
    }

    /*
    public String getAtributoY() {
        return atributoY;
    }

    public void setAtributoY(String atributoY) {
        this.atributoY = atributoY;
    }
    */
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
