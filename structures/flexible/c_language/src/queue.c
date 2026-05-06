// libs
#include <stdio.h>
#include <stdlib.h>

// defs
#define null NULL
#define bool short
#define true 1
#define false 0

// struct for nodes with only a pointer to next
typedef struct s_Node {
  int value;
  struct s_Node *next;
} Node;

// struct for queue
typedef struct s_Queue {
  Node *first;
  Node *last;
} Queue;

// function for check if the queue is empty
bool is_empty(Queue *queue) { return queue->first == queue->last; }

// function for create a new node
Node *new_node(int x) {
  Node *new = malloc(sizeof(Node));
  if (new != null) {
    new->value = x;
    new->next = null;
  } else
    printf("Erro(new_node): nao foi possivel criar no.\n");

  return new;
}

// function for create a new queue
Queue *new_queue() {
  Queue *new = (Queue *)malloc(sizeof(Queue));
  Node *head = (Node *)malloc(sizeof(Node)); // for remove one if on enqueue
  if (new != null && head != null) {
    new->first = head;
    new->last = new->first;
  } else
    printf("Erro: new_queue()");

  return new;
}

// method for free memory allocate
void free_queue(Queue *queue) {
  if (queue->first != null && queue->last != null) {
    Node *current = queue->first;
    Node *next;

    while (current != null) {
      next = current->next;
      free(current);
      current = next;
    }

    queue->first = null;
    queue->last = null;
  }
}

// method for display the queue in the screen
void print_queue(Queue *queue) {
  if (queue && !is_empty(queue)) {
    Node *cursor = queue->first->next; // ignore head cell
    while (cursor != null) {
      printf("%d\n", cursor->value);
      cursor = cursor->next;
    }
  } else
    printf("Erro(print_queue): fila nao existe ou esta vazia.\n");
}

// method for create a new node on queue
void enqueue(Queue *queue, int x) {
  if (queue) {
    Node *tmp = new_node(x);
    queue->last->next = tmp;
    queue->last = queue->last->next;
  } else
    printf("Erro(enqueue): fila nao existe.\n");
}

// function for remove node on queue
int dequeue(Queue *queue) {
  int x = -1;
  if (queue && !is_empty(queue)) {
    x = queue->first->next->value;
    Node *tmp = queue->first->next;                // to remove next a head cell
    queue->first->next = queue->first->next->next; // to maintain head cell
    if (queue->first->next == null) {
      queue->last = queue->first;
    }
    free(tmp);
    tmp = null;
  } else
    printf("Erro(dequeue): fila nao existe ou esta vazia.\n");

  return x;
}

// main method for tests
int main(int argc, char *argv[]) {
  Queue *queue = new_queue();
  enqueue(queue, 3);
  enqueue(queue, 4);
  print_queue(queue);
  printf("-\n");
  dequeue(queue);
  print_queue(queue);

  free_queue(queue);
  return 0;
}