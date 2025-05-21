import java.util.Scanner;

public class RSFF {
    public int inputRS(int Q, Scanner sc) throws InterruptedException {
        boolean exit = false;
        int S = 0, R = 0;

        // Variables to store previous inputs and outputs
        Integer prevS = null, prevR = null, prevQ = null, prevQnext = null;

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        RS Flip-Flop          ║");
        System.out.println("╚══════════════════════════════╝");

        do {
            // Show previous state for reference
            if (prevS != null && prevR != null && prevQ != null && prevQnext != null) {
                System.out.println("\nPrevious Cycle:");
                System.out.println("┌───────┬───────────────┐");
                System.out.println("│ Input │    Output     │");
                System.out.println("├───┬───┼───────┬───────┤");
                System.out.println("│ R │ S │ Q(t)  │ Q(t+1)│");
                System.out.println("├───┼───┼───────┼───────┤");
                System.out.printf("│ %d │ %d │   %d   │   %d   │\n", prevR, prevS, prevQ, prevQnext);
                System.out.println("└───┴───┴───────┴───────┘");
            }

            // Get inputs
            System.out.println("\nCurrent State: Q(t) = " + Q);
            System.out.print("Enter S (0 or 1): ");
            S = sc.nextInt();
            System.out.print("Enter R (0 or 1): ");
            R = sc.nextInt();

            // Validate
            if (S == 1 && R == 1) {
                System.out.println("\nInvalid: S and R cannot both be 1!");
                continue;
            }

            // Compute next state
            int Q_next = RS(Q, S, R);

            // Display current table
            System.out.println("\nCurrent Cycle:");
            System.out.println("┌───────┬───────────────┐");
            System.out.println("│ Input │    Output     │");
            System.out.println("├───┬───┼───────┬───────┤");
            System.out.println("│ R │ S │ Q(t)  │ Q(t+1)│");
            System.out.println("├───┼───┼───────┼───────┤");
            System.out.printf("│ %d │ %d │   %d   │   %d   │\n", R, S, Q, Q_next);
            System.out.println("└───┴───┴───────┴───────┘");

            // Store current cycle as previous for next iteration
            prevS = S;
            prevR = R;
            prevQ = Q;
            prevQnext = Q_next;

            // Update state
            Q = Q_next;

            // Reset inputs after delay
            Thread.sleep(2000);
            S = 0;
            R = 0;
            System.out.println("\nInputs reset to S=0, R=0");

            // Continue prompt
            System.out.print("\nContinue? (Y/N): ");
            char response = sc.next().toUpperCase().charAt(0);
            exit = (response == 'N');
        } while (!exit);

        return Q;
    }

    public int RS(int Q, int S, int R) {
        if (S == 1 && R == 0) return 1;  // Set
        if (S == 0 && R == 1) return 0;  // Reset
        return Q;  // Hold
    }
}
