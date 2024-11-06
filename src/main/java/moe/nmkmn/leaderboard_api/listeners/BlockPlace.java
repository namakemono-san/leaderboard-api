package moe.nmkmn.leaderboard_api.listeners;

import moe.nmkmn.leaderboard_api.Leaderboard_API;
import moe.nmkmn.leaderboard_api.utils.Cache;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {
    private final Leaderboard_API plugin;

    public BlockPlace(Leaderboard_API plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if (plugin.getConfig().getList("general.ignore_blocks").contains(block.getType().toString())) {
            return;
        }

        Cache cache = new Cache(plugin);

        Long cacheValue = cache.getCache("blockPlace", player.getUniqueId().toString());

        if (cacheValue != null) {
            cache.saveCache(player, "blockPlace", cacheValue + 1);
        } else {
            cache.saveCache(player, "blockPlace", 1);
        }
    }
}
