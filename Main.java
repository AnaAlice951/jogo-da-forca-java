// Tipos necessários para manipulação de erros de som / arquivos e diretórios
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.util.ArrayList;

import classes.Jogo;
import classes.ManipuladorTXT;
import classes.PlayerSom;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        // Instância do reprodutor de áudio
        PlayerSom player = new PlayerSom();

        // Lista de linhas do arquivo data/palavras.txt
        ArrayList<String> listaDePalavras = ManipuladorTXT.lerArquivoDeTexto("palavras.txt");
        
        // Lista de linhas do arquivo data/dicas.txt
        ArrayList<String> listaDeDicas = ManipuladorTXT.lerArquivoDeTexto("dicas.txt");

        // Tocar música de fundo em loop
        player.play("sounds/musicaFundo.wav", true);

        // Instanciar e iniciar algoritmo do jogo
        Jogo jogo = new Jogo(listaDePalavras, listaDeDicas);
        jogo.iniciar();        
    }
}