import java.util.Scanner;

public class DiceProbabilityCalculator {

    private static final double SUCCESS_CHANCE = 0.3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Cresting Lilypads - Dice Probability Calculator ===");
            System.out.println("1. Uncontested Roll");
            System.out.println("2. Clash Roll");
            System.out.println("3. Exit");
            System.out.print("Select mode: ");

            int mode = scanner.nextInt();

            switch (mode) {
                case 1:
                    uncontestedMode(scanner);
                    break;
                case 2:
                    clashMode(scanner);
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid mode.");
            }
        }
    }

    private static void uncontestedMode(Scanner scanner) {
        System.out.print("\nEnter number of dice: ");
        int diceCount = scanner.nextInt();

        if (diceCount <= 0) {
            System.out.println("Invalid dice.");
            return;
        }

        double winChance = 0.0;
        double critChance = 0.0;

        for (int successes = 0; successes <= diceCount; successes++) {
            double probability = binomialProbability(diceCount, successes);

            if (successes >= 1) {
                winChance += probability;
            }

            if (successes >= 2) {
                critChance += probability;
            }
        }

        System.out.println("\n=== " + diceCount + " dice check ===");
        System.out.printf("Win Chance: %.2f%%%n", winChance * 100);
        System.out.printf("Crit Win Chance: %.2f%%%n", critChance * 100);
    }

    private static void clashMode(Scanner scanner) {
        System.out.print("\nEnter your number of dice: ");
        int playerDice = scanner.nextInt();

        if (playerDice <= 0) {
            System.out.println("Invalid dice.");
            return;
        }

        System.out.println("Choose clash type:");
        System.out.println("1. Offensive");
        System.out.println("2. Defensive");
        System.out.print("Select type: ");
        int clashType = scanner.nextInt();

        boolean defensive;
        switch (clashType) {
            case 1:
                defensive = false;
                break;
            case 2:
                defensive = true;
                break;
            default:
                System.out.println("Invalid clash type.");
                return;
        }

        System.out.print("Enter enemy dice count OR range start: ");
        int enemyStart = scanner.nextInt();

        System.out.print("Enter enemy dice count OR range end (same number for single): ");
        int enemyEnd = scanner.nextInt();

        if (enemyEnd < enemyStart || enemyEnd <= 0 || enemyStart <= 0) {
            System.out.println("Invalid range.");
            return;
        }

        System.out.println("\n\nYour dice: " + playerDice);
        System.out.println("==============================================");
        System.out.println("Enemy Dice\tWin Chance\tCrit Win Chance");
        System.out.println("==============================================");

        for (int enemyDice = enemyStart; enemyDice <= enemyEnd; enemyDice++) {
            double winChance = 0.0;
            double critChance = 0.0;

            for (int playerSuccesses = 0; playerSuccesses <= playerDice; playerSuccesses++) {
                double playerProb = binomialProbability(playerDice, playerSuccesses);

                for (int enemySuccesses = 0; enemySuccesses <= enemyDice; enemySuccesses++) {
                    double enemyProb = binomialProbability(enemyDice, enemySuccesses);

                    double combinedProb = playerProb * enemyProb;

                    boolean win;
                    if (defensive) {
                        win = playerSuccesses >= enemySuccesses + 1;
                    } else {
                        win = playerSuccesses >= enemySuccesses;
                    }

                    win = win && playerSuccesses >= 1;

                    boolean crit = playerSuccesses >= enemySuccesses + 2;

                    if (win) {
                        winChance += combinedProb;
                    }

                    if (crit) {
                        critChance += combinedProb;
                    }
                }
            }

            System.out.printf(
                    "%d dice\t\t%.2f%%\t\t%.2f%%%n",
                    enemyDice,
                    winChance * 100,
                    critChance * 100
            );
        }

        System.out.println("==============================================");
    }


    // Binomial Probability
    private static double binomialProbability(int dice, int successes) {
        return combination(dice, successes)
                * Math.pow(SUCCESS_CHANCE, successes)
                * Math.pow(1 - SUCCESS_CHANCE, dice - successes);
    }

    // Combination nCr
    private static long combination(int n, int r) {
        if (r > n || r < 0) return 0;
        if (r == 0 || r == n) return 1;

        r = Math.min(r, n - r);

        long result = 1;
        for (int i = 0; i < r; i++) {
            result *= (n - i);
            result /= (i + 1);
        }

        return result;
    }
}