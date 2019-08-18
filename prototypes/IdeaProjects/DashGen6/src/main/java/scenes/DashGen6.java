package scenes;

/*
 *   DashGen6.java
 *   Classe integrante do Pacote scenes (Pacote Execuátvel principal da Aplicação e GUI)
 *   Responsável por executar a GUI e obter o arquivo CSV e o diretório de destino
 *   TCC - Gerador de Dashboards
 *   IFPA - Campus Belém - 2019
 *   Aluno: Glauber Matteis Gadelha
 *   Orientador: Prof. Me. Claudio Roberto de Lima Martins
 */

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
    private static final File HOME = new File(DashGen6.class.getProtectionDomain().getCodeSource().getLocation().getPath());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    //Método padrão iniciado no momento que uma aplicação JavaFX é executada. Responsável por carregar a GUI
    public void start(Stage primaryStage) {
        pStage = primaryStage;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("principal.fxml"));
            pStage.setTitle("IFPA DashGen - Prototipo 6");
            pStage.setScene(new Scene(root, 500, 600));
            pStage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }


    //JavaFX Filechooser para obter o arquivo CSV. É disparado a partir do PrincipalController.selectCSV()
    static File selectCSV(){
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
