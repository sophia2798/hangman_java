import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    static Scanner scan = new Scanner(System.in);
    public static int numCorrect = 0;
    public static void main(String[] args) {

        String hiddenWord = getRandomWord(words);
        int missedGuesses = 0;
        char userInput = '!';
        char[] missed = {};
        char [] wordArr = new char[hiddenWord.length()];
        for (int i=0; i<hiddenWord.length(); i++) {
            wordArr[i] = '_';
        }

        printDefault(gallows, wordArr, missed, 0);

        while (missedGuesses < 7) {
            System.out.print("\n\nGuess:\t");
            userInput = scan.next().charAt(0);

            if (hiddenWord.indexOf(userInput) != -1) {
                wordArr = updateWordArr(hiddenWord, userInput, wordArr);
            } else {
                missed = updateMissedArr(missed, userInput);
                missedGuesses++;
            }

            printDefault(gallows, wordArr, missed, missedGuesses);

            if (numCorrect == hiddenWord.length()) {
                System.out.println("\n\nCongrats! You got it right!");
                break;
            } else if (missedGuesses > 5) {
                System.out.println("\n\nYou dumb as fuck.");
                break;
            }
        }

        scan.close();
    }

    /**
     * Function name: getRandomWord
     * @param wordsArray String[]
     * @return word String
     * 
     * Inside function:
     * - choose a random work from an array of test words to guess in hangman
     */
    public static String getRandomWord(String wordsArray[]) {
        int randomIdx = (int) (Math.random() * wordsArray.length);
        
        return wordsArray[randomIdx];
    }

    /**
     * Function name: printMisses
     * @param missedArr char[]
     * 
     * Inside function:
     * - takes in an array of missed/incorrect guesses
     * - prints them in the terminal to show the player what incorrect guesses they have already made
     */
    public static void printMisses(char missedArr[]) {
        System.out.print("\n\nMissed:\t");

        for (int i=0; i<missedArr.length; i++) {
            System.out.print(missedArr[i] + " ");
        }
    }

    /**
     * Function name: printWord
     * @param wordArr char[]
     * 
     * Inside function:
     * - takes in an array of correctly guessed and blank spaces of the correct word
     * - prints the correct guesses and "_"'s to the terminal
     */
    public static void printWord(char wordArr[]) {
        System.out.print("\n\nWord:\t");

        for (int i=0; i<wordArr.length; i++) {
            System.out.print(wordArr[i] + " ");
        }
    }

    /**
     * Function name: printDefault
     * @param gallows String
     * @param wordArr char[]
     * @param missedArr char[]
     * @param gallowsIdx int
     * 
     * Inside function:
     * - takes in the gallows string array, words array, missed guesses array, and an index valie
     * - prints out the default game screen at every turn 
     */
    public static void printDefault(String gallows[], char wordArr[], char missedArr[], int gallowsIdx) {
        System.out.println(gallows[gallowsIdx]);
        printWord(wordArr);
        printMisses(missedArr);
    }

    /**
     * Functon name: updateMissedArr
     * @param oldArray char[]
     * @param miss char
     * @return newArr char[]
     * 
     * Inside function:
     * - creates a new array of length n+1 (where n is length of old array) with new incorrect user guess
     * - returns that new array
     */
    public static char[] updateMissedArr(char oldArray[], char miss) {
        char newArr[] = Arrays.copyOf(oldArray, oldArray.length + 1);
        newArr[newArr.length - 1] = miss;

        return newArr;
    }

    /**
     * Function name: updateWordArr
     * @param word String
     * @param guess char
     * @param oldArr char[]
     * @return oldArr (updated) char[]
     */
    public static char[] updateWordArr(String word, char guess, char oldArr[]) {

        for (int i=0; i<word.length(); i++) {
            if (word.charAt(i) == guess) {
                oldArr[i] = guess;
                numCorrect++;
            }
        }

        return oldArr;
    }
}