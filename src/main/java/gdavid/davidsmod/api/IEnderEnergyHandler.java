package gdavid.davidsmod.api;

public interface IEnderEnergyHandler {
	
	public int getEnderEnergy();
	
	public void setEnderEnergy(int e);
	
	public int getEnderEnergyLimit();
	
	public default int insertEnderEnergy(int e) {
		if (!canInsertEnderEnergy()) return e;
		if (getEnderEnergy() + e <= getEnderEnergyLimit()) {
			setEnderEnergy(getEnderEnergy() + e);
			return 0;
		} else {
			int r = getEnderEnergyLimit() - getEnderEnergy();
			setEnderEnergy(getEnderEnergyLimit());
			return e - r;
		}
	}
	
	public default int extractEnderEnergy(int e) {
		if (!canExtractEnderEnergy()) return 0;
		if (getEnderEnergy() < e) {
			int r = getEnderEnergy();
			setEnderEnergy(0);
			return r;
		} else {
			setEnderEnergy(getEnderEnergy() - e);
			return e;
		}
	}
	
	public boolean canInsertEnderEnergy();
	
	public boolean canExtractEnderEnergy();
	
}
