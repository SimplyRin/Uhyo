package net.simplyrin.uhyo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Created by SimplyRin on 2022/05/09.
 */
public class Uhyo {
	
	public static void main(String[] args) {
		new Uhyo().run();
	}
	
	public static final List<HashMap<String, Type>> TYPES = new ArrayList<HashMap<String, Type>>() {
		{
			add(_UPPER);
			add(_MID);
			add(_DOWN);
		}
	};
	
	public static final HashMap<String, Type> _UPPER = new HashMap<String, Type>() {
		{
			put("╲", Type.DOWN);
			put("▔", Type.UPPER);
			put("︺", Type.UPPER);
			put("\\", Type.DOWN);
			put("﹀", Type.UPPER);
		}
	};
	
	public static final HashMap<String, Type> _MID = new HashMap<String, Type>() {
		{
			put("√", Type.UPPER);
		}
	};
	
	public static final HashMap<String, Type> _DOWN = new HashMap<String, Type>() {
		{
			put("▁", Type.DOWN);
			put("⁄", Type.UPPER);
			put("/", Type.UPPER);
			put("╱", Type.UPPER);
			put("︹", Type.DOWN);
			put("_", Type.DOWN);
			put("︿", Type.DOWN);
		}
	};
	
	public enum Type {
		UPPER, MID, DOWN;
	}
	
	public void run() {
		System.out.println(this.generate());
	}
	
	private String previousValue = "";
	
	public String generate() {
		Entry<String, Type> first = this.get(_MID);
		
		int length = 140;
		
		String result = "";

		for (int i = 0; i < length; i++) {
			String tag = first.getKey();
			Type type = first.getValue();

			if (type.equals(Type.UPPER)) {
				first = this.get(_UPPER);
			} else if (type.equals(Type.MID)) {
				first = this.get(_MID);
			} else if (type.equals(Type.DOWN)) {
				first = this.get(_DOWN);
			}
			
			if (this.previousValue.equals(tag)) {
				i--;
				continue;
			}
			
			result += tag;

			this.previousValue = tag;
		}
		
		return result;
	}
	
	public Entry<String, Type> get(HashMap<String, Type> map) {
		List<Entry<String, Type>> list = new ArrayList<>();
		list.addAll(map.entrySet());
		return list.get(new Random().nextInt(list.size()));
	}
	
	public HashMap<String, Type> getMap(Type type) {
		int s = new Random().nextInt(1);
		
		switch (type) {
		case DOWN:
			return s == 0 ? _MID : _UPPER;
		case MID:
			return s == 0 ? _DOWN : _UPPER;
		case UPPER:
			return s == 0 ? _DOWN : _MID;
		}
		
		return null;
	}

}
