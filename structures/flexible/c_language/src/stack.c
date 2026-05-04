// libs
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// defs
#define null NULL

// struct for nodes with only a pointer to next
typedef struct s_Node {
    int value;
    struct s_Node* next;
} Node;

// struct for stack flexible
typedef struct s_Stack {
    Node* top;
} Stack;

// function for create a new node
Node* new_node(int x) {
    Node* new = malloc(sizeof(Node));
    if (new != null) {
        new->value = x;
        new->next = null;
    } else printf("Erro(new_node): nao foi possivel criar no.\n");
}

// function for create a new stack
Stack* new_stack() {
    Stack* new = malloc(sizeof(Stack));
    if (new != null) {
        new->top = null;
    } else printf("Erro(new_stack): nao foi possivel criar pilha.\n");
}

// method for a clear stack and free memory
void free_stack(Stack* stack) {
    if (stack->top != null) {
        Node* current = stack->top;
        Node* next;
        
        while (current != null) {
            next = current->next;
            free(current);
            current = next;
        }

        stack->top = null;
    } else printf("Erro(free_stack): pilha vazia.\n");
}

// method for print stack
void print_stack(Stack* stack) {
    if (stack->top != null) {
        Node* cursor = stack->top;
        while (cursor != null) {
            printf("%d\n", cursor->value);
            cursor = cursor->next;
        }
    } else printf("Erro(print_stack): pilha vazia.\n");
}

// method for a push new node in the stack struct
void push(Stack* stack, int x) {
    Node* new = new_node(x);
    if (stack && new != null) {
        new->next = stack->top;
        stack->top = new;
    } else printf("Erro(push): nao foi possivel criar nova celula na pilha.\n");
}

// function for a pop top node in the stack
int pop(Stack* stack) {
    int x = -1;
    if (stack->top != null) {
        x = stack->top->value;
        Node* tmp = stack->top;
        stack->top = stack->top->next;
        free(tmp);
    } else printf("Erro(pop): pilha vazia.\n");

    return x;
}

// main method for test
int main(int argc, char* argv[]) {
    Stack* stack = new_stack();
    push(stack, 3);
    push(stack, 7);
    push(stack, 13);
    push(stack, 21);
    push(stack, 67);
    pop(stack);
    print_stack(stack);

    free_stack(stack);

    return 0;
}
