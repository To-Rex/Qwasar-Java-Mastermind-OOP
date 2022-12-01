import java.util.Scanner;

public class Main {

    /*Mastermind is a game composed of 8 pieces of different colors.
    A secret code is then composed of 4 distinct pieces.

    The player has 10 attempts to find the secret code.
    After each input, the game indicates to the player the number of well placed pieces and the number of misplaced pieces.

    Pieces will be '0' '1' '2' '3' '4' '5' '6' '7'.

    If the player finds the code, they win, and the game stops.
    A misplaced piece is a piece that is present in the secret code but is not in a good position.

    You must read the player's input from the standard input.

    Your program will also receive the following parameters:
            -c [CODE]: specifies the secret code. If no code is specified, a random code will be generated.
            -t [ATTEMPTS]: specifies the number of attempts; by default, the player has 10 attempts.*/

//    PROMPT>java my_mastermind -c "0123"
//    Will you find the secret code?
//            ---
//    Round 0
//            >1456
//    Well placed pieces: 0
//    Misplaced pieces: 1
//            ---
//    Round 1
//            >tata
//    Wrong input!
//            >4132
//    Well placed pieces: 1
//    Misplaced pieces: 2
//            ---
//    Round 2
//            >0123
//    Congrats! You did it!


    public static String generateCode() {
        String code = "";
        for (int i = 0; i < 4; i++) {
            code += (int) (Math.random() * 8);
        }
        return code;
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static boolean isInputValid(String input) {
        if (input.length() != 4) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (input.charAt(i) < '0' || input.charAt(i) > '7') {
                return false;
            }
        }
        return true;
    }

    public static int[] getWellPlacedAndMisplacedPieces(String input, String code) {
        int[] result = new int[2];
        for (int i = 0; i < 4; i++) {
            if (input.charAt(i) == code.charAt(i)) {
                result[0]++;
            } else if (code.contains(input.charAt(i) + "")) {
                result[1]++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String code = "";
        int attempts = 10;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-c")) {
                code = args[i + 1];
            } else if (args[i].equals("-t")) {
                attempts = Integer.parseInt(args[i + 1]);
            }
        }
        if (code.equals("")) {
            code = generateCode();
        }
        System.out.println("Will you find the secret code?");
        System.out.println("---"+ code);
        for (int i = 0; i < attempts; i++) {
            System.out.println("Round " + i);
            String input = readInput();
            if (!isInputValid(input)) {
                System.out.println("Wrong input!");
                i--;
                continue;
            }
            int[] result = getWellPlacedAndMisplacedPieces(input, code);
            System.out.println("Well placed pieces: " + result[0]);
            System.out.println("Misplaced pieces: " + result[1]);
            System.out.println("---");
            if (result[0] == 4) {
                System.out.println("Congrats! You did it!");
                break;
            }

            if (i == attempts - 1) {
                System.out.println("You lost!");
            }


        }
    }

}