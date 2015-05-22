This project code answers the 'MARS ROVERS' technical test.

There is a package 'src' containing the project source code, and a package 'test' containing JUnit tests.

The main entry point of the project is the class "Main.java". A file may be given as argument to the main method. If no
file is given, or if the file path is incorrect, then the program will use the default supplied test file.
Launching the main class will result into the input and NASA output being printed to the console.

The JUnit test case 'TestSuppliedTestCase' tests the code using the supplied test data.

Assumptions are mainly the ones described in the specifications. Also, the code checks whether the robot 
can perform a given move within the plateau ; if not, an exception is thrown.

Some more features could be integrated - in particular a user interface allowing the user to choose a file,
and displaying the input and output data, would be good. The unit test files could also contain more cases.

========

Assignment

Candidates should spend no more than 8 hours working on this exercise. 
For the solution, we request that you use the specified language. You may use external libraries or tools for building or testing purposes. Specifically, you may use unit testing libraries or build tools available for your chosen language.
Please also include a brief explanation of your design and assumptions along with your code.
INTRODUCTION TO THE PROBLEM
There must be a way to supply the application with the input data via a text file (a textarea is suitable for a JavaScript-based solution). The application must run. You should provide sufficient evidence that your solution is complete by, as a minimum, indicating that it works correctly against the supplied test data. Please note that you will be assessed on your judgment as well as your execution.

MARS ROVERS
A squad of robotic rovers are to be landed by NASA on a plateau on Mars. This plateau, which is curiously rectangular, must be navigated by the rovers so that their on-board cameras can get a complete view of the surrounding terrain to send back to Earth.
A rover's position and location is represented by a combination of x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North.
In order to control a rover, NASA sends a simple string of letters. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90 degrees left or right respectively, without moving from its current spot. 'M' means move forward one grid point, and maintain the same heading.
Assume that the square directly North from (x, y) is (x, y+1).
INPUT:
The first line of input is the upper-right coordinates of the plateau, the lower-left coordinates are assumed to be 0,0.
The rest of the input is information pertaining to the rovers that have been deployed. Each rover has two lines of input. The first line gives the rover's position, and the second line is a series of instructions telling the rover how to explore the plateau.
The position is made up of two integers and a letter separated by spaces, corresponding to the x and y co-ordinates and the rover's orientation.
Each rover will be finished sequentially, which means that the second rover won't start to move until the first one has finished moving.
