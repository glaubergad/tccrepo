package dashboard;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Dataset {
    private File datasetFile;
    private Map<String, Integer> headers;
    private final List<Atributo> atributos = new ArrayList<Atributo>();
    private CSVParser parser;
    private Iterable<CSVRecord> record;
    private int numFields = 0;

    /*
     * Método construtor admite como parâmetro o objeto file obtido no Front-End
     * Para facilitar o uso em outras etapas do sistema, o caminho absoluto e o nome do arquivo são
     * armazenados como string nas variáveis caminho e arquivo
     */


    public Dataset(File fileDs) throws Exception {

        this.datasetFile = fileDs;
        this.setParser();
        this.setHeaders();
        this.headersToString();
        this.setRecord();
        this.recordsToString();
        this.setAtributos();
    }


    //Seção dos Getters e Setters


    public String getCaminho() {
        return datasetFile.getAbsolutePath();
    }

    public String getArquivo() {
        return datasetFile.getName();
    }


    private void setParser() throws IOException {
        try (
                Reader reader = Files.newBufferedReader(datasetFile.toPath());
                CSVParser parser1 = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withHeader())
        ) {
            this.parser = parser1;
        }
    }

    public CSVParser getParser() {
        return parser;
    }

    public Map<String, Integer> getHeaders() {
        return headers;
    }

    private void setHeaders() {
        this.headers = this.parser.getHeaderMap();
    }

    public void setRecord() {
        try {
            Reader in = Files.newBufferedReader(datasetFile.toPath());
            this.record = CSVFormat.DEFAULT.withDelimiter(';').withHeader().parse(in);
        } catch (Exception e) {
            System.out.println("Erro ao setar Record:" + e.getMessage());
        }
    }

    public int getNumFields() {
        return numFields;
    }

    public void setNumFields(int numFields) {
        this.numFields = numFields;
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
        this.setRecord();
        for (CSVRecord reg : this.record) {
            System.out.print("\n Linha:" + reg.getRecordNumber() + " | ");
            for (int i = 0; i < colunas; i++) {
                System.out.print(i + ":" + reg.get(i) + " | ");
            }
            System.out.println();
            if (reg.getRecordNumber() == 10)
                break;
        }
    }


    //Método para teste se uma String contém um valor numérico
      private boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //método que testa as 100 primeiras linhas do CSV a procura de valores numéricos
    private String columnDiscoverType(String col) {
        int numeric = 0, string = 0;
        //Necessário resetar o Record para ler novamente as linhas do CSV a partir do inicio
        this.setRecord();
        for (CSVRecord reg : this.record) {

            if (this.isNumeric(reg.get(col))) {
                numeric++;
            } else {
                string++;
            }
            if (reg.getRecordNumber() == 100)
                break;
        }
        if (string > 0) {
            return "T";
        } else {
            this.numFields++;
            return "N";
        }
    }

    private void setAtributos() {
        for (Map.Entry entry : this.headers.entrySet()) {
            this.atributos.add(new Atributo(Integer.parseInt(entry.getValue() + ""), entry.getKey().toString(), this.columnDiscoverType(entry.getKey() + "")));
        }
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public String atributosToString() {
        String ret = new String();
        for (Atributo atributo : this.atributos) {
            ret += atributo.getIndex() + "|" + atributo.getNome() + "|" + atributo.getTipoDado() + "\n";
        }
        return ret;
    }


}
