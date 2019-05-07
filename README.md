# FileSearcher
This is support code for the Recursion Project we created for our AP CS A course. For more info on the project, see the [Recursive Search Project.pdf](https://github.com/plaforet/FileSearcher/blob/master/Recursive%20Search%20Project.pdf "Named link title") file in the root folder. These are the methods I wrote to populate the folders with text files and add random words into those files.

Some things of note:
* wordList method fills the wordsList ArrayList with the words from the words list.txt file
* makeFile makes the text files with random words for the names
* writeToFile fills those text files with between 1 and 50 random words
* SearchHelper.java is provided to students for Part 3. The readFile method takes a file and outputs a String they can pass into countOccurrences.
* listDirectory, countOccurrences, and searchDirectory are example solutions to Parts 1, 2, & 3 respectively.
