import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;

/**
 * D (Data) Flip-Flop implementation.
 * This class simulates the behavior of a D flip-flop, which has one input:
 * - D (Data): The value to be stored in the flip-flop
 * 
 * The flip-flop has the following truth table:
 * D | Q(t+1)
 * 0 | 0
 * 1 | 1
 * 
 * The D flip-flop simply copies its input to the output on the next clock edge.
 */
public class DFF {
    // Class to store iteration data
    private static class Iteration {
        int D;
        int Q;
        int Qnext;

        Iteration(int D, int Q, int Qnext) {
            this.D = D;
            this.Q = Q;
            this.Qnext = Qnext;
        }
    }

    /**
     * Processes the D flip-flop input and returns the next state.
     * 
     * @param Q  Current state of the flip-flop (0 or 1)
     * @param sc Scanner object for reading user input
     * @return Next state of the flip-flop
     * @throws IllegalArgumentException if invalid input is provided
     */
    public int inputD(int Q, Scanner sc) {
        if (sc == null) {
            throw new IllegalArgumentException("Scanner object cannot be null");
        }
        if (Q != 0 && Q != 1) {
            throw new IllegalArgumentException("Initial Q value must be either 0 or 1");
        }

        boolean exit = false;
        int D = 0;
        List<Iteration> iterations = new ArrayList<>();

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          D Flip-Flop         ║");
        System.out.println("╚══════════════════════════════╝");

        do {
            try {
                // Get inputs
                System.out.println("\nCurrent State: Q(t) = " + Q);
                System.out.print("Enter D (0 or 1): ");
                String dInput = sc.next().trim();
                if (dInput.isEmpty()) {
                    System.out.println("Error: Input cannot be empty");
                    continue;
                }
                if (dInput.length() > 1) {
                    System.out.println("Error: Please enter only single digit (0 or 1)");
                    continue;
                }
                if (dInput.charAt(0) != '0' && dInput.charAt(0) != '1') {
                    System.out.println("Error: D must be either 0 or 1");
                    continue;
                }
                D = dInput.charAt(0) - '0';

                // Compute next state
                int Q_next = D; // D flip-flop: output follows input

                // Store iteration
                iterations.add(new Iteration(D, Q, Q_next));

                // Display all iterations in a single table
                System.out.println("\nD Flip-Flop History:");
                System.out.println("┌───────┬──────────────────┐");
                System.out.println("│ Input │      Output      │");
                System.out.println("├───────┤──────────┬───────┤");
                System.out.println("│   D   │    Q(t)  │ Q(t+1)│");
                System.out.println("├───────┼──────────┼───────┤");
                for (Iteration iter : iterations) {
                    System.out.printf("│   %d   │     %d    │   %d   │\n", iter.D, iter.Q, iter.Qnext);
                }
                System.out.println("└───────┴──────────┴───────┘");

                // Update state
                Q = Q_next;

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

    public int D(int Q, int D) {
        // Validate inputs
        if (Q != 0 && Q != 1) {
            throw new IllegalArgumentException("Q must be either 0 or 1");
        }
        if (D != 0 && D != 1) {
            throw new IllegalArgumentException("D must be either 0 or 1");
        }

        return D; // D flip-flop: output follows input
    }
}
