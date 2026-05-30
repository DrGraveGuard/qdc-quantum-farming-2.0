package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.classes.PosItem;

import net.minecraft.core.BlockPos;

public abstract class BaseTreeGrowthShape {

	public List<PosItem> logsList = null;
	public List<PosItem> leavesList = null;

		
	public int logHeight = 3;
	public int leavesHeight = 3;
	public int leavesWidth = 3;

	public BaseTreeGrowthShape(int logHeight) {
		this.logHeight = logHeight;
		logsList = generateLogs();
		leavesList = generateLeaves();
		
		
	}

	public abstract List<PosItem> generateLogs();
	public abstract List<PosItem> generateLeaves();


	
	public List<BlockPos> getLogsBlockPosList(BlockPos saplingPos)
	{
		return getBlockPosList(saplingPos, logsList);
	}
	
	public List<BlockPos> getLeavesBlockPosList(BlockPos saplingPos)
	{
		return getBlockPosList(saplingPos, leavesList);
	}


	
	private List<BlockPos> getBlockPosList(BlockPos saplingPos, List<PosItem> itemList)
	{
		List<BlockPos> res = new ArrayList<BlockPos>();
		
		for(PosItem item : itemList)
		{
			res.add(new BlockPos(item.x + saplingPos.getX(), item.y + saplingPos.getY(), item.z + saplingPos.getZ()));
		}
		
		
		
		
		
		return res;
	}
	
	
	
	protected boolean isInLogsList(PosItem search) {
		for (PosItem item : logsList) {
			if (item.isSame(search))
				return true;
		}

		return false;
	}

}
