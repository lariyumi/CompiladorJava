import java.util.Scanner;
public class meuPrograma{ 
	public static void main(String args[]) { 
		Scanner sc = new Scanner(System.in);
		float a;
		float b;
		String x;
		String y;
		System.out.println("Hello World");
		System.out.println("Fim do programa");
		a = sc.nextFloat();
		b = sc.nextFloat();
		x = sc.nextLine();
		a = (float) 14.376223;
		a = (float) -10.10;
		System.out.println(a);
		while (a<5) {
			System.out.println(a);
			a += 1;
		}
		if (a>5  &&  b<10) {
			System.out.println("Maior que 5");
			if (b>a) {
			System.out.println("B maior que A");
		}
		else {
			System.out.println("A maior que b");
		}
		}
		else {
			System.out.println("Menor ou igual a 5");
			if (b>a) {
			System.out.println("B maior que A");
		}
		else {
			System.out.println("A maior que B");
			if (x==x) {
			System.out.println("X igual a Y");
		}
		}
		}
		while (b<10  &&  a<5) {
			System.out.println(b);
			b += 1;
			a += 1;
		}
		do {
			System.out.println(a);
			a += 5;
			b -= 1;
		} while (a<5  ||  b>10);
		do {
			System.out.println(b);
			b -= 1;
		} while (b>10);
	}
}
