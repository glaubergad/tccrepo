package dashgen;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Dashboard {
    private Dataset dataset;
    private List<Grafico> listaGraficos;
    private String nomeDashboard;


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

    public String toString() {
        String out = "Nome: " + this.nomeDashboard;
        out += "\ndashgen.Dataset: " + this.dataset.getArquivo();
        out += "\nAtributos :" + this.dataset.getHeaders().size();
        for (Map.Entry entry : this.dataset.getHeaders().entrySet()) {
            out += "\n" + entry.getValue() + " | " + entry.getKey();
        }
        out += "\nQuantidade de Graficos :" + this.getListaGraficos().size();
        for (Grafico grafico : this.listaGraficos) {
            out += "\ndashgen.Grafico :" + grafico.getNome() + "|Eixo X:" + grafico.getAtributoX() + "|Tipo :" + grafico.getTipo();
        }

        return out;
    }
}
