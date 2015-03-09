/*
 * Copyright 2015 Demigods RPG
 * Copyright 2015 Alexander Chauncey
 * Copyright 2015 Alex Bennett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demigodsrpg.game.gui;

import com.demigodsrpg.game.DGGame;
import com.demigodsrpg.game.model.ShrineModel;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShrineGUI implements IInventoryGUI {
    public static final String INVENTORY_NAME = "Shrine Select";

    private final List<Inventory> INVENTORY_LIST;
    private final ImmutableMap<Integer, SlotFunction> FUNCTION_MAP;

    public ShrineGUI(final Player player) {
        // FUNCTION MAP
        ImmutableMap.Builder<Integer, SlotFunction> builder = ImmutableMap.builder();

        for (int i = 0; i < 18; i++) {
            builder.put(i, SlotFunction.WARP);
        }

        builder.put(25, SlotFunction.PREVIOUS_PAGE);
        builder.put(26, SlotFunction.NEXT_PAGE);

        FUNCTION_MAP = builder.build();

        // INVENTORY LIST
        INVENTORY_LIST = new ArrayList<>();
        List<ItemStack> items = new ArrayList<>();
        int count = 0, icount = 0;
        Iterator<ShrineModel> shrines = Iterators.filter(DGGame.SHRINE_R.getRegistered().iterator(), new Predicate<ShrineModel>() {
            @Override
            public boolean apply(ShrineModel model) {
                return model.getOwnerMojangId().equals(player.getUniqueId().toString());
            }
        });
        while (shrines.hasNext()) {
            ShrineModel model = shrines.next();
            final String name = model.getPersistentId();
            final String type = model.getShrineType().name();
            final String owner = DGGame.PLAYER_R.fromId(model.getOwnerMojangId()).getLastKnownName();

            items.add(count, new ItemStack(Material.ENCHANTED_BOOK, 1) {
                {
                    ItemMeta meta = getItemMeta();
                    meta.setDisplayName(name);
                    List<String> lore = Lists.newArrayList(ChatColor.AQUA + type, ChatColor.YELLOW + "Owner: " + ChatColor.LIGHT_PURPLE + owner);
                    meta.setLore(lore);
                    setItemMeta(meta);
                }
            });

            count++;

            if (count % 19 == 0 || !shrines.hasNext()) {
                Inventory inventory = Bukkit.createInventory(player, 27, INVENTORY_NAME + " " + icount);
                for (int i = 0; i < items.size(); i++) {
                    inventory.setItem(i, items.get(i));
                }
                if (icount > 0) {
                    inventory.setItem(25, new ItemStack(Material.PAPER, 1) {
                        {
                            ItemMeta meta = getItemMeta();
                            meta.setDisplayName(ChatColor.GOLD + "< BACK");
                            setItemMeta(meta);
                        }
                    });
                }
                if (shrines.hasNext()) {
                    inventory.setItem(26, new ItemStack(Material.PAPER, 1) {
                        {
                            ItemMeta meta = getItemMeta();
                            meta.setDisplayName(ChatColor.GOLD + "NEXT >");
                            setItemMeta(meta);
                        }
                    });
                }

                items.clear();
                count = 0;

                INVENTORY_LIST.add(inventory);
                icount++;
            }
        }
    }

    @Override
    public Inventory getInventory(Integer... inventory) {
        if (INVENTORY_LIST.size() < 1) {
            return null;
        }
        if (inventory.length == 0) {
            return INVENTORY_LIST.get(0);
        }
        return INVENTORY_LIST.get(inventory[0]);
    }

    @Override
    public SlotFunction getFunction(int slot) {
        if (FUNCTION_MAP.containsKey(slot)) {
            return FUNCTION_MAP.get(slot);
        }
        return SlotFunction.NO_FUNCTION;
    }
}
