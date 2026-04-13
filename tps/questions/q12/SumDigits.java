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

public class SumDigits {

    /*
     * Funcao para identificar se duas strings sao iguais
     * @param msg1 primeira mensagem a ser comparada
     * @param msg2 segunda mensagem a ser comparada
    */
    public static boolean isEquals(String msg1, String msg2) {

        boolean OK = true; // Variavel de controle

        // Verificar se ambas as strings possuem o mesmo tamanho
        if (msg1.length() == msg2.length()) {

            // Loop para comparar strings
            for (int x = 0; x < msg1.length() && OK; x = x + 1) {

                if (msg1.charAt(x) != msg2.charAt(x)) OK = false; // Teste dos caracteres

            }

        } else OK = false; 

        return OK; 

    }

    /*
     * Funcao recursiva para retornar soma dos digitos de um numero inteiro
     * @param value valor inteiro
     * @param x variavel para auxiliar na execucao
    */
    public static int sumOfDigits(int value, int x) {

        int k = 0; // Variavel de retorno

        if (x > value) return k; // Caso base
        else {

            // Formula para separar algarismos do numero inteiro
            k = ((value % (x * 10)) - value % x) / x; 
            return k + sumOfDigits(value, x * 10); // Motor recursivo

        }

    }

    /*
     * Funcao de encapsulamento para simplificar chamada
     * @param value valor inteiro
    */
    public static int sumOfDigitsEncap(int value) {

        // Chamada da funcao recursiva iniciando o multiplicador em 1
        return sumOfDigits(value, 1); 

    }

    // Metodo principal do programa
    public static void main(String[] args) {

        // String de entrada
        String str = MyIO.readLine();

        // Loop para entrada e processamento
        // O loop encerra se ler "FIM" ou se readLine retornar vazio (EOF)
        while (str != null && !str.equals("") && !(isEquals(str, "FIM"))) { 
         
            // Converter string para inteiro e calcular soma de forma recursiva
            int num = Integer.parseInt(str);
            MyIO.println("" + sumOfDigitsEncap(num));

            str = MyIO.readLine(); // Solicita novamente a entrada

        }

    }

}
