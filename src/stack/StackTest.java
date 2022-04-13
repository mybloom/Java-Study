package stack;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StackTest {

	@Test
	void int배열로_구현한_Stack() {
		Stack stack = new Stack();

		stack.push(1);
		stack.push(2);
		stack.push(3);

		Assertions.assertThat(stack.pop()).isEqualTo(3);
		Assertions.assertThat(stack.pop()).isEqualTo(2);
		Assertions.assertThat(stack.pop()).isEqualTo(1);

	}
}