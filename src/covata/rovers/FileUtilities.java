package covata.rovers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

/**
 * The class {@code FileUtilities} provides methods to read and load files.
 * 
 * @author ceven
 *
 */
public class FileUtilities {

	private final static Logger LOG = Logger.getLogger("FileUtilities");

	public static final String DEFAULT_INPUT_FILE_PATH = "./data/input/supplied_test.txt";

	/**
	 * Load a file. May ask for user input, or load a default file.
	 * 
	 * @param askUserInput
	 *            whether the user should be able to enter a file path manually.
	 * @return a {@code File}. If the user is prompted to enter a file path and
	 *         the path is valid, then this file is returned. Otherwise, a file
	 *         with path {@link #DEFAULT_INPUT_FILE_PATH} is returned.
	 */
	public static File loadFile(boolean askUserInput) {
		if (askUserInput) {
			LOG.info(String
					.format("Please provide an input file path, and type return to continue.\n Note that if you do not provide a valid path, the file %s will be used",
							DEFAULT_INPUT_FILE_PATH));
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			File inputFile;
			try {
				inputFile = new File(reader.readLine());
				if (!inputFile.exists() || !inputFile.isFile()) {
					LOG.warning(String
							.format("The input file path is invalid ; using %s instead",
									DEFAULT_INPUT_FILE_PATH));
					inputFile = new File(DEFAULT_INPUT_FILE_PATH);
				}
			} catch (IOException e) {
				LOG.warning("Unable to read user input ; returning null");
				return null;
			}
			return inputFile;
		}
		return new File(DEFAULT_INPUT_FILE_PATH);
	}

	/**
	 * Reads a file and converts its content into a string, where each line is
	 * separated by \n.
	 * 
	 * @param f
	 *            the file to be read
	 * @return a string containing the input information, where each line of the
	 *         input is separated by \n
	 * @throws IllegalArgumentException
	 *             if the file is determined as invalid by the method
	 *             {@link #isValidFile(File)} or if a syntax error is
	 *             encountered
	 */
	public static String readFile(File f) {
		// A list of all string lines in this file
		if (isValidFile(f)) {
			List<String> lines;
			try {
				lines = Files.readAllLines(Paths.get(f.getPath()));
				return String.join("\n", lines);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		throw new IllegalArgumentException(
				"FileUtilities.readFile: The input file is invalid: " + f);
	}

	/**
	 * Checks if a file in parameter is valid. A file is valid if it is not
	 * null, exists and is a normal file.
	 * 
	 * @param f
	 *            the files to check
	 * @return true if f is not null, exists and is a file, false otherwise
	 */
	public static boolean isValidFile(File f) {
		return f != null && f.exists() && f.isFile();
	}

}
