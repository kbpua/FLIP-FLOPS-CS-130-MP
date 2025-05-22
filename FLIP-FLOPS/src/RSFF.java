import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;

/**
 * RS (Reset-Set) Flip-Flop implementation.
 * This class simulates the behavior of an RS flip-flop, which has two inputs:
 * - R (Reset): When high, resets the output to 0
 * - S (Set): When high, sets the output to 1
 * 
 * The flip-flop has the following truth table:
 * R S | Q(t+1)
 * 0 0 | Q(t) (Hold)
 * 0 1 | 1 (Set)
 * 1 0 | 0 (Reset)
 * 1 1 | Invalid (Not allowed)
 */
public class RSFF {
    // Class to store iteration data
    private static class Iteration {
        int R;
        int S;
        int Q;
        int Qnext;

        Iteration(int R, int S, int Q, int Qnext) {
            this.R = R;
            this.S = S;
            this.Q = Q;
            this.Qnext = Qnext;
        }
    }

    /**
     * Processes the RS flip-flop input and returns the next state.
     * 
     * @param Q  Current state of the flip-flop (0 or 1)
     * @param sc Scanner object for reading user input
     * @return Next state of the flip-flop
     * @throws IllegalArgumentException if invalid inputs are provided
     */
    public int inputRS(int Q, Scanner sc) throws InterruptedException {
        if (sc == null) {
            throw new IllegalArgumentException("Scanner object cannot be null");
        }
        if (Q != 0 && Q != 1) {
            throw new IllegalArgumentException("Initial Q value must be either 0 or 1");
        }

        boolean exit = false;
        int S = 0, R = 0;
        List<Iteration> iterations = new ArrayList<>();

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        RS Flip-Flop          ║");
        System.out.println("╚══════════════════════════════╝");

        do {
            try {
                // Get inputs
                System.out.println("\nCurrent State: Q(t) = " + Q);
                System.out.print("Enter S (0 or 1): ");
                String sInput = sc.next().trim();
                if (sInput.isEmpty()) {
                    System.out.println("Error: Input cannot be empty");
                    continue;
                }
                if (sInput.length() > 1) {
                    System.out.println("Error: Please enter only single digit (0 or 1)");
                    continue;
                }
                if (sInput.charAt(0) != '0' && sInput.charAt(0) != '1') {
                    System.out.println("Error: S must be either 0 or 1");
                    continue;
                }
                S = sInput.charAt(0) - '0';

                System.out.print("Enter R (0 or 1): ");
                String rInput = sc.next().trim();
                if (rInput.isEmpty()) {
                    System.out.println("Error: Input cannot be empty");
                    continue;
                }
                if (rInput.length() > 1) {
                    System.out.println("Error: Please enter only single digit (0 or 1)");
                    continue;
                }
                if (rInput.charAt(0) != '0' && rInput.charAt(0) != '1') {
                    System.out.println("Error: R must be either 0 or 1");
                    continue;
                }
                R = rInput.charAt(0) - '0';

                // Validate
                if (S == 1 && R == 1) {
                    System.out.println("\nError: Invalid input combination - S and R cannot both be 1!");
                    continue;
                }

                // Compute next state
                int Q_next = RS(Q, S, R);

                // Store iteration
                iterations.add(new Iteration(R, S, Q, Q_next));

                // Display all iterations in a single table
                System.out.println("\nRS Flip-Flop History:");
                System.out.println("┌───────┬───────────────┐");
                System.out.println("│ Input │    Output     │");
                System.out.println("├───┬───┼───────┬───────┤");
                System.out.println("│ R │ S │ Q(t)  │ Q(t+1)│");
                System.out.println("├───┼───┼───────┼───────┤");
                for (Iteration iter : iterations) {
                    System.out.printf("│ %d │ %d │   %d   │   %d   │\n", iter.R, iter.S, iter.Q, iter.Qnext);
                }
                System.out.println("└───┴───┴───────┴───────┘");

                // Update state
                Q = Q_next;

                // Reset inputs after delay
                Thread.sleep(2000);
                S = 0;
                R = 0;
                System.out.println("\nInputs reset to S=0, R=0");

                // Display reset state table
                System.out.println("┌───────┬───────────────┐");
                System.out.println("│ Input │    Output     │");
                System.out.println("├───┬───┼───────┬───────┤");
                System.out.println("│ R │ S │ Q(t)  │ Q(t+1)│");
                System.out.println("├───┼───┼───────┼───────┤");
                System.out.printf("│ %d │ %d │   %d   │   %d   │\n", R, S, Q, Q_next);
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

    public int RS(int Q, int S, int R) {
        // Validate inputs
        if (Q != 0 && Q != 1) {
            throw new IllegalArgumentException("Q must be either 0 or 1");
        }
        if (S != 0 && S != 1) {
            throw new IllegalArgumentException("S must be either 0 or 1");
        }
        if (R != 0 && R != 1) {
            throw new IllegalArgumentException("R must be either 0 or 1");
        }
        if (S == 1 && R == 1) {
            throw new IllegalArgumentException("Invalid state: S and R cannot both be 1");
        }

        if (S == 1 && R == 0)
            return 1; // Set
        if (S == 0 && R == 1)
            return 0; // Reset
        return Q; // Hold
    }
}
