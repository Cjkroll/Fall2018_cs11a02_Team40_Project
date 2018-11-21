import java.utils.Arrays;
public class TestUniqueExistencePosition {
  public static void main(String args[]) {
    char[] word= wordArray(words); //char array of the word
    char[] enter= new char[chance]; //create an array for letter already enter, the size depends on the chance of guessing
    if (chance > 0){
      System.out.println("Enter your letter: "); ////enter a letter
      char inputletter= TextIO.getlnchar();

      int time=0; //number of time entering letter
      if (enteryet(enter, inputletter)) {
        System.out.println("You have guessed this letter already, try another one!");
      } else {
        time=time+1;
        if (existence(word, inputletter)) {
          int[] pos= position(word, inputletter);
          System.out.printf("Yes! the letter %c appears %d times in the word.%n", inputletter, pos.length); //print out congrats
          letterReplace(); //appear the letter on the word
          enter[time]=inputletter;//add the letter to enter array
        } else {
          System.out.printf("Sorry! There is no letter %c in the word.%n", inputletter); //apologize
          enter[time]=inputletter; //add to the enter array
          chance= chance - 1; // remove a chance
        }
      }
    } else {
      System.out.println("You lost! You've used out all the chances.");
    }
  }
  //create a method breaking each letter in the word into a char array
  public static char[] wordArray (String a) {
    char[] word= new char[a.length()];
    for (int i=0; i < a.length(); i++) {
    word[i]= a.charAt(i);
    }
    return word;
  }
  //create a method checking whether the letter has been entered
  public static boolean enteryet (char[] array, char letter) {
      int pos = Arrays.binarySearch(array,letter);
      if (pos<0) {
        return false;
      } else {
        return true;
      }
    }

    //create a method checking existence of letter in the word
    public static boolean existence(char[] array, char letter) {
      for (int i=0; i<array.length; i++) {
        if (array[i]==letter) {
          return true;
        }
      }
          return false;
    }

    //create a method forming an array cointaining the position of letter in the word
    public static int[] position(char[] array, char letter) {
      int[] pos= new int[array.length];
      int count=0;
      for (int i=0; i < array.length; i++) {
        if (array[i]==letter) {
          pos[count]=i;
          count=count+1;
        }
      }
        int[] newpos= new int[count];
        for (int i=0; i <newpos.length; i++){
          newpos[i]= pos[i];
        }
      return newpos;
    }
}
  //create an array for letter already enter
  //input the letter
  // check whether the letter has been entered before
    //if yes
      //resubmit another letter
    //if no
      //check whether the letter is inside the word
        //if yes
          //how many times it appears and the positions
          //congrats
          //appear on the box
          //add to the enter array
          //count time of submit
        //if no
          //apologize
          //add to the enter array
          //remove chance of submit
