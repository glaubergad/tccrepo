package DashGen;
/*
 *   Gerador.java
 *   Classe integrante do Pacote DashGen
 *   Responsável processar os dados obtidos de um objeto Dashboard. É o motor de transformação e geração de código
 *   Fonte da Aplicação Dashgen
 *   TCC - Gerador de Dashboards
 *   IFPA - Campus Belém - 2019
 *   Aluno: Glauber Matteis Gadelha
 *   Orientador: Prof. Me. Claudio Roberto de Lima Martins
 */

/*
 *Para se aproveitar de uma estrutura pronta de processamento de modelos (Templates), o Dashgen utiliza a
 *Apache FreeMarker, versão 2.3.28
 * https://freemarker.apache.org/
 */
import freemarker.template.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Gerador {

    private Dashboard dashboard;
    private static final String sep = File.separator;
    private static final File HOME = new File(Gerador.class.getProtectionDomain().getCodeSource().getLocation().getPath()+ sep +"DashGen");
    private Template template;
    private final Configuration cfg = new Configuration(new Version("2.3.28"));
    private final Map<String,Object> input = new HashMap<>();

    //Método construtor deve receber como parâmetro um objeto Dashboard
    public Gerador(Dashboard dashboard) throws IOException, TemplateException {
        this.dashboard = dashboard;

       /*
        *Com o objeto Dashboard carregado, a classe Gerador prepara um HashMap com objetos que vão preencher as
        * marcações do arquivo de template
        */
        carregaInput();

        /*
         * o método configurar passa todos os dados necessários para o motor de processamento, como o caminho dos
         * arquivos, o nome do arquivo de template, a codificação UTF-8 entre outos
         */
        configurar();

        /*
         * O método processar executa o processamento do template, no final gravando no caminho determinado um arquivo
         * final com os dados preenchendo as marcações atendendo as regras de negócio especificadas no modelo.
         */
        processar();
    }

    //Configurador dos dados necessários para processamento do template
    private void configurar() throws IOException {
        cfg.setDirectoryForTemplateLoading(new File(HOME + sep + "templates"));
        cfg.setIncompatibleImprovements(new Version(2, 3, 28));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        this.template = cfg.getTemplate("dashboard.ftl");
    }


    //Método responsável por criar um HashMap com todos os dados e objetos a serem preenchidos sobre o template
    private void carregaInput(){
        System.out.println("arquivo:" + dashboard.getDataset().getArquivo());
        input.put("arquivo", dashboard.getDataset().getArquivo());
        System.out.println("Titulo:" + dashboard.getNomeDashboard());
        input.put("titulo", dashboard.getNomeDashboard());
        System.out.println("Graficos:" + dashboard.getListaGraficos().size());
        input.put("graficos", dashboard.getListaGraficos());
    }

    //Executa o processamento do template e a geração do arquivo final, gravando no caminho especificado sob o nome
    // dashboard.html
    private void processar() throws IOException, TemplateException {
        Writer fileWriter = new FileWriter(new File(HOME+sep+"boilerplate"+sep+"dashboard.html"));
        template.process(input, fileWriter);
        fileWriter.close();
    }
}
