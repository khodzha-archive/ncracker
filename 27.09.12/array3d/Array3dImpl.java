import ru.ncedu.java.tasks.Array3d;
import java.io.PrintStream;
import java.lang.IndexOutOfBoundsException;

public class Array3dImpl<T extends CharSequence> implements Array3d<T> {
	private T[][][] array;

	public Array3dImpl() {
		this.array = (T[][][]) new Object[10][][];
		for (int i = 0; i < 10; i++) {
			this.array[i] = (T[][]) new Object[i+1][];
			for (int j = 0; j < 10; j++) {
				this.array[i][j] = (T[]) new Object[(i+1)*(j+1)];
			}
		}
	}

	public T get (int x, int y, int z) throws IndexOutOfBoundsException {
		return this.array[x][y][z];
	}

	public T set (int x, int y, int z, T value) throws IndexOutOfBoundsException {
		T oldElem = this.get(x, y, z);
		this.array[x][y][z] = value;
		return oldElem;
	}

	public void printArray(PrintStream ps) {
		for(int i = 0; i < 10; i++) {
			for (int j = 0; j < i; j ++) {
				for (int k = 0; k < i*j; k++) {
					ps.println("[" + i + "]" + "[" + j + "]" + "[" + k + "]" + ": " + this.array[i][j][k]);
				}
			}
		}
	}

}