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
                if (val == -1) break; // CORREÇÃO: Previne loop infinito no EOF
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
                if (val == -1) break; // CORREÇÃO: Previne loop infinito no EOF
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

public class Is {

    // Definir vogais e consoantes
    public static String vogais = "aeiouAEIOU";
    public static String consoantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
    public static String integer = "0123456789";
    public static String real = integer + ".,";

    /*
     * Funcao para identificar se duas strings sao iguais
     * @param msg1 primeira mensagem a ser comparada
     * @param msg2 segunda mensagem a ser comparada
    */
    public static boolean isEquals(String msg1, String msg2) {

        // CORREÇÃO: Impede erro de IndexOutOfBounds e falsos positivos em tamanhos diferentes
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

    /*
     * Funcao para identificar se todos os caracteres da string sao vogais
     * @param str string a ser identificada
    */
    public static boolean isVogais(String str) {

        boolean OK = true; // Variavel de controle

        // Loop para iterar por toda a string
        for (int x = 0; x < str.length() && OK; x = x + 1) {

            char c = str.charAt(x); // Variavel para armazenar caractere daquela posicao

            // Condicao para desconsiderar os espacos em branco
            if (c != ' ') {

                boolean In = false; // Outra variavel de controle para identificar se aquele caractere esta dentro das vogais

                // Loop interno para verificar se caractere eh vogal
                for (int y = 0; y < vogais.length() && !(In); y = y + 1) {

                    if (vogais.charAt(y) == c) In = true; // Se caractere eh vogal, In = true

                }

                OK = OK && In; // Se In = true, continua o loop, senao, pare o loop e OK = false

            }

        }

        return OK; // Retornar

    }

    /*
     * Funcao para identificar se todos os caracteres da string sao consoantes
     * @param str string a ser identificada
    */
    public static boolean isConsoantes(String str) {

        boolean OK = true; // Variavel de controle

        // Loop para iterar por toda a string
        for (int x = 0; x < str.length() && OK; x = x + 1) {

            char c = str.charAt(x); // Variavel para armazenar caractere daquela posicao

            // Condicao para desconsiderar os espacos em branco
            if (c != ' ') {

                boolean In = false; // Outra variavel de controle para identificar se aquele caractere esta dentro das consoantes

                // Loop interno para verificar se caractere eh consoante
                for (int y = 0; y < consoantes.length() && !(In); y = y + 1) {

                    if (consoantes.charAt(y) == c) In = true; // Se caractere eh consoante, In = true

                }

                OK = OK && In; // Se In = true, continua o loop, senao, pare o loop e OK = false

            }

        }

        return OK; // Retornar

    }

    /*
     * Funcao para identificar se todos os caracteres da string sao inteiros
     * @param str string a ser identificada
    */
    public static boolean isInteger(String str) {

        boolean OK = true; // Variavel de controle

        // Loop para iterar por toda a string
        for (int x = 0; x < str.length() && OK; x = x + 1) {

            char c = str.charAt(x); // Variavel para armazenar caractere daquela posicao

            boolean In = false; // Outra variavel de controle para identificar se aquele caractere esta dentro dos inteiros

            // Loop interno para verificar se caractere eh inteiro
            for (int y = 0; y < integer.length() && !(In); y = y + 1) {

                if (integer.charAt(y) == c) In = true; // Se caractere eh inteiro, In = true

            }

            OK = OK && In; // Se In = true, continua o loop, senao, pare o loop e OK = false

        }

        return OK; // Retornar

    }


    /*
     * Funcao para identificar se todos os caracteres da string sao reais
     * @param str string a ser identificada
    */
    public static boolean isReal(String str) {

        boolean OK = true; // Variavel de controle
        int max = 0; // Variavel para controlar valor maximo de pontos flutuantes (max > 1 = false)

        // Loop para iterar por toda a string
        for (int x = 0; x < str.length() && OK; x = x + 1) {

            boolean In = false; // Outra variavel de controle para identificar se aquele caractere esta dentro dos reais
            boolean Points = true; // Controlar quantidade maxima de pontos flutuantes

            char c = str.charAt(x); // Variavel para armazenar caractere daquela posicao
            if (c == '.' || c == ',') max = max + 1; // Se caractere for igual a ponto ou virgula, somar +1

            if (max > 1) Points = false; // Verifica se pontos flutuantes ja passou o limite de 1

            // Loop interno para verificar se caractere eh real
            for (int y = 0; y < real.length() && !(In); y = y + 1) {

                if (real.charAt(y) == c) In = true; // Se caractere eh real, In = true

            }

            OK = OK && In && Points; // Se In = true, continua o loop, senao, pare o loop e OK = false

        }

        return OK; // Retornar

    }

    // Metodo principal do programa
    public static void main(String[] args) {

        // String de entrada
        String str = MyIO.readLine();

        // Loop para entrada e ciframento das entradas
        while (!(isEquals(str, "FIM"))) { 
         
            // String auxiliar vazia
            String aux = "";

            // Concatena se eh vogal
            if (isVogais(str)) aux += "SIM ";
            else aux += "NAO ";

            // Concatena se eh consoante
            if (isConsoantes(str)) aux += "SIM ";
            else aux += "NAO ";

            // Concatena se eh inteiro
            if (isInteger(str)) aux += "SIM ";
            else aux += "NAO ";

            // Concatena se eh real
            if (isReal(str)) aux += "SIM";
            else aux += "NAO";

            // Imprime resultado
            MyIO.println(aux);

            str = MyIO.readLine(); // Solicita novamente a entrada

        }

    }

}
