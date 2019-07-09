import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Dataset {
    private String caminho;
    private Map<String, Integer> headers;
    private CSVParser parser;
    private Iterable<CSVRecord> record;

    public Dataset(String caminho) {
        this.caminho = caminho;
        try {
            this.setParser();
            this.setHeaders();
            this.setRecord();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Map<String, Integer> getHeaders() {
        return headers;
    }

    private void setHeaders() {
        this.headers = this.parser.getHeaderMap();
    }

    private void setParser() throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(this.caminho));
                CSVParser parser1 = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withHeader());
        ) {
            this.parser = parser1;
        }
    }

    public CSVParser getParser() {
        return parser;
    }

    public void setRecord() {
        try {
            Reader in = Files.newBufferedReader(Paths.get(this.caminho));
            this.record = CSVFormat.DEFAULT.withDelimiter(';').withHeader().parse(in);
        } catch (Exception e) {
            System.out.println("Erro ao setar Record:" + e.getMessage());
        }
    }

    public void headersToString() {
        for (Map.Entry entry : this.headers.entrySet()) {
            System.out.println(entry.getValue() + ", " + entry.getKey());
        }
    }


    public void recordsToString() {
        int colunas = this.headers.size();
        for (CSVRecord reg : this.record) {
            System.out.print("\n Linha:" + reg.getRecordNumber() + " | ");
            for (int i = 0; i < colunas; i++) {
                System.out.print(reg.get(i) + " | ");
            }
            if (reg.getRecordNumber() == 10)
                break;


        }
    }
}
