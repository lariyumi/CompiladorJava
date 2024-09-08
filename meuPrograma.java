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
		System.out.println(a);
		if (a+2>5-b) {
			System.out.println("Maior que 5");
		}
		else {
			System.out.println("Menor ou igual a 5");
		}
	}
}
