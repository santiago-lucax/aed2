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

// Estrutura semelhante a uma string
typedef struct s_String {

    char* data; // Arranjo de caracteres
    int length; // Tamanho do arranjo

} String; // Nome da estrutura

/*
 * Metodo para construir dado do tipo String
 * @param str endereco da string
 * @param value palavra atribuida a string
*/
void initString (String* str, char* value) {

    // Verificar se string existe
    if (str != null && value != null) {

        int k = 0; // Variavel para iterar 

        // Enquanto nao encontrar o fim do arranjo, itere
        while (value[k] != '\0') k = k + 1;

        str->data = malloc(sizeof(char) * (k + 1)); // Alocar um espaco com o tamanho exato de value (1 para '\0')

        for (int x = 0; x < k; x = x + 1) str->data[x] = value[x]; // Copiar caracteres

        str->data[k] = '\0'; // "Tampar" cadeia de caracteres
        str->length = k; // Atribuir tamanho

    } else printf("Erro: Nao possivel possivel alocar memoria"); // Printar erro

}

/*
 * Limpar memoria alocada para String
 * @param str endereco da estrutura a ser liberada
*/
void freeString(String* str) {

    str->length = 0; // Esvaziar tamanho
    free(str->data); // Limpar memoria
    str->data = null; // Apontar para vazio

}

/*
 * Funcao para criar uma string inversa em relacao a de entrada
 * @param str string a ser invertida
*/
String inverter(String* str) {

    String tmp; // String temporaria
    tmp.length = str->length; // Atribuir tamanho da original para a temporaria
    tmp.data = malloc(sizeof(char) * tmp.length + 1); // Alocar espaco valido

    int y = 0; // Variavel para iterar

    // Loop para iterar por toda a string original e atribuir valores em ordem decrescente a string temporaria
    for (int x = str->length - 1; x >= 0; x = x - 1) {

        tmp.data[y] = str->data[x]; // Atribuir
        y = y + 1; // Somar um

    }

    tmp.data[y] = '\0'; // "Tampar" cadeia de caracteres
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
 * Funcao principal do programa
 * @param argc contagem de argumentos
 * @param argv argumentos do programa
*/
int main(int argc, char* argv) {

    char* value = malloc(sizeof(char) * 100); // Alocar espaco
    scanf(" %99[^\n]", value); // Ler linha
    while (getchar() != '\n'); // Limpar buffer

    // Loop para a execucao do programa
    while (strCompare(value, "FIM") == 0) {

        String string; // String a ser invertida
        initString(&string, value); // Construir ela
        String inversa = inverter(&string); // String inversa
        printf("%s\n", inversa.data); // Exibir string inversa na tela
        freeString(&string); // Limpar memoria alocada
        freeString(&inversa); // Limpar memoria alocada
        scanf(" %99[^\n]", value); // Ler novamente
        while (getchar() != '\n'); // Limpar buffer

    }

    free(value); // Limpar memoria alocada
    value = null; // Apontar variavel inversa para vazio

    return 0; // Fim do programa

}
