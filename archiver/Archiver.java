import java.util.*;
import java.util.zip.*;
import java.io.*;

public class Archiver {
	private Scanner scanIn;

	public static void printHelp() {
		System.out.println("==============================================================================");
		System.out.println("This is Archiver - simple archiver/extractor");
		System.out.println("To archive files: {a|archive} folder1 file1 file2 folder2 file3 archive_name");
		System.out.println("To extract files: {x|extract} archive_name");
		System.out.println("To archive files with comments: {c|comment} folder1 folder2 file1 file2 archive_name");
		System.out.println("==============================================================================");
	}

	private String getComment(String fileName) {
		scanIn = new Scanner(System.in);
		System.out.println("Enter your comment for file: " + fileName);
		String temp = scanIn.nextLine();
		return temp;
	}


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

	private void archive(String zipFile, String[] files, boolean withComments) throws IOException {

		byte[] buffer = new byte[100000];
		String comment;
		int offset = 0;
		int readBytes = 0;
		FileInputStream fileStream;
		if (!zipFile.endsWith(".zip")) {
			zipFile += ".zip";
		}
		ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
		for(String file : files) {
			offset = 0;
			readBytes = 0;
			fileStream = new FileInputStream(file); 
			ZipEntry entry = new ZipEntry(file);
			comment = this.getComment(file);
			entry.setComment(comment);
			zipStream.putNextEntry(new ZipEntry(file));
			while ((readBytes = fileStream.read(buffer)) != -1) {
				zipStream.write(buffer, offset, readBytes);
				offset += readBytes;
			}
			zipStream.closeEntry();
			fileStream.close();
		}
		zipStream.close();
		scanIn.close();
	}


	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length == 0) {
			Archiver.printHelp();
			return;
		}
		Archiver a = new Archiver();
		if (args[0].equals("x") || args[0].equals("extract")) {
			// extraction
			a.extract(args[1]);
		} else if (args[0].equals("a") || args[0].equals("archive")) {
			String[] fileList = new String[args.length - 2];
			System.arraycopy(args, 1, fileList, 0, fileList.length);
			// archivation
			a.archive(args[args.length - 1], fileList, false);
		} else if (args[0].equals("c") || args[0].equals("comment")) {
			String[] fileList = new String[args.length - 2];
			System.arraycopy(args, 1, fileList, 0, fileList.length);
			// archivation with comments
			a.archive(args[args.length - 1], fileList, true);
		} else {
			Archiver.printHelp();
		}
	}
}