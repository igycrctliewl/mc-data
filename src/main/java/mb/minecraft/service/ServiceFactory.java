package mb.minecraft.service;

import mb.minecraft.service.impl.VillageServiceImpl;

/**
 *
 * @author mikebro
 */
public class ServiceFactory {

	private static final VillageService villageService =
			  VillageServiceImpl.getInstance();

	public static VillageService getVillageServiceInstance() {
		return villageService;
	}

}
