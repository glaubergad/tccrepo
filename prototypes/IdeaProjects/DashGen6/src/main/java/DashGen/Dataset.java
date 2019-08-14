package DashGen;
/*
 *   Dataset.java
 *   Classe integrante do Pacote DashGen
 *   Responsável por conter o conjunto de dados em forma de objeto Java
 *   TCC - Gerador de Dashboards
 *   IFPA - Campus Belém - 2019
 *   Aluno: Glauber Matteis Gadelha
 *   Orientador: Prof. Me. Claudio Roberto de Lima Martins
 */

//Para reduzir a complexidade, foi utilizada a biblioteca Apache Commons CSV - Importada usando o Maven
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Dataset {
    private File datasetFile;
    private Map<String, Integer> headers;
    private CSVParser parser;
    private Iterable<CSVRecord> record;

    /*
     * Método construtor admite como parâmetro o objeto file obtido no Front-End
     * Para facilitar o uso em outras etapas do sistema, o caminho absoluto e o nome do arquivo são
     * armazenados como string nas variáveis caminho e arquivo
     */
    public Dataset(File fileDs) throws Exception {

        this.datasetFile = fileDs;
            this.setParser();
            this.setHeaders();
            this.setRecord();
    }


    //Seção dos Getters e Setters


    public String getCaminho() {
        return datasetFile.getAbsolutePath();
    }

    public String getArquivo() {
        return datasetFile.getName();
    }

    public Map<String, Integer> getHeaders() {
        return headers;
    }

    private void setHeaders() {
        this.headers = this.parser.getHeaderMap();
    }

    private void setParser() throws IOException {
        try (
                Reader reader = Files.newBufferedReader(datasetFile.toPath());
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
            Reader in = Files.newBufferedReader(datasetFile.toPath());
            this.record = CSVFormat.DEFAULT.withDelimiter(';').withHeader().parse(in);
        } catch (Exception e) {
            System.out.println("Erro ao setar Record:" + e.getMessage());
        }
    }


    //Método usado para exibir no console todos os cabeçalhos de dados do CSV - Finalidade de Teste

    public void headersToString() {
        for (Map.Entry entry : this.headers.entrySet()) {
            System.out.println(entry.getValue() + ", " + entry.getKey());
        }

    }

    //Método usado para exibir no console dados das 3 primeiras linhas do CSV - Finalidade de Teste
    public void recordsToString() {
        int colunas = this.headers.size();
        for (CSVRecord reg : this.record) {
            System.out.print("\n Linha:" + reg.getRecordNumber() + " | ");
            for (int i = 0; i < colunas; i++) {
                System.out.print(reg.get(i) + " | ");
            }
            if (reg.getRecordNumber() == 3)
                break;
        }
    }
}
