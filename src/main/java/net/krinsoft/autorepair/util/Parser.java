package net.krinsoft.autorepair.util;

/**
 *
 * @author krinsdeath
 */
public class Parser {

    public static String getType(String input) {
        if (input.contains("WOOD") || input.contains("BOW")) { return "wood"; }
        if (input.contains("STONE")) { return "stone"; }
        if (input.contains("IRON") || input.contains("SHEARS")) { return "iron"; }
        if (input.contains("GOLD")) { return "gold"; }
        if (input.contains("DIAMOND")) { return "diamond"; }
        if (input.contains("LEATHER")) { return "leather"; }
        if (input.contains("CHAINMAIL")) { return "chainmail"; }
        return null;
    }

    public static String getTool(String input) {
        if (input.contains("BOW")) { return "bow"; }
        if (input.contains("HOE")) { return "hoe"; }
        if (input.contains("SPADE")) { return "spade"; }
        if (input.contains("SWORD")) { return "sword"; }
        if (input.contains("PICKAXE")) { return "pickaxe"; }
        if (input.contains("AXE")) { return "axe"; }
        if (input.contains("SHEARS")) { return "shears"; }
        if (input.contains("HELMET")) { return "helmet"; }
        if (input.contains("CHESTPLATE")) { return "chestplate"; }
        if (input.contains("LEGGINGS")) { return "leggings"; }
        if (input.contains("BOOTS")) { return "boots"; }
        return null;
    }

}
