package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.classes;

public class PosePoint {

	public float x,y,z = -1;
	boolean isZSet = false;
	
	public PosePoint(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public PosePoint(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		isZSet = true;
	}
	
	
	public PosePoint getInverse()
	{
		if(!isZSet)
		return new PosePoint(x*-1, y*-1);
		
		return new PosePoint(x*-1, y*-1, z*-1);
	}
}
