/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;

/**
 *
 * @author BRUNO BELTRE GUZMAN
 */
public class Colores {

    private Color fondo1;
    private Color gris1;
    private Color gris2;
    private Color gris3;
    private Color colorPrymary;

    public Colores() {
        fondo1 = new Color(24, 25, 26);
        gris1 = new Color(36, 37, 38);
        gris2 = new Color(78, 79, 80);
        gris3 = new Color(99, 101, 103);
        colorPrymary = new Color(255, 50, 21);
    }

    public Color getFondo1() {
        return fondo1;
    }

    public Color getGris1() {
        return gris1;
    }

    public Color getGris2() {
        return gris2;
    }

    public Color getGris3() {
        return gris3;
    }

    public Color getColorPrymary() {
        return colorPrymary;
    }
    
    Color borderVerde = new Color(	0,	127,	0);

    public Color getBorderVerde() {
        return borderVerde;
    }

    Color borderAzul = new Color(0, 126, 255);

    public Color getBorderAzul() {
        return borderAzul;
    }

    Color borderRojo = new Color(	255,	0,	0);

    public Color getBorderRojo() {
        return borderRojo;
    }

}
