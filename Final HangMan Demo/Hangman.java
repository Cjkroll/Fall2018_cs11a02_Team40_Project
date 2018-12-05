/**
@author Caleb Kroll
@author Vy Tran
@author Qian Sun
@author Tiana Murrieta

Play hangman!
*/

public class Hangman{

  /**
  Keeps tracks of total games played during current session of Hangman.
  */
  public static int gameCount = 0;

  /**
  Keeps tracks of total wins during current Hangman session.
  */
  public static int wins = 0;

  /**
  Keeps tracks of which words have been used in current Hangman session to avoid repeats.
  */
  public static boolean[] wordUsed = new boolean[26275]; // 26275 is the number of words in text file used here
  public static void main(String[] args) {


    // Loading the game program
    String[] words = HangmanSetup.loadFile("cs11a02_hangman_words.txt", 26275);
    String[] definitions = HangmanSetup.loadFile("cs11a02_hangman_definitions.txt", 26275);  // optional; not essential to playing game.

    // giving user the rundown of the program
    HangmanSetup.greetUser();
    HangmanSetup.promptRules();

    // Setting up and playing the specific game
    do{
      String lettersUsed = "";
      int lives = PlayHangman.difficultySelection();
      int wordIndex = PlayHangman.getWordIndex(26275);
      String word = words[wordIndex];
      StringBuilder hiddenWord = PlayHangman.buildHiddenString(word);

      while(lives > 0){
        System.out.println();
        PlayHangman.printUI(hiddenWord, lives, lettersUsed); // UI for game loaded after each letter has been input
        String letter = PlayHangman.getLetter(hiddenWord, lives, lettersUsed);  // this whole method based on code in WordHandling.java
        lettersUsed = lettersUsed + letter ;
        if(word.contains(letter)){                            // if input letter is in word, the hidden word is changed to reveal the input letter
          for(int i = 0; i < word.length(); i++){
            if(word.substring(i, i+1).equals(letter)){
              hiddenWord = hiddenWord.replace(i, i+1, letter);
            }
          }
          if(hiddenWord.toString().equals(word)){
            System.out.println();
            System.out.println("You won!");                   // once hidden word matches the word, game ends
            wins++;
            break;
          }
        }
        else{
          lives--;                  // alternatively, game ends when you run out of lives
          if(lives == 0){
            System.out.println();
            System.out.println("You lost.");
            System.out.println();
            System.out.printf("The word was %s.%n" , word);    // reveal word to user
          }
        }
      }

      gameCount++;
      System.out.println();
      System.out.printf("The definition of %s is:%n" , word);
      System.out.println();
      System.out.println(definitions[wordIndex]);

    } while(playAgain());   // game plays until user decides to end it.

    System.out.println();
    System.out.printf("You played %d game(s) in total and you've won %d time(s)!%n", gameCount,wins);  // showing some stats for user
    System.out.println();
    System.out.printf("You've won %.1f%% of games!%n", (double)wins/gameCount*100); // winrate
    System.out.println();
    System.out.println("These are all the words you encountered:");
    System.out.println();
    for(int i = 0; i < words.length; i++){    // printing out all words that were used in this session of hangman
      if(wordUsed[i] == true){
        System.out.println(words[i]);
      }
    }

    System.out.println();
    System.out.println("Bye!");
    System.out.println();
  } // program ends!

  /**
  Method allows user to either play a new game of hangman or to end the program.

  @return boolean TRUE if user wants to play again.
  */

  public static boolean playAgain(){
    boolean newGame;
    System.out.println();
    System.out.println("The game's over! Want to play again?");
    System.out.print("Enter 'yes' or 'no': ");
    newGame = TextIO.getlnBoolean();
    return newGame;
  }
}
