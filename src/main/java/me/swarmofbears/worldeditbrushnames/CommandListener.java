package me.swarmofbears.worldeditbrushnames;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CommandListener implements Listener {

    private Logger logger;

    CommandListener(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage();
        try {
            if (command.startsWith("/br ") || command.startsWith("/brush ")) {
                SetItemBrushInfo(e, command);
            } else if (command.startsWith("/mask")) {
                SetItemMaskInfo(e, command);
            }
        } catch (Exception exception) {
            e.getPlayer().sendMessage("[WorldEditBrushNames] Error - see console for details");
            logger.info(exception.getMessage());
        }
    }

    private void SetItemBrushInfo(PlayerCommandPreprocessEvent e, String command) {
        // logger.info("Detected /brush command.  Attempting to set name.  Rev 1.2");
        PlayerInventory playerInventory = e.getPlayer().getInventory();

        ItemStack selectedItem = playerInventory.getItemInMainHand();

        if (selectedItem.getType() == Material.AIR) {
            return;
        }

        // Get rid of the /br || /brush
        String segmentedCommand[] = command.split(" ", 2);
        if (segmentedCommand.length < 2) { // entered just "/brush" or incomplete command
            return;
        }
        String brushText = segmentedCommand[1];

        // Get and Set Item Meta
        ItemMeta selectedItemMeta = selectedItem.getItemMeta();
        selectedItemMeta.setDisplayName(ChatColor.YELLOW + brushText);
        selectedItem.setItemMeta(selectedItemMeta);
        playerInventory.setItemInMainHand(selectedItem);
    }


    private void SetItemMaskInfo(PlayerCommandPreprocessEvent e, String command) {
        // logger.info("Detected /mask command.  Attempting to set lore.  Rev 1.2");
        PlayerInventory playerInventory = e.getPlayer().getInventory();

        ItemStack selectedItem = playerInventory.getItemInMainHand();

        if (selectedItem.getType() == Material.AIR) {
            return;
        }

        // Create lore
        List<String> lore;
        if (command.equals("/mask")) {
            // Cleared the mask
            lore = Arrays.asList(ChatColor.BLUE + "Mask: " + ChatColor.GRAY + "None");
        } else {
            lore = Arrays.asList(ChatColor.BLUE + "Mask: " + command.substring(6));
        }

        // Get and Set Item Meta
        ItemMeta selectedItemMeta = selectedItem.getItemMeta();
        selectedItemMeta.setLore(lore);
        selectedItem.setItemMeta(selectedItemMeta);
        playerInventory.setItemInMainHand(selectedItem);
    }
}
