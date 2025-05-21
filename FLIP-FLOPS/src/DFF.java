import java.util.Scanner;

public class DFF {

    public int inputD(int Q, Scanner sc) throws InterruptedException {
        boolean exit = false;
        int D = 0;

        Integer prevD = null, prevQ = null, prevQnext = null;

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          D Flip-Flop         ║");
        System.out.println("╚══════════════════════════════╝");

        do {
            if (prevD != null && prevQ != null && prevQnext != null) {
                System.out.println("\nPrevious Cycle: D Flip-Flop");
                System.out.println("┌─────────┬────────────────────┐");
                System.out.println("│  Input  │       Output       │");
                System.out.println("├───┬─────┼──────────┬─────────┤");
                System.out.println("│ D │ Q(t)│       Q(t+1)       │");
                System.out.println("├───┼─────┼──────────┴─────────┤");
                System.out.printf("│ %d │  %d  │          %d         │\n", prevD, prevQ, prevQnext);
                System.out.println("└───┴─────┴────────────────────┘");
            }

            System.out.println("\nCurrent State: Q(t) = " + Q);
            System.out.print("Please provide D input (0 or 1): ");
            D = sc.nextInt();

            int Q_next = D; // D flip-flop: output follows input

            System.out.println("\nCurrent Cycle: D Flip-Flop");
            System.out.println("┌─────────┬────────────────────┐");
            System.out.println("│  Input  │       Output       │");
            System.out.println("├───┬─────┼──────────┬─────────┤");
            System.out.println("│ D │ Q(t)│       Q(t+1)       │");
            System.out.println("├───┼─────┼──────────┴─────────┤");
            System.out.printf("│ %d │  %d  │          %d         │\n", D, Q, Q_next);
            System.out.println("└───┴─────┴────────────────────┘");

            prevD = D;
            prevQ = Q;
            prevQnext = Q_next;

            Q = Q_next;

            Thread.sleep(2000);
            D = 0;
            System.out.println("\nInput has been reset to D = 0");
            System.out.println("\n---------------------------------------------------");

            System.out.print("Continue? (Y/N): ");
            char response = sc.next().toUpperCase().charAt(0);
            exit = (response == 'N');
        } while (!exit);

        return Q;
    }
}
