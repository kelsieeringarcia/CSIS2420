package ceHash;

import java.util.Random;

import edu.princeton.cs.algs4.LinearProbingHashST;

public class Predictor {

	private static String context;
	private static LinearProbingHashST<String, MoveCounter> contextMap;
	
	public Predictor() {
		context = "****";
		contextMap = new LinearProbingHashST<String, MoveCounter>();
		contextMap.put(context, new MoveCounter());
		
	}
	
	public static Move predict() {
		if(contextMap.get(context).left == contextMap.get(context).right) {
			Move m = getRandomMove();
			return getRandomMove();
		}else if(contextMap.get(context).left > contextMap.get(context).right) {
			return Move.LEFT;
		}else{
			return Move.RIGHT;
		}
			
	}
	
	private static Move getRandomMove() {
		Random rand = new Random();
		if(rand.nextDouble() > .5) {
			return Move.LEFT;
		}else {
			return Move.RIGHT;
		}
	}
	
	public static void recordMove(Move m) {
		if(m == null) throw new NullPointerException("Move cannot be null");
		
		StringBuilder sb = new StringBuilder();
		sb.append(context.substring(1, 3));
		
		if (m == Move.LEFT)
			sb.append("L");
		else
			sb.append("R");
		
		context = sb.toString();
		if(!contextMap.contains(context)){
			MoveCounter mc = new MoveCounter();
			mc.increment(m);
			contextMap.put(context, mc);
		}else {
			MoveCounter mc = contextMap.get(context);
			mc.increment(m);
		}
		
		
		
	}
}
