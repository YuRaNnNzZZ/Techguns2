package techguns;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import techguns.blocks.EnumOreType;
import techguns.items.armors.ICamoChangeable;
import techguns.tileentities.operation.AmmoPressBuildPlans;
import techguns.tileentities.operation.CamoBenchRecipes;
import techguns.tileentities.operation.CamoBenchRecipes.CamoBenchRecipe;
import techguns.tileentities.operation.ChargingStationRecipe;
import techguns.tileentities.operation.ChemLabRecipes;
import techguns.tileentities.operation.FabricatorRecipe;
import techguns.tileentities.operation.MetalPressRecipes;
import techguns.tileentities.operation.ReactionBeamFocus;
import techguns.tileentities.operation.ReactionChamberRecipe;
import techguns.tileentities.operation.ReactionChamberRecipe.RiskType;
import techguns.util.ItemStackOreDict;
import techguns.util.ItemUtil;

/**
* Class that contains recipes for machines
*
*/
public class TGMachineRecipes {
		
	public static void addRecipes(){
		//AMMO PRESS
		ArrayList<String> metal2 = new ArrayList<String>();
		ArrayList<String> metal1 = new ArrayList<String>();
		ArrayList<String> powders = new ArrayList<String>();
		metal2.add("ingotCopper");
		metal2.add("ingotTin");
		metal2.add("ingotIron");
		metal2.add("ingotBronze");
		metal1.add("ingotSteel");
		metal1.add("ingotLead");
		powders.add("gunpowder");
		AmmoPressBuildPlans.init(metal1, metal2, powders);
		
		//METAL PRESS
		MetalPressRecipes.addRecipe("ingotSteel", "obsidian", TGItems.newStackByOreDict("ingotObsidianSteel", 1), true);

		// Plates
		MetalPressRecipes.addRecipe("ingotTin","ingotTin", TGItems.newStackByOreDict("plateTin", 2), true);
		MetalPressRecipes.addRecipe("ingotCopper","ingotCopper", TGItems.newStackByOreDict("plateCopper", 2), true);
		MetalPressRecipes.addRecipe("ingotBronze","ingotBronze", TGItems.newStackByOreDict("plateBronze", 2), true);
		MetalPressRecipes.addRecipe("ingotIron","ingotIron", TGItems.newStackByOreDict("plateIron", 2), true);
		MetalPressRecipes.addRecipe("ingotSteel","ingotSteel", TGItems.newStackByOreDict("plateSteel", 2), true);
		MetalPressRecipes.addRecipe("ingotLead","ingotLead", TGItems.newStackByOreDict("plateLead", 2), true);
		MetalPressRecipes.addRecipe("ingotObsidianSteel", "ingotObsidianSteel", TGItems.newStackByOreDict("plateObsidianSteel", 2), true);
		MetalPressRecipes.addRecipe("ingotTitanium","ingotTitanium", TGItems.newStackByOreDict("plateTitanium", 2), true);

		for (String entryName : OreDictionary.getOreNames()) {
			if (entryName.startsWith("plate")) {
				ItemStack result = TGItems.newStackByOreDict(entryName, 2);

				if (MetalPressRecipes.getRecipesFor(result) != null) {
					continue;
				}

				String ingotOreName = entryName.replaceFirst("^plate", "ingot");

				if (ItemUtil.existsInOredict(ingotOreName) && ItemUtil.existsInOredict(entryName.replaceFirst("^plate", "nugget"))) {
					MetalPressRecipes.addRecipe(ingotOreName, ingotOreName, TGItems.newStackByOreDict(entryName, 2), true);
				}
			}
		}

		MetalPressRecipes.addRecipe("fiberCarbon", "fiberCarbon", TGItems.newStack(TGItems.PLATE_CARBON, 2), true);
		MetalPressRecipes.addRecipe("plateSteel", "plateBronze", TGItems.newStack(TGItems.STEAMARMOR_PLATE, 1), true);

		// Wires
		MetalPressRecipes.addRecipe("plateCopper", "plateCopper", TGItems.newStackByOreDict("wireCopper", 8), true);

		if (ItemUtil.existsInOredict("plateGold")) {
			MetalPressRecipes.addRecipe("plateGold", "plateGold", TGItems.newStackByOreDict("wireGold", 8), true);
		} else {
			MetalPressRecipes.addRecipe("ingotGold", "ingotGold", TGItems.newStackByOreDict("wireGold", 2), true);
		}

		// Mechanical Parts
		MetalPressRecipes.addRecipe("plateIron", new ItemStack(Items.FLINT,1), TGItems.newStack(TGItems.MECHANICAL_PARTS_IRON, 1), true);
		MetalPressRecipes.addRecipe("plateObsidianSteel", "gemQuartz", TGItems.newStack(TGItems.MECHANICAL_PARTS_OBSIDIAN_STEEL, 1), true);
		MetalPressRecipes.addRecipe("plateCarbon", new ItemStack(Items.BLAZE_ROD), TGItems.newStack(TGItems.MECHANICAL_PARTS_CARBON, 2), true);

		// Ammo
		MetalPressRecipes.addRecipe("plateObsidianSteel", TGItems.newStack(TGItems.TGX, 1), TGItems.newStack(TGItems.ADVANCED_ROUNDS, 16), true);
		MetalPressRecipes.addRecipe("plateIron", new ItemStack(Blocks.TNT,1), TGItems.newStack(TGItems.GRENADE_40MM, 16), true);
		MetalPressRecipes.addRecipe("plateObsidianSteel","plateTitanium",TGItems.newStack(TGItems.GAUSSRIFLE_SLUGS,4), true);
		
		//CHEM LAB

		if(ItemUtil.existsInOredict("dustCoal")){
			ChemLabRecipes.addRecipe("dustRedstone",1, "dustCoal",1, null,0, new FluidStack(FluidRegistry.WATER,250), null, new ItemStack(Items.GUNPOWDER,1), true,5);
		} else {
			ChemLabRecipes.addRecipe("dustRedstone",1, new ItemStack(Items.COAL),1, null,0, new FluidStack(FluidRegistry.WATER,250), null, new ItemStack(Items.GUNPOWDER,1), true,5);
		}
		
		if(!TGFluids.oils.isEmpty()){
			//ChemLabRecipes.addRecipe("slimeball", 1, new ItemStack(Items.COAL), 1, null, 0, new FluidStack(FluidRegistry.WATER,1000), new FluidStack(TGFluids.OIL,250), null, true, 20);
			
			TGFluids.oils.forEach(f -> {
				ChemLabRecipes.addRecipe("itemRawRubber", 1, (ItemStack)null, 0, null, 0, new FluidStack(f,500), null, TGItems.newStack(TGItems.RAW_PLASTIC, 1), false, 25);
			});
		} else {
			ChemLabRecipes.addRecipe("itemRawRubber", 1, new ItemStack(Items.COAL,1), 1, null, 0, new FluidStack(TGFluids.WATER,1000), null, TGItems.newStack(TGItems.RAW_PLASTIC, 1), true, 25);
		}
		
		ChemLabRecipes.addRecipe(TGItems.BIOMASS, 1, new ItemStack(Items.GUNPOWDER), 1, null, 0, new FluidStack(TGFluids.WATER,1000), new FluidStack(TGFluids.ACID,1000), null, true, 20);
		
		if (ItemUtil.existsInOredict("itemBioFuel")){
			ChemLabRecipes.addRecipe("itemBioFuel",4, TGItems.newStack(TGItems.BIO_TANK_EMPTY,1), 1, null, 0, new FluidStack(TGFluids.WATER,500), null, TGItems.newStack(TGItems.BIO_TANK, 1), false, 1);
			ChemLabRecipes.addRecipe("itemBioFuel",4, new ItemStack(Items.GUNPOWDER), 1, null, 0, new FluidStack(TGFluids.WATER,1000), new FluidStack(TGFluids.ACID,1000), null, false, 20);
				
		}
		
		
		ItemStack fuelTank = TGItems.newStack(TGItems.FUEL_TANK, 1);
		ItemStack fuelTankEmpty = TGItems.newStack(TGItems.FUEL_TANK_EMPTY, 1);
		if(!TGFluids.fuels.isEmpty()){
			TGFluids.fuels.forEach(f -> {
				ChemLabRecipes.addRecipe("gunpowder", 1, "gemLapis", 1, null, 0, new FluidStack(f,250), null, TGItems.newStack(TGItems.TGX, 1), true, 20);
				ChemLabRecipes.addRecipe(fuelTankEmpty, 1, (ItemStack)null, 0, null, 0, new FluidStack(f,250), null, fuelTank, false, 1);
				//ChemLabRecipes.addRecipe(fuelTank, 1, (ItemStack)null, 0, null, 0, null, new FluidStack(f,250), fuelTankEmpty, false, 1);
			});
		} else if (TGFluids.fuels.isEmpty() || TGConfig.keepLavaRecipesWhenFuelIsPresent){
			ChemLabRecipes.addRecipe("gunpowder", 1, "gemLapis", 1, null, 0, new FluidStack(FluidRegistry.LAVA,500), null, TGItems.newStack(TGItems.TGX, 1), true, 20);
			//ChemLabRecipes.addRecipe(fuelTankEmpty, 1, (ItemStack)null, 0, null, 0, new FluidStack(TGFluids.LAVA,500), null, fuelTank, false, 1);
			ChemLabRecipes.addRecipe(fuelTank, 1, (ItemStack)null, 0, null, 0, null,new FluidStack(TGFluids.LAVA,500), fuelTankEmpty, false, 1);
			
		}
		
		ChemLabRecipes.addRecipe("gemDiamond", 1, new ItemStack(Items.BLAZE_POWDER,1), 1, null, 0, new FluidStack(TGFluids.LAVA,1000), null, TGItems.newStack(TGItems.CARBON_FIBERS, 2), true, 25);
		
		ItemStackOreDict stackLogWood = new ItemStackOreDict("logWood").setNoStrictMode();
		ItemStackOreDict nullStack = new ItemStackOreDict((ItemStack)null);
		ChemLabRecipes.addRecipe(stackLogWood, 1, nullStack, 0, null, 0, new FluidStack(TGFluids.WATER,1000), null, TGItems.newStack(TGItems.RAW_RUBBER, 1), false, 20);
		
		ChemLabRecipes.addRecipe(TGItems.newStack(TGItems.BIOMASS, 1),1, TGItems.newStack(TGItems.BIO_TANK_EMPTY,1), 1, null, 0, new FluidStack(TGFluids.WATER,500), null, TGItems.newStack(TGItems.BIO_TANK, 1), true, 1);
		
		ChemLabRecipes.addRecipe(new ItemStack(Items.COAL,1), 1, TGItems.newStack(TGItems.COMPRESSED_AIR_TANK_EMPTY, 1), 1, null, 0, new FluidStack(TGFluids.WATER,250), null, TGItems.newStack(TGItems.COMPRESSED_AIR_TANK, 1), true, 5);
		
		ChemLabRecipes.addRecipe(new ItemStack(Blocks.NETHERRACK), 1, new ItemStack(Blocks.SOUL_SAND), 1, null, 0, new FluidStack(TGFluids.LAVA,1000), null, TGItems.newStack(TGItems.NETHER_CHARGE, 4), true, 20);
		
		ItemStackOreDict uranium = new ItemStackOreDict("oreUranium").setNoStrictMode();

		ChemLabRecipes.addRecipe(uranium, 1, nullStack, 0, null, 0, new FluidStack(TGFluids.ACID,250), null, TGItems.newStack(TGItems.YELLOWCAKE, 1), false, 20);
		
		ChemLabRecipes.addRecipe(TGItems.NUCLEAR_POWERCELL_EMPTY, 1, TGItems.ENRICHED_URANIUM, 1, null, 0, new FluidStack(TGFluids.WATER,1000), null, TGItems.newStack(TGItems.NUCLEAR_POWERCELL, 1), true, 40);
		
		if (TGFluids.MILK!=null){
			ChemLabRecipes.addRecipe(new ItemStack(Items.DYE,1,2), 1, "itemRawRubber", 1, null, 0, new FluidStack(TGFluids.MILK,500), null, new ItemStack(Items.SLIME_BALL), true, 25);
		}
		
		ChemLabRecipes.addRecipe(new ItemStack(Items.LEATHER,2), 2, "slimeball", 1, null, 0, new FluidStack(TGFluids.ACID,500), null, TGItems.newStack(TGItems.TREATED_LEATHER,2), false, 20);

		ChemLabRecipes.addRecipe(new ItemStack(Items.ROTTEN_FLESH), 1, (ItemStack)null, 0, null, 0, new FluidStack(TGFluids.WATER,500), null, new ItemStack(Items.LEATHER,1), false, 15);
		
		ChemLabRecipes.addRecipe(new ItemStack(Blocks.GRAVEL), 1, new ItemStack(Blocks.SAND), 1, null, 0, new FluidStack(TGFluids.WATER,250), null, new ItemStack(Blocks.CONCRETE,2,8), false, 5);
		ChemLabRecipes.addRecipe(new ItemStack(Blocks.GRAVEL), 1, new ItemStack(Blocks.DIRT), 1, null, 0, new FluidStack(TGFluids.WATER,250), null, new ItemStack(Blocks.CLAY,2), false, 5);

		
		ChemLabRecipes.addRecipe(TGItems.RIFLE_ROUNDS, 1, new ItemStack(Items.BLAZE_POWDER), 1, null, 0, new FluidStack(TGFluids.LAVA,250), null, TGItems.newStack(TGItems.RIFLE_ROUNDS_INCENDIARY,1), false, 25);
		ChemLabRecipes.addRecipe(TGItems.PISTOL_ROUNDS, 2, new ItemStack(Items.BLAZE_POWDER), 1, null, 0, new FluidStack(TGFluids.LAVA,250), null, TGItems.newStack(TGItems.PISTOL_ROUNDS_INCENDIARY,2), false, 25);
		ChemLabRecipes.addRecipe(TGItems.SNIPER_ROUNDS, 1, new ItemStack(Items.BLAZE_POWDER), 1, null, 0, new FluidStack(TGFluids.LAVA,250), null, TGItems.newStack(TGItems.SNIPER_ROUNDS_INCENDIARY,1), false, 25);
		ChemLabRecipes.addRecipe(TGItems.SHOTGUN_ROUNDS, 8, new ItemStack(Items.BLAZE_POWDER), 1, null, 0, new FluidStack(TGFluids.LAVA,250), null, TGItems.newStack(TGItems.SHOTGUN_ROUNDS_INCENDIARY,8), false, 25);
		
		
		/**
		 * FABRICATOR
		 */
		FabricatorRecipe.addRecipe(new ItemStackOreDict("ingotGold"), 1, FabricatorRecipe.copperWires, 1, FabricatorRecipe.redstone, 3, FabricatorRecipe.plastic, 1, TGItems.ENERGY_CELL_EMPTY, 1);
		FabricatorRecipe.addRecipe(new ItemStackOreDict(new ItemStack(Blocks.SOUL_SAND,1)), 1, FabricatorRecipe.goldWires, 1, FabricatorRecipe.redstone, 1, FabricatorRecipe.plastic, 1, TGItems.CYBERNETIC_PARTS, 1);
		FabricatorRecipe.addRecipe(new ItemStackOreDict("ingotTitanium"), 2, FabricatorRecipe.circuit_basic, 4, FabricatorRecipe.mechanicalPartsT3, 1, FabricatorRecipe.carbonPlate, 4, TGItems.POWER_ARMOR_PLATING, 2);
		FabricatorRecipe.addRecipe(new ItemStackOreDict(TGItems.newStack(TGItems.COIL, 1)), 1, FabricatorRecipe.circuit_elite, 2, FabricatorRecipe.mechanicalPartsT3, 1, FabricatorRecipe.titaniumPlate, 1, TGItems.SONIC_EMITTER, 1);		
		FabricatorRecipe.addRecipe(new ItemStackOreDict(TGItems.newStack(TGItems.ENRICHED_URANIUM, 1)), 1, FabricatorRecipe.circuit_elite, 2, FabricatorRecipe.mechanicalPartsT3, 2, FabricatorRecipe.leadPlate, 2, TGItems.RAD_EMITTER, 1);		
		
		FabricatorRecipe.addRecipe(new ItemStackOreDict("ingotSteel", 2), 1, FabricatorRecipe.circuit_basic, 1, FabricatorRecipe.redstone, 4, FabricatorRecipe.leadPlate, 2, TGItems.NUCLEAR_POWERCELL_EMPTY, 1);		
		
		/**
		 * Camo Bench
		 */
		CamoBenchRecipes.addRecipe(new CamoBenchRecipe(Blocks.WOOL));
		CamoBenchRecipes.addRecipe(new CamoBenchRecipe(Blocks.CONCRETE));
		CamoBenchRecipes.addRecipe(new CamoBenchRecipe(Blocks.CONCRETE_POWDER));
		CamoBenchRecipes.addRecipe(new CamoBenchRecipe(Blocks.STAINED_HARDENED_CLAY));
		CamoBenchRecipes.addRecipe(new CamoBenchRecipe(Blocks.STAINED_GLASS));
		CamoBenchRecipes.addRecipe(new CamoBenchRecipe(Blocks.STAINED_GLASS_PANE));
		//CamoBenchRecipes.addRecipe(new CamoBenchRecipe(Blocks.BED));
		CamoBenchRecipes.addRecipe(new CamoBenchRecipe(Blocks.STANDING_BANNER));
		CamoBenchRecipes.addRecipe(new CamoBenchRecipe(Blocks.CARPET));
		
		TGBlocks.BLOCKLIST.forEach(b -> {
			if (b instanceof ICamoChangeable) {
				if (((ICamoChangeable)b).addBlockCamoChangeRecipes() ) {
					CamoBenchRecipes.addRecipe(new CamoBenchRecipe((Block)b, (ICamoChangeable)b));
				}
			}
		});
		CamoBenchRecipes.addRecipe(new CamoBenchRecipes.TGLampCamoBenchRecipe(TGBlocks.LAMP_0,0));
		
		
		/**
		 * CHARGING STATION
		 */
		ChargingStationRecipe.addRecipe(new ItemStackOreDict(TGItems.ENERGY_CELL_EMPTY), TGItems.ENERGY_CELL, 50000);
		ChargingStationRecipe.addRecipe(new ItemStackOreDict(TGItems.REDSTONE_BATTERY_EMPTY), TGItems.REDSTONE_BATTERY, 10000);
		
		
		/**
		 * REACTION CHAMBER
		 */
		//Beam focus
		ReactionBeamFocus.addBeamFocus(TGItems.RC_HEAT_RAY, 100, TGSounds.REACTION_CHAMBER_HEATRAY_WORK);
		ReactionBeamFocus.addBeamFocus(TGItems.RC_UV_EMITTER, 100, TGSounds.CHEM_LAB_WORK);
		
		//Recipes
										/**
										 * ID (STR), INPUT-STACK, FOCUS, FLUID_TYPE, ITEM_OUTPUTS, TICKS, REQ_COMPLETION, INTENSITY, INTENSITY_MARGIN, LIQUIDLEVEL, LIQUID_CONSUMPTION, INSTABILITY_CHANCE, RISK, RF_TICK
										 */
		ReactionChamberRecipe.addRecipe("RC_UV_WHEAT",new ItemStackOreDict(new ItemStack(Items.WHEAT_SEEDS, 1)), TGItems.RC_UV_EMITTER, FluidRegistry.WATER, new ItemStack[]{new ItemStack(Items.WHEAT,1), new ItemStack(Items.WHEAT_SEEDS,2)}, 10, 5, 3, 1, 1, 1000, 0.5f, RiskType.BREAK_ITEM,50000);
		
		ReactionChamberRecipe.addRecipe("RC_LASER_FOCUS",new ItemStackOreDict("gemDiamond",1), TGItems.RC_HEAT_RAY, TGFluids.LIQUID_REDSTONE, new ItemStack[]{TGItems.newStack(TGItems.LASER_FOCUS, 1)}, 10, 5, 3, 1, 4, 4000, 0.5f, RiskType.BREAK_ITEM,100000);
		
		ReactionChamberRecipe.addRecipe("RC_TITANIUM",new ItemStackOreDict(new ItemStack(TGBlocks.TG_ORE, 1,EnumOreType.ORE_TITANIUM.ordinal())), TGItems.RC_HEAT_RAY, TGFluids.ACID, new ItemStack[]{TGItems.newStack(TGItems.ORE_TITANIUM, 2),new ItemStack(Blocks.IRON_ORE, 1)}, 2, 1, 5, 0, 3, 100,0f, RiskType.BREAK_ITEM, 25000);
		
		ReactionChamberRecipe.addRecipe("RC_BLAZEROD",new ItemStackOreDict(TGItems.newStack(TGItems.QUARTZ_ROD, 1)), TGItems.RC_HEAT_RAY, TGFluids.LAVA, new ItemStack[]{new ItemStack(Items.BLAZE_ROD, 1)}, 5, 3, 7, 2, 4, 1000,0.5f, RiskType.BREAK_ITEM, 250000);
		
		ReactionChamberRecipe.addRecipe("RC_GLOWSTONE",new ItemStackOreDict("blockRedstone"), TGItems.RC_HEAT_RAY, TGFluids.LAVA, new ItemStack[]{new ItemStack(Blocks.GLOWSTONE, 1)}, 5, 3, 7, 2, 4, 1000,0.5f, RiskType.EXPLOSION_MEDIUM, 250000);
		
		ReactionChamberRecipe.addRecipe("RC_ANTIGRAV",new ItemStackOreDict(new ItemStack(Items.NETHER_STAR)), TGItems.RC_HEAT_RAY, TGFluids.LIQUID_ENDER, new ItemStack[]{TGItems.newStack(TGItems.ANTI_GRAV_CORE, 1)}, 10, 7, 8, 2, 4, 4000, 1f, RiskType.EXPLOSION_LOW, 500000);
		
		ReactionChamberRecipe.addRecipe("RC_URANIUM",new ItemStackOreDict("dustUranium"), TGItems.RC_HEAT_RAY, TGFluids.WATER, new ItemStack[]{TGItems.newStack(TGItems.ENRICHED_URANIUM, 1)}, 5, 4, 7, 0, 3, 1000, 0f, RiskType.BREAK_ITEM, 250000);
		
		
		//Smelting recipes
		GameRegistry.addSmelting(TGBlocks.TG_ORE.getStackFor(EnumOreType.ORE_COPPER), TGItems.INGOT_COPPER, 0.5f);
		GameRegistry.addSmelting(TGBlocks.TG_ORE.getStackFor(EnumOreType.ORE_TIN), TGItems.INGOT_TIN, 0.5f);
		GameRegistry.addSmelting(TGBlocks.TG_ORE.getStackFor(EnumOreType.ORE_LEAD), TGItems.INGOT_LEAD, 1.0f);
		
		GameRegistry.addSmelting(TGItems.TURRET_ARMOR_IRON, new ItemStack(Items.IRON_INGOT,5), 0f);
		GameRegistry.addSmelting(TGItems.TURRET_ARMOR_STEEL, TGItems.newStack(TGItems.INGOT_STEEL,5), 0f);
		GameRegistry.addSmelting(TGItems.TURRET_ARMOR_OBSIDIAN_STEEL, TGItems.newStack(TGItems.INGOT_OBSIDIAN_STEEL,5), 0f);
		
		GameRegistry.addSmelting(TGItems.BARREL_IRON, new ItemStack(Items.IRON_INGOT,6), 0f);
		GameRegistry.addSmelting(TGItems.BARREL_OBSIDIAN_STEEL, TGItems.newStack(TGItems.INGOT_OBSIDIAN_STEEL,6), 0f);
		
		GameRegistry.addSmelting(TGItems.PLATE_BRONZE, TGItems.INGOT_BRONZE, 0f);
		GameRegistry.addSmelting(TGItems.PLATE_COPPER, TGItems.INGOT_COPPER, 0f);
		GameRegistry.addSmelting(TGItems.PLATE_TIN, TGItems.INGOT_TIN, 0f);
		GameRegistry.addSmelting(TGItems.PLATE_LEAD, TGItems.INGOT_LEAD, 0f);
		GameRegistry.addSmelting(TGItems.PLATE_IRON, new ItemStack(Items.IRON_INGOT,1), 0f);
		GameRegistry.addSmelting(TGItems.PLATE_OBSIDIAN_STEEL, TGItems.INGOT_OBSIDIAN_STEEL, 0f);
		GameRegistry.addSmelting(TGItems.PLATE_TITANIUM, TGItems.INGOT_TITANIUM, 0f);
		GameRegistry.addSmelting(TGItems.PLATE_STEEL, TGItems.INGOT_STEEL, 0f);
		
		GameRegistry.addSmelting(TGItems.RAW_RUBBER, TGItems.RUBBER_BAR,0);
		GameRegistry.addSmelting(TGItems.RAW_PLASTIC,TGItems.PLASTIC_SHEET, 0);
		
		GameRegistry.addSmelting(TGItems.ORE_TITANIUM, TGItems.INGOT_TITANIUM, 0);
	
	}
}