package ceMail;

import java.util.Random;

public class Mail implements Comparable<Mail> {
	DeliveryType type;
	String mailCode;
	
	public Mail(DeliveryType type, String mailCode) {
		super();
		this.type = type;
		this.mailCode = mailCode;
	}
	
	public Mail() {
		this.type = randomType();
		this.mailCode = randomMailCode();
	}
	
	private String randomMailCode() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < 5; i++) {
			sb.append( (char) (rand.nextInt(25) + 65));
		}
		return sb.toString();
	}
	
	private DeliveryType randomType() {
		int pick = new Random().nextInt(DeliveryType.values().length);
		return DeliveryType.values()[pick];
	}

	@Override
	public String toString() {
		return mailCode + "(" + type + ")";
	}

	@Override
	public int compareTo(Mail o) {
		return type.compareTo(o.type);
	}

}
