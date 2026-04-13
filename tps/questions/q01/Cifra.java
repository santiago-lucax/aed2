/*
 * @author Lucas Santiago Pereira - 897498
*/

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
                if (val == -1) break; // CORREÇÃO: Previne loop infinito ao chegar no fim do arquivo
                tmp = (char)val;
                
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
                if (val == -1) break; // CORREÇÃO: Previne loop infinito ao chegar no fim do arquivo
                tmp = (char)val;
                
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

// Classe Cifra de Cezar
public class Cifra {

    /*
     * Metodo para aplicar a cifra de Cezar em uma String
     * @param msg string a ser cifrada
     * @param key chave de ciframento
    */
    public static String cezarCrip(String msg, int key) {

        String crip = ""; // Inicializar string vazia

        // Loop iterativo para cifrar todos os caracteres da string
        for (int x = 0; x < msg.length(); x = x + 1) {

            int tmp = msg.charAt(x); // Variavel tmp para armazenar valor inteiro
            char c = (char) (tmp + key); // Variavel com cast de valor inteiro para char
 
            crip = crip + c; // Adicionar char cifrado a string

        }

        return crip; // Retornar string cifrada

    }

    /*
     * Funcao para identificar se duas strings sao iguais
     * @param msg1 primeira mensagem a ser comparada
     * @param msg2 segunda mensagem a ser comparada
    */
    public static boolean isEquals(String msg1, String msg2) {
        
        // CORREÇÃO: Se os tamanhos forem diferentes, as strings não são iguais.
        if (msg1.length() != msg2.length()) {
            return false;
        }

        boolean OK = true; // Variavel de controle

        // Loop para comparar strings
        for (int x = 0; x < msg1.length() && OK; x = x + 1) {

            if (msg1.charAt(x) != msg2.charAt(x)) OK = false; // Teste dos caracteres

        }

        return OK; 

    }

    /*
     * Metodo principal para teste
    */
    public static void main(String[] args) {

        // Definir dados
        String msg = MyIO.readLine(); // String de entrada
        int key = 3; // Constante

        // Loop para entrada e ciframento das entradas
        while (!(isEquals(msg, "FIM"))) { 
         
            MyIO.println(cezarCrip(msg, key)); // Imprime mensagem cifrada
            msg = MyIO.readLine(); // Lê de novo no final do loop

        }

    }

}
