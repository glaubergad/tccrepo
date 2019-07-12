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
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import java.io.IOException;

public class Generator {

    private Dashboard dashboard;
    private Configuration cfg;
    private HashMap<String,Object> input;

    public Generator() {
    }

    public Generator(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public boolean prepareGenerator(){
        //Configuração do FreeMaker
        //Deve ser feito somente uma vez na aplicação e depois reusar

        cfg.setClassForTemplateLoading(Main.class,"templates");

        //Ajustes padrão recomendados
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // Preparação da entrada de dados para o Template
        // Necessário para formar o mapa de informações para montagem
        // do código final
        input.put("arquivo",this.dashboard.getDataset().getArquivo());
        input.put()
        return true;
    }


}
