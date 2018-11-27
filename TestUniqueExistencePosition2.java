import java.util.Arrays;
public class TestUniqueExistencePosition2 {

  public static void main(String args[]) {

//introduce the string array and select a word from it randomly
    String[] fruits = {
    "apple","apricot","banana","blackberry","blueberry","cherry","cranberry","currant","fig","grape","grapefruit","grapes","kiwi","kumquat","lemon","lime","melon","nectarine","orange","peach","pear","persimmon","pineapple"
    ,"plum","pomegranate","prune","raspberry","strawberry","tangerine","watermelon"};
    String word = fruits[(int)Math.random() * fruits.length];

    String[] letters = new String[word.length()];
	    for(int i = 0; i < word.length(); i++)
	    {letters[i] = String.valueOf(word.charAt(i));}

//for each corresponding word length, set the corresponding questionmarks array, and print out
    int length=word.length();
    String print;
    if(length==3){
      String[] blanks = new String[] {"\u003F","\u003F","\u003F"};
      print=Arrays.toString(blanks);

    }
    else if(length==4){
      String[] blanks = new String[] {"\u003F","\u003F","\u003F","\u003F"};
      print=Arrays.toString(blanks);

    }
    else if(length==5){
      String[] blanks = new String[] {"\u003F","\u003F","\u003F","\u003F","\u003F"};
      print=Arrays.toString(blanks);

    }
    else if(length==6){
      String[] blanks = new String[] {"\u003F","\u003F","\u003F","\u003F","\u003F","\u003F"};
      print=Arrays.toString(blanks);

    }
    else if(length==7){
      String[] blanks = new String[] {"\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F"};
      print=Arrays.toString(blanks);

    }
    else if(length==8){
      String[] blanks = new String[] {"\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F"};
      print=Arrays.toString(blanks);

    }
    else if(length==9){
      String[] blanks = new String[] {"\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F"};
      print=Arrays.toString(blanks);

    }
    else if(length==10){
      String[] blanks = new String[] {"\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F"};
      print=Arrays.toString(blanks);

    }
    else{
      String[] blanks = new String[] {"\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F","\u003F"};
      print=Arrays.toString(blanks);

    }
    System.out.println(print);
    char[] questionmarks=print.toCharArray();
//ask user for chance
    System.out.println("Please enter difficulty level, which is the number of chances you would like to have:");
    int chance=TextIO.getlnInt();

//game begins, within while loop
    while(chance > 0){
      System.out.println("Enter your letter: ");
      char inputletter= TextIO.getlnChar();

//if the letter is being used, prompt user for another input
   String lettersUsed="";
   if (enteryet(lettersUsed, inputletter)) {
     System.out.println("You have guessed this letter already, try another one!");
     continue;
   } else
//first store input
  {lettersUsed = lettersUsed + inputletter;
//if it's included in word array
//first tells user how many times it is in the word
   if (existence(word, inputletter)) {
   int[] pos= position(word, inputletter);
   System.out.printf("Yes! the letter %c appears %d times in the word.%n", inputletter, pos.length);
//then show user the blanks after replacement
   for (int i = 0; i < length; i++) {
    String inputletterasString = Character.toString(inputletter);
    if (letters[i] == inputletterasString)
       {System.out.print(inputletterasString);  ////need to figure out this part, still doesn't print
        }
    else{System.out.print("\u003F");
        }
    }
    System.out.println();
  chance= chance - 1;
}
//if input is not included in the word
else {
  System.out.printf("Sorry! There is no letter %c in the word.%n", inputletter);
  chance= chance - 1;
 }
}
}//end of whileloop
System.out.println("You lost! You've used out all the chances.");
}//end of public static void




//methods!!
//enteryet
public static boolean enteryet (String lettersUsed, char letter) {
    if(lettersUsed.contains(""+letter)){
      return true;
    }
    else{
      return false;
    }
}
//existence
public static boolean existence(String word, char letter) {
  for (int i=0; i<word.length(); i++) {
    if (word.charAt(i)==letter) {
      return true;
    }
  }
      return false;
}
//position
public static int[] position(String word, char letter) {
  int[] pos= new int[word.length()];
  int count=0;
  for (int i=0; i < word.length(); i++) {
    if (word.charAt(i) == letter) {
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
