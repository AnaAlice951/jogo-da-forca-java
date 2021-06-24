package classes.components;

// Tipo da biblioteca AWT
import java.awt.Dimension;

// Componente gráfico da biblioteca Swing
import javax.swing.JLabel;

import classes.InterfaceGrafica;

/**
 * Classe responsável por renderizar a dica
 */
public class Dica extends JLabel{
    public Dica(){
        setPreferredSize(new Dimension(InterfaceGrafica.LARGURA, 60));
    }
}