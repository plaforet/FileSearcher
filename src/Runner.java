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

	// Starter code
	public static boolean searchFile(File f, String s) {
		Scanner fileText;
		try {
			fileText = new Scanner(f);
			while (fileText.hasNextLine()) {
				if (fileText.nextLine().indexOf(s) != -1) {
					fileText.close();
					return true;
				}
			}
			fileText.close();
			return false;
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			return false;
		}

	}

	// Example of student code
	public static boolean searchFolder(File folder, String search) {
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				if (searchFolder(file, search)) {
					return true;
				}
			}
			else {
				if (searchFile(file, search)) {
					return true;
				}
			}
		}
		return false;
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

	public static void main(String[] args) throws IOException {
		wordList(new File("word list.txt"));
		listLength = wordsList.size();
		// fillFolders(new File("Files"));
		// fillFiles(new File("Files"));
		File root = new File("Files/");
		File usedWordsFile = new File("usedWords.txt");
		PrintWriter outputStream = new PrintWriter(usedWordsFile);
		usedWords(root, outputStream);
		outputStream.close();
		System.out.println(searchFile(new File("Files/test.txt"), "bike"));
	}

}
