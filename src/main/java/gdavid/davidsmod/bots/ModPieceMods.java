package gdavid.davidsmod.bots;

import gdavid.davidsmod.api.bots.PieceMod;
import gdavid.davidsmod.util.CreatePieceUtil;
import gdavid.davidsmod.util.RegUtil;

public class ModPieceMods {
	
	// TODO handle in compatible pieces
	
	// TODO skip callback
	public static final PieceMod state_a = RegUtil.regPieceMod(CreatePieceUtil.mod("state_a", ModPieceCategories.state));
	public static final PieceMod state_b = RegUtil.regPieceMod(CreatePieceUtil.mod("state_b", ModPieceCategories.state));
	public static final PieceMod state_c = RegUtil.regPieceMod(CreatePieceUtil.mod("state_c", ModPieceCategories.state));
	public static final PieceMod state_d = RegUtil.regPieceMod(CreatePieceUtil.mod("state_d", ModPieceCategories.state));
	
	public static final PieceMod id_1 = RegUtil.regPieceMod(CreatePieceUtil.mod("id_1", ModPieceCategories.identification));
	public static final PieceMod id_2 = RegUtil.regPieceMod(CreatePieceUtil.mod("id_2", ModPieceCategories.identification));
	public static final PieceMod id_3 = RegUtil.regPieceMod(CreatePieceUtil.mod("id_3", ModPieceCategories.identification));
	public static final PieceMod id_4 = RegUtil.regPieceMod(CreatePieceUtil.mod("id_4", ModPieceCategories.identification));
	
	public static final PieceMod target_chest = RegUtil.regPieceMod(CreatePieceUtil.mod("target_chest", ModPieceCategories.targetting));
	public static final PieceMod target_flag = RegUtil.regPieceMod(CreatePieceUtil.mod("target_flag", ModPieceCategories.targetting));
	public static final PieceMod target_named = RegUtil.regPieceMod(CreatePieceUtil.mod("target_named", ModPieceCategories.targetting));
	public static final PieceMod target_player = RegUtil.regPieceMod(CreatePieceUtil.mod("target_player", ModPieceCategories.targetting));
	public static final PieceMod target_target = RegUtil.regPieceMod(CreatePieceUtil.mod("target_target", ModPieceCategories.targetting));
	
	public static final PieceMod overclock = RegUtil.regPieceMod(CreatePieceUtil.mod("overclock", ModPieceCategories.boosters));
	public static final PieceMod extended_range = RegUtil.regPieceMod(CreatePieceUtil.mod("extended_range", ModPieceCategories.boosters));
	
	public static void reg() {
		// used to explicitly initialize static fields, thus registering the mods
	}

}
