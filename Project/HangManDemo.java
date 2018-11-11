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
StringBuilder test = new StringBuilder("Hello");

System.out.println(test);
System.out.println(test.replace(0,1, "C"));
System.out.println(test.toString().contains("e"));

}
}
