package classes;

// Tipos necessários para manipulação de erros de áudio / arquivos e diretórios
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.util.ArrayList;

/**
 * Classe responsável por gerenciar os dados do algoritmo do jogo
 */
public class Jogo {

    // Variáveis do jogo
    private String palavraOriginal, dica, palavraEscondida;
    private int erros;
    private ArrayList<String> listaPalavras, listaDicas;

    // Instância do gerenciador da interface gráfica
    private InterfaceGrafica graficos = new InterfaceGrafica(this);

    /**
     * Constrói a classe
     * 
     * @param listaPalavras lista com cada linha do arquivo data/palavras.txt
     * @param listaDicaslista lista com cada linha do arquivo data/dicas.txt
     */
    public Jogo(ArrayList<String> listaPalavras, ArrayList<String> listaDicas) {
        this.listaPalavras = listaPalavras;
        this.listaDicas = listaDicas;
    }

    /**
     * Redefine o jogo para um estado inicial e dá início ao algoritmo
     * 
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public void iniciar() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        // Redefinição das variáveis do jogo para um estado inicial
        this.erros = 0;
        this.palavraEscondida = "";

        // Sorteio da palavra e sua respectiva dica
        this.sortearPalavra();
        
        // Criação de uma versão escondida da palavra, utilizam-se asteriscos para escondê-la
        for (int i = 0; i < palavraOriginal.length(); i++) {
            this.palavraEscondida += "*";
        }

        // Definição das propriedades do gerenciador da interface gráfica
        graficos.setPalavraOriginal(this.palavraOriginal);
        graficos.setPalavraEscondida(this.palavraEscondida);
        graficos.setDica("DICA: " + this.dica);

        // Redefinição dos componentes de teclado e forca para um estado inicial
        graficos.resetarTeclado();
        graficos.resetarForca();

        // Renderização da interface gráfica
        graficos.renderizar();
    }

    /**
     * Sorteia um índice aleatório do vetor de palavras e associa o par palavra-dica às propriedades da classe
     */
    private void sortearPalavra() {
        int indiceAleatorio = (int) (Math.random() * this.listaPalavras.size());

        this.palavraOriginal = listaPalavras.get(indiceAleatorio);
        this.dica = listaDicas.get(indiceAleatorio);
    }

    /**
     * Recebe a letra do evento disparado pelo teclado e verifica a sua ocorrência na palavra sorteada
     * 
	 * Baseado em referências encontradas na internet
     * 
	 * Beginners Book: Java – String substring() Method example
	 * @link https://beginnersbook.com/2013/12/java-string-substring-method-example/
     * 
     * @param letra caractere inserido na entrada pelo usuário
     * 
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public void novaTentativa(String letra) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        boolean existe = false;
        for (int i = 0; i < this.palavraOriginal.length(); i++){
            if(this.palavraOriginal.charAt(i) == Character.toLowerCase(letra.charAt(0))) {
                existe = true;
                this.palavraEscondida = 
                    this.palavraEscondida.substring(0, i) + 
                    letra + 
                    this.palavraEscondida.substring(i + 1, this.palavraOriginal.length());
            }
        }

        if(!existe) {

            // Se a palavra não conter a letra, contabiliza-se um erro e atualiza-se a imagem da forca
            this.erros++;
            graficos.componenteForca.atualizar(erros);
        }

        // Atualização das posições onde a letra é encontrada na palavra
        graficos.setPalavraEscondida(this.palavraEscondida);
    }

    /**
     * Verifica o estado atual do jogo (vitória, derrota ou em andamento) comparando a palavra escondida e a original
     * 
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public void verificaStatus() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if(this.palavraOriginal.equals(this.palavraEscondida.toLowerCase())) {

            // Se o jogo estiver ganho, a vitória é anunciada
            graficos.renderizarMensagemVitoria();
        } else if(this.erros >= 6) {
            
            // Se o jogo estiver perdido, a derrota é anunciada
            graficos.renderizarMensagemDerrota();
        }
    }
}
