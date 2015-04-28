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

package com.demigodsrpg.aspect.bloodlust;

import com.demigodsrpg.ability.Ability;
import com.demigodsrpg.ability.AbilityResult;
import com.demigodsrpg.aspect.Aspect;
import com.demigodsrpg.aspect.Groups;
import com.demigodsrpg.data.DGData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BloodlustAspectIII implements Aspect {
    @Override
    public Group getGroup() {
        return Groups.BLOODLUST_ASPECT;
    }

    @Override
    public int getId() {
        return 12;
    }

    @Override
    public String getInfo() {
        return "Mastery over bloodlust.";
    }

    @Override
    public Tier getTier() {
        return Tier.III;
    }

    @Override
    public String name() {
        return "Bloody Sacrament";
    }

    @Ability(name = "Mighty Fists", info = "Attacking with no item does extra damage.", type = Ability.Type.PASSIVE)
    public AbilityResult fistsAbility(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (DGData.PLAYER_R.fromPlayer(player).getAspects().contains(getGroup() + " " + getTier().name())) {
                if (player.getItemInHand().getType().equals(Material.AIR)) {
                    event.setDamage(15.0);
                }
            }
        }

        return AbilityResult.SUCCESS;
    }
}