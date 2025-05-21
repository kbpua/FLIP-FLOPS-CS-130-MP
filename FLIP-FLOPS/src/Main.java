import java.util.*;

public class Main {
    public static void main(String[] args) {
        RSFF rsFlipFlop = new RSFF();
        DFF dFlipFlop = new DFF();
        JKFF jkFlipFlop = new JKFF();
        TFF tFlipFlop = new TFF();
        int Q = 0;
        boolean exit = false;
        Scanner sc = null;

        try {
            sc = new Scanner(System.in);

            while (!exit) {
                try {
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

                    String input = sc.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("Error: Empty input! Please enter a valid choice.");
                        continue;
                    }
                    if (input.length() > 1) {
                        System.out.println("Error: Please enter only a single character (A, B, C, D, or E).");
                        continue;
                    }

                    char type = input.toUpperCase().charAt(0);
                    if (type < 'A' || type > 'E') {
                        System.out.println("Error: Invalid option! Please choose A, B, C, D, or E.");
                        continue;
                    }

                    switch (type) {
                        case 'A':
                            try {
                                Q = rsFlipFlop.inputRS(Q, sc);
                                Q = 0;
                            } catch (Exception e) {
                                System.out.println("Error in RS flip-flop operation: " + e.getMessage());
                            }
                            break;
                        case 'B':
                            try {
                                Q = dFlipFlop.inputD(Q, sc);
                                Q = 0;
                            } catch (Exception e) {
                                System.out.println("Error in D flip-flop operation: " + e.getMessage());
                            }
                            break;
                        case 'C':
                            try {
                                Q = jkFlipFlop.inputJK(Q, sc);
                                Q = 0;
                            } catch (Exception e) {
                                System.out.println("Error in JK flip-flop operation: " + e.getMessage());
                            }
                            break;
                        case 'D':
                            try {
                                Q = tFlipFlop.inputT(Q, sc);
                                Q = 0;
                            } catch (Exception e) {
                                System.out.println("Error in T flip-flop operation: " + e.getMessage());
                            }
                            break;
                        case 'E':
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
            if (sc != null) {
                sc.close();
            }
        }
    }
}
