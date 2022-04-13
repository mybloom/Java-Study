package stack;

import java.util.Arrays;

/**
 * int배열 사용해서 정수 저장하는 Stack
 * void push(int data)
 * int pop()
 */
public class Stack {

	private int[] stack;
	private int capacity = 10;
	private int top;

	public Stack() {
		this.stack = new int[capacity];
		this.top = 0;
	}

	public void push(int data) {
		stack[top] = data;
		top++;
	}

	public int pop() throws NullPointerException{
		int length = stack.length;

		if(length == 0) {
			throw new NullPointerException("stack에 data가 없습니다.");
		}

		int result =  stack[top-1];
		stack[top-1] = 0;
		top--;
		return result;
	}
}
