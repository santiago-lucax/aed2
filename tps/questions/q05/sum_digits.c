/*
 * Nome: Lucas Santiago Pereira
 * Matricula: 897498
 * Data: 23/03/2026
*/

// Bibliotecas
#include <stdio.h>
#include <stdlib.h>

// Definicao para null = NULL
#define null NULL

/*
 * Funcao recursiva para retornar soma dos digitos de um numero inteiro
 * @param value valor inteiro
 * @param x variavel para auxiliar na execucao
*/
int sumOfDigits(int value, int x) {

    int k = 0; // Variavel de retorno

    if (x > value) return k; // Caso base
    else {

        k = ((value % (x * 10)) - value % x) / x; // Formula para separar algarismos do numero inteiro
        return k = k + sumOfDigits(value, x * 10); // Motor

    }

}

/*
 * Funcao de encapsulamento para simplificar chamada
 * @param value valor inteiro
*/
int sumOfDigitsEncap(int value) {

    int x = sumOfDigits(value, 1); // Chamada da funcao recursiva com constante 1
    return x; // Retorno

}

/*
 * Funcao para transformar uma cadeia de caracteres em um numero inteiro
 * @param str cadeia de caracteres
 * @param num numero inteiro a ser gerado
*/
int toInt(const char* str, int num) {

    if (*str == '\0') return num; // Caso base

    // str + 1 = proximo valor da cadeia (str[x + 1])
    // *str - '0' = valor da posicao (str[x] - '0')
    return toInt(str + 1, num * 10 + (*str - '0')); // Motor

}

/*
 * Funcao para encapsular
 * @param str string a ser convertida para inteiro
*/
int toIntEncap(const char* str) {

    return toInt(str, 0); // Retornar

}

/*
 * Funcao para comparar dois arranjos de caracteres
 * @param str1 primeiro arranjo a ser comparado
 * @param str2 segundo arranjo a ser comparado
*/
int strCompare(char* str1, char* str2) {

    int OK = 1; // Variavel para controle

    int x = 0; // Variavel para iterar
    while (OK == 1 && str1[x] != '\0' && str2[x] != '\0') {

        if (str1[x] != str2[x]) OK = 0; // Comparar se caracter eh igual
        x = x + 1; // Soma um

    }

    // CORREÇÃO: Garante que os tamanhos sejam iguais para validar
    if (str1[x] != str2[x]) OK = 0;

    return OK; // Retornar

}

/*
 * Funcao principal do programa
 * @param argc contagem de argumentos
 * @param argv argumentos do programa
*/
int main(int argc, char* argv[]) {

    char* resp = malloc(sizeof(char) * 100); // Alocar espaco com tamanho 100

    // CORREÇÃO: Loop infinito controlado para lidar com o Fim de Arquivo (EOF)
    while (1) {
        resp[0] = '\0';
        int lido = scanf(" %99[^\n]", resp); // Ler linha

        if (lido == EOF) break; // Sai do loop se o arquivo terminar

        // CORREÇÃO: Tratamento para ignorar o 'Carriage Return' (\r) do Windows
        int len = 0;
        while(resp[len] != '\0') len++;
        if(len > 0 && resp[len-1] == '\r') resp[len-1] = '\0';

        if (strCompare(resp, "FIM") == 1) break; // Quebra caso encontre a palavra FIM

        int x = toIntEncap(resp); // Tranformar string em inteiro
        printf("%d\n", sumOfDigitsEncap(x)); // Imprimir soma dos digitos
    }

    free(resp); // Boa prática: liberar o ponteiro antes de encerrar
    resp = null;

    return 0;
}
