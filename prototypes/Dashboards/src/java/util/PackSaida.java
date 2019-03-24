/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;

/**
 *
 * @author glaubergad
 */
public class PackSaida {
   private File pathDataset;
   private File pathSaida;

    public PackSaida(File pathDataset, File pathSaida) {
        this.pathDataset = pathDataset;
        this.pathSaida = pathSaida;
    }



    public File getPathDataset() {
        return pathDataset;
    }

    public void setPathDataset(File pathDataset) {
        this.pathDataset = pathDataset;
    }

    public File getPathSaida() {
        return pathSaida;
    }

    public void setPathSaida(File pathSaida) {
        this.pathSaida = pathSaida;
    }
   
    public void finalizaArquivo(){
        //TODO Código de geração do ZIP em pasta temporária
    }
   
   
}
