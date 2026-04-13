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
    return x; // Retornar tamanho
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
    // CORREÇÃO: Limitar a conversão (+32) apenas para letras maiúsculas
    if (c >= 'A' && c <= 'Z') tmp = tmp + 32; 
    return tmp; // Retornar
}

/*
 * Funcao para comparar dois arranjos de caracteres
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
 * Funcao para identificar duas palavras separadas por espacos se sao isAnagrama
 * @param line linha a ser manipulada para verificar a existencia ou nao de um anagrama
*/
int isAnagrama(const char line[]) {
    int OK = 1; // Variavel de controle
    int alphabeto[26] = {0}; // Arranjo inicializado com 0

    int x = 0; // Controle

    // CORREÇÃO: Adicionado limite de '\0' para evitar memory leak se a linha não tiver espaço
    while (line[x] != ' ' && line[x] != '\0') {
        char c = toLower(line[x]);
        if (isLower(c)) alphabeto[c - 'a']++; 
        x++; 
    }

    int y = length(line) - 1; // Controle do fim da linha ate primeiro espaco encontrado
    
    // CORREÇÃO: Adicionado limite y >= 0
    while (y >= 0 && line[y] != ' ') {
        char c = toLower(line[y]); 
        if (isLower(c)) alphabeto[c - 'a']--; 
        y--; 
    }

    // Loop para verificar se alphabeto esta zerado
    for (x = 0; x < 26; x = x + 1) {
        if (alphabeto[x] != 0) OK = 0; // Caso exista alguma posicao diferente de zero, nao eh anagrama
    }

    return OK; // Retornar resultado
}

/*
 * Funcao principal do programa
*/
int main(int argc, char* argv[]) {

    char line[150]; // Buffer ligeiramente maior por seguranca

    while (1) {
        line[0] = '\0';
        int lido = scanf(" %149[^\n]", line);
        
        if (lido == EOF) break; // Controle de Fim de Arquivo

        getchar(); // Limpar o buffer do \n

        // Tratamento para arquivos com Carriage Return (\r) do Windows
        int len = length(line);
        if(len > 0 && line[len-1] == '\r') line[len-1] = '\0';

        if (strCompare(line, "FIM") == 1) break;

        if (isAnagrama(line)) {
            printf("SIM\n"); 
        } else {
            // CORREÇÃO: Sem o acento Til (~), adequando ao pub.out
            printf("NAO\n"); 
        }
    }

    return 0; // Fim do programa
}
