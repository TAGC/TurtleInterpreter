package turtle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import turtle.util.*;
import turtle.implementations.*;

public class Main {
	
	private static Scanner input;
	private static Paper paper;
	private static final Map<String, Turtle> turtlemap
	                     = new HashMap<String, Turtle>();
	private static boolean filein, fileout;
	private static String[] currentcommandline;
	private static String outputlocation;
	private static String[] commandlist;
	private static PrintStream output;
	private static String[] turtletypes;
	
    public static void main(String[] args) throws FileNotFoundException {
    	String command;
    	String[] commands;
    	int currentcommand, totalcommands;
    	
    	turtletypes = new String[]{"normal", "continuous", "bouncy", "reflecting", "wrapping"};
    	commandlist =new String[]{"paper", "new", "pen", "move", "right", "left", "show"};
    	paper = new Paper(10, 10);
    	input = new Scanner(System.in);
    	
    	filein = false;
    	fileout = false;
    	totalcommands = 0;
    	commands = new String[]{};
    	
    	if (args.length >= 1) {
    		commands = readFromFile(args[0]);
    		totalcommands = commands.length;
    		filein = true;
    	}
    	
    	if (args.length == 2) {
    		outputlocation = args[1];
    		output = new PrintStream(new File(outputlocation));
    		fileout = true;
    	} else {
    		output = System.out;
    	}
    	
    	currentcommand = 0;
    	while ((filein && currentcommand < totalcommands) || !filein) {
    		if (filein) {
    			currentcommandline = commands[currentcommand].split(" ");
    			command = currentcommandline[0];
    			processCommand(command);
    			currentcommand++;
    			
    		} else {
	    		System.out.println("Please enter a command: ");
	        	System.out.println("- Paper\n- New\n- Pen\n- Move\n- Right\n- Left\n- Show\n");
	    		command = input.next().toLowerCase();
	    		processCommand(command);
	    		System.out.println();
    		}
    	}
    }
    
    //reads data from the file and splits into separate commands
    public static String[] readFromFile(String filepath) {
    	File file;
    	String[] splitfiledata;
    	String filedata, nextword, prevword;
 
    	try {
    		file = new File(filepath);
    		filedata = "";
    		Scanner filereader;
    		filereader = new Scanner(file);
    		
    		prevword = filereader.next();
    		while (true) {
    			try {
    				nextword = filereader.next();
    				if (contains(commandlist, nextword)) {
    					filedata += String.format("%s\n", prevword);
    				} else {
    					filedata += String.format("%s ", prevword);
    				}
    				prevword = nextword;
    			} catch (Exception e){
    				filedata += prevword; 
    				break;
    			}
    		}
    		splitfiledata = filedata.split("\n");
    		return splitfiledata;
    		
    	} catch (Exception e) {
    		System.out.println("ERROR: " + e);
    	}
    	
    	return null;
    }
    
