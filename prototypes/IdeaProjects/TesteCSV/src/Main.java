import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

import java.util.Map;


public class Main {

    private static Dataset dataset;
    private static final File HOME = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());

    public static void main(String[] args) throws IOException {
        //metodo orientado a objetos
        System.out.println("Exibindo os cabeçalhos do CSV");

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Selecione o arquivo CSV: ");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos CSV", "csv");
        jfc.setFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File arquivoCSV = jfc.getSelectedFile();

            System.out.println("Caminho obtido do JFileChooser :" + arquivoCSV.getAbsolutePath());
            System.out.println("Esse é o nome do arquivo: " + arquivoCSV.getName());
            dataset = new Dataset(arquivoCSV.getAbsolutePath(), arquivoCSV.getName());
        } else {
            System.out.println("Arquivo invalido ou não encontrado");
        }


        System.out.println("Caminho obtido do objeto Dataset :" + dataset.getCaminho());

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


        System.out.println("\n Testando a criação de gráficos");
        Grafico grafico1 = new Grafico("sexo","Sexo Entrevistados",Grafico.TIPO_PIZZA);
        Grafico grafico2 = new Grafico("classe_funcional","Classe Funcional",Grafico.TIPO_PIZZA);
        Grafico grafico3 = new Grafico("previsao_idade","Idade Prevista",Grafico.TIPO_BARRASV);

        Dashboard dashboard = new Dashboard(dataset,"Meu Dashboard Gerado pelo DashGen");
        System.out.println("Inserindo Grafico 1 :" + grafico1.getNome());
        dashboard.insereGrafico(grafico1);
        System.out.println("Inserindo Grafico 2 :" + grafico2.getNome());
        dashboard.insereGrafico(grafico2);
        System.out.println("Inserindo Grafico 3 :" + grafico3.getNome());
        dashboard.insereGrafico(grafico3);

        System.out.println("Exibindo o dashboard");
        System.out.println(dashboard.toString());
        try {
            Generator gerador = new Generator(dashboard, HOME.getPath());
        }catch (Exception e){
            System.out.println("Erro de Geração:"+  e.getMessage());
        }

    }
}