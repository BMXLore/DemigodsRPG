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

package com.demigodsrpg.enchantment;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

public abstract class CustomEnchantment extends EnchantmentWrapper {
    public CustomEnchantment(int id) {
        super(id);
    }

    @Override
    public abstract boolean canEnchantItem(ItemStack item);

    @Override
    public abstract boolean conflictsWith(Enchantment other);

    @Override
    public abstract EnchantmentTarget getItemTarget();

    @Override
    public abstract int getMaxLevel();

    @Override
    public abstract String getName();

    @Override
    public abstract int getStartLevel();

    public abstract CustomEnchantmentType getType();
}
