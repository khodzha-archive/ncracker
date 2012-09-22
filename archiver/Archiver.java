import java.util.*;
import java.util.zip.*;
import java.io.*;

public class Archiver {
	private void extract(String filename) throws IOException {
		ZipFile file = new ZipFile(filename);
		ZipEntry entry;
		InputStream zipStream;
		FileOutputStream outStream;
		byte[] buffer;
		Enumeration entries = file.entries();
		while (entries.hasMoreElements()) {
			entry = (ZipEntry) entries.nextElement();
			zipStream = file.getInputStream(entry);
			outStream = new FileOutputStream(entry.getName());
			buffer = new byte[100000];
			int offset = 0;
			int readBytes = 0;
			while ((readBytes = zipStream.read(buffer)) != -1) {
				outStream.write(buffer, offset, readBytes);
				offset += readBytes;
			}
			zipStream.close();
			outStream.close();
			System.out.println( "* " + entry.getName() + " extracted");
		}
		System.out.println("==============================================================================");
		System.out.println("Extraction finished");
	}

	private void archive(String zipFile, String[] files) throws IOException {
		byte[] buffer = new byte[100000];
		if (!zipFile.endsWith(".zip")) {
			zipFile += ".zip";
		}
		ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
		for(String file : files) {
			int offset = 0;
			int readBytes = 0;
			FileInputStream fileStream = new FileInputStream(file); 
			zipStream.putNextEntry(new ZipEntry(file));
			while ((readBytes = fileStream.read(buffer)) != -1) {
				zipStream.write(buffer, offset, readBytes);
				offset += readBytes;
			}
			zipStream.closeEntry();
		}
		zipStream.close();
	}


	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length == 0) {
			System.out.println("Please supply list of files and archive name");
			return;
		}
		Archiver a = new Archiver();
		if (args[0].equals("x") || args[0].equals("extract")) {
			// extraction
			a.extract(args[1]);
		} else if (args[0].equals("a") || args[0].equals("archive")) {
			String[] fileList = new String[args.length - 2];
			System.arraycopy(args, 1, fileList, 0, fileList.length);
			a.archive(args[args.length - 1], fileList);
		}
	}
}