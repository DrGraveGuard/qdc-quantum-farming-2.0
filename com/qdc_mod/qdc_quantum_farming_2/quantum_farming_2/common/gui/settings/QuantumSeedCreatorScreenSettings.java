package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.inventory.InventoryItem;

public class QuantumSeedCreatorScreenSettings {

	public class CONSTANTS {


		public class BUTTON {
			public static final Point SIZE = new Point(15, 12);
		}
		
		public class ITEMS {


			public static final Point SIZE = new Point(18, 18);
			
			public static final TextureColor BORDER_COLOR = TextureColor.WHITE_1;
			public static final TextureColor BORDER_COLOR_HOVER = TextureColor.GREEN_2;
			public static final TextureColor BORDER_COLOR_ACTIVE = TextureColor.ORANGE_2;
			
			public static final TextureColor BG_COLOR = TextureColor.GRAY_2;
			
		}
	}




	
	
	public class MAIN_WINDOW {

		public static final Point POS = new Point(0, 0);
		public static final Point SIZE = new Point(420, 200);

	}
	
	public class PARTICLE_STORE_WINDOW {

		public static final Point POS = new Point(-5, -18);
		public static final Point SIZE = new Point(415, 18);

		public class ITEMS {
			public static final Point SIZE = new Point(65, 18);
			public static final Point GAP_SIZE = new Point(5, 2);

			public static final List<Point> ITEM_POS_LIST = GuiFunctions.generateIconPosList(6, 6, SIZE, GAP_SIZE, POS);
		}

	}

	public class MAIN_MENU_BUTTON {

		public static final Point POS = new Point(0, 0);
		public static final Point SIZE = new Point(104, 15);

	}

	public class MAIN_TITLE {

		public static final Point POS = new Point(MAIN_MENU_BUTTON.SIZE.x + 5, -0);
		public static final Point SIZE = new Point(MAIN_WINDOW.SIZE.x - (MAIN_MENU_BUTTON.SIZE.x + 5), 15);

		public static final Point TEXT_POS = new Point(POS.x + 3, POS.y + 2);
		public static final Point DECORATION_POS = new Point(POS.x, POS.y + SIZE.y - 1);
		public static final int DECORATION_WIDTH = SIZE.x;

	}
	


	public class DISCOVERED_ITEMS_WINDOW {

		public static final Point POS = new Point(0, MAIN_TITLE.POS.y+ MAIN_TITLE.SIZE.y +3);
		public static final Point SIZE = new Point(180, 100);

		public class TITLE {

			public static final Point POS = DISCOVERED_ITEMS_WINDOW.POS;
			public static final Point SIZE = new Point(DISCOVERED_ITEMS_WINDOW.SIZE.x, 15);

		}
		
		
		public class SEARCH_WINDOW {

			public static final Point POS = new Point(DISCOVERED_ITEMS_WINDOW.POS.x + 5, TITLE.POS.y+ TITLE.SIZE.y +3);
			public static final Point SIZE = new Point(DISCOVERED_ITEMS_WINDOW.SIZE.x-10, 15);

		}
		
		public class BUTTONS {

			public class PREV {
				public static final Point POS = new Point(DISCOVERED_ITEMS_WINDOW.POS.x + 5, SEARCH_WINDOW.POS.y + SEARCH_WINDOW.SIZE.y+ 5);
			}

			public class NEXT {
				public static final Point POS = new Point(
						DISCOVERED_ITEMS_WINDOW.POS.x + DISCOVERED_ITEMS_WINDOW.SIZE.x - (CONSTANTS.BUTTON.SIZE.x + 5), PREV.POS.y);
			}

		}

		public class TAB_INDEX {

			public static final Point POS = new Point(DISCOVERED_ITEMS_WINDOW.POS.x + DISCOVERED_ITEMS_WINDOW.SIZE.x/2,
					BUTTONS.PREV.POS.y);
			public static final Color TEXT_COLOR = Color.white;

		}

		public class ITEMS {
			public static final Point POS = new Point(DISCOVERED_ITEMS_WINDOW.POS.x + 3,
					BUTTONS.PREV.POS.y + CONSTANTS.BUTTON.SIZE.y + 3);
			public static final Point GAP_SIZE = new Point(2, 2);

			
			public static final int ITEM_LIST_MAX = 16;
			public static final int ITEM_LIST_WIDTH = 8;

