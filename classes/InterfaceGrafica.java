package classes;

// Tipos necessários para manipulação de erros de áudio
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Biblioteca AWT
import java.awt.*;

// Componentes gráficos da biblioteca Swing
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import classes.components.Dica;
import classes.components.Forca;
import classes.components.Palavra;
import classes.components.Teclado;

/**
 * Classe responsável por gerenciar a interface gráfica do jogo
 */
public class InterfaceGrafica {

    // Variáveis gráficas
    public static int LARGURA = 520, ALTURA = 480;
    String palavraOriginal, palavraEscondida, dica;
    
    // Instância do reprodutor de áudio
    static PlayerSom player = new PlayerSom();

    // Instância do Jogo
    Jogo jogo;

    // Criação da janela
    JFrame tela = new JFrame("Jogo da Forca");
    
    // Declaração dos componentes gráficos
    Dica componenteDica;
    Forca componenteForca;
    Palavra componentePalavra;
    Teclado componenteTeclado;

    /**
     * Constrói a classe
     * 
     * @param jogo instância do Jogo
     */
    public InterfaceGrafica(Jogo jogo) {
        this.jogo = jogo;

        // Inicialização dos componentes gráficos
        componenteDica = new Dica();
        componenteForca = new Forca();
        componentePalavra = new Palavra();
        componenteTeclado = new Teclado(this.jogo);
    }

    /**
     * Retorna a palavra original
     * 
     * @return palavra original
     */
    public String getPalavraOriginal() {
        return palavraOriginal;
    }

    /**
     * Substitui a palavra original pela string do parâmetro
     * 
     * @param palavraOriginal nova palavra
     */
    public void setPalavraOriginal(String palavraOriginal) {
        this.palavraOriginal = palavraOriginal;
    }


    /**
     * Retorna a palavra escondida
     * 
     * @return palavra escondida
     */
    public String getPalavraEscondida() {
        return palavraEscondida;
    }

    /**
     * Substitui a palavra escondida pela string do parâmetro
     * 
     * @param palavraEscondida nova palavra
     */
    public void setPalavraEscondida(String palavraEscondida) {
        this.palavraEscondida = palavraEscondida;
        this.componentePalavra.setText(palavraEscondida);
    }

    /**
     * Retorna a dica
     * 
     * @return dica
     */
    public String getDica() {
        return dica;
    }

    /**
     * Substitui a dica pela string do parâmetro
     * 
     * @param dica nova dica
     */
    public void setDica(String dica) {
        this.dica = "DICA: " + dica;
        this.componenteDica.setText(dica);
    }

    /**
     * Renderiza toda a interface gráfica
     * 
	 * Baseado em referências encontradas na internet
     * 
	 * Documentação oficial da classe BoxLayout
	 * @link https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
     */
    public void renderizar() {
        
        // Janela
        tela.setSize(LARGURA, ALTURA);
        tela.setResizable(false);
        tela.setVisible(true);
        tela.setLocationRelativeTo(null);
        tela.setLayout(new BoxLayout(tela.getContentPane(), BoxLayout.PAGE_AXIS));
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dica
        tela.add(componenteDica);
        componenteDica.setAlignmentX(Component.CENTER_ALIGNMENT);
        componenteDica.setFont(new Font("Times new Roman", Font.BOLD,20));

        // Forca
        tela.add(componenteForca);
        componenteForca.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Palavra escondida
        tela.add(componentePalavra);
        componentePalavra.setFont(new Font("Times new Roman", Font.BOLD,35));
        componentePalavra.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Teclado
        tela.add(componenteTeclado);
        componenteTeclado.setAlignmentX(Component.CENTER_ALIGNMENT);
        componenteTeclado.setAlignmentY(Component.HEIGHT);
    }

    /**
     * Anuncia a vitória em uma caixa de diálogo e dá as opções de jogar novamente ou encerrar a execução
     * 
	 * Baseado em referências encontradas na internet
     * 
	 * Mkyong: Java Swing – JOptionPane showOptionDialog example
	 * @link https://mkyong.com/swing/java-swing-joptionpane-showoptiondialog-example/
     */
    public void renderizarMensagemVitoria() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        // Reproduz som tema de vitória
        player.play("sounds/vitoria.wav", false);
        int op = JOptionPane.showOptionDialog(
            tela, 
            "Você venceu! Deseja jogar novamente?", 
            "Fim de jogo", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            null, 
            null
        );

        if(op == 0) {
            // Reinício do jogo
            jogo.iniciar();
        } else if (op == 1) {
            System.exit(0);
        }
    }

    /**
     * Anuncia a derrota em uma caixa de diálogo e dá as opções de jogar novamente ou encerrar a execução
     * 
	 * Baseado em referências encontradas na internet
     * 
	 * Mkyong: Java Swing – JOptionPane showOptionDialog example
	 * @link https://mkyong.com/swing/java-swing-joptionpane-showoptiondialog-example/
     */
    public void renderizarMensagemDerrota() throws UnsupportedAudioFileException, IOException, LineUnavailableException, HeadlessException {
        
        // Reproduz som tema de derrota
        player.play("sounds/derrota.wav", false);
        int op = JOptionPane.showOptionDialog(
            tela, 
            "Você perdeu! Deseja jogar novamente?", 
            "Fim de jogo", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            null, 
            null
        );

        if(op == 0) {
            // Reinício do jogo
            jogo.iniciar();
        } else if (op == 1) {
            System.exit(0);
        } else {
            System.exit(0);
        }
    }

    /**
     * Redefine o teclado para o estado inicial
     */
    public void resetarTeclado() {
        for (int i = 0; i < this.componenteTeclado.teclaArray.size(); i++) {
            this.componenteTeclado.teclaArray.get(i).setEnabled(true);
        }
    }

    /**
     * Redefine o componente gráfico da forca para o estado inicial
     */
    public void resetarForca() {
        this.componenteForca.atualizar(0);
    }
}
