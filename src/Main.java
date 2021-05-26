import java.util.Scanner;
public class Main {
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj wymiary tablicy danych");
        System.out.println("Liczba wierszy:");
        int row = input.nextInt();
        System.out.println("Liczba kolumn:");
        int col = input.nextInt();

        Interpolation in = new Interpolation(row, col);

    }
}
