/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import grafico.Grafico;
import java.util.List;

/**
 *
 * @author glaubergad
 */
public class ModeloDashboard {
    
    private DataSet dataset;
    private List<Grafico> graficos;

    public ModeloDashboard(DataSet dataset, List<Grafico> graficos) {
        this.dataset = dataset;
        this.graficos = graficos;
    }

    public DataSet getDataset() {
        return dataset;
    }

    public void setDataset(DataSet dataset) {
        this.dataset = dataset;
    }

    public List<Grafico> getGraficos() {
        return graficos;
    }

    public void setGraficos(List<Grafico> graficos) {
        this.graficos = graficos;
    }
    
    
    
    
}
