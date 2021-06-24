package classes;

// Classe necessária para manipulação de arquivos e diretórios
import java.io.File;

// Tipo necessário para manipulação de erros em arquivos e diretórios
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Classe responsável por gerenciar a manipulação de arquivos
 */
public class ManipuladorTXT {

    /**
     * Padroniza as palavras e dicas dos arquivos de texto em ArrayList<String>
     * 
	 * Baseado em referências encontradas na internet
     * 
	 * Resposta de Calebe Oliveira no fórum do StackOverflow
	 * @link https://pt.stackoverflow.com/a/1824
     * 
     * @param nomeArquivo nome do arquivo de texto a ser lido
     * 
     * @return lista com cada linha do arquivo
     */
    public static ArrayList<String> lerArquivoDeTexto(String nomeArquivo) {
        
        // Lista com cada linha do arquivo
        ArrayList<String> linhas = new ArrayList<String>();
        
        try {
            File txt = new File("data", nomeArquivo);
            Scanner entrada = new Scanner(txt);

            // Percorre o arquivo de texto salvando linha por linha no ArrayList
            while(entrada.hasNextLine()) {
                linhas.add(sanitizarLinha(entrada.nextLine()));
            }

            entrada.close();

            return linhas;
        } catch(FileNotFoundException erro) {
            System.out.println("Erro ao carregar o arquivo.");
            erro.printStackTrace();
            return null;
        }
    }

    /**
     * Sanitiza e padroniza a string
     * 
	 * Baseado em referências encontradas na internet
     * 
	 * Resposta de Guilherme Nascimento no fórum do StackOverflow
	 * @link https://pt.stackoverflow.com/a/260998
     * 
     * @param linha string a ser sanitizada
     * 
     * @return string sanitizada
     */
    public static String sanitizarLinha(String linha) {
        String linhaSanitizada = Normalizer.normalize(linha, Normalizer.Form.NFD); 
        Pattern padrao = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        linhaSanitizada = padrao.matcher(linhaSanitizada).replaceAll("");

        return linhaSanitizada.toLowerCase();
    }
}