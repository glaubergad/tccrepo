package DashGen;

/*
 *   packSaida.java
 *   Classe integrante do Pacote DashGen
 *   Classe que gera como produto de sua execução no diretorio especificado uma cópia dos arquivos necessários, além
 *   do arquivo HTML gerado na pasta especificada. Além disso, gera também um arquivo formato ZIP contendo toda a
 *   estrutura
 *   TCC - Gerador de Dashboards
 *   IFPA - Campus Belém - 2019
 *   Aluno: Glauber Matteis Gadelha
 *   Orientador: Prof. Me. Claudio Roberto de Lima Martins
 */

import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;

public class PackSaida {
    private final String sep = File.separator;
    private final File boilerplateDir = new File(PackSaida.class.getProtectionDomain().getCodeSource().getLocation().getPath() + sep + "DashGen" + sep + "boilerplate");
    private File destDir, csvFile;
    private static final String FILE_NAME = "dashgen.zip";

    //Método construtor recebe o diretorio de destino e o arquivo CSV para composição do pacote de saída Dashgen
    public PackSaida(File destDir, File csvFile) throws IOException {
        this.destDir = destDir;
        this.csvFile = csvFile;
        //Recebidos os dados necessários, é processada a cópia dos arquivos
        processarCopia();
        /*
         * Método processa a compactação dos arquivos contidos no diretório de origem da aplicação em um arquivo ZIP
         * no diretório de destino
         */
        processarZip();
    }

    private void processarCopia() throws IOException {
        FileUtils.copyFile(this.csvFile, new File(this.destDir.getAbsolutePath() + sep + "data" + sep + csvFile.getName()));
        FileUtils.copyDirectory(this.boilerplateDir, this.destDir);
    }

    private void processarZip() {
        ZipUtil.pack(this.destDir, new File(this.destDir.getAbsolutePath() + sep + FILE_NAME));
    }
}
