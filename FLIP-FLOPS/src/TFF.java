import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;

/**
 * T (Toggle) Flip-Flop implementation.
 * This class simulates the behavior of a T flip-flop, which has one input:
 * - T (Toggle): When high, toggles the output state
 * 
 * The flip-flop has the following truth table:
 * T | Q(t+1)
 * 0 | Q(t) (Hold)
 * 1 | Q'(t) (Toggle)
 * 
 * The T flip-flop is essentially a JK flip-flop with J and K inputs tied
 * together.
 * When T is 1, the output toggles; when T is 0, the output remains unchanged.
 */
public class TFF {
    // Class to store iteration data
    private static class Iteration {
        int T;
        int Q;
        int Qnext;

        Iteration(int T, int Q, int Qnext) {
            this.T = T;
            this.Q = Q;
            this.Qnext = Qnext;
        }
    }

    /**
     * Processes the T flip-flop input and returns the next state.
     * 
     * @param Q  Current state of the flip-flop (0 or 1)
     * @param sc Scanner object for reading user input
     * @return Next state of the flip-flop
     * @throws IllegalArgumentException if invalid input is provided
     */
    public int inputT(int Q, Scanner sc) {
        if (sc == null) {
            throw new IllegalArgumentException("Scanner object cannot be null");
        }
        if (Q != 0 && Q != 1) {
            throw new IllegalArgumentException("Initial Q value must be either 0 or 1");
        }

        boolean exit = false;
        int T = 0;
        List<Iteration> iterations = new ArrayList<>();

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          T Flip-Flop         ║");
        System.out.println("╚══════════════════════════════╝");

        do {
            try {
                // Get inputs
                System.out.println("\nCurrent State: Q(t) = " + Q);
                System.out.print("Enter T (0 or 1): ");
                String tInput = sc.nextLine().trim();
                if (tInput.isEmpty()) {
                    System.out.println("Error: Input cannot be empty");
                    continue;
                }
                if (tInput.length() > 1) {
                    System.out.println("Error: Please enter only single digit (0 or 1)");
                    continue;
                }
                if (tInput.charAt(0) != '0' && tInput.charAt(0) != '1') {
                    System.out.println("Error: T must be either 0 or 1");
                    continue;
                }
                T = tInput.charAt(0) - '0';

                // Compute next state
                int Q_next = T(Q, T);

                // Store iteration
                iterations.add(new Iteration(T, Q, Q_next));

                // Display all iterations in a single table
                System.out.println("\nT Flip-Flop History:");
                System.out.println("┌───────┬──────────────────┐");
                System.out.println("│ Input │      Output      │");
                System.out.println("├───────┤──────────┬───────┤");
                System.out.println("│   T   │    Q(t)  │ Q(t+1)│");
                System.out.println("├───────┼──────────┼───────┤");
                for (Iteration iter : iterations) {
                    System.out.printf("│   %d   │     %d    │   %d   │\n", iter.T, iter.Q, iter.Qnext);
                }
                System.out.println("└───────┴──────────┴───────┘");

                // Update state
                Q = Q_next;

                // Continue prompt with separate loop
                boolean validResponse = false;
                while (!validResponse) {
                    System.out.print("\nContinue? (Y/N): ");
                    String response = sc.nextLine().trim().toUpperCase();
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
            } catch (Exception e) {
                System.out.println("Unexpected error occurred: " + e.getMessage());
                sc.nextLine(); // Clear any remaining input
            }
        } while (!exit);

        return Q;
    }

    private int getValidInput(Scanner sc) {
        int input;
        while (true) {
            try {
                input = sc.nextInt();
                if (input != 0 && input != 1) {
                    System.out.println("Error: Input must be either 0 or 1");
                    System.out.print("Please try again: ");
                    continue;
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer (0 or 1)");
                System.out.print("Please try again: ");
                sc.nextLine(); // Clear the invalid input
            }
        }
    }

    public int T(int Q, int T) {
        // Validate inputs
        if (Q != 0 && Q != 1) {
            throw new IllegalArgumentException("Q must be either 0 or 1");
        }
        if (T != 0 && T != 1) {
            throw new IllegalArgumentException("T must be either 0 or 1");
        }

        return T == 1 ? 1 - Q : Q; // Toggle if T=1, hold if T=0
    }
}
