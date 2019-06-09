package gdavid.davidsmod.tile;

import static gdavid.davidsmod.util.RegUtil.*;

public class ModTiles {
	
	public static void reg() {
		regTe("storage_crate", TileStorageCrate.class);
		regTe("miracle_condenser", TileMiracleCondenser.class);
		regTe("ender_energy_condenser", TileEnderEnergyCondenser.class);
	}
	
}
