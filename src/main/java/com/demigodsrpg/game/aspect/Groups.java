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

package com.demigodsrpg.game.aspect;

import com.demigodsrpg.game.aspect.bloodlust.BloodlustAspect;
import com.demigodsrpg.game.aspect.crafting.CraftingAspect;
import com.demigodsrpg.game.aspect.demon.DemonAspect;
import com.demigodsrpg.game.aspect.fire.FireAspect;
import com.demigodsrpg.game.aspect.lightning.LightingAspect;
import com.demigodsrpg.game.aspect.water.WaterAspect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Groups {

    // -- PUBLIC RELEASE -- //

    public static final LightingAspect LIGHTNING_ASPECT = new LightingAspect();
    public static final FireAspect FIRE_ASPECT = new FireAspect();
    public static final WaterAspect WATER_ASPECT = new WaterAspect();
    public static final BloodlustAspect BLOODLUST_ASPECT = new BloodlustAspect();
    public static final CraftingAspect CRAFTING_ASPECT = new CraftingAspect();

    // -- EXPANSION 1 -- //

    public static final DemonAspect DEMON_ASPECT = new DemonAspect();

    // -- GROUP LIST -- //

    public static final Aspect.Group[] groupList = new Aspect.Group[]{
            LIGHTNING_ASPECT, FIRE_ASPECT, WATER_ASPECT, BLOODLUST_ASPECT, CRAFTING_ASPECT
    };

    // -- PRIVATE CONSTRUCTOR -- //

    private Groups() {
    }

    // -- HELPER METHODS -- //

    public static Aspect.Group[] values() {
        return groupList;
    }

    public static Aspect.Group valueOf(final String name) {
        for (Aspect.Group group : groupList) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    public static List<Aspect> aspectsInGroup(final Aspect.Group group) {
        List<Aspect> aspects = new ArrayList<>(0);
        aspects.addAll(Arrays.asList(Aspects.values()));
        aspects.removeIf(new Predicate<Aspect>() {
            @Override
            public boolean test(Aspect aspect) {
                return !aspect.getGroup().equals(group);
            }
        });
        return aspects;
    }
}