package ceLinearBinary;
import edu.princeton.cs.algs4.Selection;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchTest {

	
	
	@Test
	void testLinearNumberNotInThere() {
		int[] numbers = new int[] {5,4,3,2,1,0};
		int key = 6;
		int expResults = -1;
		int results = Search.linear(numbers, key);
		assertEquals(expResults, results);
	}
	
	@Test
	void testLinearNumberInThere() {
		int[] numbers = new int[] {5,4,3,2,1,0};
		int key = 5;
		int expResults = 0;
		int results = Search.linear(numbers, key);
		assertEquals(expResults, results);
	}
	
	@Test
	void testLinearFirstNumber() {
		int[] numbers = new int[] {5,4,3,2,1,0};
		int key = 5;
		int expResults = 0;
		int results = Search.linear(numbers, key);
		assertEquals(expResults, results);
	}
	
	@Test
	void testLinearMiddleNumber() {
		int[] numbers = new int[] {5,4,3,2,1,0};
		int key = 3;
		int expResults = 2;
		int results = Search.linear(numbers, key);
		assertEquals(expResults, results);
	}
	
	@Test
	void testLinearLastNumber() {
		int[] numbers = new int[] {5,4,3,2,1,0};
		int key = 0;
		int expResults = 5;
		int results = Search.linear(numbers, key);
		assertEquals(expResults, results);
	}
	
	@Test
	void testBinaryNumberNotInThere() {
		int[] numbers = new int[] {0, 1, 2, 3, 4, 5};
		int key = 6;
		int expResults = -1;
		int results = Search.binary(numbers, key);
		assertEquals(expResults, results);
	}
	
	@Test
	void testBinaryNumberInThere() {
		int[] numbers = new int[] {0, 1, 2, 3, 4, 5};
		int key = 2;
		int expResults = 2;
		int results = Search.binary(numbers, key);
		assertEquals(expResults, results);
	}
	
	@Test
	void testBinaryFirstNumber() {
		int[] numbers = new int[] {0, 1, 2, 3, 4, 5};
		int key = 0;
		int expResults = 0;
		int results = Search.binary(numbers, key);
		assertEquals(expResults, results);
	}
	
	@Test
	void testBinaryMiddleNumber() {
		int[] numbers = new int[] {0, 1, 2, 3, 4, 5};
		int key = 3;
		int expResults = 3;
		int results = Search.binary(numbers, key);
		assertEquals(expResults, results);
	}
	
	@Test
	void testBinaryLastNumber() {
		int[] numbers = new int[] {0, 1, 2, 3, 4, 5};
		int key = 5;
		int expResults = 5;
		int results = Search.binary(numbers, key);
		assertEquals(expResults, results);
	}

}
