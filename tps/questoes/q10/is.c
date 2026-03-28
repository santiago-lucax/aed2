/*
 * Nome: Lucas Santiago Pereira
 * Matricula: 897498
 * Data: 26/03/2026
*/

// Bibliotecas
#include <stdio.h>
#include <stdlib.h>

/*
 * Funcao para retornar tamanho de determinada cadeia de caractere
 * @param str cadeia de caractere
*/
int length(const char* str) {

    int x = 0; // Controle
    while (*(str + x) != '\0') x = x + 1; // Contando quantidade de caracteres
    return x; // Retonrar tamanho

}

/*
 * Funcao para comparar dois arranjos de caracteres
 * @param str1 primeiro arranjo a ser comparado
 * @param str2 segundo arranjo a ser comparado
*/
int strCompare(char* str1, char* str2) {

    int OK = 1; // Variavel para controle

    int x = 0; // Variavel para iterar

    // Verificar se ambas as strings possuem mesmo tamanho
    if (length(str1) == length(str2)) {
    
        while (OK == 1 && str1[x] != '\0' && str2[x] != '\0') {

            if (str1[x] != str2[x]) OK = 0; // Comparar se caracter eh igual
            x = x + 1; // Soma um

        }

    } else OK = 0;

    return OK; // Retornar

}

/*
 * Funcao para verificar se caractere eh uma Leitura
 * @param c caractere a ser avaliado
*/
int isLetter(const char c) {

    int OK = 0; // Controle
    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) OK = 1; // Verificar se eh uma letra
    return OK; // Retornar

}

/*
 * Funcao para transformar caractere maiusculo em maiusculo
 * @param c caractere a ser transformado
*/
char toLower(const char c) {

    char tmp = c; // Copia
    if (isLetter(c) && (c >= 'A' && c <= 'Z')) tmp = tmp + 32; // Verificar se eh letra e maiuscula 
    return tmp; // Retornar

}

/*
 * Funcao para verificar se caractere eh uma vogal
 * @param c caractere a ser avaliado
*/
int isVogal(char c) {

    int OK = 0; // Controle
    char tmp = toLower(c); // Copia
    if (tmp == 'a' || tmp == 'e' || tmp == 'i' || tmp == 'o' || tmp == 'u') OK = 1; // Verificar se eh uma vogal
    return OK; // Retornar

}

/*
 * Funcao para verificar se caractere eh um numero inteiro
 * @param c caractere a ser avaliado
*/
int isInt(char c) {

    int OK = 0; // Controle
    if (c >= '0' && c <= '9') OK = 1; // Verificar se eh uma inteiro
    return OK; // Retornar

}

/*
 * Funcao para verificar se caractere eh um numero real
 * @param c caractere a ser avaliado
*/
int isR(char c) {

    int OK = 0; // Controle
    if (c >= '0' && c <= '9' || c == '.' || c == ',') OK = 1; // Verificar se eh uma inteiro
    return OK; // Retornar

}

/*
 * Funcao recursiva para identificar se determinada string possui apenas vogais
 * @param string cadeia a ser avaliada
 * @param x contador
*/
int isVogais(char string[], int x) {

    int OK = 1; // Controle

    if (string[x] == '\0') OK = 1; // Caso base
    else {

        char c = toLower(string[x]); // Ler caractere naquela posicao

        // Se for vogal continue as cahamadas
        if (isVogal(c)) {

            OK = isVogais(string, x + 1); // Chamada (Motor)

        } else OK = 0; // Senao, retorne 0

    }

    return OK; // Retornar

}

/*
 * Funcao recursiva para identificar se determinada string possui apenas consoantes
 * @param string cadeia a ser avaliada
 * @param x contador
*/
int isConsoantes(char string[], int x) {

    int OK = 1; // Controle

    if (string[x] == '\0') OK = 1; // Caso base
    else {

        char c = toLower(string[x]); // Ler caractere naquela posicao

        // Se for consoantes continue as cahamadas
        if (!isVogal(c) && isLetter(c)) {

            OK = isConsoantes(string, x + 1); // Chamada (Motor)

        } else OK = 0; // Senao, retorne 0

    }

    return OK; // Retornar

}

/*
 * Funcao recursiva para identificar se determinada string possui apenas numeros inteiros
 * @param string cadeia a ser avaliada
 * @param x contador
*/
int isInteger(char string[], int x) {

    int OK = 1; // Controle

    if (string[x] == '\0') OK = 1; // Caso base
    else {

        char c = string[x]; // Variavel para guardar aquela posicao de x
        // Se for inteiro continue as cahamadas
        if (isInt(c)) {

            OK = isInteger(string, x + 1); // Chamada (Motor)

        } else OK = 0; // Senao, retorne 0

    }

    return OK; // Retornar

}

/*
 * Funcao recursiva para identificar se determinada string possui apenas numeros reais
 * @param string cadeia a ser avaliada
 * @param x contador
*/
int isReal(char string[], int x, int max) {

    int OK = 1; // Controle

    if (string[x] == '\0') OK = 1; // Caso base
    else {

        char c = string[x]; // Variavel para guardar aquela posicao de x
        int novos = (c == '.' || c == ',') ? max + 1: max;

        // Se for inteiro e ou possuir pontos flutuantes continue as chamadas
        if ((isInt(c) || c == '.' || c == ',') && novos <= 1) {

            OK = isReal(string, x + 1, novos); // Chamada (Motor)

        } else OK = 0; // Senao, retorne 0

    }

    return OK; // Retornar

}

/*
 * Funcao principal do programa
 * @param argc contagem de argumentos
 * @param argv argumentos do programa
*/
int main(int argc, char* argv[]) {

    char line[1000]; // Linha para entrada
    scanf(" %255[^\n]", line); // Ler entrada pela primeira vez

    // Loop para entradas
    while (strCompare(line, "FIM") == 0 ) {

        isVogais(line, 0) ? printf("SIM ") : printf("NAO "); // Verificar se eh vogal
        isConsoantes(line, 0) ? printf("SIM ") : printf("NAO "); // Verificar se eh consoante
        isInteger(line, 0) ? printf("SIM ") : printf("NAO "); // Verificar se eh inteiro
        isReal(line, 0, 0) ? printf("SIM\n") : printf("NAO\n"); // Verificar se eh numero real

        scanf(" %255[^\n]", line); // Leitura novamente

    }

    return 0; // Fim do programa

}
