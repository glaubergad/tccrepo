import DashGen.Dataset;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.File;


public class DashGenController {

    private File csvFile, destFolder;
    public Label lblPathCsv;
    public Label lblPathDest;

    public void selectCSV(ActionEvent actionEvent) {
        try {
            csvFile = DashGenFx.selectCSV();
            lblPathCsv.setText(csvFile != null ? csvFile.getAbsolutePath() : "Local...");
        } catch (Exception e) {
            System.out.println("selectCSV:" + e.toString());
        }

        try {
            setDataset();
        } catch (Exception e) {
            System.out.println("setDataset:" + e.toString());
        }
    }


    public void selectDest(ActionEvent actionEvent) {
        try {
            destFolder = DashGenFx.selectDestFolder();
            lblPathDest.setText(destFolder.getAbsolutePath());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    private void setDataset() throws Exception {
        Dataset dataset = new Dataset(csvFile.getAbsolutePath(), csvFile.getName());
    }

    private void setPackSaida() {

    }


    public void addGraficoClick(ActionEvent actionEvent) {
    }

    public void finishDashboardClick(ActionEvent actionEvent) {
    }


}
