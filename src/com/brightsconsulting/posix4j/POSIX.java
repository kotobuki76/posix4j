package com.brightsconsulting.posix4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class POSIX {

	public static final String DS = System.getProperty("file.separator");
	public static final String HOME = System.getProperty("user.home");

	public static void mkdir(String dirPath) {

		File dir = new File(dirPath);
		dir.mkdirs();
	}

	public static void echo(String line, File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter filewriter = new FileWriter(file, true);
		filewriter.write(line);
		filewriter.write("\r\n");
		filewriter.close();
	}

	public static ArrayList<String> ls(String path) {
		File dir = new File(path);
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < dir.list().length; i++) {
			ret.add(dir.list()[i]);
		}
		return ret;
	}

	public static ArrayList<String> grep(ArrayList<String> arg, String contains) {
		ArrayList<String> ret = new ArrayList<String>();
		for (String item : arg) {
			if (item.contains(contains)) {
				ret.add(item);
			}
		}
		return ret;
	}

	public static int eval(String s) throws IOException, InterruptedException {

		Runtime runtime = Runtime.getRuntime();
		Process p = runtime.exec(s);
		int code = p.waitFor();
		return code;
	}

	public static int ssh(String sshkey, String user, String addr, String s) throws IOException, InterruptedException {
		return POSIX.eval("ssh -i " + sshkey + " -oStrictHostKeyChecking=no " + user + "@" + addr + " " + s);
	}
}
