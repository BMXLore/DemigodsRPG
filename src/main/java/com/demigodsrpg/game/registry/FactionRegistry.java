/*
 * Copyright 2015 Demigods RPG
 * Copyright 2015 Alexander Chauncey
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

package com.demigodsrpg.game.registry;

import com.demigodsrpg.game.deity.Faction;
import com.demigodsrpg.game.util.JsonSection;

import java.util.Optional;

public class FactionRegistry extends AbstractRegistry<Faction> {

    private static final String FILE_NAME = "factions.dgcfg";

    public Faction factionFromName(final String name) {
        Optional<Faction> found = getRegistered().stream().filter(faction -> faction.getName().equalsIgnoreCase(name)).findAny();
        if (found.isPresent()) {
            return found.get();
        }
        return null;
    }

    @Override
    protected Faction valueFromData(String stringKey, JsonSection data) {
        return new Faction(stringKey, data);
    }

    @Override
    protected String getFileName() {
        return FILE_NAME;
    }

    @Override
    protected boolean isPretty() {
        return true;
    }
}
