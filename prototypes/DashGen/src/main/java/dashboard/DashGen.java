package dashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;


public class DashGen extends Application {
    private static Stage pStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        pStage = primaryStage;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dashboard/frontend.fxml"));
            pStage.setTitle("Gerador de Dashboards");
            pStage.setScene(new Scene(root, 700, 600));
            pStage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
    //JavaFX Filechooser para obter o arquivo CSV. É disparado a partir do PrincipalController.selectCSV()
    static File selectCSV()  throws Exception {
        FileChooser csvFileChooser = new FileChooser();
        csvFileChooser.setTitle("Selecione o CSV para o DashGen");
        csvFileChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        csvFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivos CSV", "*.csv"));
        return csvFileChooser.showOpenDialog(pStage);
    }

    //JavaFX DirectoryChooser para obter o diretório de destino. É disparado a partir do PrincipalController.selectDest()
    static File selectDestFolder() throws Exception {
        DirectoryChooser destDirectoryChooser = new DirectoryChooser();
        destDirectoryChooser.setTitle("Selecione o diretorio para o DashGen");
        destDirectoryChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        return destDirectoryChooser.showDialog(pStage);
    }
}
