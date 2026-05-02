/*
 * FIFO (First in, First Out)
 * Fila linear na linguagem C
*/

// libs
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// defs
#define null NULL

// struct for queue
typedef struct s_Queue {
    int* data;
    int capacity;
    int last;
    // in this struct the variable for first element is not necessary
} Queue;

// new_queue for create a new queue struct
Queue* new_queue(int capacity) {
    Queue* new = null;
    if (capacity > 0) {
        new = (Queue*)malloc(sizeof(Queue));
        if (new != null) {
            new->data = (int*)malloc(sizeof(int) * capacity);
            if (new->data != null) {
                for (int i = 0; i < capacity; i++) new->data[i] = 0; // initial values for data is 0
                new->capacity = capacity;
                new->last = 0;
            } else printf("Nao foi possivel alocar espaco para os dados.\n");
        } else printf("Nao foi possivel criar Queue.\n");
    } else printf("Tamanho invalido.\n");

    return new;
}

// free_queue for clean and desallocate memory
void free_queue(Queue* queue) {
    if (queue != null) {
        if (queue->data != null) {
            free(queue->data);
            queue->capacity = 0;
            queue->last = 0;
        }

        free(queue);
    } else printf("Nao foi possivel limpar Fila, fila ja vazia.\n");
}

// verify if queue is full
bool is_full(Queue* queue) {
    return queue->last == queue->capacity;
}

// verify if queue is empty
bool is_empty(Queue* queue) {
    return queue->last == 0;
}

// print_queue to display the stack on the screen
void print_queue(Queue* queue) {
    if (queue) {
        int i = 0;
        for (/**/; i < queue->capacity - 1; i++) {
            printf("%d ", queue->data[i]);
        }
        printf("%d", queue->data[i]);
    }

    printf("\n");
}

// enqueue() for add element in the queue
void enqueue(Queue* queue, int x) {
    if (queue && !is_full(queue)) {
        queue->data[queue->last++] = x;
    } else printf("Erro: fila cheia ou inexistente.\n");
}

// dequeue() for remove element from queue
int dequeue(Queue* queue) {
    int item = 0;
    if (queue && !is_empty(queue)) {
        item = queue->data[0];
        for (int i = 0; i < queue->last - 1; i++) {
            queue->data[i] = queue->data[i + 1];
        }

        queue->data[--queue->last] = 0;
    } else printf("Fila vazia.\n");

    return item;
}

int main(int argc, char* argv[]) {
    Queue* queue = new_queue(5);
    enqueue(queue, 3);
    enqueue(queue, 7);
    enqueue(queue, 13);
    print_queue(queue);
    dequeue(queue);
    print_queue(queue);
    enqueue(queue, 8);
    enqueue(queue, 11);
    enqueue(queue, 21);
    print_queue(queue);

    free_queue(queue);

    return 0;
}
