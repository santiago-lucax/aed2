/*
 * FIFO (First in, First Out)
 * Fila circular linear na linguagem C
*/

// libs
#include <stdio.h>
#include <stdlib.h>

// defs
#define null NULL
#define bool short
#define true 1
#define false 0

// struct for queue
typedef struct s_Queue {
    int* data;
    int capacity;
    int n;
    int first;
    int last;
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
                new->n = 0;
                new->first = 0;
                new->last = -1; // para que a primeira insercao ocorra bem (evita a posicao extra recomendada pelo professor)
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
            queue->n = 0;
            queue->first = 0;
            queue->last = 0;
        }

        free(queue);
    } else printf("Nao foi possivel limpar Fila, fila ja vazia.\n");
}

// verify if queue is full
bool is_full(Queue* queue) {
    return queue->n == queue->capacity;
}

// verify if queue is empty
bool is_empty(Queue* queue) {
    return queue->n == 0;
}

// print_queue to display the queue on the screen
void print_queue(Queue* queue) {
    // if (queue) == if (queue != null)
    if (queue && !is_empty(queue)) {
        int i = queue->first;
        for (/**/; i < queue->last; i = i + 1 % queue->capacity) {
            printf("%d ", queue->data[i]);
        }
        printf("%d", queue->data[i]);
        printf("\n");
    } else printf("Erro(print_queue): fila vazia.\n");

}

// method for insert element in circular queue
void enqueue(Queue* queue, int x) {
    if (!queue || is_full(queue)) {
        printf("Erro(push): fila cheia.\n ");
    } else {
        queue->last = (queue->last + 1) % queue->capacity; // primeira insercao: last = -1
        queue->data[queue->last] = x;
        queue->n++;
    }
}

// function for remove element in circular queue
int dequeue(Queue* queue) {
    int x = queue->first;
    if (is_empty(queue)) printf("Erro(pop): fila vazia.\n");
    else {
        queue->data[queue->first] = 0;
        queue->first++ % queue->capacity;
        queue->n--;
    }
    return x;
}

// main method for tests
int main(int argc, char* argv[]) {
    Queue* queue = new_queue(15);
    enqueue(queue, 3);
    enqueue(queue, 5);
    enqueue(queue, 7);
    enqueue(queue, 4);
    enqueue(queue, 3);
    enqueue(queue, 2);
    enqueue(queue, 8);
    enqueue(queue, 9);
    enqueue(queue, 22);
    enqueue(queue, 13);
    enqueue(queue, 19);
    enqueue(queue, 20);
    print_queue(queue);
    dequeue(queue);
    dequeue(queue);
    dequeue(queue);
    print_queue(queue);

    free_queue(queue);

    return 0;
}
