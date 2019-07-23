package scenes;

import DashGen.Dataset;
import DashGen.Grafico;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrincipalController {
    public File csvFile, destFolder;
    public Dataset dataset;
    public List<Grafico> graficos;
    public final List<String> tipoGrafico = new ArrayList<String>();
    public Label lblPathCsv;
    public Label lblPathDest;
    public ComboBox cbAtributo;
    public ComboBox cbTipoGrafico;
    public TextField tfTituloGrafico;
    public TextField tfTituloDashboard;
    public String tituloDashboard;



    public void selectCSV(ActionEvent actionEvent) {
        try {
            csvFile = DashGen6.selectCSV();
            lblPathCsv.setText(csvFile.getAbsolutePath());
            tituloDashboard = tfTituloDashboard.getText();
            setDataset();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void selectDest(ActionEvent actionEvent) {


        try {
            destFolder = DashGen6.selectDestFolder();
            lblPathDest.setText(destFolder.getAbsolutePath());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void setDataset() throws Exception {
        dataset = new Dataset(csvFile.getAbsolutePath(), csvFile.getName());
        populateCbAtributo(dataset.getHeaders());
        populateCbTipoGrafico();

    }

    private void populateCbAtributo(Map<String, Integer> headers) {
        cbAtributo.getItems().setAll(headers.keySet());
    }

    private void populateCbTipoGrafico() {
        tipoGrafico.add(Grafico.TIPO_BARRAS);
        tipoGrafico.add(Grafico.TIPO_BARRASV);
        tipoGrafico.add(Grafico.TIPO_PIZZA);
        cbTipoGrafico.getItems().setAll(tipoGrafico);
    }

    public void addGrafico(ActionEvent actionEvent) {
        try {
            graficos.add(new Grafico(cbAtributo.getValue().toString(), tfTituloGrafico.getText(), cbTipoGrafico.getValue().toString()));
        }catch (Exception e){
            System.out.println("AddGrafico:"+e.toString());
        }
    }

    public void endDashboard(ActionEvent actionEvent) {
    }
}
