// libs
#include <stdio.h>
#include <stdlib.h>

// defs
#define null NULL

// struct for list
typedef struct s_List {
    int* data;
    int length;
    int n;
} List;

// new_list() for init a new list struct
List* new_list(int length) {
    List* new = null;
    if (length > 0) {
        new = (List*)malloc(sizeof(List));
        if (new != null) {
            new->data = (int*)malloc(sizeof(int) * length);
            if (new->data != null) {
                for (int i = 0; i < length; i++) new->data[i] = 0;
                new->length = length;
                new->n = 0;
            } else printf("Nao foi possivel alocar espaco.\n");
        } else printf("Erro: lista nao criada.\n");
    } else printf("Erro: tamanho invalido.\n");
    return new;
}

// free_list() for clear and desallocate memory
void free_list(List* list) {
    if (list != null) {
        if (list->data != null) {
            free(list->data);
            list->length = 0;
            list->n = 0;
        }

        free(list);
    } else printf("Nao foi possivel limpar lista, lista ja vazia.\n");
}

// DEPOIS CONTINUO...

// function for identify if list is full
int is_full(List* list) {
    int x = 0;
    if (list->n == list->length) x = 1;
    return x;
}

// function for identify if list is empty
int is_empty(List* list) {
    int x = 0;
    if (list->n == 0) x = 1;
    return x;
}

// main method for tests
int main(int argc, char* argv[]) {
    List* list = new_list(5);

    free_list(list);

    return 0;
}
