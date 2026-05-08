// libs
#include <stdio.h>
#include <stdlib.h>

// defs
#define null NULL
#define bool short
#define true 1
#define false 0

// node for tree
typedef struct s_Node {
    int element;
    struct s_Node *right;
    struct s_Node *left;
} Node;

// struct for tree
typedef struct s_Tree {
    Node *root;
} Tree;

// function for create a new node
Node* new_node(int x) {
    Node *new = (Node*)malloc(sizeof(Node));
    if (new != null) {
        new->element = x;
        new->right = null;
        new->left = new->right;
    }

    return new;
}

// function for create a new tree
Tree* new_tree() {
    Tree *new = (Tree*)malloc(sizeof(Tree));
    if (new != null) {
        new->root = null;
    }

    return new;
}

// function for insert element on tree
Node* insert_rec(Node *root, int x) {
    Node *tmp = root;
    if (tmp == null) tmp = new_node(x);
    else if (x < tmp->element) tmp->left = insert_rec(tmp->left, x);
    else if (x > tmp->element) tmp->right = insert_rec(tmp->right, x);
    else printf("Erro.\n");

    return tmp;
}

// call for a recursive insert function
void insert(Tree *tree, int x) {
    tree->root = insert_rec(tree->root, x);
}

// method for insert with two pointers
void insert_two_pointers(Node **root, int x) {
    if(root != null) {
        if ((*root) == null) (*root) = new_node(x);
        else if (x < (*root)->element) insert_two_pointers(&(*root)->left, x);
        else if (x > (*root)->element) insert_two_pointers(&(*root)->right, x);
        else perror("Error.\n");
    } else perror("Error.\n");
}

// verify if two trees are mirror
bool is_mirror(Node *root1, Node *root2) {
    bool ok = false;
    if (root1 == null && root2 == null) ok = true; // if both are empty
    else if (root1 == null || root2 == null) ok = false; // if only an empty one
    else {
        // if elements are equals
        if (root1->element == root2->element) {
            ok = is_mirror(root1->left, root2->right) && is_mirror(root1->right, root2->left);
        } else ok = false; // if elements are differents
    }

    return ok;
}

// function for sums all elements in tree
int sum_tree(Node *root) {
    int sum = 0;
    if (root != null) {
        sum = root->element + sum_tree(root->left) + sum_tree(root->right);
    }

    return sum;
}

// function for count only elements pairs in tree
int only_pairs(Node *root) {
    int pairs = 0;
    if (root != null) {
        pairs = ((root->element % 2 == 0) ? 1 : 0) + only_pairs(root->left) + only_pairs(root->right);
    }

    return pairs;
}

// function for check if two tree are equals
bool is_equals(Node *root1, Node *root2) {
    bool ok = false;
    if (root1 == null && root2 == null) ok = true; // both are void?
    else if (root1 == null || root2 == null) ok = false; // only one is void?
    else {
        if (root1->element == root2->element) {
            ok = is_equals(root1->left, root2->left) && is_equals(root1->right, root2->right);
        } else ok = false;
    }

    return ok;
}

// function to check if there is a number divisible by x
bool find_divisible(Node *root, int x) {
    bool ok = false;
    if (root != null) {
        ok = (root->element % x == 0);
        if (ok == false) {
            ok = find_divisible(root->left, x);
            if (ok == false) {
                ok = find_divisible(root->right, x);
            }
        }
    }

    return ok;
}

// method for print tree
void print_tree_rec(Node *root) {
    if (root != null) {
        printf("%d\n", root->element);
        print_tree_rec(root->left);
        print_tree_rec(root->right);
    }
}

// call for recursive method
void print_tree(Tree *tree) {
    print_tree_rec(tree->root);
}

// main method for tests
int main(int argc, char *argv[]) {
    Tree *tree = new_tree();
    Tree *tree2 = new_tree();

    // tree 1:
    insert(tree, 7);
    insert_two_pointers(&(tree->root), 12);
    insert_two_pointers(&(tree->root), 5);

    // tree 2:
    insert(tree2, 7);
    insert_two_pointers(&(tree2->root), 3);
    insert_two_pointers(&(tree2->root), 5);

    print_tree(tree);
    printf("---\n");
    print_tree(tree2);

    // check
    bool equals = is_equals(tree->root, tree2->root);
    bool exist = find_divisible(tree->root, 2);
    printf("equals? %d\nexist: %d\n", equals, exist);

    return 0;
}