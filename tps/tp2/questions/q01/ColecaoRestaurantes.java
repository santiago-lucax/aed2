// Biblioteca padrão para leitura
import java.util.Scanner;

/*
 * Classe para armazenar métodos e funções úteis para o projeto
*/
class Utils {
    // Função para identificar se caractere é um inteiro
    public boolean isInt(final char c) {
        boolean ok = false;
        if (c >= '0' && c <= '9') ok = true; // Verificar se é um número
        return ok;
    }

    // Função para tranformar string em um inteiro
    public int toInt(String s) {
        boolean isNegative = false; // Variável para identificar se número é ou não negativo
        int acumulado = 0; // Variável do valor númerico da string

        if (s.charAt(0) == '-') isNegative = true; // Identificar se é positivo ou negativo

        // Laço para transformar string em um número inteiro
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isInt(c)) {
                acumulado = (acumulado * 10) + (c - '0'); // Transformar caractere em seu valor inteiro através da tabela ASCII
            } else throw new IllegalArgumentException("Número inválido."); // Enviar exceção caso encontre algum caractere não númerico
        }

        if (isNegative) acumulado = (-1) * acumulado; // Se for negativo, multiplicar por -1

        return acumulado; // Retornar valor inteiro
    }
} // Fim Utils

/*
 * Classe para definir formato adequado de data
*/ 
class Data {
    // Atributos privados
    private int ano;
    private int mes;
    private int dia;

    // Construtor vazio com chamada para construtor com parâmetros
    Data() {
        this(1, 1, 1);
    }

    // Construtor com parâmetros
    Data(int dia, int mes, int ano) {
        // Verificar veracidade dos dados
        if (dia < 1 || dia > 30 || mes < 1 || mes > 12 || ano < 0) {
            throw new IllegalArgumentException("Valores de data inválidos.");
        }

        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }

    // Get e set para ano
    public int getAno() { return this.ano; }
    public void setAno(int ano) {
        if (ano < 0) throw new IllegalArgumentException("Ano inválido.");
        this.ano = ano;
    }

    // Get e set para mes
    public int getMes() { return this.mes; }
    public void setMes(int mes) {
        if (mes < 1 || mes > 12) throw new IllegalArgumentException("Mês inválido.");
        this.mes = mes;
    }

    // Get e set para dia
    public int getDia() { return this.dia; }
    public void setDia(int dia) {
        if (dia < 1 || dia > 30) throw new IllegalArgumentException("Dia inválido.");
        this.dia = dia;
    }

    // Função para ler data e atribuir a classe
    public void parseData(String data) {
        int i = 0;
        while (data.charAt(i) != '-') {}
    }
} // Fim Data

public class ColecaoRestaurantes {
    public static void main(String[] args) {
        Utils uts = new Utils();
        String s = "-198l";
        int x = 0;
        x = uts.toInt(s);
        System.out.println(x + 10);
    }
} // Fim main
