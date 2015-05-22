package covata.rovers;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		File file = null;
		if (args != null && args.length > 0) {
			file = new File(args[0]);
		}
		if (!Utilities.isValidFile(file)) {
			file = Utilities.loadFile(false);
		}
		Experiment exp = new Experiment(file);
		System.out.println("Input file: " + exp.getInputFilePath());
		System.out.println("Input:\n" + exp.getInputFile().toSimpleString());
		System.out.println("Output:\n" + exp.getOutput().toString());
	}
}
