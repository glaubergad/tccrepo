import freemarker.core.ParseException;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
    private HashMap<String,Object> input;

    public Generator() {
    }

    public Generator(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public boolean prepareGenerator{
        this.dashboard.getDataset().getHeaders();
    }


}
