package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box.shapes;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box.classes.BaseTreeGrowthShape;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.classes.PosItem;

import net.minecraft.core.BlockPos;

public class TreeGrowthShapeCylinder extends BaseTreeGrowthShape{



	public TreeGrowthShapeCylinder(int logHeight) {
//		logsList = generateLogs();
//		leavesList = generateLeaves();
		super(logHeight);
	}

	@Override
	public List<PosItem> generateLogs() {
		List<PosItem> res = new ArrayList<PosItem>();

		for (int i = 0; i < logHeight; i++) {
			res.add(new PosItem(0, i, 0));
		}

		return res;
	}
	@Override
	public List<PosItem> generateLeaves() {
		List<PosItem> res = new ArrayList<PosItem>();

		int startDistance = ((leavesWidth - 1) / 2) * -1;
		int decorDistance = ((leavesWidth - 1) / 2) +1;

		for (int y = 0; y < leavesHeight; y++) {
			for (int x = 0; x < leavesWidth; x++) {
				for (int z = 0; z < leavesWidth; z++) {

					PosItem item = new PosItem(startDistance + x, y + logHeight - 2, startDistance + z);
					
					if(!isInLogsList(item))
					res.add(item);
				}
			}
			
			res.add(new PosItem(decorDistance, y + logHeight - 2, 0));
			res.add(new PosItem(0, y + logHeight - 2, decorDistance));
			res.add(new PosItem(decorDistance*-1, y + logHeight - 2, 0));
			res.add(new PosItem(0, y + logHeight - 2, decorDistance*-1));
		}

		return res;
	}


}
