/*
 * @author Lucas Santiago Pereira - 897498
 * @date 26/03/2026
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

public class Password {

    /*
     * Funcao para identificar se duas strings sao iguais
     * @param msg1 primeira mensagem a ser comparada
     * @param msg2 segunda mensagem a ser comparada
    */
    public static boolean isEquals(String msg1, String msg2) {

        // CORREÇÃO: Impede erro de IndexOutOfBounds e falsos positivos
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
     * Funcao para identificar se caractere e uma letra minuscula
     * @param c caractere a ser avaliado
    */
    public static boolean isMin(char c) {
    
        boolean OK = false;
        if (c >= 'a' && c <= 'z') OK = true;
        return OK;

    }

    /*
     * Funcao para identificar se caractere e uma letra maiuscula
     * @param c caractere a ser avaliado
    */
    public static boolean isMaiusc(char c) {

        boolean OK = false;
        if (c >= 'A' && c <= 'Z') OK = true;
        return OK;

    }

    /*
     * Funcao para identificar se caractere e um numero
     * @param c caractere a ser avaliado
    */
    public static boolean isNumber(char c) {

        boolean OK = false;
        if (c >= '0' && c <= '9') OK = true;
        return OK;

    }

    /*
     * Funcao para identificar se caractere e do tipo especial
     * @param c caractere a ser avaliado
    */
    public static boolean isSpecial(char c) {

        boolean OK = false;
        // CORREÇÃO: Utilizando a lógica inversa para pegar TODOS os caracteres especiais imprimíveis (>= 33) e ignorar espaços
        if (!isMin(c) && !isMaiusc(c) && !isNumber(c) && c >= 33 && c <= 126) OK = true;
        return OK;

    }

    /*
     * Funcao para validar se determinada senha e valida seguindo os criterios: 
     * senha >= 8; letra maiuscula >= 1; minuscula >= 1; numero >= 1 e especial >= 1
     * @param pass senha a ser avaliada
    */
    public static boolean isValid(String pass) {

        boolean OK = true; // Variavel de controle

        boolean[] table = new boolean[5]; // Arranjo booleano para avaliar ocorrencia das condicoes
        if (pass.length() >= 8) table[0] = true; // Se tamanho for >= 8, primeira posicao do arranjo ja recebe true

        // Laço para identificar as ocorrencias dos caracteres
        for (int x = 0; x < pass.length(); x = x + 1) {
    
            char c = pass.charAt(x); // Ler caractere na atual posicao
            if (isMin(c))     table[1] = true; // Se for minuscula = pos[1] = true
            if (isMaiusc(c))  table[2] = true; // Se for maiuscula = pos[2] = true
            if (isNumber(c))  table[3] = true; // Se for numero = pos[3] = true
            if (isSpecial(c)) table[4] = true; // Se for caractere especial = pos[4] = true

        }

        // Laço para verificar se todas as posicoes do arranjo sao verdadeiras, se sim, senha valida, senao, senha invalida
        for (int x = 0; x < 5; x = x + 1) if (table[x] != true) OK = false;
 
        return OK; // Retornar

    }

    public static void main(String[] args) {

        // String de entrada
        String pass = MyIO.readLine();

        // Loop para entrada e ciframento das entradas
        while (!(isEquals(pass, "FIM"))) { 
         
            if (isValid(pass)) MyIO.println("SIM"); // Caso a senha seja valida, imprima "SIM"
            else MyIO.println("NAO"); // Caso a senha seja invalida, imprima "NAO"

            pass = MyIO.readLine(); // Solicita novamente a entrada

        }

    }

}
