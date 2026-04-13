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
 * Funcao para contar todas as ocorrencias de determinada letra uma unica vez
 * @param string cadeia de caractere a ser manipulada
*/
int longestERRADA(char string[]) {

    int x = 0; // Contador

    int alpha[26] = {0}; // Arranjo para guardar posicoes do alfabeto

    int i = 0; // Contador

    // Loop para contar todas as ocorrencias de uma letra
    while (string[i] != '\0') {

        char c = string[i]; // Guardar caractere naquela posicao

        // Verificar se caractere eh uma letra minuscula
        if (c >= 'a' && c <= 'z') {

            // Se aquela posicao estiver vazia = 0, adicionar +1
            if (alpha[c - 'a'] == 0) alpha[c - 'a']++;

        }

        i++; // Proximo

    }

    // Contar quantas letras possuem a string
    for (int j = 0; j < 26; j = j + 1) {

        if (alpha[j] != 0) x = x + 1; // Se letra existir, +1

    }

    return x; // Retornar

}

// Continuar, to quase
/*
int longest(char string[]) {

    int maior = 0;

    for (int x = 0; x < length(string) - 1; x = x + 1) {

        int OK = 1;
        int count = 1;
        for (int y = x + 1; y < length(string) && OK; y = y + 1) {

            if (string[x] == string[y]) OK = 0;
            else count++;

        }

        if (count > maior) maior = count;

    }
        

    return maior;

}
*/

int longest(char string[]) {

    int maior = 0;

    int x = 1;
    int lim = 0;
    while (string[x] != '\0') {

        int OK = 1;
        int count = 1;
        for (int y = x - 1; y >= lim && OK; y = y - 1) {

            if (string[x] == string[y]) { 

                lim = x;
                OK = 0;

            } else count++;

        }

        if (maior < count) maior = count;

        x = x + 1;

    }

    return maior;

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

        printf("%d\n", longest(line));

        scanf(" %[^\n]", line); // Leitura novamente

    }

    return 0; // Fim do programa

}
