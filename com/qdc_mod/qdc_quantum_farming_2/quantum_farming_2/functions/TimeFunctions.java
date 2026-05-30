package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;

public class TimeFunctions {

	
	public static String convertSecondsToMinutes(int seconds)
	{
		 int S = seconds % 60;  // Calculate the remaining seconds
	        int H = seconds / 60;  // Convert total seconds to minutes
	        int M = H % 60;         // Calculate the remaining minutes
	        H = H / 60;            // Convert total minutes to hours
	        
	        
	        return fixNum(H) + ":"  + fixNum(M) + ":" + fixNum(S);
	}
	
	private static String fixNum(int num)
	{
		if(num < 10)
			return "0" + num;
		
		return num+"";
	}
	
}
