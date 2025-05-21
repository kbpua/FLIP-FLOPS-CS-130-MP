import java.util.Scanner;

public class TFF {
    public int inputT(int Q, Scanner sc) throws InterruptedException {
        boolean exit = false;
        int T = 0;

        Integer prevT = null, prevQ = null, prevQnext = null;

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          T Flip-Flop         ║");
        System.out.println("╚══════════════════════════════╝");

        do {
            // Display previous cycle if available
            if (prevT != null && prevQ != null && prevQnext != null) {
                System.out.println("\nPrevious Cycle: T Flip-Flop");
                System.out.println("┌─────────┬────────────────────┐");
                System.out.println("│  Input  │       Output       │");
                System.out.println("├───┬─────┼──────────┬─────────┤");
                System.out.println("│ T │ Q(t)│       Q(t+1)       │");
                System.out.println("├───┼─────┼──────────┴─────────┤");
                System.out.printf("│ %d │  %d  │          %d         │\n", prevT, prevQ, prevQnext);
                System.out.println("└───┴─────┴────────────────────┘");
            }

            // Get input
            System.out.println("\nCurrent State: Q(t) = " + Q);
            System.out.print("Please provide T input (0 or 1): ");
            T = sc.nextInt();

            // Compute next state
            int Q_next = T == 1 ? 1 - Q : Q;

            // Display current cycle
            System.out.println("\nCurrent Cycle: T Flip-Flop");
            System.out.println("┌─────────┬────────────────────┐");
            System.out.println("│  Input  │       Output       │");
            System.out.println("├───┬─────┼──────────┬─────────┤");
            System.out.println("│ T │ Q(t)│       Q(t+1)       │");
            System.out.println("├───┼─────┼──────────┴─────────┤");
            System.out.printf("│ %d │  %d  │          %d         │\n", T, Q, Q_next);
            System.out.println("└───┴─────┴────────────────────┘");

            // Store previous state
            prevT = T;
            prevQ = Q;
            prevQnext = Q_next;

            // Update state
            Q = Q_next;

            // Delay and reset
            Thread.sleep(2000);
            T = 0;
            System.out.println("\nInput has been reset to T = 0");
            System.out.println("\n---------------------------------------------------");

            // Ask to continue
            System.out.print("Continue? (Y/N): ");
            char response = sc.next().toUpperCase().charAt(0);
            exit = (response == 'N');

        } while (!exit);

        return Q;
    }
}
