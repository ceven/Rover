package covata.rovers;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		File file = null;
		if (args != null && args.length > 0) {
			file = new File(args[0]);
		}
		if (!FileUtilities.isValidFile(file)) {
			file = FileUtilities.loadFile(false);
		}
		String input = FileUtilities.readFile(file);
		NASAControl exp = new NASAControl(input);
		System.out.println("Input:\n" + input);
		System.out.println("Output:\n" + exp.getOutputStr().toString());
	}
}
