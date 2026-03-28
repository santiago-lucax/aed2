/*
 * Nome: Lucas Santiago Pereira
 * Matricula: 897498
 * Data: 23/03/2026
*/

// Bibliotecas
#include <stdio.h>
#include <stdlib.h>

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

    return OK; // Retornar

}

/*
 * Funcao principal do programa
 * @param argc contagem de argumentos
 * @param argv argumentos do programa
*/
int main(int argc, char* argv[]) {

    char* resp = malloc(sizeof(char) * 100); // Alocar espaco com tamanho 99
    scanf(" %99[^\n]", resp); // Ler linha
    while (strCompare(resp, "FIM") == 0 ) {

        int x = toIntEncap(resp); // Tranformar string em inteiro
        printf("%d\n", sumOfDigitsEncap(x)); // Imprimir soma dos digitos
        scanf(" %99[^\n]", resp); // Ler linha

    }

}
