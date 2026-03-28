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

    // Apenas métodos de String
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
                tmp = (char)in.read();
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
                tmp = (char)in.read();
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

// Classe principal do programa
public class InverseString {

    /*
     * Funcao para identificar se duas strings sao iguais
     * @param msg1 primeira mensagem a ser comparada
     * @param msg2 segunda mensagem a ser comparada
    */
    public static boolean isEquals(String msg1, String msg2) {

        boolean OK = true; // Variavel de controle

        // Verificar se sao do mesmo tamanho para evitar o "F" que saia da repeticao
        if (msg1.length() == msg2.length()) {

            // Loop para comparar strings
            for (int x = 0; x < msg1.length() && OK; x = x + 1) {

                if (msg1.charAt(x) != msg2.charAt(x)) OK = false; // Teste dos caracteres

            }

        } else OK = false; // Se nao possuirem o mesmo tamanho = false

        return OK; // Retorna 

    }

    /*
     * Funcao para retornar o inverso da string de entrada
     * @param string string a ser invertida
     * @param x variavel contadora do fim da string
    */
    public static String inverter(String string, int x) {

        // Se x for menor que 0, retorne vazio, senao, retorne aquele caractere na posicao x mais o proximo
        return x < 0 ? "" : string.charAt(x) + inverter(string, x - 1);

    }

    /*
     * Funcao para encapsular funcao anterior
     * @param string a ser invertida
    */
    public static String inverterEncap(String string) {

        // Chamada com x = ultima posicao da string
        return inverter(string, string.length() - 1);

    }

    // Metodo de teste
    public static void main(String[] args) {

        // String de entrada
        String str = MyIO.readLine();

        // Loop para entrada e ciframento das entradas
        while (!(isEquals(str, "FIM"))) { 
         
            String rts = inverterEncap(str); // Inverter string de entrada
            MyIO.println(rts); // Imprimir string invertida

            str = MyIO.readLine(); // Solicita novamente a entrada

        }

    }

}
