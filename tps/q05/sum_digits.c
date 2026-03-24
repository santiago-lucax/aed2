// Bibliotecas
#include <stdio.h>
#include <stdlib.h>

int sumOfDigits(int value, int x) {

    int k = 0;

    if (x > value) return k;
    else {

        k = ((value % (x * 10)) - value % x) / x;
        return k = k + sumOfDigits(value, x * 10); 

    }

}

int sumOfDigitsEncap(int value) {

    int x = sumOfDigits(value, 1);
    return x;

}

int main(int argc, char* argv[]) {

    int x = 12345;
    printf("%d\n", (x % 10 - x % 1) / 1); // 5
    printf("%d\n", (x % 100 - x % 10) / 10); // 4
    printf("%d\n", (x % 1000 - x % 100) / 100); // 3
    printf("%d\n", (x % 10000 - x % 1000) / 1000); // 2
    printf("%d\n", (x % 100000 - x % 10000) / 10000); // 1

    printf("%d\n", sumOfDigits(123456, 1));
    printf("%d\n", sumOfDigitsEncap(123456));

}
