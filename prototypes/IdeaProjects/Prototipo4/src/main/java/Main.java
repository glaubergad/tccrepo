import dashgen.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

import java.util.Map;

public class Main {

    private static Dataset dataset;
    private static final File HOME = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    private static File arquivoCSV, destDir;

    public static void main(String[] args) {

        System.out.println("Selecionando o CSV");

        //JFileChooser para escolher o arquivo CSV
        JFileChooser csv = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        csv.setDialogTitle("Selecione o arquivo CSV: ");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos CSV", "csv");
        csv.setFileFilter(filter);
        int returnValue = csv.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            arquivoCSV = csv.getSelectedFile();

            //Feedback no terminal dos dados do CSV
            System.out.println("Caminho obtido do JFileChooser :" + arquivoCSV.getAbsolutePath());
            System.out.println("Esse é o nome do arquivo: " + arquivoCSV.getName());

            //Instancia um novo Dataset a partir o CSV
            try {
                dataset = new Dataset(arquivoCSV.getAbsolutePath(), arquivoCSV.getName());
            } catch (Exception e) {
                System.out.println("Exceção de dashgen.Dataset: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo invalido ou não encontrado");
        }

        System.out.println("Selecionando o Diretorio de Destino");

        //JFileChooser indicando o diretorio de destino
        JFileChooser destino = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        destino.setDialogTitle("Indique o diretório onde será criado o Dashboard: ");
        destino.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue2 = destino.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue2 == JFileChooser.APPROVE_OPTION) {
            destDir = destino.getSelectedFile();
        }

        //feedback terminal dos dados do diretorio destino
        System.out.println("Caminho obtido do objeto dashgen.Dataset :" + dataset.getCaminho());

        //Testando obtenção dos dados do cabeçalho do CSV
        System.out.println("\n Capturando o Map do headers");
        Map<String, Integer> headers = dataset.getHeaders();

        //Iterando e exibindo as opções do cabeçalho
        System.out.println("\n Exibindo conteudo do Map de Headers");
        for (Map.Entry entry : headers.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        System.out.println("\n Quantidade de Colunas = " + headers.size());
        int size = headers.size();

        System.out.println("\n Exibindo as 3 primeiras linhas do CSV com o método recordsToString()");
        dataset.recordsToString();


        //Instanciando graficos para gerar um dashboard
        System.out.println("\n Testando a criação de gráficos");
        Grafico grafico1 = new Grafico("sexo", "Sexo Entrevistados", Grafico.TIPO_PIZZA);
        Grafico grafico2 = new Grafico("classe_funcional", "Classe Funcional", Grafico.TIPO_BARRASV);
        Grafico grafico3 = new Grafico("previsao_idade","Idade Prevista",dashgen.Grafico.TIPO_BARRAS);

        //Instanciando novo modelo de Dashboard
        Dashboard dashboard = new Dashboard(dataset, "Meu Dashboard Gerado pelo DashGen");

        //Inserindo objetos gráficos no dashboard
        System.out.println("Inserindo dashgen.Grafico 1 :" + grafico1.getNome());
        dashboard.insereGrafico(grafico1);
        System.out.println("Inserindo dashgen.Grafico 2 :" + grafico2.getNome());
        dashboard.insereGrafico(grafico2);
        System.out.println("Inserindo dashgen.Grafico 3 :" + grafico3.getNome());
        dashboard.insereGrafico(grafico3);

        //Instanciando um novo gerador de dashboards
        try {
            Gerador gerador = new Gerador(dashboard);
        } catch (Exception e) {
            System.out.println("Erro gerando dashgen.Dashboard:" + e.toString());
        }


        //Gerando os arquivos de saida. Nesse teste geramos uma cópia do dashboard funcional e um arquivo zip dentro do mesmo diretorio
        try {
            PackSaida packSaida = new PackSaida(new File(HOME + "\\boilerplate"), destDir, arquivoCSV);
            System.out.println("Arquivo gerado com sucesso");
        } catch (IOException e) {
            System.out.println("Erro gerando Pack de Saida:" + e.toString());
        }
    }
}
