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
 * Metodo para utilizar a criptografia de Cezar em uma cadeia de caracteres de modo recursivo
 * @param line string a ser criptografada
 * @param x indice inicial,  geralmente = 0
 * @param key chave de criptografia = 3
 * @param crip apontador para string criptografada
*/
void cezarCrip(char line[], int x, int key, char crip[]) {

    // Caso base
    if (line[x] == '\0') {

        crip[x] = '\0'; // "Tampar" string criptografada
        return; // Retornar

    }

    crip[x] = line[x] + key; // Criptografar caractere

    cezarCrip(line, x + 1, key, crip); // Motor ou chamada recursiva

}

/*
 * Metodo para encapsular e facilitar chamada do metodo recursivo
 * @param string cadeia de caractere a ser criptografada
 * @param criptstring cadeia de caractere criptografada
*/
void cezarCripEncap(char string[], char criptstring[]) {

    cezarCrip(string, 0, 3, criptstring); // Chamada do metodo

}

/*
 * Funcao principal do programa
 * @param argc contagem de argumentos
 * @param argv argumentos do programa
*/
int main(int argc, char* argv[]) {

    char line[1000]; // Linha para entrada
    char crip[1000]; // Cadeia para receber linha criptografada
    scanf(" %255[^\n]", line); // Ler entrada pela primeira vez

    // Loop para entradas
    while (strCompare(line, "FIM") == 0 ) {

        cezarCripEncap(line, crip); // Chamada do metodo
        printf("%s\n", crip); // Exibir string criptografada
        scanf(" %255[^\n]", line); // Leitura novamente

    }

    return 0; // Fim do programa

}
