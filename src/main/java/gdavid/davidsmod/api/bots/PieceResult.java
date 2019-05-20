package gdavid.davidsmod.api.bots;

public class PieceResult {
	
	public static final PieceResult next = new PieceResult();
	public static final PieceResult skip = new PieceResult(2, false);
	public static final PieceResult wait = new PieceResult(0, false);
	public static final PieceResult loop = new PieceResult(-1, false);
	public static final PieceResult restart = new PieceResult(false);
	public static final PieceResult fault_continue = new PieceResult(1, false, true);
	public static final PieceResult fault_retry = new PieceResult(0, false, true);
	public static final PieceResult fault_restart = new PieceResult(true);
	
	public int nextStep;
	public boolean absolute;
	public boolean fault;
	
	public PieceResult() {
		this(1, false);
	}
	
	public PieceResult(boolean fault) {
		this(0, true, fault);
	}
	
	public PieceResult(int nextStep) {
		this(nextStep, true);
	}
	
	public PieceResult(int nextStep, boolean absolute) {
		this(nextStep, absolute, false);
	}
	
	public PieceResult(int nextStep, boolean absolute, boolean fault) {
		this.nextStep = nextStep;
		this.absolute = absolute;
		this.fault = fault;
	}
	
}
