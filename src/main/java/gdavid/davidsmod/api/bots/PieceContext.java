package gdavid.davidsmod.api.bots;

import net.minecraft.entity.Entity;

public class PieceContext {
	
	public final int process;
	public final int step;
	public final Program program;
	public final Piece piece;
	public final PieceMod[] mods;
	public Entity bot;
	
	public PieceContext(Entity bot, Program program, int processId, int stepId) {
		process = processId;
		step = stepId;
		this.program = program;
		piece = program.pieces[processId][step];
		mods = program.mods[processId][step];
		this.bot = bot;
	}
	
}
