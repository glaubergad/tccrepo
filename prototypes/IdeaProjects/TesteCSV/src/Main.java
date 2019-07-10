import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import java.util.Map;


public class Main {

    private static Dataset dataset;

    public static void main(String[] args) throws IOException {
        //metodo orientado a objetos
        System.out.println("Exibindo os cabeçalhos do CSV");

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Selecione o arquivo CSV: ");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos CSV", "csv");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File  arquivoCSV = jfc.getSelectedFile();
            System.out.println("Caminho obtido do JFileChooser :"+arquivoCSV.getAbsolutePath());
            dataset = new Dataset(arquivoCSV.getAbsolutePath());
        }else{
            System.out.println("Arquivo invalido ou não encontrado");
        }



        System.out.println("Caminho obtido do objeto Dataset :"+dataset.getCaminho());

        System.out.println("Usando o método headersToString()");
        dataset.headersToString();

        System.out.println("\n Capturando o Map do headers");
        Map<String, Integer> headers = dataset.getHeaders();


        System.out.println("\n Exibindo conteudo do Map de Headers");
        for (Map.Entry entry : headers.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        System.out.println("\n Quantidade de Colunas = " + headers.size());
        int size = headers.size();

        System.out.println("\n Exibindo as 10 primeiras linhas do CSV com o método recordsToString()");
        dataset.recordsToString();





       /* Método força bruta
       try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withHeader());
        ) {
            Map<String,Integer> headers = csvParser.getHeaderMap();

            for (Map.Entry entry : headers.entrySet()) {
                System.out.println(entry.getValue() + ", " + entry.getKey());
            }

        }

        */
    }
}