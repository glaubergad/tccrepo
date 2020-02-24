package dashboard;
/*
 *   Dashboard.java
 *   Classe integrante do Pacote DashGen
 *   Responsável por agregar em um objeto o Dataset, a Lista de Gráficos e o Título do Dashboard
 *   TCC - Gerador de Dashboards
 *   IFPA - Campus Belém - 2019
 *   Aluno: Glauber Matteis Gadelha
 *   Orientador: Prof. Me. Claudio Roberto de Lima Martins
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Dashboard {
    private Dataset dataset;
    private List<Grafico> listaGraficos;
    private String nomeDashboard;


    /*
     *   Sobrecarga de método construtor, podendo um objeto ser instanciado sem nenhum parâmetro,
     *   Recebendo somente o Dataset e o Título ou
     *   Recebendo Além do Dataset e do Título, a lista de Gráficos*
     */


    public Dashboard() {
    }

    public Dashboard(Dataset dataset, String nomeDashboard) {
        this.dataset = dataset;
        this.nomeDashboard = nomeDashboard;
        this.listaGraficos = new ArrayList<Grafico>();
    }

    public Dashboard(Dataset dataset, String nomeDashboard, List<Grafico> listaGraficos) {
        this.dataset = dataset;
        this.nomeDashboard = nomeDashboard;
        this.listaGraficos = listaGraficos;
    }


    //Seção dos Getters e Setters dos atributos

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public List<Grafico> getListaGraficos() {
        return listaGraficos;
    }

    public void setListaGraficos(List<Grafico> listaGraficos) {
        this.listaGraficos = listaGraficos;
    }

    public void insereGrafico(Grafico grafico) {
        this.listaGraficos.add(grafico);
    }


    public String getNomeDashboard() {
        return nomeDashboard;
    }

    public void setNomeDashboard(String nomeDashboard) {
        this.nomeDashboard = nomeDashboard;
    }

    // Fim da seção de Getters e Setters


    //Método toString sobrescrito para se obter por saída uma string com os dados do Dashboard

    public String toString() {
        String out = "Nome: " + this.nomeDashboard;
        out += "\ndashgen.Dataset: " + this.dataset.getArquivo();
        out += "\nAtributos :" + this.dataset.getHeaders().size();

        for (Map.Entry entry : this.dataset.getHeaders().entrySet()) {
            out += "\n" + entry.getValue() + " | " + entry.getKey();
        }
        out += "\nQuantidade de Graficos :" + this.getListaGraficos().size();

        for (Grafico grafico : this.listaGraficos) {
            out += "\ndashgen.Grafico :" + grafico.getNome() + "|Atributo :" + grafico.getAtributoX() + "|Tipo :" + grafico.getTipo();
        }

        return out;
    }
}
