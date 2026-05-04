/*
 * LIFO (Last in, FIrst Out)
 * Pilha linear na linguagem C
*/

// libs
#include <stdio.h>
#include <stdlib.h>

// defs
#define null NULL

// struct for stack
typedef struct s_Stack {
    int* data;
    int length;
    int n;
} Stack;

// new_stack() for create a new struct for stack
Stack* new_stack(int length) {
    Stack* new = null;
    if (length > 0) {
        new = (Stack*)malloc(sizeof(Stack));
        if (new != null) {
            new->data = (int*)malloc(sizeof(int) * length);
            if (new->data != null) {
                for (int i = 0; i < length; i++) new->data[i] = 0; // initial values for data is 0
                new->length = length;
                new->n = 0;
            }
        } else printf("Nao foi possivel criar Stack.\n");
    } else printf("Tamanho invalido.\n");

    return new;
}

// free_stack for clean and desallocate memory
void free_stack(Stack* stack) {
    if (stack != null) {
        if (stack->data != null) {
            free(stack->data);
            stack->length = 0;
            stack->n = 0;
        }

        free(stack);
    } else printf("Nao possivel limpar stack, stack ja vazia.\n");
}

// print_stack to display the stack on the screen
void print_stack(Stack* stack) {
    if (stack->length > 0) {
        int i = 0;
        for (/**/; i < stack->length - 1; i++) {
            printf("%d ", stack->data[i]);
        }
        printf("%d", stack->data[i]);
    }

    printf("\n");
}

// push() for add element in stack
void push(Stack* stack, int x) {
    int n = stack->n; // int n = (*stack).n
    int length = stack->length;
    if (n < length) {
        stack->data[n] = x;
        stack->n++;
    } else printf("Erro: Pilha cheia.\n");
}

// pop() for remove element from stack
int pop(Stack* stack) {
    int x = 0;
    x = stack->data[stack->n - 1];
    stack->data[stack->n - 1] = 0;
    stack->n--;
    return x;
}

// main method for tests
int main(int argc, char* argv[]) {
    int n = 0;
    scanf("%d", &n);
    Stack* stack = new_stack(n);

    push(stack, 1);
    push(stack, 3);
    push(stack, 7);
    print_stack(stack);

    int x = pop(stack);
    print_stack(stack);

    free_stack(stack);

    return 0;
}
