import java.util.Scanner;
public class Interpolation {
    String F[][];
    int Q[][];
    Scanner input = new Scanner(System.in);
    int argX;
    String valY, s;
    int n, m, length;

    public Interpolation(int n, int m){
        this.n = n;
        this.m = m;
        F = new String[n][m];
        System.out.println("Podaj wartości Xi, od najmniejszej do najwiekszej:");
        for(int i = 0; i < m; i++){
            argX = input.nextInt();
            s = String.valueOf(argX);
            F[0][i] = s;
            System.out.print(" Xi | ");
            for(int p = 0; p <= i; p++){
                System.out.print(F[0][p] + " | ");
            }
            System.out.println();
        }
        System.out.println("Teraz podaj wartości funkcji w tych punktach:");
        System.out.println("Jeśli wskazane przez '?' pole jest liczbą, wpisz ją. Jeśli jest puste, wpisz literę 'N'");
        for(int i = 1; i < n; i++){
            for(int j = 0; j < m; j++){
                F[i][j] = "  ?  ";
                showData();
                while(true){
                    valY = input.next();
                    if(valY.equals("N")){
                        F[i][j] = valY;
                        break;
                    }
                    else if(isNumeric(valY)){
                        F[i][j] = valY;
                        break;
                    }
                    else{
                        System.out.println("Podano zla wartosc, podaj jeszcze raz");
                        return;
                    }
                }
            }
        }
        showData();
        getLength();
        Q = new int[length][length+1];
        diffQuotients();
        //showDiffQuotientsArr(); //wyświetlenie tablicy 2d ilorazów różnicowych
        //showCoeff(); //wyswietlenie wspolczynników b_0, ... b_n.
        showPoly();
    }
    private void showCoeff() {
        System.out.println();
        for(int i = 0; i < length; i++){
            System.out.println("b" + i + ": " + Q[i][i + 1]);
        }
    }
    private int getLength() {
        length = 0;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!F[i][j].equals("N")){
                    length += 1;
                }
            }
        }
        return length;
    }
    private void diffQuotients() {
        int w = 0;
        for(int b = 0; b < m; b++){
            for(int a = 1; a < n; a++){
                if(!F[a][b].equals("N")){
                    Q[w][0] = Integer.parseInt(F[0][b]);
                    Q[w][1] = Integer.parseInt(F[1][b]);
                    w += 1;
                }
            }
        }
        for(int i = 1; i < length; i++){
            if(Q[i][0] != Q[i-1][0]) {
                for (int j = 2; j <= i + 1; j++) {
                    Q[i][j] = (Q[i][j - 1] - Q[i - 1][j - 1]) / (Q[i][0] - Q[i - j + 1][0]);
                }
            }
            else{
                int counter = 0;
                for(int l = i; l > 0; l--){
                    if(Q[l][0] == Q[l-1][0])
                        counter += 1;
                    else break;
                }
                for(int d = 0; d < m; d++){
                    if(Integer.parseInt(F[0][d]) == Q[i][0]){
                        int p = 2;
                        for(int j = 2; j < 2 + counter; j++){
                            Q[i][j] = Integer.parseInt(F[p][d]) / factorial(j - 1);
                            p+=1;
                        }
                        if(counter == i) break;
                        else {
                            for (int j = 2 + counter; j <= i + 1; j++)
                                Q[i][j] = (Q[i][j - 1] - Q[i - 1][j - 1]) / (Q[i][0] - Q[i - j + 1][0]);
                        }
                    }
                }
            }
        }
    }
    private void showDiffQuotientsArr() {
        System.out.println();
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length + 1; j++){
                System.out.print(Q[i][j] + " | ");
            }
            System.out.println();
        }
    }
    public static int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    public void showData(){
        System.out.print(" Xi | ");
        for(int i = 0; i < m; i++){
            System.out.print(F[0][i] + " | ");
        }
        System.out.println();
        for(int i = 1; i < n; i++){

            s = "f";
            for(int st = 0; st < i; st++){
                if(st>0)
                    s = s + "'";
            }
            s = s + "(argX)";
            System.out.print(s + " | ");

            for(int j = 0; j < m; j++){
                System.out.print(F[i][j] + " | ");
            }
            System.out.println();
        }
    }
    public void showPoly(){
        System.out.print("H(x) = (" + Q[0][1] + ") ");
        for(int i = 1; i < length; i++){
            if(Q[i][i + 1] == 0){
                continue;
            }
            if(i != length)
                System.out.print(" + ");

            System.out.print("(" + Q[i][i+1] + ")");
            for(int j = 1; j <= i; j++) {
                if(Q[j - 1][0] < 0) {
                    System.out.print("(x + " + Math.abs(Q[j - 1][0]) + ")");
                }
                else {
                    System.out.print("(x - " + Q[j - 1][0] + ")");
                }
            }
        }
    }

    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}