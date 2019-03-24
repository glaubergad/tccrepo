/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafico;

import java.util.List;

/**
 *
 * @author glaubergad
 */
public class Grafico {

    private TipoGrafico tipoGrafico;
    private String dimensaoX;
    private List<String> dimensaoY;

    public Grafico(TipoGrafico tipoGrafico, String dimensaoX, List<String> dimensaoY) {
        this.tipoGrafico = tipoGrafico;
        this.dimensaoX = dimensaoX;
        this.dimensaoY = dimensaoY;
    }

    public TipoGrafico getTipoGrafico() {
        return tipoGrafico;
    }

    public void setTipoGrafico(TipoGrafico tipoGrafico) {
        this.tipoGrafico = tipoGrafico;
    }

    public String getDimensaoX() {
        return dimensaoX;
    }

    public void setDimensaoX(String dimensaoX) {
        this.dimensaoX = dimensaoX;
    }

    public List<String> getDimensaoY() {
        return dimensaoY;
    }

    public void setDimensaoY(List<String> dimensaoY) {
        this.dimensaoY = dimensaoY;
    }
}
