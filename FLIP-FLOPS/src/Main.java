import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RSFF rsFlipFlop = new RSFF();
        DFF dFlipFlop = new DFF();
        JKFF jkFlipFlop = new JKFF();
        TFF tFlipFlop = new TFF();
        int Q = 0;
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        while (!exit) {
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

            char type = sc.next().toUpperCase().charAt(0);

            switch (type) {
                case 'A':
                    Q = rsFlipFlop.inputRS(Q, sc);
                    Q = 0;
                    break;
                case 'B':
                    Q = dFlipFlop.inputD(Q, sc);
                    Q = 0;
                    break;
                case 'C':
                    Q = jkFlipFlop.inputJK(Q, sc);
                    Q = 0;
                    break;
                case 'D':
                    Q = tFlipFlop.inputT(Q, sc);
                    Q = 0;
                    break;
                case 'E':
                    System.out.println("Thank you for using our Flip-Flop Simulator!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option! Please choose A, B, C, D, or E.");
            }
        }
    }
}
