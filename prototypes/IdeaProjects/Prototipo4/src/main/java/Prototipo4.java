import freemarker.template.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Prototipo4 {

    private static Dataset dataset;
    private static final File HOME = new File(Prototipo4.class.getProtectionDomain().getCodeSource().getLocation().getPath());

    public static void main(String[] args) {

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
            try {
                preparaConfig(dashboard);
            }catch(Exception e){
                System.out.println("Exceção de Template: " +e.getMessage());
            }
    }

        private static void preparaConfig(Dashboard dashboard) throws TemplateException, IOException {
            Configuration cfg = new Configuration(new Version("2.3.28"));
            cfg.setDirectoryForTemplateLoading(new File(HOME +"/templates"));

            // Some other recommended settings:
            cfg.setIncompatibleImprovements(new Version(2, 3, 28));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setLocale(Locale.US);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            Map<String,Object> input = new HashMap<>();

            System.out.println("arquivo:" + dashboard.getDataset().getArquivo());
            input.put("arquivo", dashboard.getDataset().getArquivo());
            System.out.println("Titulo:" + dashboard.getNomeDashboard());
            input.put("titulo", dashboard.getNomeDashboard());
            System.out.println("Graficos:" + dashboard.getListaGraficos().size());
            input.put("graficos", dashboard.getListaGraficos());

            try {
                Writer fileWriter = new FileWriter(new File(HOME+"\\boilerplate\\dashboard.html"));
                Template template = cfg.getTemplate("templatedcjs.ftl");
                template.process(input, fileWriter);
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Erro Process Template: "+e.getMessage()+e.getCause());

            }
        }
}
