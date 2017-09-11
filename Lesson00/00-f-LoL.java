/*
 * Try to compile and run this program. 
 * Try to run it without the "&" char at the end of the invocation of the interpreter, that is "java LoL" instead of "java LoL&"
 * The number provided by the shell after typing the command "java LoL&" is the process id (pid) associated to the run program. 
 * The program will hang. Try to type CTRL+C to interrupt it
 * Try to close the terminal: the program will be automatically be closed by the operating system because it is an "orphan" program
 * Run again the program, and run "ps -ax". You will find a line where "java LoL" appears.
 * In order to filer the output to the only java programs, type "ps -ax | grep java"
 * The first column of this output refers to the user that has invoked the program, while the second line refers to the process id
 * We can kill the program by simply typing "kill -9 pid", where you have to replace <pid> with the process id of the program to kill.
 * Please Note: DO NOT EVER TYPE "killall java", it will kill any program using java as an interpreter. Maybe there is something important sharing your same JVM...
 */
class LoL {
	public static void main(String args[]) {
		while (true);
	}
}
