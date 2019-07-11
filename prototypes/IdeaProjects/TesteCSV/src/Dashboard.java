import java.util.List;

public class Dashboard {
    private Dataset dataset;
    private List<Grafico> listaGraficos;

    public Dashboard() {
    }

    public Dashboard(Dataset dataset) {
        this.dataset = dataset;
    }

    public Dashboard(Dataset dataset, List<Grafico> listaGraficos) {
        this.dataset = dataset;
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

    public void graficosToString() {
        for (Grafico grafico : listaGraficos) {
            System.out.println("Grafico:" + grafico.getNome());
            System.out.println("AtrX :" + grafico.getAtributoX() +
                    "AtrY :" + grafico.getAtributoY() +
                    "Tipo :" + grafico.getTipo());
        }
    }
}
