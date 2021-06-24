package classes.components;

import java.util.ArrayList;

// Bibliteca AWT
import java.awt.*;

// Componentes gráficos da biblioteca Swing
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe responsável por renderizar a forca
 */
public class Forca extends JPanel {

    // Array contendo as imagens da forca em cada um dos seus possíveis estágios
    ArrayList<ImageIcon> corpoEstagios = new ArrayList<ImageIcon>(){{
        add(new ImageIcon("img/forca1.png"));
        add(new ImageIcon("img/forca2.png"));
        add(new ImageIcon("img/forca3.png"));
        add(new ImageIcon("img/forca4.png"));
        add(new ImageIcon("img/forca5.png"));
        add(new ImageIcon("img/forca6.png"));
        add(new ImageIcon("img/forca7.png"));
    }};

    // Manipulação da imagem da forca
    JLabel imgForca = new JLabel();
    ImageIcon novaImagem = new ImageIcon(corpoEstagios.get(0).getImage().getScaledInstance(220, 220,  java.awt.Image.SCALE_SMOOTH));
    
    /**
     * Constrói a classe
     */
    public Forca(){
        
        // Definição de tamanhos
        setPreferredSize(new Dimension(220,220));
        imgForca.setPreferredSize(new Dimension(220,220));

        // Disposição da imagem
        imgForca.setIcon(novaImagem);
        add(imgForca);
    }

    /**
     * Atualiza a imagem da forca conforme a quantidade de erros
     * 
	 * Baseado em referências encontradas na internet
     * 
	 * Resposta de Daniel no fórum do StackOverflow
	 * @link https://pt.stackoverflow.com/a/94450
     * 
     * @param erros quantidade de erros
     */
    public void atualizar(int erros) {
        
        // Redimensionamento e renderização da nova imagem
        novaImagem = new ImageIcon(corpoEstagios.get(erros).getImage().getScaledInstance(220, 220,  java.awt.Image.SCALE_SMOOTH));
        imgForca.setIcon(novaImagem);
    }
}