    public static boolean contains(String[] xs, String x) {
    	for(String word : xs) {
    		if (word.equals(x)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public static Turtle createTurtle(String type, Position pos) {
    	Turtle turtle;
    	
    	if (type.equals("normal")) {
    		turtle = new NormalTurtle(pos, Direction.NORTH, type, false, paper, '*');
    	} else if (type.equals("continuous")) {
    		turtle = new ContinuousTurtle(pos, Direction.NORTH, type, false, paper, '*');
    	} else if (type.equals("bouncy")) {
    		turtle = new BouncyTurtle(pos, Direction.NORTH, type, false, paper, '*');
    	} else if (type.equals("reflecting")) {
    		turtle = new ReflectingTurtle(pos, Direction.NORTH, type, false, paper, '*');
    	} else if (type.equals("wrapping")) {
    		turtle = new WrappingTurtle(pos, Direction.NORTH, type, false, paper, '*');
    	} else {
    		turtle = null;
    	}  	
    	
    	return turtle;
    }
    
    public static void processCommand(String command) {
    	int paperwidth, paperheight, x, y, distance, rotation;
    	Turtle turtle;
    	String type, name, penstate;
    	if (command.equals("paper")) {
    		if (filein) {
    			paperwidth = Integer.parseInt(currentcommandline[1]);
    			paperheight = Integer.parseInt(currentcommandline[2]);
    			paper = new Paper(paperwidth, paperheight);
    			
    		} else {
	    		System.out.println("Please enter paper width");
	    		paperwidth = input.nextInt();
	    		
	    		System.out.println("Please enter paper height");
	    		paperheight = input.nextInt();
	    		
	    		paper = new Paper(paperwidth, paperheight);
	    		turtlemap.clear();
	    		
	    		System.out.printf("Paper created with width %s and height %s",
	    						   paper.getWidth(), paper.getHeight());
	    		System.out.println();
    		}
    		return;
    	}
    	
    	else if (command.equals("new")) {
    		if (filein) {
    			type = currentcommandline[1];
    			name = currentcommandline[2];
    			x = Integer.parseInt(currentcommandline[3]);
    			y = paper.getHeight() - Integer.parseInt(currentcommandline[4]) - 1;
    			turtle = createTurtle(type, new Position(x, y));
    			turtlemap.put(name.toLowerCase(), turtle);
    			
    		} else {
    			System.out.println("Please enter the turtle's type");
	    		type = input.next().toLowerCase();
	    		
	    		if (!contains(turtletypes, type)) {
	    			System.out.println("Invalid turtle type given");
	    			return;
	    		}
	    		
	    		System.out.println("Please enter the turtle's name");
	    		name = input.next();
	    		
	    		System.out.println("Please give the x-coord of " + name);
	    		x = input.nextInt();
	    		
	    		System.out.println("Please give the y-coord of " + name);
	    		y = paper.getHeight() - input.nextInt() - 1;
	    		
	    		if (!paper.checkWithinBounds(new Position(x, y))) {
	    			System.out.println("The given position is out of bounds");
	    			return;
	    		}
	    		
	    		turtle = createTurtle(type, new Position(x, y));
	    		turtlemap.put(name.toLowerCase(), turtle);
	    		
	    		System.out.printf("%s created at (%s, %s)\n",
	    				          name,
	    				          turtle.getPosition().getX(),
	    				          paper.getHeight() - 1 -
						          turtle.getPosition().getY() 
						          );
    		}
    		return;
    	}
    	
    	else if (command.equals("pen")) {
    		if (filein) {
    			name = currentcommandline[1];
    			turtle = turtlemap.get(name.toLowerCase());
    			penstate = currentcommandline[2];
    			if (penstate.equals("down")) {
        			turtle.setPenDown();
        		} else if (penstate.equals("up")) {
        			turtle.setPenUp();
        		} else {
        			turtle.setChar(penstate.charAt(0));
        		}
    			
    		} else {
	    		System.out.println("Please enter the name of the turtle");
	    		name = input.next();
	    		turtle = turtlemap.get(name.toLowerCase());
	    		if (turtle == null) {
	    			System.out.println("That turtle does not exist");
	    			return;
	    		}
	    		System.out.printf("Please enter 'down' to set the pen down, 'up' to set the pen up or enter " +
	    				          "a character to set %s's brush character\n", name);
	    		
	    		penstate = input.next();
	    		if (penstate.toLowerCase().equals("down")) {
	    			turtle.setPenDown();
	    		} else if (penstate.toLowerCase().equals("up")) {
	    			turtle.setPenUp();
	    		} else {
	    			turtle.setChar(penstate.charAt(0));
	    		}
	    		
	    		System.out.printf("%s has its pen %s with brush character %s\n",
	    				           name,
				          		   turtle.isPenDown() ? "down" : "up", 
				                   turtle.getBrushChar());
    		}
    		return;
    	}
    	
    	else if (command.equals("move")) {
    		if (filein) {
    			name = currentcommandline[1];
    			turtle = turtlemap.get(name.toLowerCase());
    			distance = Integer.parseInt(currentcommandline[2]);
    			turtle.move(distance);
    			
    		} else {
	    		System.out.println("Please enter the name of the turtle");
	    		name = input.next();
	    		turtle = turtlemap.get(name.toLowerCase());
	    		if (turtle == null) {
	    			System.out.println("That turtle does not exist");
	    			return;
	    		}
	    		
	    		System.out.println("Please enter the distance you'd like to move " + name);
	    		
	    		distance = input.nextInt();
	    		
	    		turtle.move(distance);
    		}
    		return;
    	}
    	
    	else if (command.equals("right")) {
    		if (filein) {
    			name = currentcommandline[1];
    			turtle = turtlemap.get(name.toLowerCase());
    			rotation = Integer.parseInt(currentcommandline[2]);
    			turtle.rotate(rotation % 360);
    			
    		} else {
	    		System.out.println("Please enter the name of the turtle");
	    		name = input.next();
	    		turtle = turtlemap.get(name.toLowerCase());
	    		if (turtle == null) {
	    			System.out.println("That turtle does not exist");
	    			return;
	    		}
	    		
	    		System.out.printf("Please enter the number of degrees to rotate %s right\n", name);
	    		
	    		rotation = input.nextInt();
	    		turtle.rotate(rotation % 360);
	    		
	    		System.out.printf("%s rotated clockwise - now orientated %s\n",
				                  name, turtle.getDirection().name().toLowerCase());
	    		return;
    		}
    	}
    	
    	else if (command.equals("left")) {
    		if (filein) {
    			name = currentcommandline[1];
    			turtle = turtlemap.get(name.toLowerCase());
    			rotation = Integer.parseInt(currentcommandline[2]);
    			turtle.rotate(360 - (rotation % 360));
    			
    		} else {
	    		System.out.println("Please enter the name of the turtle");
	    		name = input.next();
	    		turtle = turtlemap.get(name.toLowerCase());
	    		if (turtle == null) {
	    			System.out.println("That turtle does not exist");
	    			return;
	    		}
	    		
	    		System.out.printf("Please enter the number of degrees to " + "rotate %s left\n", name);
	    		
	    		rotation = input.nextInt();
	    		turtle.rotate(360 - (rotation % 360));
	    		
	    		System.out.printf("%s rotated anticlockwise - now orientated %s\n", name, 
	    				          turtle.getDirection().name().toLowerCase());
	    		return;
    		}
    	}
    	
    	else if (command.equals("show")) {
    		if (fileout) {
    			output.println(paper.show(false));
    		} else {
    			output.println(paper.show(true));
    		}
    		return;
    	}
    	
    	else {
    		System.out.println("Invalid command given: " + command);
    		return;
    	}
    }
}
