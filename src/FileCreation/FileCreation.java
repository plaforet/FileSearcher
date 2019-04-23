package FileCreation;

public class FileCreation {
	public static void wordList(File list, ArrayList<String> wordsList) throws FileNotFoundException {
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
}
