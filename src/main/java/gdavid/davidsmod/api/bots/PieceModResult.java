package gdavid.davidsmod.api.bots;

public class PieceModResult {
	
	public static final PieceModResult normal = new PieceModResult();
	public static final PieceModResult skip = new PieceModResult(1);
	public static final PieceModResult fault_normal = new PieceModResult(true);
	public static final PieceModResult fault_skip = new PieceModResult(1, false, true);
	
	public int execStep;
	public boolean absolute;
	public boolean fault;
	
	public PieceModResult() {
		this(0);
	}
	
	public PieceModResult(boolean fault) {
		this(0, false, fault);
	}
	
	public PieceModResult(int execStep) {
		this(execStep, false);
	}
	
	public PieceModResult(int execStep, boolean absolute) {
		this(execStep, absolute, false);
	}
	
	public PieceModResult(int execStep, boolean absolute, boolean fault) {
		this.execStep = execStep;
		this.absolute = absolute;
		this.fault = fault;
	}
	
}
