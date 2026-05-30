package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box.classes.BaseTreeGrowthShape;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box.classes.TreeGrowthShapeResult;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box.shapes.TreeGrowthShapeCone;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box.shapes.TreeGrowthShapeCylinder;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box.shapes.TreeGrowthShapeDefault;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box.shapes.TreeGrowthShapeSphere;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class TreeShapeBox {

	public static final Random randome = new Random();
	
	
	public static final int MIN_LOG_HEIGHT = 3;
	public static final int MAX_LOG_HEIGHT = 7;;

	public static final List<BaseTreeGrowthShape> shapeList = generateShapeList();
	
	
	public static List<BaseTreeGrowthShape> generateShapeList()
	{
		List<BaseTreeGrowthShape> shapes = new ArrayList<BaseTreeGrowthShape>();
		
		for(int i =MIN_LOG_HEIGHT;i<MAX_LOG_HEIGHT;i++)
		{
			shapes.add(new TreeGrowthShapeDefault(i));
		}
		
		for(int i =MIN_LOG_HEIGHT;i<MAX_LOG_HEIGHT;i++)
		{
			shapes.add(new TreeGrowthShapeCylinder(i));
		}
		
		for(int i =MIN_LOG_HEIGHT;i<MAX_LOG_HEIGHT;i++)
		{
			shapes.add(new TreeGrowthShapeCone(i));
		}
	
		for(int i =MIN_LOG_HEIGHT;i<MAX_LOG_HEIGHT;i++)
		{
			shapes.add(new TreeGrowthShapeSphere(i));
		}
		
		return shapes;
	}
	
	
	
	private static TreeGrowthShapeResult getRandomShape(BlockPos saplingPos) {
		
		int index = randome.nextInt(shapeList.size());
		
		BaseTreeGrowthShape shape = shapeList.get(index);
		
		
		return new TreeGrowthShapeResult(shape.getLogsBlockPosList(saplingPos),
				shape.getLeavesBlockPosList(saplingPos));
	}

	public static void growTree(Level level, BlockPos saplingPos,Block log, Block leaves) {

		TreeGrowthShapeResult shapeRes = getRandomShape(saplingPos);
		
		
		
		// generate logs
		
		for(BlockPos pos : shapeRes.logsPosList)
		{
			if(isBlockAir(level, pos))
			level.setBlockAndUpdate(pos, log.defaultBlockState());
		}
		
		
		// generate leaves
		
		for(BlockPos pos : shapeRes.leavesPosList)
		{
			if(isBlockAir(level, pos))
			level.setBlockAndUpdate(pos, leaves.defaultBlockState());
		}
		
	}
	
	private static boolean isBlockAir(Level level, BlockPos pos)
	{
		if(level.getBlockState(pos).getBlock().asItem() == Items.AIR)
			return true;
		
		return false;
	}

}
