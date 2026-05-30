package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes;

import java.awt.Color;

import net.minecraft.nbt.CompoundTag;

public class MachineData {

	public String _machineName ="...";
	public String _toolTip = "...";
	public String _sizeString = "5x5";
	public String _speedString = "1/7";
	public Color _toolTipColor = Color.green;
	public String _mainItemName = "...";
	public boolean _isPowered = false;
	
	public float _decorationAngle = 0;
	public boolean _decorationGoingUp = false;
	
	public MachineData(String machineName)
	{
		this._machineName = machineName;
	}
	
	
	public void load(CompoundTag input)
	{
		_toolTip = input.getString("tooltip");
		_toolTipColor = new Color(input.getInt("tooltipcolor"));

		_speedString = input.getString("speedstring");
		_sizeString = input.getString("sizestring");
		
		_mainItemName = input.getString("mainitemname");
		
		_isPowered = input.getBoolean("ispowered");
		
		_decorationAngle = input.getFloat("decorationAngle");
		
		_decorationGoingUp = input.getBoolean("decorationGoingUp");
	}
	
	public void save(CompoundTag output)
	{
		output.putString("tooltip", _toolTip);
		output.putInt("tooltipcolor", _toolTipColor.getRGB());

		output.putString("speedstring", _speedString);
		output.putString("sizestring", _sizeString);
		
		output.putString("mainitemname", _mainItemName);
		
		output.putBoolean("ispowered", _isPowered);
		
		output.putFloat("decorationAngle", _decorationAngle);
		
		output.putBoolean("decorationGoingUp", _decorationGoingUp);
	}
}
