import java.util.Scanner;
import java.util.InputMismatchException;

public class DFF {
    public int inputD(int Q, Scanner sc) throws InterruptedException {
        if (sc == null) {
            throw new IllegalArgumentException("Scanner object cannot be null");
        }
        if (Q != 0 && Q != 1) {
            throw new IllegalArgumentException("Initial Q value must be either 0 or 1");
        }

        boolean exit = false;
        int D = 0;

        // Variables to store previous inputs and outputs
        Integer prevD = null, prevQ = null, prevQnext = null;

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          D Flip-Flop         ║");
        System.out.println("╚══════════════════════════════╝");

        do {
            try {
                // Show previous state for reference
                if (prevD != null && prevQ != null && prevQnext != null) {
                    System.out.println("\nPrevious Cycle: D Flip-Flop");
                    System.out.println("┌───────┬───────────────┐");
                    System.out.println("│ Input │    Output     │");
                    System.out.println("├───┬───┼───────┬───────┤");
                    System.out.println("│ D │ Q │ Q(t)  │ Q(t+1)│");
                    System.out.println("├───┼───┼───────┼───────┤");
                    System.out.printf("│ %d │ %d │   %d   │   %d   │\n", prevD, prevQ, prevQ, prevQnext);
                    System.out.println("└───┴───┴───────┴───────┘");
                }

                // Get inputs
                System.out.println("\nCurrent State: Q(t) = " + Q);
                System.out.print("Enter D (0 or 1): ");
                D = getValidInput(sc);

                // Compute next state
                int Q_next = D; // D flip-flop: output follows input

                // Display current table
                System.out.println("\nCurrent Cycle: D Flip-Flop");
                System.out.println("┌───────┬───────────────┐");
                System.out.println("│ Input │    Output     │");
                System.out.println("├───┬───┼───────┬───────┤");
                System.out.println("│ D │ Q │ Q(t)  │ Q(t+1)│");
                System.out.println("├───┼───┼───────┼───────┤");
                System.out.printf("│ %d │ %d │   %d   │   %d   │\n", D, Q, Q, Q_next);
                System.out.println("└───┴───┴───────┴───────┘");

                // Store current cycle as previous for next iteration
                prevD = D;
                prevQ = Q;
                prevQnext = Q_next;

                // Update state
                Q = Q_next;

                // Reset inputs after delay
                Thread.sleep(2000);
                D = 0;
                System.out.println("\nInput reset to D=0");
                // Display reset state table
                System.out.println("┌───────┬───────────────┐");
                System.out.println("│ Input │    Output     │");
                System.out.println("├───┬───┼───────┬───────┤");
                System.out.println("│ D │ Q │ Q(t)  │ Q(t+1)│");
                System.out.println("├───┼───┼───────┼───────┤");
                System.out.printf("│ %d │ %d │   %d   │   %d   │\n", D, Q, Q, Q_next);
                System.out.println("└───┴───┴───────┴───────┘");

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
