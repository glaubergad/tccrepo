package scenes;

import DashGen.Dataset;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class DashGen6 extends Application {

    private static Stage pStage;
    static Dataset dataset;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("principal.fxml"));
            pStage.setTitle("IFPA DashGen - Prototipo 5");
            pStage.setScene(new Scene(root, 500, 600));
            pStage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    static File selectCSV(){
        FileChooser csvFileChooser = new FileChooser();
        csvFileChooser.setTitle("Selecione o CSV para o DashGen");
        csvFileChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        csvFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivos CSV", "*.csv"));
        return csvFileChooser.showOpenDialog(pStage);
    }

    static File selectDestFolder() throws Exception {
        DirectoryChooser destDirectoryChooser = new DirectoryChooser();
        destDirectoryChooser.setTitle("Selecione o diretorio para o DashGen");
        destDirectoryChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        return destDirectoryChooser.showDialog(pStage);
    }

}
