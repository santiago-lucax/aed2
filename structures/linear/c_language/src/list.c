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

// print_list to display the list on the screen
void print_list(List* list) {
    if (list) {
        int i = 0;
        for (/**/; i < list->length - 1; i++) {
            printf("%d ", list->data[i]);
        }
        printf("%d", list->data[i]);
    }

    printf("\n");
}

void push_back(List* list, int x) {
    if (list && !is_full(list)) {
        list->data[list->n++] = x;
    } else printf("Erro(push_back): lista nao existe ou esta cheia.\n");
}

void push_front(List* list, int x) {
    if (list && !is_full(list)) {
        for (int i = list->n; i > 0; i--) list->data[i] = list->data[i-1];
        list->data[0] = x;
        list->n++;
    } else printf("Erro(push_front): lista nao existe ou esta cheia.\n");
}

int pop_back(List* list) {
    int x = -1;
    if (list && !is_empty(list)) {
        x = list->data[--list->n];
        list->data[list->n] = 0;
    } else printf("Erro(pop_back): lista nao existe ou esta vazia.\n");

    return x;
}

int pop_front(List* list) {
    int x = -1;
    if (list && !is_empty(list)) {
        x = list->data[0];
        list->data[0] = 0;
        for (int i = 0; i < list->n - 1; i++) list->data[i] = list->data[i+1];
        list->data[--list->n] = 0;
    } else printf("Erro(pop_front): lista nao existe ou esta vazia.\n");

    return x;
}

void push_list(List* list, int x, int pos) {
    if (list && !is_full(list)) {
        if (pos >= 0 && pos <= list->length - 1) {
            if (list->data[pos] != 0) {
                for (int i = list->n; i > pos; i--) list->data[i] = list->data[i-1];
                list->data[pos] = x;
            } else list->data[pos] = x;
        } else printf("Erro(push_list): posicao invalida.\n");
    } else printf("Erro(push_list): lista nao existe ou esta cheia.\n");
}

int pop_list(List* list, int pos) {
    int x = list->data[pos];
    if (list && !is_empty(list)) {
        if (pos == list->n - 1) list->data[pos] = 0;
        else {
            for (int i = pos; i < list->n; i++) list->data[i] = list->data[i+1];
            list->data[list->n - 1] = 0;
        }
    } else printf("Erro(pop_list): lista nao existe ou esta vazia.\n");
    return x;
}

// main method for tests
int main(int argc, char* argv[]) {
    List* list = new_list(5);
    push_front(list, 5);
    push_front(list, 4);
    push_front(list, 9);
    push_back(list, 3);
    push_back(list, 7);
    pop_back(list);
    pop_front(list);
    pop_list(list, 1);
    push_list(list, 7, 0);
    print_list(list);

    free_list(list);

    return 0;
}
