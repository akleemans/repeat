import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Simulate a key press

/*
robot.keyPress(KeyEvent.VK_A);
robot.keyRelease(KeyEvent.VK_A);
*/

// Get pixel colour

/*
try {
    Robot robot = new Robot();

    //
    // The the pixel color information at 20, 20
    //
    Color color = robot.getPixelColor(20, 20);

    //
    // Print the RGB information of the pixel color
    //
    System.out.println("Red   = " + color.getRed());
    System.out.println("Green = " + color.getGreen());
    System.out.println("Blue  = " + color.getBlue());

} catch (AWTException e) {
    e.printStackTrace();
}
*/

public class Main {

	static Robot robot;
	
	public static void main(String[] args) {
		
		// Check if call is valid
		if (args.length != 2) {
			System.out.println("Please provide 2 arguments: The script and how many times to repeat.");
			return;
		}
		
		// maxLoops
		// TODO throw exception if not a number
		int maxLoops = Integer.valueOf(args[1]);
		
		// Read file
		ArrayList<String> program = new ArrayList<String>();
		
		try {
			FileInputStream fstream = new FileInputStream(args[0]);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				strLine = strLine.split("#")[0];
				program.add(strLine);
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			return;
		}

		// Remove emtpy lines
		for (int i = 0; i < program.size(); i++) {
			if (program.get(i).length() < 3) {
				program.remove(i);
				i--;
			}
		}
		
		System.out.println("Provided Script:");
		System.out.println("=================================");
		for (int i = 0; i < program.size(); i++) {
			System.out.println("> "+program.get(i));
		}
		System.out.println("=================================");
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
		}

		int loops = 0;
		int pc = -1;
		String[] command;
		//String[] arg = new String[3];
		
		while (loops < maxLoops*program.size()) {
			loops++;
			
			pc = (pc+1)%program.size();
			
			command = program.get(pc).replace(",", "").split(" ");
			System.out.println("Command: "+command[0]+", pc = "+pc);
			
			// Do commands
			if (command[0].equals("click")) {
				click(Integer.valueOf(command[1]), Integer.valueOf(command[2]));
			}
			else if (command[0].equals("doubleclick")) {
				click(Integer.valueOf(command[1]), Integer.valueOf(command[2]));
				click(Integer.valueOf(command[1]), Integer.valueOf(command[2]));
			}
			else if (command[0].equals("wait")) {
				wait(Double.valueOf(command[1]));
			}
		}
	}
	
	private static void click(int posX, int posY) {
		robot.mouseMove(posX, posY);

		// Simulate click
		robot.mousePress(InputEvent.BUTTON1_MASK);
		wait(0.1);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	private static void wait(double time) {
		try {
			//Thread.currentThread();
			Thread.sleep((long)(time*1000));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
