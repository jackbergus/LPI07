class MyPrint {
	/* public = the entry point `main` shall be accessible from the extern (the JVM)
         * static = the entry point shall be accessible without creating an ``instance`` of the class. This topic will be taught at the end of the course
         * void   = the entry point is a mathematical function that does not provide an output value
         * main   = name of the entry point
         * String = the name of a default class in Java, representing strings
         * args   = when you invoke a command, e.g. cd .., cd is the name of the program, and .. is its first argument. args will contain all the arguments passed to the program
         * []     = args is an ``array of strings'', that is args contains more than one possible argument, or any
         */
	public static void main(String args[]) {
		MyPrint.print("Hello World!");
	}

	/* Creating a new method, similar to the println in System.out.
         * The difference between main and print is that print is automagically selected by the interpreter to be run, while print isn't
         * Moreover, the method accepts only one string, and not many strings as the former method
         */
	public static void print(String text) {
		System.out.println(text);
	}
}
/* Exercise: change this program in order to print any string you like :) */
