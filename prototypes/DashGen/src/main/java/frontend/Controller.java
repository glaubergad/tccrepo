package frontend;

import dashboard.*;
import freemarker.template.TemplateException;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Group.*;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public RadioButton rbReduceSum;
    public RadioButton rbContagem;
    public ToggleGroup rbGroup;
    public Button btnSelectCsv;
    public Button btnSelectDest;
    public Button btnClearGraficos;
    public Button btnEndDashboard;
    public Button btnAddGrafico;
    public Label lblSomatoria;
    private File csvFile, destFolder;
    private Dataset dataset;
    private Dashboard dashboard;
    private String tituloDashboard;
    private final List<Grafico> graficos = new ArrayList<Grafico>();
    private final List<String> tipoGrafico = new ArrayList<String>();
    private int grouping;
    public Label lblPathCsv;
    public Label lblPathDest;
    public ComboBox cbAtributoX;
    public ComboBox cbAtributoY;
    public ComboBox cbTipoGrafico;
    public TextField tfTituloGrafico;
    public TextField tfTituloDashboard;
    public ListView lvGraficos;

    /*
    /Método que abre uma caixa de seleção de Pasta de Destino para o Dashboard gerado. É necessário que o usuário tenha
    /permissão de escrita na pasta.
    */
    public void selectCSV(ActionEvent actionEvent) {
        try {
            resetForm();
            csvFile = Main.selectCSV();
            lblPathCsv.setText(csvFile.getAbsolutePath());
            tituloDashboard = tfTituloDashboard.getText();
            setDataset();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /*
    /Método que abre uma caixa de seleção de Pasta de Destino para o Dashboard gerado. É necessário que o usuário tenha
    /permissão de escrita na pasta.
    */
    public void selectDest(ActionEvent actionEvent) {
        try {
            destFolder = Main.selectDestFolder();
            lblPathDest.setText(destFolder.getAbsolutePath());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //Método instancia um objeto Dataset com o conjunto de dados contido no arquivo CSV selecionado.
    private void setDataset() throws Exception {
        dataset = new Dataset(csvFile);
        populateCbAtributoX(dataset.getAtributos());
        if (dataset.getNumFields() > 0) {
            populateCbAtributoY(dataset.getAtributos());
            lblSomatoria.setVisible(true);
            cbAtributoY.setVisible(true);
            rbReduceSum.setVisible(true);
        } else {
            cbAtributoY.setVisible(false);
            rbReduceSum.setVisible(false);
        }
        populateCbTipoGrafico();

    }

    //Método povoa a combobox do atributo principal da dimensão para o gráfico
    private void populateCbAtributoX(List<Atributo> atributos) {
        cbAtributoX.getItems().clear();
        for (Atributo atributo : atributos)
            cbAtributoX.getItems().add(atributo.getNome());
        rbReduceSum.setVisible(false);
    }

    //Método povoa a combobox do atibuto de somatória somente com atributos identificados como numéricos.
    private void populateCbAtributoY(List<Atributo> atributos) {
        cbAtributoY.getItems().clear();
        int i = 0;
        for (Atributo atributo : atributos) {
            if (atributo.getTipoDado().equals("N")) {
                cbAtributoY.getItems().add(atributo.getNome());
            }
        }
    }

    //Método preenche as opções da Combobox Tipo de Gráfico
    private void populateCbTipoGrafico() {
        cbTipoGrafico.getItems().clear();
        cbTipoGrafico.getItems().setAll(Grafico.getTipoGraficos());
    }

    //Método adiciona novo gráfico à listagem exibida na tela.
    public void addGrafico(ActionEvent actionEvent) {
        try {
            String atributoX = cbAtributoX.getSelectionModel().getSelectedItem().toString();
            String atributoY;
            if (this.grouping == 1) {
                atributoY = cbAtributoY.getSelectionModel().getSelectedItem().toString();
            } else {
                atributoY = cbAtributoX.getSelectionModel().getSelectedItem().toString();
            }
            String tipoGraf = cbTipoGrafico.getSelectionModel().getSelectedItem().toString();
            String titGraf = tfTituloGrafico.getText();
            System.out.println("Grafico:" + titGraf + " Tipo:" + tipoGraf
                    + " Dimensão:" + atributoX + " Somatoria:" + atributoY
                    + " agrupamento:" + this.grouping);
            graficos.add(new Grafico(atributoX, atributoY, titGraf, tipoGraf, this.grouping));
            lvGraficos.getItems().setAll(graficos);
            cbAtributoX.getSelectionModel().clearSelection();
            cbAtributoY.getSelectionModel().clearSelection();
            btnEndDashboard.setDisable(false);
        } catch (Exception e) {
            System.out.println("AddGrafico:" + e.toString());
            new Alert(Alert.AlertType.INFORMATION, "Você não selecionou os dados para inserir o gráfico!" + e.toString()).showAndWait();
        }
    }

    //Método finaliza a geração do Dashboard, executando o motor de templates FreeMarker, copiando os arquivos para a pasta de destino e gerando o zip de saida.
    public void endDashboard(ActionEvent actionEvent) {
        setaDashboard();
        try {
            setaGerador();
            setaPackSaida();
        } catch (Exception e) {
            System.out.println("SetaGerador:" + e.toString());
            new Alert(Alert.AlertType.INFORMATION, "Não foi possível gerar o Dashboard. Verifique as opções escolhidas!").showAndWait();
        } finally {
            new Alert(Alert.AlertType.INFORMATION, "Dashboard gerado com sucesso!").showAndWait();
            resetForm();
        }


    }

    //Método instancia objeto Dashboard juntando todas as informações definidas pelo usuário na UI
    private void setaDashboard() {
        tituloDashboard = tfTituloDashboard.getText();
        dashboard = new Dashboard(dataset, tituloDashboard, graficos);
    }

    //Método instancia a classe gerador, que processa o template FreeMarker e gera em disco o arquivo Dashboard.html.
    private void setaGerador() throws IOException, TemplateException {
        new Gerador(dashboard);
    }

    //Método instancia novo pack  de saída para gerar em disco o Dashboard completo.
    private void setaPackSaida() throws IOException {
        new PackSaida(destFolder, csvFile);
    }

    //Método redefine todos os itens da tela para o estado inicial
    private void resetForm() {
        graficos.clear();
        tipoGrafico.clear();
        cbTipoGrafico.getItems().clear();
        cbAtributoX.getItems().clear();
        cbAtributoY.getItems().clear();
        lvGraficos.getItems().clear();
        tfTituloGrafico.clear();
        tfTituloDashboard.clear();
        btnEndDashboard.setDisable(true);
        lblSomatoria.setVisible(false);
        cbAtributoY.setVisible(false);
        rbReduceSum.setVisible(false);
        lblPathCsv.setText("");
        lblPathDest.setText("");

    }

    //Método limpa os gráficos da lista caso o usuário tenha se equivocado
    public void clearGraficosClick(ActionEvent actionEvent) {
        lvGraficos.getItems().clear();
        btnEndDashboard.setDisable(true);
        graficos.clear();
    }

    //Método para identificar qual método de agrupamento o usuário  selecionou
    public void rbSelected() {
        if (rbContagem.isSelected()) {
            this.grouping = Integer.parseInt(rbContagem.getUserData().toString());
            cbAtributoY.setVisible(false);
            lblSomatoria.setVisible(true);
            cbAtributoX.getSelectionModel().select(0);
        } else {
            this.grouping = Integer.parseInt(rbReduceSum.getUserData().toString());
            lblSomatoria.setVisible(true);
            cbAtributoY.setVisible(true);
        }
        System.out.println("Agrupamento tipo:" + this.grouping);
    }
}
