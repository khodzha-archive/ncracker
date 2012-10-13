import java.io.*;

public class JCL {
	private File currentDir = new File(".");

	public static void main(String[] args) throws Exception {
		JCL env = new JCL();
		System.out.println("Welcome to JCL! Type exit to quit.");
		Console console = System.console();
		String command;
		//SyntaxParser parser = new SyntaxParser();
		//CommandLauncher launcher = new CommandLauncher();
		while(! (command = console.readLine()).equalsIgnoreCase("exit") ) {
			//interpeter.launch(parser.parse(command));
			if (command.equals("pwd")) {
				env.pwdImplementation();
			} else if (command.split(" ")[0].equals("cat")) {
				env.catImplementation(command.split(" ")[1]);
			} else if (command.equals("ls") || command.split(" ")[0].equals("ls")) {
				if (command.equals("ls")) {
					env.lsImplementation(null);
				} else {
					env.lsImplementation(command.split(" ")[1]);
				}
			} else if (command.split(" ")[0].equals("cd")) {
				env.cdImplementation(command.split(" ")[1]);
			}
			console.flush();
		}
	}

	public void pwdImplementation() throws Exception {
		System.out.println(currentDir.getCanonicalPath());
	}

	public void catImplementation(String path) throws Exception {
		File f = new File(path);
		if (! f.isAbsolute()) {
			f = new File(currentDir.getCanonicalPath() + "/" + path);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(f))));
		String str;
		while ((str = br.readLine()) != null) {
			System.out.println(str);
	  	}
	}

	public void lsImplementation(String path) throws Exception {
		File lsDir = (path != null) ? new File(path) : currentDir;
		String[] list = lsDir.list();
		for (String file : list) {
			System.out.println(file);
		}
	}

	public void cdImplementation(String path) throws Exception {
		currentDir = new File(currentDir.getCanonicalPath() + "/" + path);
	}
}	