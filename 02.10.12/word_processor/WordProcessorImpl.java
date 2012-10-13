import ru.ncedu.java.tasks.WordProcessor;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class WordProcessorImpl implements WordProcessor {
	String text;

	public String getText() {
		return this.text;
	}

	public void setSource(String src) throws IllegalArgumentException {
		if (src == null) {
			throw new IllegalArgumentException();
		}
		this.text = src;
	}

	public void setSourceFile(String srcFile) throws IOException, IllegalArgumentException {
		if (srcFile == null) {
			throw new IllegalArgumentException();
		}
		FileInputStream stream = new FileInputStream(srcFile);
		setSource(stream);
	}

	public void setSource (FileInputStream fis) throws IOException, IllegalArgumentException {
		if (fis == null) {
			throw new IllegalArgumentException();
		}
		Reader reader = new InputStreamReader(fis);
		readReader(reader);
	}

	public Set<String> wordsStartWith (String begin) {
		if (this.text == null) {
			throw new IllegalArgumentException();
		}
		String regExp = ((begin == null) ? "" : begin) + "\\S+";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(this.text);
		Set<String> words = new HashSet<String>();
		String word;
		int start;
		int end;
		while(matcher.find()) {
			start = matcher.start();
			end = matcher.end();
			word = new String(this.text.substring(start, end));
			words.add(word.toLowerCase());
		}
		return words;
	}

	public static void main(String[] args) {
		try {
		WordProcessorImpl a = new WordProcessorImpl();
		a.setSourceFile("test");
		Set<String> q = a.wordsStartWith((String)null);
		for (String b : q) {
			System.out.println(b);
		}
		} catch (Exception e) {}
	}

	private void readReader(Reader reader) throws IOException {
		int c;
		StringBuilder builder = (this.text == null) ? new StringBuilder() : new StringBuilder(this.text);
		while ( ( c= reader.read() ) != -1) {
			builder.append((char) c);
		}
		this.text = builder.toString();
	}
}