package de.kenneinname.spastattack.utils;

import de.kenneinname.spastattack.Spastattack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class RecipeLoader {

    public void registerRecipes() {
        ItemStack lightStack = new ItemStack(Material.LIGHT, 4);
        ItemMeta lightMeta = lightStack.getItemMeta();
        lightStack.setItemMeta(lightMeta);

        NamespacedKey recipeKey = new NamespacedKey(Spastattack.getSpastattack(), "light_recipe");

        ShapelessRecipe lightRecipe = new ShapelessRecipe(recipeKey, lightStack);
        lightRecipe.addIngredient(Material.GLOWSTONE);
        lightRecipe.addIngredient(Material.SEA_LANTERN);

        Bukkit.addRecipe(lightRecipe);
    }

}
