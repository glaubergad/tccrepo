import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Main {
    private static final String SAMPLE_CSV_FILE_PATH = "C:\\Users\\glaubergad\\IdeaProjects\\TesteCSV\\src\\aposentadoria.csv";
    private static Dataset dataset;

    public static void main(String[] args) throws IOException {
        //metodo orientado a objetos
        System.out.println("Exibindo os cabeçalhos do CSV");

        dataset = new Dataset(SAMPLE_CSV_FILE_PATH);

        System.out.println(dataset.getCaminho());

        System.out.println("Usando o método headersToString");
        dataset.headersToString();

        System.out.println("Capturando o Map do headers");
        Map<String, Integer> headers = dataset.getHeaders();

        for (Map.Entry entry : headers.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        System.out.println("Quantidade de Colunas = " + headers.size());
        int size = headers.size();

        System.out.println("Exibindo as "+ (headers.size()-1) + " primeiras linhas do CSV");
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