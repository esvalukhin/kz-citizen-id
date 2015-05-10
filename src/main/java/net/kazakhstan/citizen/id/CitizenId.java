/*
 * Copyright 2015, Eugene Svalukhin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.kazakhstan.citizen.id;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Eugene Svalukhin.
 */

/**
 * This class represents number that used in Kazakhstan for subject identification.
 */
public class CitizenId {

    private final String value;
    private final CitizenType type;

    /**
     * Creates object that represented physical or juridical citizen id
     * @param value citizen id
     * @throws IllegalArgumentException if incoming value is empty or null, has length not equal to 12 symbols, contains
     * nondigital symbols
     */
    public CitizenId(String value) {
        if (value == null || value.equals("")) {
            throw new IllegalArgumentException("Citizen value can not be a empty");
        }
        if (value.trim().length() != 12) {
            throw new IllegalArgumentException("Citizen value must have length 12 symbols");
        }
        Pattern pattern = Pattern.compile("\\d{12}");
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Citizen value must have 12 digital symbols");
        }
        this.value = value.trim();
        this.type = verifyType(this.value);
    }

    private CitizenType verifyType(String id) {
        if (id.charAt(4) <= '3') {
            return CitizenType.PHYSICAL;
        } else {
            return CitizenType.JURIDICAL;
        }
    }

    /**
     * Method checks if citizen id represent physical subject
     * @return true if object contains number of physical subject
     */
    public boolean isPhysical() {
        return type == CitizenType.PHYSICAL;
    }

    /**
     * Method return type of stored number
     * @return type of number
     */
    public CitizenType getType() {
        return type;
    }

    /**
     * Method return stored number of subject
     * @return stored number
     */
    public String getValue() {
        return value;
    }

    /**
     * Method checks if citizen id represents juridical subject
     * @return true if object contains number of juridical subject
     */
    public boolean isJuridical() {
        return type == CitizenType.JURIDICAL;
    }
}
