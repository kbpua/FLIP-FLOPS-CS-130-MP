import java.util.*;

/**
 * Main class for the Flip-Flop Simulator application.
 * This program simulates different types of flip-flops:
 * - RS (Reset-Set) Flip-Flop
 * - D (Data) Flip-Flop
 * - JK Flip-Flop
 * - T (Toggle) Flip-Flop
 */
public class Main {
    /**
     * Main method that runs the Flip-Flop Simulator.
     * Creates instances of different flip-flops and handles user interaction.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize flip-flop instances
        RSFF rsFlipFlop = new RSFF();
        DFF dFlipFlop = new DFF();
        JKFF jkFlipFlop = new JKFF();
        TFF tFlipFlop = new TFF();
        
        // Initialize state variables
        int Q = 0;  // Initial state of flip-flops
        boolean exit = false;  // Control variable for program exit
        Scanner sc = null;  // Scanner for user input

        try {
            sc = new Scanner(System.in);

            // Main program loop
            while (!exit) {
                try {
                    // Display menu
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════╗");
                    System.out.println("║               Flip-Flop Menu               ║");
                    System.out.println("╠════════════════════════════════════════════╣");
                    System.out.println("║ A: RS flip-flop                            ║");
                    System.out.println("║ B: D flip-flop                             ║");
                    System.out.println("║ C: JK flip-flop                            ║");
                    System.out.println("║ D: T flip-flop                             ║");
                    System.out.println("║ E: Exit application                        ║");
                    System.out.println("╚════════════════════════════════════════════╝");
                    System.out.print("Enter your choice: ");

                    // Get and validate user input
                    String input = sc.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("Error: Empty input! Please enter a valid choice.");
                        continue;
                    }
                    if (input.length() > 1) {
                        System.out.println("Error: Please enter only a single character (A, B, C, D, or E).");
                        continue;
                    }

                    // Process user choice
                    char type = input.toUpperCase().charAt(0);
                    if (type < 'A' || type > 'E') {
                        System.out.println("Error: Invalid option! Please choose A, B, C, D, or E.");
                        continue;
                    }

                    // Handle user selection
                    switch (type) {
                        case 'A':  // RS Flip-Flop
                            try {
                                Q = rsFlipFlop.inputRS(Q, sc);
                                Q = 0;  // Reset state after operation
                            } catch (Exception e) {
                                System.out.println("Error in RS flip-flop operation: " + e.getMessage());
                            }
                            break;
                        case 'B':  // D Flip-Flop
                            try {
                                Q = dFlipFlop.inputD(Q, sc);
                                Q = 0;  // Reset state after operation
                            } catch (Exception e) {
                                System.out.println("Error in D flip-flop operation: " + e.getMessage());
                            }
                            break;
                        case 'C':  // JK Flip-Flop
                            try {
                                Q = jkFlipFlop.inputJK(Q, sc);
                                Q = 0;  // Reset state after operation
                            } catch (Exception e) {
                                System.out.println("Error in JK flip-flop operation: " + e.getMessage());
                            }
                            break;
                        case 'D':  // T Flip-Flop
                            try {
                                Q = tFlipFlop.inputT(Q, sc);
                                Q = 0;  // Reset state after operation
                            } catch (Exception e) {
                                System.out.println("Error in T flip-flop operation: " + e.getMessage());
                            }
                            break;
                        case 'E':  // Exit program
                            System.out.println("Thank you for using our Flip-Flop Simulator!");
                            exit = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input format! Please enter a valid choice.");
                    sc.nextLine(); // Clear the invalid input
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Critical error: " + e.getMessage());
        } finally {
            // Clean up resources
            if (sc != null) {
                sc.close();
            }
        }
    }
}