			public static final List<Point> ITEM_POS_LIST = GuiFunctions.generateIconPosList(ITEM_LIST_MAX,
					ITEM_LIST_WIDTH, CONSTANTS.ITEMS.SIZE, DISCOVERED_ITEMS_WINDOW.ITEMS.GAP_SIZE, POS);
		}

	}
	

	public class WORK_WINDOW {

		public static final Point POS = new Point(DISCOVERED_ITEMS_WINDOW.POS.x + DISCOVERED_ITEMS_WINDOW.SIZE.x + 5,DISCOVERED_ITEMS_WINDOW.POS.y);
		public static final Point SIZE = new Point(215, 135);

		

		public class WORK_WINDOW_TITLE {

			
			public static final Point POS = WORK_WINDOW.POS;
			public static final Point SIZE = new Point(WORK_WINDOW.SIZE.x, 12);
		}

		public class MAIN_ITEM {

			public static final Point POS = new Point(WORK_WINDOW.POS.x + 5, WORK_WINDOW_TITLE.POS.y + WORK_WINDOW_TITLE.SIZE.y + 1);
			public static final Point SIZE = new Point(WORK_WINDOW.SIZE.x - 10, 18);

			public static final TextureColor COLOR_DEFAULT = TextureColor.PURPLE_1;
			
			public class ICON {
				public static final Point POS = new Point(MAIN_ITEM.POS.x +1, MAIN_ITEM.POS.y +1 );
				public static final Point SIZE = new Point(20, 20);

			}

			public class NAME {
				public static final Point POS = new Point(MAIN_ITEM.POS.x + ICON.SIZE.x+3, MAIN_ITEM.POS.y);

				public static final int HEIGHT = 9;
				public static final Point TEXT_POS = new Point(POS.x, POS.y + 4);
				public static final Color TEXT_COLOR = Color.white;
			}



		}
		
		public class PARTICLES {
			public static final TextureColor COLOR_HAVE_ENOUGH = TextureColor.GREEN_1;
			public static final TextureColor COLOR_NOT_HAVE_ENOUGH = TextureColor.RED_2;
			public static final TextureColor COLOR_DEFAULT = TextureColor.GRAY_1;
			public static final Color TEXT_COLOR_HAVE = Color.white;
			public static final Color TEXT_COLOR_NOT_HAVE = Color.white;
			public static final Color TEXT_COLOR_DEFAULT = Color.black;

			public static final Point SIZE = new Point(65, 18);
			public static final Point GAP_SIZE = new Point(1, 1);

			public class TITLE {

				public static final Point POS = new Point(WORK_WINDOW.POS.x + 5,
						MAIN_ITEM.POS.y + MAIN_ITEM.SIZE.y);
				public static final Point SIZE = new Point(WORK_WINDOW.SIZE.x - 10, 12);

			}

			public static final Point WINDOW_SIZE = new Point(TITLE.SIZE.x, 45);

			public static final Point START_POS = new Point(TITLE.POS.x + 1, TITLE.POS.y + TITLE.SIZE.y);

			public static final List<Point> PARTICLE_POS_LIST = GuiFunctions.generateIconPosList(
					6, 3, SIZE, GAP_SIZE, START_POS);
		}
		
		
		public class ITEM_SELECTION_INFO {
			
			public static final Point POS = new Point(PARTICLES.TITLE.POS.x,PARTICLES.TITLE.POS.y+3);
			public static final Point SIZE = new Point(PARTICLES.WINDOW_SIZE.x,50);
			
			public static final Color TEXT_COLOR = Color.white;
			public static final TextureColor BG_COLOR = TextureColor.BLUE_1;
			
			public static final Point LINE_ONE_POS = new Point(POS.x+3,POS.y+3);
			public static final Point LINE_TWO_POS = new Point(LINE_ONE_POS.x,LINE_ONE_POS.y + 10);
			public static final Point LINE_THREE_POS = new Point(LINE_ONE_POS.x,LINE_TWO_POS.y + 13);
			public static final Point LINE_FOUR_POS = new Point(LINE_ONE_POS.x,LINE_THREE_POS.y + 10);
			
			
			
			
			
