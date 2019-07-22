import freemarker.core.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.IOException;

public class Generator {

    private Dashboard dashboard;
    private File caminho;
    private Configuration cfg;
    private HashMap<String, Object> input;
    private Template template;


    public Generator(Dashboard dashboard, String caminho) throws TemplateException, IOException {
        this.dashboard = dashboard;
        this.caminho = new File(caminho);
        this.cfg = new Configuration(new Version("2.3.28"));
        this.cfg.setDirectoryForTemplateLoading(new File(this.getClass().getClassLoader().getResource("").getPath()+"/templates"));

        // Some other recommended settings:
        this.cfg.setIncompatibleImprovements(new Version(2, 3, 28));
        this.cfg.setDefaultEncoding("UTF-8");
        this.cfg.setLocale(Locale.US);
        this.cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);


        System.out.println("arquivo:" + this.dashboard.getDataset().getArquivo());
        input.put("arquivo", this.dashboard.getDataset().getArquivo());
        System.out.println("Titulo:" + this.dashboard.getNomeDashboard());
        input.put("titulo", this.dashboard.getNomeDashboard());
        System.out.println("Graficos:" + this.dashboard.getListaGraficos().size());
        input.put("graficos", this.dashboard.getListaGraficos());

        try {
            Writer fileWriter = new FileWriter(new File("\\boilerplate\\dashboard.html"));
            this.template = this.cfg.getTemplate("scriptdcjs.ftl");
            this.template.process(input, fileWriter);
            fileWriter.close();
        } catch (TemplateException e) {
            System.out.println("Erro Process Template: "+e.getMessage()+e.getCause());

        }

    }

    public void prepareGenerator(){

    }

    public void writeDashboard() throws Exception{
        System.out.println(this.caminho.getAbsolutePath() + "\\boilerplate\\dashboard.html");
        Writer fileWriter = new FileWriter(new File(caminho.getAbsolutePath() + "\\boilerplate\\dashboard.html"));
        try {
            this.template.process(input, fileWriter);
        } catch (TemplateException e) {
            System.out.println("Erro Process Template: "+e.getMessage()+e.getCause());
        } finally
         {
            fileWriter.close();
        }

    }

//File teste = new File(this.getClass().getClassLoader().getResource("").getPath()+"/templates");

}
