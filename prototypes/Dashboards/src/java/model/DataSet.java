/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.List;

/**
 *
 * @author glaubergad
 */
public class DataSet {
    private File arquivo;
    private List<String> colunas;

    public DataSet(File arquivo) {
        this.arquivo = arquivo;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public List<String> getColunas() {
        return colunas;
    }

    public void setColunas(List<String> colunas) {
        this.colunas = colunas;
        //TODO - Lógica de reconhecimento da primeira coluna do CSV
        //Formar uma lista com os nomes dos atributos
    }  
    
}
