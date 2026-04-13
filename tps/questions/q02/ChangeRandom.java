/*
 * @author Lucas Santiago Pereira - 897498
*/

// Bibliotecas
import java.util.Random;
import java.io.*;
import java.nio.charset.*;

/*
 * Classe MyIO reduzida com apenas o necessario para o exercicio
*/
class MyIO {

    private static BufferedReader in = new BufferedReader(
        new InputStreamReader(System.in, Charset.forName("ISO-8859-1"))
    );
    private static String charset = "ISO-8859-1";

    public static void setCharset(String charset_){
        charset = charset_;
        in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
    }

    public static void print(String x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        } catch(UnsupportedEncodingException e){
            System.out.println("Erro: charset invalido");
        }
    }

    public static void println(String x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        } catch(UnsupportedEncodingException e){
            System.out.println("Erro: charset invalido");
        }
    }

    public static String readString(){
        String s = "";
        char tmp;
        try{
            do{
                int val = in.read();
                if (val == -1) break; // CORREÇÃO: Tratamento de Fim de Arquivo (EOF)
                tmp = (char) val;
                
                if(tmp != '\n' && tmp != ' ' && tmp != 13){
                    s += tmp;
                }
            }while(tmp != '\n' && tmp != ' ');
        }catch(IOException ioe){
            System.out.println("lerString: " + ioe.getMessage());
        }
        return s;
    }

    public static String readLine(){
        String s = "";
        char tmp;
        try{
            do{
                int val = in.read();
                if (val == -1) break; // CORREÇÃO: Tratamento de Fim de Arquivo (EOF)
                tmp = (char) val;
                
                if(tmp != '\n' && tmp != 13){
                    s += tmp;
                }
            }while(tmp != '\n');
        }catch(IOException ioe){
            System.out.println("lerLine: " + ioe.getMessage());
        }
        return s;
    }

    public static String readString(String prompt){
        print(prompt);
        return readString();
    }

    public static String readLine(String prompt){
        print(prompt);
        return readLine();
    }
}

// Classe Alteracao Aleatoria
public class ChangeRandom {

    // Instancie como static para que o estado persista entre as chamadas
    public static Random rand = new Random();
    static {

        rand.setSeed(4);

    }

    /*
     * Funcao que gera dois caracteres aleatorios e toda ocorrencia do primeiro e substituido pelo segunda
     * @param str string a ser modificada
    */
    public static String changeRandom(String str) {

        String tmp = ""; // String temporaria vazia
        
        // Geracao de duas variaveis com caracteres randomicos
        char a = (char)('a' + (Math.abs(rand.nextInt()) % 26)); // Variavel que guarda caractere a ser susbtituido
        char b = (char)('a' + (Math.abs(rand.nextInt()) % 26)); // Variavel que guarda caractere a ser trocado

        // Loop para iterar por toda a string e fazer as modificacoes
        for (int x = 0; x < str.length(); x = x + 1) {

            // Checar se caractere e valido para ser substituido
            if (str.charAt(x) == a) tmp += b;
            else tmp += str.charAt(x); // Substituir caractere valido

        }

        return tmp; // Retornar

    }

    /*
     * Funcao para identificar se duas strings sao iguais
     * @param msg1 primeira mensagem a ser comparada
     * @param msg2 segunda mensagem a ser comparada
    */
    public static boolean isEquals(String msg1, String msg2) {

        // CORREÇÃO: Teste prévio de tamanho das strings
        if (msg1.length() != msg2.length()) {
            return false;
        }

        boolean OK = true; // Variavel de controle

        // Loop para comparar strings
        for (int x = 0; x < msg1.length() && OK; x = x + 1) {

            if (msg1.charAt(x) != msg2.charAt(x)) OK = false; // Teste dos caracteres

        }

        return OK; // Retorna 

    }

    public static void main(String[] args) {

        String str = MyIO.readLine(); // Instanciar string

        // Loop para entrada e modificacoes na string
        while (!(isEquals(str, "FIM"))) {

            MyIO.println(changeRandom(str)); // Modifica
            str = MyIO.readLine(); // Le de novo no final do loop

        }

    }

}
