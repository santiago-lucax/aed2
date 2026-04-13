/*
 * Nome: Lucas Santiago Pereira
 * Matricula: 897498
 * Data: 23/03/2026
*/

#include <stdio.h>
#include <stdlib.h>

int length(const char* str) {

    int x = 0;
    while (*(str + x) != '\0') x = x + 1;
    return x;

}

int isLower(const char c) {

    int OK = 1;

    if (!(c >= 'a' && c <= 'z')) OK = 0;

    return OK;

}

char* toLower(const char* str) {

    char* tmp = NULL;

    if (length(str) > 0) {

        tmp = malloc(sizeof(char) * (length(str) + 1));

        for (int x = 0; x < length(str); x = x + 1) {

            if (!(isLower(*(str+x)))) *(tmp + x) = *(str + x) + 32;
            else *(tmp + x) = *(str + x);

        }

        *(tmp + length(str)) = '\0';

    }

    return tmp;

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

/* Problema: retorna true mesmo com mais de uma letra igual e.g. Lucas e Uclus = 0; Uclus e Lucas = 1; ERRADO
int isAnagrama(const char* str1, const char* str2) {

    int OK = 1;
    int count = 0;

    if (length(str1) == length(str2)) {

        for (int x = 0; x < length(str1) - 1 && OK; x = x + 1) {

            count = 0;
            for (int y = 0; y < length(str2) - 1; y = y + 1) {

                if (*(str1 + x) == *(str2 + y)) count++;

            }

            if (count == 0) OK = 0;
            else if (count > 1) OK = 0;

        }

    } else OK = 0;

    return OK;

}*/

int sizeEquals(const char line[]) {

    int OK = 1;

    int x = 0;
    while (line[x] != ' ') x++;

    int y = length(line) - 1;
    while(line[y] != ' ') y--;

    if (x != y) OK = 0;

    return OK;

}

int isAnagrama(const char line[]) {

    int OK = 1;
    int count = 0;

    if (sizeEquals(line)) {

        int x = 0;
        while (line[x] != ' ' && OK == 1) {

            count = 0;
            int y = length(line) - 1;
            while (line[y] != ' ') {

                if (line[x] == line[y]) count++;

                y--;

            }

            if (count <= 0 || count > 1) OK = 0;

            x++;

        }

    } else OK = 0;

    return OK;

}

void testAnagrama(char line[], int *p) {

    *p = isAnagrama(toLower(line));

}

int main(int argc, char* argv[]) {

/*
    const char* string1 = "Lucas";
    const char* string2 = "Sacul";

    printf("%d\n", length(string1));
    printf("%d\n", length(string2));

    int x = isAnagrama(toLower(string2), toLower(string1));
    int y = isAnagrama(toLower(string1), toLower(string2));

    if (x == 1 && (x == y)) printf("SIM\n");
    else printf("NAO\n");

    printf("%d\n", x);
    printf("%d\n", y);
*/

    char line[100];
    int x = 0;
    int *p = &x;
    scanf(" %[^\n]", line);
    while (strCompare(line, "FIM") == 0 ) {

        testAnagrama(line, p);
        if (x == 1) printf("SIM\n");
        else printf("NAO\n");
        scanf(" %[^\n]", line);

    }

    return 0;

}
