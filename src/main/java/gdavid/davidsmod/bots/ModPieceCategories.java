package gdavid.davidsmod.bots;

import gdavid.davidsmod.api.bots.PieceCategory;
import gdavid.davidsmod.util.CreatePieceUtil;
import gdavid.davidsmod.util.RegUtil;

public class ModPieceCategories {
	
	public static final PieceCategory targetting = RegUtil.regPieceCategory(CreatePieceUtil.category("targetting"));
	public static final PieceCategory movement = RegUtil.regPieceCategory(CreatePieceUtil.category("movement"));
	public static final PieceCategory interaction = RegUtil.regPieceCategory(CreatePieceUtil.category("interaction"));
	public static final PieceCategory identification = RegUtil.regPieceCategory(CreatePieceUtil.category("identification"));
	public static final PieceCategory state = RegUtil.regPieceCategory(CreatePieceUtil.category("state"));
	public static final PieceCategory boosters = RegUtil.regPieceCategory(CreatePieceUtil.category("boosters"));
	
	public static void reg() {
		// used to explicitly initialize static fields, thus registering the categories
	}
	
}
