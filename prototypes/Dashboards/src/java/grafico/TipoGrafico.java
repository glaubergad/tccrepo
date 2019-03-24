/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafico;

/**
 *
 * @author glaubergad
 */
public class TipoGrafico {

    private String tipo;
    private int maxDimY;

    public TipoGrafico(String tipo, int maxDimY) {
        this.tipo = tipo;
        this.maxDimY = maxDimY;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMaxDimY() {
        return maxDimY;
    }

    public void setMaxDimY(int maxDimY) {
        this.maxDimY = maxDimY;
    }

}
