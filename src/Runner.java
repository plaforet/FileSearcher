import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Runner {
	static ArrayList<String> wordsList = new ArrayList<String>();
	static Random rand = new Random();
	static int listLength;

	public static void wordList(File list) throws FileNotFoundException {
		Scanner words = new Scanner(list);
		while (words.hasNextLine()) {
			wordsList.add(words.nextLine());
		}
		words.close();

	}

	public static void makeFile(File folder) throws IOException {
		int r = rand.nextInt(wordsList.size());
		File temp = new File(folder, wordsList.get(r) + ".txt");
		temp.createNewFile();
	}

	public static void writeToFile(File file, String path) {
		try {
			PrintWriter outputStream = new PrintWriter(path);
			String text = "";
			for (int i = 0; i < rand.nextInt(50); i++) {
				text += wordsList.get(rand.nextInt(listLength)) + " ";
			}
			outputStream.println(text);
			outputStream.close();
		} catch (FileNotFoundException e) {

		}
	}

	public static void fillFiles(File folder) throws IOException {
		File[] fileList;
		fileList = folder.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				fillFiles(fileList[i]);
			}

			else {
				writeToFile(fileList[i], fileList[i].getPath());
			}
		}

	}

	public static void fillFolders(File folder) throws IOException {
		File[] fileList;
		fileList = folder.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				makeFile(fileList[i]);
				fillFolders(fileList[i]);
			}
		}

	}

	public static String wordsFromFile(File f)
			throws FileNotFoundException {
		String text = "";
		Scanner fileContents = new Scanner(f);
		while (fileContents.hasNextLine()) {
			text += fileContents.nextLine();
		}
		fileContents.close();
		return text;
	}

	public static void usedWords(File folder, PrintWriter outputStream)
			throws FileNotFoundException {
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				usedWords(files[i], outputStream);
			}
			else {
				String text = wordsFromFile(files[i]);
				outputStream.println(text);
			}
		}

	}
	
	
	// Example of student code
	public static void listDirectory(File directory) {
		File[] paths = directory.listFiles();
		for (File path : paths) {
			if (path.isDirectory()) {
				listDirectory(path);
			}
			else {
				System.out.println(path);
			}
		}
	}

	public static int countOccurrences(String text, String searchWord) {
		int wordLength = searchWord.length();
		int index = text.indexOf(searchWord);
		if (index == -1) {
			return 0;
		}
		if (index == text.length() - wordLength) {
			return 1;
		}
		String newText = text.substring(index + wordLength);
		return 1 + countOccurrences(newText, searchWord);
	}

	public static void searchDirectory(File directory, String searchWord) {
		File[] paths = directory.listFiles();
		for (File path : paths) {
			if (path.isDirectory()) {
				listDirectory(path);
			}
			else {
				String text = SearchHelper.readFile(path);
				int count = countOccurrences(text, searchWord);
				if (count > 0) {
					System.out.println(path + ": " + count);
				}
			}
		}
	}
	
	

	public static void main(String[] args) throws IOException {
		wordList(new File("word list.txt"));
		listLength = wordsList.size();
		// fillFolders(new File("Files"));
		// fillFiles(new File("Files"));
		File root = new File("files/");
		File usedWordsFile = new File("usedWords.txt");
		PrintWriter outputStream = new PrintWriter(usedWordsFile);
		usedWords(root, outputStream);
		outputStream.close();
		System.out.println(searchFile(new File("test.txt"), "bike"));
	}

}
