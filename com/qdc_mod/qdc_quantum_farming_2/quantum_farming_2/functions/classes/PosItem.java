package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.classes;

public class PosItem {

	public int x;
	public int y;
	public int z;
	
	public PosItem(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	public boolean isSame(PosItem other)
	{
		if(x == other.x && y == other.y && z == other.z)
			return true;
		
		return false;
	}
}
