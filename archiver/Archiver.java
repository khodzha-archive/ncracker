import java.util.*;
import java.util.zip.*;
import java.io.*;

public class Archiver {
	private void extract(String filename) throws IOException {
		ZipFile file = new ZipFile(filename);
		while (file.entries().hasMoreElements()) {
			ZipEntry entry = (ZipEntry) file.entries().nextElement();
			InputStream zipStream = file.getInputStream(entry);
			FileOutputStream outStream = new FileOutputStream(entry.getName());
			byte[] buffer = new byte[100000];
			int offset = 0;
			int readBytes = 0;
			while ((readBytes = zipStream.read(buffer)) != -1) {
				outStream.write(buffer, offset, readBytes);
				offset += readBytes;
			}
		}
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
		} else {
			byte[] buffer = new byte[100000];
			String zipFile = args[args.length - 1];
			if (!zipFile.endsWith(".zip")) {
				zipFile += ".zip";
			}
			ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
			String[] files = Arrays.copyOfRange(args, 0, args.length -1);
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
	}
}