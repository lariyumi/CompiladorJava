#include <stdio.h>
int main() { 
		float a;
		float b;
		char x[100];
		char y[100];
		printf("Hello World");
		printf("Fim do programa");
		scanf("%f", &a);
		scanf("%f", &b);
		scanf("%s", &x);
		a = 14.376223;
		a = -10.10;
		printf("%f", &a);
		while (a<5) {
			printf("%f", &a);
			a += 1;
		}
		if (a>5) {
			printf("Maior que 5");
			if (b>a) {
			printf("B maior que A");
		}
		else {
			printf("A maior que b");
		}
		}
		else {
			printf("Menor ou igual a 5");
			if (b>a) {
			printf("B maior que A");
		}
		else {
			printf("A maior que B");
			if (x==x) {
			printf("X igual a Y");
		}
		}
		}
		while (b<10) {
			printf("%f", &b);
			b += 1;
			while (a<5) {
			printf("%f", &a);
			a += 1;
		}
			do {
			printf("%f", &a);
			a += 5;
		} while (a<5);
		}
		do {
			printf("%f", &b);
			b -= 1;
		} while (b>10);
	return 0;
 }
