package dashboard;
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

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Gerador {

    private Dashboard dashboard;
    private static final String sep = File.separator;
    private final File HOME = new File("templates");
    private File destDir;
    private Template template;
    private final Configuration cfg = new Configuration(new Version("2.3.28"));
    private final Map<String,Object> input = new HashMap<>();

    //Método construtor deve receber como parâmetro um objeto Dashboard
    public Gerador(Dashboard dashboard, File destDir) throws IOException, TemplateException {
        this.dashboard = dashboard;
        this.destDir = destDir;
        carregaInput();
        configurar();
        processar();
    }

    //Configurador dos dados necessários para processamento do template
    private void configurar() throws IOException {
        cfg.setDirectoryForTemplateLoading(HOME);
        cfg.setIncompatibleImprovements(new Version(2, 3, 28));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(new Locale("pt","BR"));
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
        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(destDir.getAbsolutePath()  +sep+"dashboard.html")),"UTF-8"));
        template.process(input, fileWriter);
        fileWriter.close();
    }
}
