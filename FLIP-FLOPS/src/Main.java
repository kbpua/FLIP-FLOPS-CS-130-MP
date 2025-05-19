import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("Please choose flip-flop type:\n\n" +
                           "A: RS flip-flop\n" +
                           "B: D flip-flop\n" +
                           "C: JK flip-flop\n" +
                           "D: T flip-flop\n");
        char type = sc.next().toUpperCase().charAt(0);

        switch (type) {
            case 'A':
                System.out.println("\nPls provide Q input: ");
                int RSQ = sc.nextInt();
                System.out.println("Pls provide S input: ");
                int S = sc.nextInt();
                System.out.println("Pls provide R input: ");
                int R = sc.nextInt();

                int RSAnswer = RS(RSQ,S,R);

                if (S == 1 && R == 1) {
                    System.out.println("The value is indeterminate.");
                }

                System.out.println("\nthe output is: " + RSAnswer);

                break;
            case 'B':
                System.out.println("\nPls provide Q input: ");
                int DQ = sc.nextInt();
                System.out.println("Pls provide D input: ");
                int D = sc.nextInt();

                int DAnswer = D(DQ,D);

                System.out.println("\nthe output is: " + DAnswer);
                break;
            case 'C':
                System.out.println("\nPls provide Q input: ");
                int JKQ = sc.nextInt();
                System.out.println("Pls provide J input: ");
                int J = sc.nextInt();
                System.out.println("Pls provide K input: ");
                int K = sc.nextInt();

                int JKAnswer = JK(JKQ, J, K);

                System.out.println("\nthe output is: " + JKAnswer);
                break;
            case 'D':
                System.out.println("\nPls provide Q input: ");
                int TQ = sc.nextInt();
                System.out.println("Pls provide T input: ");
                int T = sc.nextInt();

                int TAnswer = T(TQ,T);

                System.out.println("\nthe output is: " + TAnswer);
                break;
        }
    }
    public static int RS(int RSQ, int S, int R) {

        // input is in QSR order
        // RS flip-flop that has set state 10, reset state 01, and memory state 00

        int RSNS = 0;

        // RESET

        if (S == 0 && R == 1) {
            RSNS = 0;
            return RSNS;
        }

        // SET

        else if (S == 1 && R == 0) {
            RSNS = 1;
            return RSNS;
        }

        // MEMORY STATE

        else if (S == 0 && R == 0) {
            RSNS = RSQ;
        }

        return RSNS;
    }

    public static int D(int DQ, int D) {

        // input is in QD order

        int DNS = 0;

        // RESET

        if (D == 0) {
            DNS = 0;
        }

        // SET

        if (D == 1) {
            DNS = 1;
        }
        return DNS;
    }

    public static int JK(int JKQ, int J, int K) {

        // input is in QJK order
        // RS flip-flop that has set state 10, reset state 01, and memory state 00, and 11 for compliment

        int JKNS = 0;

        // RESET

        if (J == 0 && K == 1) {
            JKNS = 0;
            return JKNS;
        }

        // SET

        else if (J == 1 && K == 0) {
            JKNS = 1;
            return JKNS;
        }

        // MEMORY STATE

        else if (J == 0 && K == 0) {
            JKNS = JKQ;
        }

        // INDETERMINATE case Q = 0

        else if (JKQ == 0 && J == 1 && K == 1) {
            JKNS = 1;
        }

        // INDETERMINATE case Q = 1

        else if (JKQ == 1 && J == 1 && K == 1) {
            JKNS = 0;
        }

        return JKNS;
    }

    public static int T(int TQ, int T) {

        // input is in QT order

        int TNS = 0;

        // MEMORY STATE

        if (T == 0) {
            TNS = TQ;
        }

        // COMPLIMENT case Q = 0

        else if (TQ == 0 && T == 1) {
            TNS = 1;
        }

        // COMPLIMENT case Q = 1

        else if (TQ == 1 && T == 1) {
            TNS = 0;
        }
        return TNS;
    }


}