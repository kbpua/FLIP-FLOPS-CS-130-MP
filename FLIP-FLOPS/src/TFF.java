import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * T (Toggle) Flip-Flop implementation.
 * This class simulates the behavior of a T flip-flop, which has one input:
 * - T (Toggle): When high, toggles the output state
 * 
 * The flip-flop has the following truth table:
 * T | Q(t+1)
 * 0 | Q(t)    (Hold)
 * 1 | Q'(t)   (Toggle)
 * 
 * The T flip-flop is essentially a JK flip-flop with J and K inputs tied together.
 * When T is 1, the output toggles; when T is 0, the output remains unchanged.
 */
public class TFF {
    /**
     * Processes the T flip-flop input and returns the next state.
     * 
     * @param Q Current state of the flip-flop (0 or 1)
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

        // Variables to store previous inputs and outputs
        Integer prevT = null, prevQ = null, prevQnext = null;

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          T Flip-Flop         ║");
        System.out.println("╚══════════════════════════════╝");

        do {
            try {
                // Show previous state for reference
                if (prevT != null && prevQ != null && prevQnext != null) {
                    System.out.println("\nPrevious Cycle: T Flip-Flop");
                    System.out.println("┌───────┬───────────────┐");
                    System.out.println("│ Input │    Output     │");
                    System.out.println("├───┬───┼───────┬───────┤");
                    System.out.println("│ T │ Q │ Q(t)  │ Q(t+1)│");
                    System.out.println("├───┼───┼───────┼───────┤");
                    System.out.printf("│ %d │ %d │   %d   │   %d   │\n", prevT, prevQ, prevQ, prevQnext);
                    System.out.println("└───┴───┴───────┴───────┘");
                }

                // Get inputs
                System.out.println("\nCurrent State: Q(t) = " + Q);
                System.out.print("Enter T (0 or 1): ");
                T = getValidInput(sc);

                // Compute next state
                int Q_next = T(Q, T);

                // Display current table
                System.out.println("\nCurrent Cycle: T Flip-Flop");
                System.out.println("┌───────┬───────────────┐");
                System.out.println("│ Input │    Output     │");
                System.out.println("├───┬───┼───────┬───────┤");
                System.out.println("│ T │ Q │ Q(t)  │ Q(t+1)│");
                System.out.println("├───┼───┼───────┼───────┤");
                System.out.printf("│ %d │ %d │   %d   │   %d   │\n", T, Q, Q_next);
                System.out.println("└───┴───┴───────┴───────┘");

                // Store current cycle as previous for next iteration
                prevT = T;
                prevQ = Q;
                prevQnext = Q_next;

                // Update state
                Q = Q_next;

                // Continue prompt
                System.out.print("\nContinue? (Y/N): ");
                String response = sc.next().trim().toUpperCase();
                if (response.length() != 1 || (response.charAt(0) != 'Y' && response.charAt(0) != 'N')) {
                    System.out.println("Error: Please enter 'Y' or 'N'");
                    continue;
                }
                exit = (response.charAt(0) == 'N');

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

        return T == 1 ? 1 - Q : Q;  // Toggle if T=1, hold if T=0
    }
}
