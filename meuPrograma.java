import java.util.Scanner;
public class meuPrograma{ 
	public static void main(String args[]) { 
		Scanner sc = new Scanner(System.in);
		int a;
		int b;
		String x;
		String y;
		System.out.println("Hello World");
		System.out.println("Fim do programa");
		a = sc.nextInt();
		b = sc.nextInt();
		x = sc.nextLine();
		y = sc.nextLine();
		System.out.println(a);
		if (a>5) {
			if (b>a) {
			System.out.println("Maior que 5");
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
			if (x==y) {
			System.out.println("X igual a Y");
		}
		}
		}
	}
}
