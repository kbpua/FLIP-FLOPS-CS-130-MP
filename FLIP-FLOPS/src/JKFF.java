import java.util.Scanner;

public class JKFF {

    public int inputJK(int Q, Scanner sc) throws InterruptedException {
        boolean exit = false;
        int J = 0, K = 0;

        // Previous cycle storage
        Integer prevJ = null, prevK = null, prevQ = null, prevQnext = null;

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          JK Flip-Flop        ║");
        System.out.println("╚══════════════════════════════╝");

        do {
            // Display previous cycle if available
            if (prevJ != null && prevK != null && prevQ != null && prevQnext != null) {
                System.out.println("\nPrevious Cycle: JK Flip Flop");
                System.out.println("┌───────┬───────────────┐");
                System.out.println("│ Input │    Output     │");
                System.out.println("├───┬───┼───────┬───────┤");
                System.out.println("│ J │ K │ Q(t)  │ Q(t+1)│");
                System.out.println("├───┼───┼───────┼───────┤");
                System.out.printf("│ %d │ %d │   %d   │   %d   │\n", prevJ, prevK, prevQ, prevQnext);
                System.out.println("└───┴───┴───────┴───────┘");
            }

            // Get inputs
            System.out.println("\nCurrent state: Q = " + Q);
            System.out.print("Please provide J input (0 or 1): ");
            J = sc.nextInt();
            System.out.print("Please provide K input (0 or 1): ");
            K = sc.nextInt();

            // Compute next state
            int Q_next = JK(Q, J, K);

            // Display current output
            System.out.println("\nCurrent Cycle: JK Flip Flop");
            System.out.println("┌───────┬───────────────┐");
            System.out.println("│ Input │    Output     │");
            System.out.println("├───┬───┼───────┬───────┤");
            System.out.println("│ J │ K │ Q(t)  │ Q(t+1)│");
            System.out.println("├───┼───┼───────┼───────┤");
            System.out.printf("│ %d │ %d │   %d   │   %d   │\n", J, K, Q, Q_next);
            System.out.println("└───┴───┴───────┴───────┘");

            // Save values for next loop
            prevJ = J;
            prevK = K;
            prevQ = Q;
            prevQnext = Q_next;

            // Update state
            Q = Q_next;

            // Delay and reset
            Thread.sleep(2000);
            J = 0; K = 0;
            System.out.println("\nInputs have been reset to J = 0, K = 0");
            System.out.println("\n---------------------------------------------------");

            // Continue prompt
            System.out.print("Continue? (Y/N): ");
            char response = sc.next().toUpperCase().charAt(0);
            exit = (response == 'N');

        } while (!exit);

        return Q;
    }

    public int JK(int Q, int J, int K) {
        if (J == 0 && K == 1) return 0;       // Reset
        if (J == 1 && K == 0) return 1;       // Set
        if (J == 1 && K == 1) return 1 - Q;   // Toggle
        return Q;                             // Hold
    }
}
