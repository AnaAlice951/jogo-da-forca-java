package classes;

// Classes necessárias para reprodução de áudio
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

// Classe necessária para manipulação de arquivos e diretórios
import java.io.File;

// Tipos necessários para manipulação de erros de áudio
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Classe responsável por gerenciar a reprodução de áudio
 */
public class PlayerSom {
	private Clip clip; 
	private AudioInputStream audioStream; 

	/**
	 * Reproduzir áudios
	 * 
	 * Baseado em referências encontradas na internet
	 * Tópico iniciado por Roshana Pitigala no fórum do StackOverflow
	 * @link https://stackoverflow.com/q/34534732
	 * 
	 * @param diretorio diretório do arquivo de áudio
	 * @param loop define se o áudio será reproduzido em loop
	 * 
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
    public void play(String diretorio, boolean loop) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		audioStream = AudioSystem.getAudioInputStream(new File(diretorio).getAbsoluteFile());  
		clip = AudioSystem.getClip(); 
		clip.open(audioStream);

		if(loop){
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
			   
        clip.start();
    }
}