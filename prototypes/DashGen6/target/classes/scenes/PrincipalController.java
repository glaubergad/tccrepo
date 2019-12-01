package scenes;

import DashGen.*;
import DashGen.Atributo;
import freemarker.template.TemplateException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrincipalController {
    private File csvFile, destFolder;
    private Dataset dataset;
    private Dashboard dashboard;
    private String tituloDashboard;
    private final List<Grafico> graficos = new ArrayList<Grafico>();
    private final List<String> tipoGrafico = new ArrayList<String>();
    public Label lblPathCsv;
    public Label lblPathDest;
    public ComboBox cbAtributoX;
    public ComboBox cbAtributoY;
    public ComboBox cbTipoGrafico;
    public TextField tfTituloGrafico;
    public TextField tfTituloDashboard;
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
        dataset = new Dataset(csvFile);
        populateCbAtributoX(dataset.getAtributos());
        populateCbAtributoY(dataset.getAtributos());
        populateCbTipoGrafico();

    }

    private void populateCbAtributoX(List<Atributo> atributos) {
        cbAtributoX.getItems().clear();
        for(Atributo atributo:atributos)
        cbAtributoX.getItems().add(atributo.getNome());
    }

    private void populateCbAtributoY(List<Atributo> atributos) {
        cbAtributoY.getItems().clear();
        int i = 0;
        for (Atributo atributo : atributos) {
            if (atributo.getTipoDado().equals("N")) {
                cbAtributoY.getItems().add(atributo.getNome());
            }
        }
    }

    private void populateCbTipoGrafico() {
        tipoGrafico.add(Grafico.TIPO_BARRAS);
        tipoGrafico.add(Grafico.TIPO_BARRASV);
        tipoGrafico.add(Grafico.TIPO_PIZZA);
        //tipoGrafico.add(Grafico.TIPO_TABELA);
        cbTipoGrafico.getItems().clear();
        cbTipoGrafico.getItems().setAll(tipoGrafico);
    }

   public void selectedCbTipoGrafico(ActionEvent event){
       String value = cbTipoGrafico.getValue().toString();
       if(value == "dc.pieChart"){
           cbAtributoY.hide();
       }
   }


    public void addGrafico(ActionEvent actionEvent) {
        try {
            String atributoX = cbAtributoX.getSelectionModel().getSelectedItem().toString();
            String atributoY = cbAtributoY.getSelectionModel().getSelectedItem().toString();
            String tipoGraf = cbTipoGrafico.getSelectionModel().getSelectedItem().toString();
            String titGraf = tfTituloGrafico.getText();
            graficos.add(new Grafico(atributoX, atributoY, titGraf, tipoGraf));
            lvGraficos.getItems().setAll(graficos);
            cbAtributoX.getSelectionModel().clearSelection();
            cbAtributoY.getSelectionModel().clearSelection();
        } catch (Exception e) {
            System.out.println("AddGrafico:" + e.toString());
        }
    }

    public void endDashboard(ActionEvent actionEvent) {
        setaDashboard();
        try {
            setaGerador();
            setaPackSaida();
        } catch (Exception e) {
            System.out.println("SetaGerador:" + e.toString());
        } finally {
            new Alert(Alert.AlertType.INFORMATION, "Dashboard gerado com sucesso!").showAndWait();
            resetForm();
        }


    }

    private void setaDashboard() {
        tituloDashboard = tfTituloDashboard.getText();
        dashboard = new Dashboard(dataset, tituloDashboard, graficos);
    }

    private void setaGerador() throws IOException, TemplateException {
        new Gerador(dashboard);
    }

    private void setaPackSaida() throws IOException {
        new PackSaida(destFolder, csvFile);
    }

    //Método redefine todos os itens da tela para o estado inicial
    private void resetForm() {
        graficos.clear();
        tipoGrafico.clear();
        cbTipoGrafico.getItems().clear();
        cbAtributoX.getItems().clear();
        lvGraficos.getItems().clear();
        tfTituloGrafico.clear();
        tfTituloDashboard.clear();
        lblPathCsv.setText("");
        lblPathDest.setText("");
        cbAtributoY.getItems().clear();
        cbTipoGrafico.getItems().clear();

    }

    //Método limpa os gráficos da lista caso o usuário tenha se equivocado
    public void clearGraficosClick(ActionEvent actionEvent) {
        lvGraficos.getItems().clear();
        graficos.clear();
    }
}
