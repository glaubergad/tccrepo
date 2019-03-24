/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import model.ModeloDashboard;
import model.DataSet;
import util.PackSaida;

/**
 *
 * @author glaubergad
 */
public class MotorTemplate {
    private ModeloDashboard modeloDashboard;
    private DataSet dataset;
    private PackSaida packSaida;

    public MotorTemplate() {
    }

    public MotorTemplate(ModeloDashboard modeloDashboard, DataSet dataset) {
        this.modeloDashboard = modeloDashboard;
        this.dataset = dataset;
    }
    
    
    
    
}
