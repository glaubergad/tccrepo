package DashGen;

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
    private static final File HOME = new File(Gerador.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    private Template template;
    private final Configuration cfg = new Configuration(new Version("2.3.28"));
    private final Map<String,Object> input = new HashMap<>();


    public Gerador(Dashboard dashboard) throws IOException, TemplateException {
        this.dashboard = dashboard;
        carregaInput();
        configurar();
        processar();
    }

    private void configurar() throws IOException {
        cfg.setDirectoryForTemplateLoading(new File(HOME +"/templates"));
        cfg.setIncompatibleImprovements(new Version(2, 3, 28));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        this.template = cfg.getTemplate("dashboard.ftl");
    }

    private void carregaInput(){
        System.out.println("arquivo:" + dashboard.getDataset().getArquivo());
        input.put("arquivo", dashboard.getDataset().getArquivo());
        System.out.println("Titulo:" + dashboard.getNomeDashboard());
        input.put("titulo", dashboard.getNomeDashboard());
        System.out.println("Graficos:" + dashboard.getListaGraficos().size());
        input.put("graficos", dashboard.getListaGraficos());
    }

    private void processar() throws IOException, TemplateException {
        Writer fileWriter = new FileWriter(new File(HOME+"\\boilerplate\\dashboard.html"));
        template.process(input, fileWriter);
        fileWriter.close();
    }
}
