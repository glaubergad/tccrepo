import DashGen.Dataset;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.File;

public class DashGenController {

    public File csvFile, destFolder;
    public Label lblPathCsv;

    public Label lblPathDest;
    public Tab tab_graf1;
    public Tab tab_arquivos;
    public Tab tab_graf2;
    public Tab tab_graf3;
    public TabPane tabPane;
    public Dataset dataset;
    public Button btnTab1Next;
    public Button btnTab2Back;
    public Button btnTab2Next;
    public Button btnTab3Back;
    public Button btnTab3Next;
    public Button btnTab4Back;
    public Button btnTab4Finish;


    public void selectDest(ActionEvent actionEvent) {
        try {
            destFolder = DashGenFx.selectDestFolder();
            lblPathDest.setText(destFolder.getAbsolutePath());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public void selectCSV(ActionEvent actionEvent) {
        try {
            csvFile = DashGenFx.selectCSV();
            lblPathCsv.setText(csvFile.getAbsolutePath());
            setDataset();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }




    private void setDataset() throws Exception {
        dataset = new Dataset(csvFile.getAbsolutePath(), csvFile.getName());
    }

    public void goTab1(ActionEvent actionEvent) {
        tabPane.getSelectionModel().select(tab_arquivos);
    }

    public void goTab2(ActionEvent actionEvent) {
        if(csvFile != null && destFolder!= null){
            tabPane.getSelectionModel().select(tab_graf1);
        }
    }

    public void goTab3(ActionEvent actionEvent) {
        tabPane.getSelectionModel().select(tab_graf2);
    }

    public void goTab4(ActionEvent actionEvent) {
        tabPane.getSelectionModel().select(tab_graf3);
    }



    public void finishDashboard(ActionEvent actionEvent) {

    }
}
