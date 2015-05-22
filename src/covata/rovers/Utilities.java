package covata.rovers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

import covata.rovers.Move.RobotMove;
import covata.rovers.Rover.Direction;

public class Utilities {

	private final static Logger LOG = Logger.getLogger("FileUtilities");

	public static final String DEFAULT_INPUT_FILE_PATH = "./data/input/supplied_test.txt";

	/**
	 * Load a file. May ask for user input, or load a default file.
	 * 
	 * @param askUserInput
	 *            whether the user should be able to enter a file path manually
	 * @return a File, either from the user entered path or a default file.
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

	public static InputFile readFile(File f) {
		if (isValidFile(f)) {
			Position urCorner = null;
			LinkedHashMap<Rover, List<RobotMove>> rovers = new LinkedHashMap<Rover, List<Move.RobotMove>>();
			if (f != null && f.exists() && f.isFile()) {
				try {
					BufferedReader bf = new BufferedReader(new FileReader(f));
					// Get the UR corner pos
					String line = bf.readLine();
					String[] lineSplit = line.split("\\s+");
					urCorner = new Position(Integer.parseInt(lineSplit[0]),
							Integer.parseInt(lineSplit[1]));
					line = bf.readLine();
					// Get all rovers pos and moves
					Position roverPos = null;
					Direction dir = null;
					List<Move.RobotMove> moves = null;
					while (line != null) {
						if (roverPos == null) {
							lineSplit = line.split("\\s+");
							roverPos = new Position(
									Integer.parseInt(lineSplit[0]),
									Integer.parseInt(lineSplit[1]));
							dir = Direction.getDirection(lineSplit[2]);
						} else if (moves == null) {
							lineSplit = line.split("");
							moves = new ArrayList<Move.RobotMove>();
							for (String moveStr : lineSplit) {
								moves.add(Move.getMove(moveStr));
							}
						}
						if (roverPos != null && moves != null) {
							Rover rover = new Rover(roverPos, dir);
							rovers.put(rover, moves);
							roverPos = null;
							dir = null;
							moves = null;
						}
						line = bf.readLine();
					}
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return new InputFile(urCorner, rovers);
		}
		throw new IllegalArgumentException(
				"Utilities.readFile: The input file is invalid: " + f);
	}

	/**
	 * 
	 * @param rovers
	 *            a list of {@code Rover}
	 * @return a string with the rover position and direction on each line
	 */
	public static String getOutput(Collection<Rover> rovers) {
		String output = "";
		Iterator<Rover> it = rovers.iterator();
		Rover rover;
		while (it.hasNext()) {
			rover = it.next();
			output += rover.getPosition().getX() + " "
					+ rover.getPosition().getY() + " "
					+ rover.getDirection().toString()
					+ (it.hasNext() ? "\n" : "");
		}
		return output;
	}

	public static boolean isValidFile(File f) {
		return f != null && f.exists() && f.isFile();
	}

}
