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

// struct for queue
typedef struct s_Queue {
    Node* first;
    Node* last;
} Queue;

// function for create a new node
Node* new_node(int x) {
    Node* new = malloc(sizeof(Node));
    if (new != null) {
        new->value = x;
        new->next = null;
    } else printf("Erro(new_node): nao foi possivel criar no.\n");
}

// function for create a new queue
Queue* new_queue() {
    Queue* new = (Queue*)malloc(sizeof(Queue));
    Node* head = (Node*)malloc(sizeof(Node)); // for remove one if on push
    if (new != null && head != null) {
        new->first = head;
        new->last = new->first;
    } else printf("Erro: new_queue()");
}

// method for free memory allocate
void free_queue(Queue* queue) {
    if (queue->first != null && queue->last != null) {
        Node* current = queue->first;
        Node* next;

        while (current != null) {
            next = current->next;
            free(current);
            current = next;
        }

        queue->first = null;
        queue->last = null;
    } 
}

// main method for tests
int main(int argc, char* argv[]) {
    return 0;
}
