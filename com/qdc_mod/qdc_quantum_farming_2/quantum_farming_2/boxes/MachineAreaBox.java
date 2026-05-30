package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes;

import java.util.ArrayList;



public class MachineAreaBox {

	public static final AreaBox box_crop_planter = new AreaBox(11, 11, 1);
	public static final AreaBox box_crop_harvester = new AreaBox(11, 11, 1);
	public static final AreaBox box_tree_harvester = new AreaBox(15, 15, 40);
	public static final AreaBox fisher = new AreaBox(5, 5, 1);
	
	public static final AreaBox block_breaker = new AreaBox(1, 1, 1);
	
	
	public static class AreaBox
	{
		public int width = 0;
		public int length = 0;
		public int height = 0;
		

		
		public AreaBox(int width, int length, int height)
		{
			this.width = width;
			this.height = height;
			this.length = length;
		}
	}
	
}
