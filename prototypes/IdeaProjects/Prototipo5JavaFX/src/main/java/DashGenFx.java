import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class DashGenFx extends Application {

    private static Stage pStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
    pStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("views/DashGen.fxml"));
        pStage.setTitle("IFPA DashGen - Prototipo 5");
        pStage.setScene(new Scene(root,500,500));
        pStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    static File selectCSV() throws Exception {
        FileChooser csvFileChooser = new FileChooser();
        csvFileChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        csvFileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Arquivos CSV", "*.csv"));
        return csvFileChooser.showOpenDialog(pStage);
    }

    static File selectDestFolder() throws Exception {
        DirectoryChooser destDirectoryChooser = new DirectoryChooser();
        destDirectoryChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        return destDirectoryChooser.showDialog(pStage);
    }
}
