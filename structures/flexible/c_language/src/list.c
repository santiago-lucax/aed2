// libs
#include <stdio.h>
#include <stdlib.h>

// defs
#define null NULL
#define bool short
#define true 1
#define false 0

typedef struct s_Node {
	int value;
	struct s_Node *next;
} Node;

typedef struct s_List {
	Node *first;
	Node *last;
} List;

Node* new_node(int x) {
	Node *new = (Node*)malloc(sizeof(Node));
	if (new != null) {
		new->value = x;
		new->next = null;
	}

	return new;
}

List* new_list() {
	List *new = (List*)malloc(sizeof(List));
	if (new != null) {
		new->first = null;
		new->last = null;
	}

	return new;
}

void push_front(List *list, int x) {
	if (list != null) {
		Node *new = new_node(x);
		if (new != null) {
			if (list->first == null) {
                list->first = list->last = new;
			} else {
				new->next = list->first;
				list->first = new;
			}
		}

        new = null;
	}
}

void push_back(List *list, int x) {
	if (list != null) {
		Node *new = new_node(x);
		if (new != null) {
			if (list->first == null) {
				list->first = new;
				list->last = new;
			} else {
				list->last->next = new;
				list->last = new;
			}
		}

        new = null;
	}
}

int list_length(List *list) {
	int length = 0;
	if (list != null) {
		Node *cursor = list->first;
		while (cursor != null) {
			length++;
			cursor = cursor->next;		
		}
	}

	return length;
}

void push(List *list, int x, int pos) {
    int length = list_length(list);
	if (list != null) {
		Node *new = new_node(x);
		if (new != null) {
			if (list->first == null) {
				list->first = new;
				list->last = new;
			} else {
			     if (pos < 0 || pos >= length) printf("Erro.\n");
                 else if (pos == 0) push_front(list, x);
                 else if (pos == length -1) push_back(list, x);
                 else {
                    Node *cursor = list->first;
                    for (int j = 0; j < pos - 1; j++, cursor = cursor->next);
                    Node *new = new_node(x);
                    new->next = cursor->next;
                    cursor->next = new;

                    cursor = new = null;
                 }
			}
		}
	}
}

// method for remove first node of list
int pop_front(List *list) {
    int x = -1;
    if (list != null) {
        x = list->first->value;
        Node *tmp = list->first;
        list->first = list->first->next;
        free(tmp);
        tmp = null;
    }

    return x;
}

// method for remove last node of list
int pop_back(List *list) {
    int x = -1;
    if (list != null) {
        x = list->last->value;
        Node *cursor = list->first;
        for (int i = 0; i < list_length(list) -1; i++, cursor = cursor->next);
        Node *tmp = cursor->next;

        free(tmp);
        tmp = cursor = null;
    }
    return x;
}

void print_list(List *list) {
	if (list != null) {
		Node *cursor = list->first;
		while (cursor != null) {
			printf("%d\n", cursor->value);
			cursor = cursor->next;
		}

		printf("\n");
	}
}

// main method for tests
int main(int argc, char *argv[]) {
	List *list = new_list();
	push_front(list, 1);
	push_front(list, 4);
	push_front(list, 5);
	push_back(list, 5);
	push_back(list, 16);
    push(list, 15, 3);
    pop_front(list);
    pop_back(list);
	print_list(list);

	return 0;
}