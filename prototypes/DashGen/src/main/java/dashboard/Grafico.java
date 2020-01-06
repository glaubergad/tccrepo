package dashboard;

/*
 *   Grafico.java
 *   Classe integrante do Pacote DashGen
 *   Responsável por representar um Gráfico a compor um Dashboard em formato de objeto Java
 *   TCC - Gerador de Dashboards
 *   IFPA - Campus Belém - 2019
 *   Aluno: Glauber Matteis Gadelha
 *   Orientador: Prof. Me. Claudio Roberto de Lima Martins
 */

public class Grafico {

    private String atributoX;
    private String atributoY;
    private String nome;
    private String tipo;
    private int grouping =0;

    //Declaração de constantes para definir os tipos de gráficos da biblioteca DC.js
    public static final String TIPO_BARRAS = "dc.barChart";
    public static final String TIPO_BARRASV = "dc.rowChart";
    public static final String TIPO_PIZZA = "dc.pieChart";
    public static final String TIPO_TABELA = "dc.dataTable";


    /*
     * Sobrecarga de método construtor, podendo o objeto ser instanciado sem qualquer parâmetro ou
     * Já recebendo como parâmetro o nome do atributo do Dataset, o Título e o Tipo)
     */


    public Grafico() {
    }

    public Grafico(String atributoX, String nome, String tipo,int grouping) {
        this.atributoX = atributoX;
        this.nome = nome;
        this.tipo = tipo;
        if(grouping!=0){
            this.grouping = 1;
        }
    }

    public Grafico(String atributoX, String atributoY, String nome, String tipo,int grouping) {
        this.atributoX = atributoX;
        this.atributoY = atributoY;
        this.nome = nome;
        this.tipo = tipo;
        if(grouping!=0){
            this.grouping = 1;
        }
        this.grouping = grouping;
    }


    //Seção de Getters e Setters

    public int getGrouping() {
        return grouping;
    }

    public void setGrouping(int grouping) {
        this.grouping = grouping;
    }

    public String getAtributoX() {
        return atributoX;
    }

    public void setAtributoX(String atributoX) {
        this.atributoX = atributoX;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Fim da seção de Getters e Setters


    public String getAtributoY() {
        return atributoY;
    }

    public void setAtributoY(String atributoY) {
        this.atributoY = atributoY;
    }

    //Método toString() retorna uma string representando o objeto de forma textual
    public String toString() {
        return this.getAtributoX()+"|"+ this.getAtributoY()+"|"+this.nome+"|"+this.tipo;

    }
}