			public static final String LINE_ONE_TEXT = "This Potion Effect has not been";
			public static final String LINE_TWO_TEXT = "discovered yet.";
			public static final String LINE_THREE_TEXT = "You can still remove, but no particles";
			public static final String LINE_FOUR_TEXT = "will be recieved.";
		}
		
		
		public class CAN_MAKE_SECTION {
			public static final Point POS = new Point(WORK_WINDOW.POS.x + 5,
					PARTICLES.TITLE.POS.y + PARTICLES.WINDOW_SIZE.y + 8);
			
			public static final Point SIZE = new Point(51, 12);
			
			public static final Point TEXT_POS = new Point(POS.x+SIZE.x+3, POS.y+3);
			
			public static final Color TEXT_COLOR = Color.white;
		}
		
		public class TIME_ON_PLAYER_SECTION {
			public static final Point POS = new Point(CAN_MAKE_SECTION.POS.x, CAN_MAKE_SECTION.POS.y + CAN_MAKE_SECTION.SIZE.y+3);
			
			public static final Point SIZE = new Point(78, 12);
			
			public static final Point TEXT_POS = new Point(POS.x+SIZE.x+3, POS.y+3);
			
			public static final Color TEXT_COLOR = Color.white;
		}
		
	
		
		public class BUTTON_MAKE_STACK {
			public static final Point SIZE = new Point(WORK_WINDOW.SIZE.x-10, 15);
			public static final Point POS = new Point(WORK_WINDOW.POS.x + 5, WORK_WINDOW.POS.y + WORK_WINDOW.SIZE.y- (SIZE.y+3));

				
			}
		
		public class BUTTON_MAKE_ONE {
			public static final Point SIZE = new Point(WORK_WINDOW.SIZE.x-10, 15);
			public static final Point POS = new Point(WORK_WINDOW.POS.x + 5, BUTTON_MAKE_STACK.POS.y - (BUTTON_MAKE_STACK.SIZE.y + 2));

				
			}
	
	}
	
	
	public class INVENTORY {

		public static final Point POS = new Point(DISCOVERED_ITEMS_WINDOW.POS.x, DISCOVERED_ITEMS_WINDOW.POS.y + DISCOVERED_ITEMS_WINDOW.SIZE.y + 2);
		public static final Point SIZE = new Point(180, 90);

		public class TITLE {
			public static final Point POS = new Point(INVENTORY.POS.x, INVENTORY.POS.y);
			public static final Point SIZE = new Point(INVENTORY.SIZE.x,12);


		}

		public class ITEM {
			public static final Point POS = new Point(INVENTORY.POS.x + 3, INVENTORY.TITLE.POS.y + INVENTORY.TITLE.SIZE.y+ 0);
			public static final Point SIZE = new Point(18, 18);
			public static final Point GAP_SIZE = new Point(1, 1);
		}

		public static final List<Integer> INVENTORY_ACTUAL_INDEX_LIST = getInventoryActualIndex();

		public static final List<Point> INVENTORY_ITEMS_POS_LIST = GuiFunctions.generateIconPosList(36, 9,
				INVENTORY.ITEM.SIZE, INVENTORY.ITEM.GAP_SIZE, INVENTORY.ITEM.POS);

		public static List<InventoryItem> generateInventoryItemList() {
			List<InventoryItem> res = new ArrayList<InventoryItem>();

			int index = 9;

			int listIndex = 0;

			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 9; col++) {

					res.add(new InventoryItem(listIndex, index, INVENTORY_ITEMS_POS_LIST.get(listIndex),
							ITEM.SIZE));

					index++;
					listIndex++;
				}
			}

			index = 0;

			for (int col = 0; col < 9; col++) {

				res.add(new InventoryItem(listIndex, index, INVENTORY_ITEMS_POS_LIST.get(listIndex),
						ITEM.SIZE));

				index++;
				listIndex++;
			}

			return res;
		}

		private static List<Integer> getInventoryActualIndex() {
			List<Integer> res = new ArrayList<Integer>();

			int index = 9;

			int listIndex = 0;

			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 9; col++) {

					res.add(index);

					index++;
					listIndex++;
				}
			}

			index = 0;

			for (int col = 0; col < 9; col++) {

				res.add(index);

				index++;
				listIndex++;
			}

			return res;
		}
	}
}
	
	



