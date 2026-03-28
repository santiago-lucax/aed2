/*
 * Nome: Lucas Santiago Pereira
 * Matricula: 897498
 * Data: 25/03/2026
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
 * Funcao para verificar se caractere e minuscula
 * @param c caractere a ser verificado
*/
int isLower(const char c) {

    int OK = 1; // Controle

    if (!(c >= 'a' && c <= 'z')) OK = 0; // Verificar se eh minuscula

    return OK; // Retornar

}

/*
 * Funcao para retornar valor do caractere em minuscula
 * @param c caractere a ser transformado
*/
char toLower(const char c) {

    char tmp = c; // Copia do caractere

    if (!(c >= 'a' && c <= 'z'))tmp = tmp + 32; // Transformar caractere em minuscula

    return tmp; // Retornar

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
 * Funcao para identificar duas palavras separadar por espacos se sao isAnagrama
 * @param line linha a ser manipulada para verificar a existencia ou nao de um anagrama
*/
int isAnagrama(const char line[]) {

    int OK = 1; // Variavel de controle

    // Arranjo de inteiros inicializados com 0 para funcionar como se fossem um alfabeto
    int alphabeto[26] = {0};

    int x = 0; // Controle

    // Iteracao da posicao 0 ate o primeiro espaco da linha
    while (line[x] != ' ') {

        char c = toLower(line[x]); // Caso caractere seja maiuscula, tranforme-a em minuscula
        if (isLower(c)) alphabeto[c - 'a']++; // Somar uma unidade na posicao equivalente a letra no alpabeto ex: 0 = a, 1 = b...
        x++; // Proximo

    }

    int y = length(line) - 1; // Controle do fim da linha ate primeiro espaco encontrado
    while (line[y] != ' ') {

        char c = toLower(line[y]); // Caso caractere seja maiuscula, tranforme-a em minuscula
        if (isLower(c)) alphabeto[c - 'a']--; // Subtrair uma unicade na posicao equivalente a letra no alfabeto ex: 0 = a; 1 = b...
        y--; // Proximo

    }

    // Loop para verificar se alphabeto esta completo por zeros. Se sim, existe anagrama, se nao, nao existe
    for (x = 0; x < 26; x = x + 1) {

        if (alphabeto[x] != 0) OK = 0; // Caso exista alguma posicao diferente de zero, nao existe anagrama

    }

    return OK; // Retornar resultado

}

/*
 * Funcao principal do programa
 * @param argc contagem de argumentos
 * @param argv argumentos do programa
*/
int main(int argc, char* argv[]) {

    char line[100]; // Linha para entrada
    scanf(" %[^\n]", line); // Ler entrada pela primeira vez

    // Loop para entradas
    while (strCompare(line, "FIM") == 0 ) {

        if (isAnagrama(line)) printf("SIM\n"); // Caso exista anagrama, imprima SIM
        else printf("NÃO\n"); // Caso contrario, imprima NÃO
        scanf(" %[^\n]", line); // Leitura novamente

    }

    return 0; // Fim do programa

}
