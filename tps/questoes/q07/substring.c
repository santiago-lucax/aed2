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
    while (OK == 1 && str1[x] != '\0' && str2[x] != '\0') {

        if (str1[x] != str2[x]) OK = 0; // Comparar se caracter eh igual
        x = x + 1; // Soma um

    }

    return OK; // Retornar

}

/*
 * Funcao para retornar a maior substring dentro de uma string maior
 * @param string cadeia de caracteres a ser identificada a sua maior sub cadeia
*/
int longest(char string[]) {

    int n = length(string); // n = tamanho da string
    int max = 0; // Variavel para guardar maior substring

    // Laço para iterar por toda a cadeia
    for (int x = 0; x < n; x = x + 1) {

        int alpha[26] = {0}; // Arranjo com quantidade exata do alfabeto inicializada com zeros
        int size = 0; // Tamanho daquela determinada substring
        int OK = 1; // Variavel de controle (Para evitar uso de "return")

        // Laço para iterar enquanto existir um substring valida
        for (int y = x; y < n && OK; y = y + 1) {

            if (alpha[string[y] - 'a'] == 1) OK = 0; // Verificar se determinada letra ja esta presente na substring
            else {

                alpha[string[y] - 'a'] = 1; // Senao, marcar ocorrencia da letra
                size = size + 1; // Tamanho da subtring recebe +1

            }

        }

        if (size > max) max = size; // Se tamanho da nova subtring for maior que a anterior, max = size

    }

    return max; // Retornar 

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
