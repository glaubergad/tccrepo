package scenes;

import DashGen.*;
import freemarker.template.TemplateException;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrincipalController {
    public File csvFile, destFolder;
    public Dataset dataset;
    public Dashboard dashboard;
    public Gerador gerador;
    public PackSaida packsaida;
    public final List<Grafico> graficos = new ArrayList<Grafico>();
    public final List<String> tipoGrafico = new ArrayList<String>();
    public Label lblPathCsv;
    public Label lblPathDest;
    public ComboBox cbAtributo;
    public ComboBox cbTipoGrafico;
    public TextField tfTituloGrafico;
    public TextField tfTituloDashboard;
    public String tituloDashboard;
    public ListView lvGraficos;


    public void selectCSV(ActionEvent actionEvent) {
        try {
            resetForm();
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
            String atributo = cbAtributo.getSelectionModel().getSelectedItem().toString();
            String tipoGraf = cbTipoGrafico.getSelectionModel().getSelectedItem().toString();
            String titGraf = tfTituloGrafico.getText();
            graficos.add(new Grafico(atributo,titGraf,tipoGraf));

            lvGraficos.getItems().setAll(graficos);
        }catch (Exception e){
            System.out.println("AddGrafico:"+e.toString());
        }
    }

    public void endDashboard(ActionEvent actionEvent) {
        setaDashboard();
        try{
            setaGerador();
            setaPackSaida();
        }catch (Exception e){
            System.out.println("SetaGerador:"+e.toString());
        }finally {
            new Alert(Alert.AlertType.INFORMATION, "Dashboard gerado com sucesso!").showAndWait();
            resetForm();
        }



    }

    public void setaDashboard(){
        tituloDashboard = tfTituloDashboard.getText();
        dashboard = new Dashboard(dataset,tituloDashboard,graficos);
    }

    public void setaGerador() throws IOException, TemplateException {
        gerador = new Gerador(dashboard);
    }

    public void setaPackSaida() throws IOException {
        packsaida = new PackSaida(destFolder,csvFile);
    }

    public void resetForm(){
        graficos.clear();
        cbTipoGrafico.getItems().clear();
        cbAtributo.getItems().clear();
        lvGraficos.getItems().clear();
        tfTituloGrafico.clear();
        tfTituloDashboard.clear();
        lblPathCsv.setText("");
        lblPathDest.setText("");
        cbAtributo.getSelectionModel().clearSelection();
        cbTipoGrafico.getSelectionModel().clearSelection();
    }

    public void clearGraficosClick(ActionEvent actionEvent) {
        lvGraficos.getItems().clear();
        graficos.clear();
    }
}
