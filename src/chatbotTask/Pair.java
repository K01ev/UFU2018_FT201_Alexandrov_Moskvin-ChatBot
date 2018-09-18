package chatbotTask;

public class Pair<T1, T2> {
	private T1 firstValue;
	private  T2 secondValue;
	
	public Pair(T1 value1, T2 value2) {
		firstValue = value1;
		secondValue = value2;
	}
	
	public T1 getFirst() {
		return firstValue;
	}
	public T2 getSecond() {
		return secondValue;
	}
}
