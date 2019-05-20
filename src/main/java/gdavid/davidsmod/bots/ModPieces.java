package gdavid.davidsmod.bots;

import gdavid.davidsmod.api.bots.Piece;
import gdavid.davidsmod.api.bots.PieceResult;
import gdavid.davidsmod.util.CreatePieceUtil;
import gdavid.davidsmod.util.RegUtil;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ModPieces {
	
	// TODO actions
	
	public static final Piece follow_player = RegUtil.regPiece(CreatePieceUtil.piece("follow_player", ModPieceCategories.movement, ctx -> PieceResult.next));
	public static final Piece follow_target = RegUtil.regPiece(CreatePieceUtil.piece("follow_target", ModPieceCategories.movement, ctx -> PieceResult.next));
	public static final Piece go_to_chest = RegUtil.regPiece(CreatePieceUtil.piece("go_to_chest", ModPieceCategories.movement, ctx -> PieceResult.next));
	public static final Piece go_to_marker = RegUtil.regPiece(CreatePieceUtil.piece("go_to_marker", ModPieceCategories.movement, ctx -> PieceResult.next));
	
	public static final Piece avoid_chest = RegUtil.regPiece(CreatePieceUtil.piece("avoid_chest", ModPieceCategories.movement, ctx -> PieceResult.next));
	public static final Piece avoid_marker = RegUtil.regPiece(CreatePieceUtil.piece("avoid_marker", ModPieceCategories.movement, ctx -> PieceResult.next));
	public static final Piece avoid_monster = RegUtil.regPiece(CreatePieceUtil.piece("avoid_monster", ModPieceCategories.movement, ctx -> PieceResult.next));
	public static final Piece avoid_player = RegUtil.regPiece(CreatePieceUtil.piece("avoid_player", ModPieceCategories.movement, ctx -> PieceResult.next));
	public static final Piece avoid_sun = RegUtil.regPiece(CreatePieceUtil.piece("avoid_sun", ModPieceCategories.movement, ctx -> PieceResult.next));
	public static final Piece avoid_target = RegUtil.regPiece(CreatePieceUtil.piece("avoid_target", ModPieceCategories.movement, ctx -> PieceResult.next));
	
	public static final Piece switch_state = RegUtil.regPiece(CreatePieceUtil.piece("switch_state", ModPieceCategories.state, ctx -> PieceResult.next));
	
	public static final Piece trigger_in_range = RegUtil.regPiece(CreatePieceUtil.piece("trigger_in_range", ModPieceCategories.state, ctx -> PieceResult.next));
	public static final Piece trigger_redstone_signal = RegUtil.regPiece(CreatePieceUtil.piece("trigger_redstone_signal", ModPieceCategories.state, ctx -> PieceResult.next));
	public static final Piece trigger_right_clicked = RegUtil.regPiece(CreatePieceUtil.piece("trigger_right_clicked", ModPieceCategories.state, ctx -> PieceResult.next));
	
	public static final Piece melee_attack = RegUtil.regPiece(CreatePieceUtil.piece("melee_attack", ModPieceCategories.interaction, ctx -> PieceResult.next));
	public static final Piece ranged_attack = RegUtil.regPiece(CreatePieceUtil.piece("ranged_attack", ModPieceCategories.interaction, ctx -> PieceResult.next));

	public static final Piece place_block = RegUtil.regPiece(CreatePieceUtil.piece("place_block", ModPieceCategories.interaction, ctx -> PieceResult.next));
	public static final Piece break_block = RegUtil.regPiece(CreatePieceUtil.piece("break_block", ModPieceCategories.interaction, ctx -> PieceResult.next));

	public static final Piece put_in_chest = RegUtil.regPiece(CreatePieceUtil.piece("put_in_chest", ModPieceCategories.interaction, ctx -> PieceResult.next));
	public static final Piece take_from_chest = RegUtil.regPiece(CreatePieceUtil.piece("take_from_chest", ModPieceCategories.interaction, ctx -> PieceResult.next));
	
	public static final Piece target = RegUtil.regPiece(CreatePieceUtil.piece("target", ModPieceCategories.targetting, ctx -> PieceResult.next));
	
	public static void reg() {
		// used to explicitly initialize static fields, thus registering the pieces
	}
	
}
