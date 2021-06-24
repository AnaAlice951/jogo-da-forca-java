package classes.components;

import java.util.ArrayList;

// Tipos necessários para manipulação de erros de áudio
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Biblioteca AWT (suporte a eventos)
import java.awt.*;
import java.awt.event.*;

// Componentes gráficos da biblioteca Swing
import javax.swing.JButton;
import javax.swing.JPanel;

import classes.InterfaceGrafica;
import classes.Jogo;

/**
 * Classe responsável por renderizar o teclado
 */
public class Teclado extends JPanel{ 

    // Instância do jogo
    Jogo jogo;

    /**
     * Classe reponsável por renderizar cada tecla do teclado
     */
    public class Tecla extends JButton{
        
        /**
         * Constrói a classe
         * 
         * @param letra letra da tecla
         */
        public Tecla(String letra){
            setSize(15, 15);
            setText(letra);
        }     
    } 
    
    // Lista com cada letra do alfabeto (incluindo o caractere 'Ç')
    ArrayList<String> alfabeto = new ArrayList<String>(){{
        add("A"); add("B"); add("C"); add("Ç"); add("D"); add("E"); add("F"); add("G"); add("H");
        add("I"); add("J"); add("K"); add("L"); add("M"); add("N"); add("O"); add("P"); add("Q"); 
        add("R"); add("S"); add("T"); add("U"); add("V"); add("W"); add("X"); add("Y"); add("Z");
    }};

    // Lista de componentes Tecla
    public ArrayList<Tecla> teclaArray = new ArrayList<Tecla>(){{
        for (int i = 0; i < alfabeto.size(); i++){
                
            // Vincula cada letra a uma tecla
            add(new Tecla(alfabeto.get(i)));
        }
    }};

    
    /**
     * Constrói a classe
     * 
	 * Baseado em referências encontradas na internet
     * 
	 * Devmedia - Java Listeners: Trabalhando com ActionListener e KeyListener em Java
	 * @link https://www.devmedia.com.br/java-listeners-trabalhando-com-actionlistener-e-keylistener-em-java/31850
     * 
     * @param jogo instância do Jogo
     */
    public Teclado(Jogo jogo){
        this.jogo = jogo;
        setPreferredSize(new Dimension(InterfaceGrafica.LARGURA, 100));
        
        for (int i = 0; i < teclaArray.size(); i++){
            
            // Adição de cada tecla da lista ao componente Teclado
            this.add(teclaArray.get(i));

            // Adição do método de suporte ao disparo de eventos em cada um dos botões
            teclaArray.get(i).addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    // Desativação das teclas ao serem acionadas
                    ((Tecla) e.getSource()).setEnabled(false);

                    try {
                        
                        // Chamada de uma nova tentativa, passando a letra da tecla acionada como parâmetro
                        jogo.novaTentativa((((Tecla)e.getSource()).getText()));
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
                        e2.printStackTrace();
                    }

                    try {
                        
                        // Verificação do estado atual do jogo
                        jogo.verificaStatus();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    }
}