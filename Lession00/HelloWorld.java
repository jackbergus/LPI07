class HelloWorld {
	/* public = the entry point `main` shall be accessible from the extern (the JVM)
         * static = the entry point shall be accessible without creating an ``instance`` of the class. This topic will be taught at the end of the course
         * void   = the entry point is a mathematical function that does not provide an output value
         * main   = name of the entry point
         * String = the name of a default class in Java, representing strings
         * args   = when you invoke a command, e.g. cd .., cd is the name of the program, and .. is its first argument. args will contain all the arguments passed to the program
         * []     = args is an ``array of strings'', that is args contains more than one possible argument, or any
         */
	public static void main(String args[]) {
		/*
                   System = is another class in Java, similar to Hello World
                   out    = I want to select all the ``ways'' through which Java connects to the ``output'' of the terminal, and allows to print some values.
                   println = this is another ``public static'' method, which accept only one string as an input.
                */
		System.out.println("Hello World!");
	}
}
