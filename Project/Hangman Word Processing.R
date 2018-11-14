# author: Caleb Kroll

# date: 5 Nov 2018


# This program is creating a dataframe containing a column of words

# and a column of definitions corresponding to those words.

# This is to be used as a database of words for a potential hangman program.

# The rationale for doing this is I want each word used in the hangman game to also be defined for the user

# (they can learn and have fun!).

# Formatting the data as described above will make keeping track much easier.

# For example, if both the file of words and the file of definitions are loaded into separate arrays in java,

# then whatever index is used to retrieve a word from the word array can also retrieve its corresponding

# definition from the definition array.

# This versatility for an index is possible specifically because of the formatting done in R!



# set wd where all final files will be saved

setwd(dir = "~/Desktop/cs11a02/Project/")

# read in .txt file containing all words from Oxford English Dictionary

# at https://sites.google.com/a/vhhscougars.org/johnsearch/searchindex/oxford-english-dictionary

test_txt <- readLines("~/Downloads/Oxford_English_Dictionary.txt")

# convert to data frame

test_txt <- as.data.frame(test_txt)

# convert elements from data type factor to character

test_txt$test_txt <- as.character(test_txt$test_txt)

# rename column

colnames(test_txt) <- "definition"

# add new column to df containing first word (the word that is being defined)

test_txt$words <- sub("([A-Za-z]+).*", "\\1", test_txt$definition)

# convert elements of words column to lowercase

 test_txt$words <- tolower(test_txt$words)

# load in .txt file containing all alphabetical english words
 
 # taken from this GitHub repository: https://github.com/dwyl/english-words
 
all_words <- as.data.frame(readLines("~/Desktop/cs11a02/GitHub/english-words/words_alpha.txt"))


# change column name

colnames(all_words) <- "words"

# combine data frame with words only with dataframe of definitions based on matches between columns named 'words'

# essentially filtering out words among the ~370k found in the GitHub .txt file to only the ~ 26,500 words which have a 

# definition within the oxford english dictionaruy .txt file used


merger <- merge.data.frame(test_txt, all_words)

# quick-and-dirty removal of any quasi-words that may occcur multiple times (like 'ad',  and 'a')

merger <- merger[!duplicated(merger$words), ]


# add new column to merger which shows the number of characters in the word found in each row

merger$word_length <- nchar(as.character(merger$words))


# remove all rows which contain words with 2 or fewer characters

test <- merger[merger$word_length > 2, ]


# confirming shortest words now are 3 characters long

min(test$word_length)

#########################################################################################################

# exporting full dataframe 

write.csv(test, file = "cs11a02_hangman_data_with_word_lengths", quote = FALSE, row.names = FALSE, col.names = FALSE)



##################################################################################################

# extracting all words into their own dataframe

words <- test$words

words <- as.data.frame(words, stringsAsFactors = FALSE)

# saving the 'list' of words into their own .txt file

write.table(words, file = "cs11a02_hangman_words.txt", quote = FALSE, row.names = FALSE, col.names = FALSE)

# extracting all definitions into their own dataframe

definitions <- test$definition

definitions <- as.data.frame(definitions, stringAsFactors = FALSE)

# saving the 'list' of definitions into their own .txt file

write.table(definitions, file = "cs11a02_hangman_definitions.txt", quote = FALSE, row.names = FALSE)

# saving this workspace

save.image(file = "hangman_words.RData")

# Done!