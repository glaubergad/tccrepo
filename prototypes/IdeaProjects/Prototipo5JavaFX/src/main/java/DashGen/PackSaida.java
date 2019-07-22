package DashGen;

import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;

public class PackSaida {
    private File boilerplateDir,destDir,csvFile;
    private static final String FILE_NAME = "dashgen.zip";

    public PackSaida(File boilerplateDir, File destDir,File csvFile) throws IOException {
        this.destDir = destDir;
        this.boilerplateDir = boilerplateDir;
        this.csvFile =csvFile;
        processarCopia();
        processarZip();
    }

    private void processarCopia() throws IOException {
        FileUtils.copyFile(this.csvFile, new File(boilerplateDir.getAbsolutePath()+"\\data\\" + csvFile.getName()));
        FileUtils.copyDirectory(this.boilerplateDir,this.destDir);
    }

    private void processarZip(){
        ZipUtil.pack(this.boilerplateDir,new File(this.destDir.getAbsolutePath()+"\\"+ FILE_NAME));
    }
}
