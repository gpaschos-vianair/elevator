package vianair.elevator;

public class Logger {

	public static <T> void Log(T msg) {
		System.out.println(msg);
	}

	public static <T> void Log(Iterable<T> iterable) {
		for (var i : iterable) {
			System.out.println(i);
		}
	}
	
}
