public class HangManDemo{
public static void main(String[] args) {
  /*
  Program structure:

  I think using a different method for each of these is the way to go.

  Possibly each method will require submethods to break up any complex problems

  1) Load files

      Upon running program, the .txt files containing words and deifnitions should be loaded

      Things to fix with words/definitions:
                                            Save as .csv file containing both elements
                                            Edit out letters excluding a (as those are in the file)

      Read in .csv as 2d array to be used

  2) Initialize variables that want to be saved across each game of hangman:

        int gameCount = 0     : keep track of how many games user has played so far

        int[] wordIndex   : keep track of what words have been selected in prior games based on their row index in 2d array

        int wordIndexCount = 0: keep track of position in above Index across games

        int wins = 0    : games won


  3) Greet player

    Tell player what the game is

    (Optional??) Prompt user if they want to hear the rules of game

                            should use an if statement to only prompt this question when gameCount = 0

                            perhaps above isn't needed if we contain the game itself in while loop that skips over these initial stages
  ------------------------------------------------
Loop everything from here on (the "game loop"): end of this loop is marked by getting user input of whether they'd like to play another game or not

4) Initialize important variables specific to each game only:

                                an empty array of type char: make array length = 26 (no more than 26 letter can be selected anyways)
                                charCount = 0;    keep track of what position in above array should be filled by user letter input
                                String word = "";
                                int lives;   player's lives for the current game
 5) Difficulty selection

    Ask player what difficulty setting they want

    4 options: easy, medium, hard, custom

    return an integer equal to player's lives based on their selection (int lives above)


 6) Word selection

    Produce random number that will be row index of 2d array of words and definitions

    Check that the number is not contained in wordIndex:
                                                          If it is in array, rerun to get new number
                                                          Else save word in word variable + store the index in wordIndex
                                                          and increment wordIndexCount

  7) Prompt user to start game

    Use TextIO to ask them to press 'Enter' when ready

----------------------------------------
  Loop the process of loading UI, getting input, and evaluating input ("Input loop"): end of this loop occurs either through user guessing word, or running out of lives

 8) Load the game UI

  First line: left side shows StringBuilder object with "_", 1 for each letter in selected word
              right side shows number of lives remaining
              use printf to format this well

9a) User input: checking letter is unique

      User types in letter

      Check whether the letter is contained within char array initialized in 4:
          if letter is in array, print message to user and allow them to type in new letter  (looping required)
          else add letter to next free index in the array based on charCount
          increment charCount so next unique input can be stored in next postion of array

9b) User input: checking if selected word contains letter

  */



// Loading the game program

// Method 1
String[] words = loadWords();
String[] definitions = loadDefinitions();
int[] wordLength = getWordLength();

// Method 2
int gameCount = 0;
int wins = 0;
boolean[] wordUsed = new boolean[26275];  // perhaps make this a method, so if you reach word cap, can just remake new array of falses, so game cycles rather than quitting (could control by making gamecount divisble by lngth of array)

// Method 3
greet();
rules();

// Setting up and playing the specific game
do{
String lettersUsed = "";
int lives = difficultySelection();
int wordIndex = getWordIndex(wordUsed, gameCount);
String word = words[wordIndex];
StringBuilder hiddenWord = buildHiddenString(word);


// need to print UI which includes hidden word as StringBuilder object

while(lives > 0){
  System.out.printf("%-20s %20s %20s%n", "Word: " + hiddenWord, "Lives remaining: " + lives, "Letters used: " + lettersUsed); // UI for game loaded after each letter has been input
  String letter = getLetter(lettersUsed);  // this whole method based on code in WordHandling.java
  lettersUsed = lettersUsed + letter;
  if(word.contains(letter)){
    for(int i = 0; i < word.length(); i++){
      if(word.substring(i, i+1).equals(letter)){
        hiddenWord = hiddenWord.replace(i, i+1, letter);
        }
      }
      if(hiddenWord.toString().equals(word)){
        System.out.println("You won!");
        wins++;
        break;
    }
  }
  else{
    lives--;
    if(lives == 0){
      System.out.println("You lost.");
    }
  }
}

System.out.printf("The word was %s.%n" , word);
//definition();
gameCount++;
//winrate();
} while(playAgain());









}
// Method 1

// this is good
public static String[] loadWords(){
  String file = "cs11a02_hangman_words.txt";
  String[] words = new String[26275];
  TextIO.readFile(file);
  for(int i = 0; i < 26275; i++){
    words[i] = TextIO.getln();
  }
  TextIO.readStandardInput();
  return words;
}

// this is good
public static String[] loadDefinitions(){
  String file = "cs11a02_hangman_definitions.txt";
  String[] definitions = new String[26275];
  TextIO.readFile(file);
  for(int i = 0; i < 26275; i++){
    definitions[i] = TextIO.getln();
  }
  TextIO.readStandardInput();
  return definitions;
}
// this is good (but should probably remove, as we don't need it)
public static int[] getWordLength(){
  String file = "cs11a02_hangman_word_lengths.txt";
  int[] wordLengths = new int[26275];
  TextIO.readFile(file);
  for(int i = 0; i < 26275; i++){
    wordLengths[i] = TextIO.getlnInt();
  }
  TextIO.readStandardInput();
  return wordLengths;
}

// Method 3

// this is good
public static void greet(){
  System.out.println();
  System.out.println("Hello. This is the game of hangman with over 26,000 words to play with!");
}

// this is good
public static void rules(){
  System.out.println();
  System.out.println("Would you like to here the rules?");
  System.out.println();
  System.out.print("Enter 'yes' or 'no': ");
  boolean seeRules = TextIO.getlnBoolean();
  System.out.println();
  if(seeRules == true){
    System.out.println();
    System.out.println("A word will be randomly chosen and its length will be displayed.");
    System.out.println();
    System.out.println("You will enter a letter you think is in the word.");
    System.out.println();
    System.out.println("If you choose a letter in the word, that will be displayed.");
    System.out.println();
    System.out.println("If you choose incorrectly, you'll lose a life.");
    System.out.println();
    System.out.println("To win, correctly guess all the letters in the word before you run out of lives.");
    System.out.println();
    System.out.println("Run out of lives and it's game over.");
    System.out.println();
    System.out.println("Ready to play?");
  }
  else{}
}
// this is good (maybe incorporate word length as a factor in difficulty design)
public static int difficultySelection(){
  int lives = 0;
  System.out.println();
  System.out.println("Select a difficulty entering 1-4. Your choices are:");
  System.out.println();
  System.out.println("1. You're an innocent tenderfoot. (9 lives)");
  System.out.println("2. You got a bad lawyer. (6 lives)");
  System.out.println("3. Cripplingly hard. (3 lives)");
  System.out.println("4. Be your own jury. (custom)");
  System.out.println();
  while(lives == 0){
  int selection = TextIO.getlnInt();
  switch(selection){
    case(1): lives = 9; break;
    case(2): lives = 6; break;
    case(3): lives = 3; break;
    case(4): lives = customDifficulty(); break;
    default: System.out.println("Please enter a number between 1 and 4 to choose your difficulty."); break;
  }
}
return lives;
}
// this is good
public static int customDifficulty(){
  System.out.println();
  System.out.println("Well well. How many lives do you wanna have?");
  int customLives = TextIO.getlnInt();
  System.out.println();
  return customLives;
}

// Method 6: Word Selection. once you get number, want to check if it's already set to true. if it is rerun to get another number until you get one that is still set to false

// think about how we could design this so that for a given difficulty a certain range of word lengths are required
// like easy = words between 3-5, med = 6-10, hard = 11+, and custom is everything
// can probably just pass lives as a parameter to the method
// then use a switch somehow

// partial solution in ContinueTest.java file. Shows how to check if word is appropriate length for a given difficulty

// also consider putting gameCount if else as first thing done, since if gamecount is maxed out, then there's no point
// generating any random number in first place, so move it first as an if, then everything else is done otherwise.

public static int getWordIndex(boolean[] wordUsed, int gameCount){
  while(true){
    int randomIndex = (int)(Math.random()*26275);
    if(wordUsed[randomIndex] == false){
      wordUsed[randomIndex] = true;
      return randomIndex;
    }
    else if(gameCount == wordUsed.length){
    System.out.println("Warning: All words have been used.");
    System.out.println("Game terminating. Please rerun if you want to play more.");
    System.exit(0);
  }
}
}

// building hidden word that will be modified by correct user input
public static StringBuilder buildHiddenString(String word){
  String hiddenWordString = "";
  for(int i = 0; i < word.length(); i++){
    hiddenWordString = hiddenWordString + "*";
  }
  StringBuilder hiddenWordStringBuilder = new StringBuilder(hiddenWordString);
  return hiddenWordStringBuilder;
}

// get each letter input by User

public static String getLetter(String lettersUsed){
  while(true){
    String letter = inputChar();
    if (isLetter(letter) && isUnique(letter, lettersUsed)){
      return letter;
    }
    else {
      continue;
    }
  }
}

// part of getLetter
public static String inputChar(){
  System.out.println();
  System.out.print("Enter a letter: ");
  char input = TextIO.getlnChar();
    input = Character.toLowerCase(input);
    String letter = "" + input;
    return letter;
  }

// part of getLetter
  public static boolean isLetter(String letter){
    boolean isLetter;
    if(letter.matches("[a-zA-Z]")){
      isLetter = true;
    }
    else{
      System.out.println();
      System.out.println("Please enter letters only.");
      isLetter = false;
    }
    return isLetter;
  }

// part of getLetter
    public static boolean isUnique(String letter, String lettersUsed){
      boolean isUnique;
      if(lettersUsed.contains(letter)) {
        isUnique = false;
        System.out.println();
        System.out.println("You already used that letter!");
        return isUnique;
      }
      else{
        isUnique = true;
        return isUnique;
      }
    }

// this is good
public static boolean playAgain(){
  boolean newGame;
  System.out.println();
  System.out.println("The game's over! Want to play again?");
  System.out.print("Enter 'yes' or 'no' ");
  newGame = TextIO.getlnBoolean();
  return newGame;



}

}
