import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;

/**
 * JK Flip-Flop implementation.
 * This class simulates the behavior of a JK flip-flop, which has two inputs:
 * - J (Jump): When high, sets the output to 1
 * - K (Kill): When high, resets the output to 0
 * 
 * The flip-flop has the following truth table:
 * J K | Q(t+1)
 * 0 0 | Q(t) (Hold)
 * 0 1 | 0 (Reset)
 * 1 0 | 1 (Set)
 * 1 1 | Q'(t) (Toggle)
 * 
 * The JK flip-flop is an improvement over the RS flip-flop as it allows
 * both inputs to be 1, which results in toggling the output.
 */
public class JKFF {
    // Class to store iteration data
    private static class Iteration {
        int J;
        int K;
        int Q;
        int Qnext;

        Iteration(int J, int K, int Q, int Qnext) {
            this.J = J;
            this.K = K;
            this.Q = Q;
            this.Qnext = Qnext;
        }
    }

    /**
     * Processes the JK flip-flop input and returns the next state.
     * 
     * @param Q  Current state of the flip-flop (0 or 1)
     * @param sc Scanner object for reading user input
     * @return Next state of the flip-flop
     * @throws IllegalArgumentException if invalid inputs are provided
     */
    public int inputJK(int Q, Scanner sc) throws InterruptedException {
        if (sc == null) {
            throw new IllegalArgumentException("Scanner object cannot be null");
        }
        if (Q != 0 && Q != 1) {
            throw new IllegalArgumentException("Initial Q value must be either 0 or 1");
        }

        boolean exit = false;
        int J = 0, K = 0;
        List<Iteration> iterations = new ArrayList<>();

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          JK Flip-Flop        ║");
        System.out.println("╚══════════════════════════════╝");

        do {
            try {
                // Get inputs
                System.out.println("\nCurrent State: Q(t) = " + Q);
                System.out.print("Enter J (0 or 1): ");
                String jInput = sc.next().trim();
                if (jInput.isEmpty()) {
                    System.out.println("Error: Input cannot be empty");
                    continue;
                }
                if (jInput.length() > 1) {
                    System.out.println("Error: Please enter only single digit (0 or 1)");
                    continue;
                }
                if (jInput.charAt(0) != '0' && jInput.charAt(0) != '1') {
                    System.out.println("Error: J must be either 0 or 1");
                    continue;
                }
                J = jInput.charAt(0) - '0';

                System.out.print("Enter K (0 or 1): ");
                String kInput = sc.next().trim();
                if (kInput.isEmpty()) {
                    System.out.println("Error: Input cannot be empty");
                    continue;
                }
                if (kInput.length() > 1) {
                    System.out.println("Error: Please enter only single digit (0 or 1)");
                    continue;
                }
                if (kInput.charAt(0) != '0' && kInput.charAt(0) != '1') {
                    System.out.println("Error: K must be either 0 or 1");
                    continue;
                }
                K = kInput.charAt(0) - '0';

                // Compute next state
                int Q_next = JK(Q, J, K);

                // Store iteration
                iterations.add(new Iteration(J, K, Q, Q_next));

                // Display all iterations in a single table
                System.out.println("\nJK Flip-Flop History:");
                System.out.println("┌───────┬───────────────┐");
                System.out.println("│ Input │    Output     │");
                System.out.println("├───┬───┼───────┬───────┤");
                System.out.println("│ J │ K │ Q(t)  │ Q(t+1)│");
                System.out.println("├───┼───┼───────┼───────┤");
                for (Iteration iter : iterations) {
                    System.out.printf("│ %d │ %d │   %d   │   %d   │\n", iter.J, iter.K, iter.Q, iter.Qnext);
                }
                System.out.println("└───┴───┴───────┴───────┘");

                // Update state
                Q = Q_next;

                // Reset inputs after delay
                Thread.sleep(2000);
                J = 0;
                K = 0;
                System.out.println("\nInputs reset to J=0, K=0");

                // Display reset state table
                System.out.println("┌───────┬───────────────┐");
                System.out.println("│ Input │    Output     │");
                System.out.println("├───┬───┼───────┬───────┤");
                System.out.println("│ J │ K │ Q(t)  │ Q(t+1)│");
                System.out.println("├───┼───┼───────┼───────┤");
                System.out.printf("│ %d │ %d │   %d   │   %d   │\n", J, K, Q, Q_next);
                System.out.println("└───┴───┴───────┴───────┘");

                // Continue prompt with separate loop
                boolean validResponse = false;
                while (!validResponse) {
                    System.out.print("\nContinue? (Y/N): ");
                    String response = sc.next().trim().toUpperCase();
                    if (response.isEmpty()) {
                        System.out.println("Error: Input cannot be empty");
                        continue;
                    }
                    if (response.length() > 1) {
                        System.out.println("Error: Please enter only 'Y' or 'N'");
                        continue;
                    }
                    char responseChar = response.charAt(0);
                    if (responseChar != 'Y' && responseChar != 'N') {
                        System.out.println("Error: Please enter only 'Y' or 'N'");
                        continue;
                    }
                    exit = (responseChar == 'N');
                    validResponse = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer (0 or 1)");
                sc.nextLine(); // Clear the invalid input
            } catch (InterruptedException e) {
                System.out.println("Error: Operation was interrupted");
                throw e;
            } catch (Exception e) {
                System.out.println("Unexpected error occurred: " + e.getMessage());
                sc.nextLine(); // Clear any remaining input
            }
        } while (!exit);

        return Q;
    }

    public int JK(int Q, int J, int K) {
        // Validate inputs
        if (Q != 0 && Q != 1) {
            throw new IllegalArgumentException("Q must be either 0 or 1");
        }
        if (J != 0 && J != 1) {
            throw new IllegalArgumentException("J must be either 0 or 1");
        }
        if (K != 0 && K != 1) {
            throw new IllegalArgumentException("K must be either 0 or 1");
        }

        if (J == 0 && K == 1)
            return 0; // Reset
        if (J == 1 && K == 0)
            return 1; // Set
        if (J == 1 && K == 1)
            return 1 - Q; // Toggle
        return Q; // Hold
    }
}
