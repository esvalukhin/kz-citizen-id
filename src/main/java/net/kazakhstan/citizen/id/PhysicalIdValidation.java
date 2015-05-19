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

/**
 * Created by Eugene Svalukhin.
 */

/**
 * Class checks validity of physical subject citizen id according official algorithm which is published on
 * page http://adilet.zan.kz/rus/docs/P1300000853
 */
public class PhysicalIdValidation implements IdValidation {
    private final CitizenId citizenId;
    private final int firstWeights[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private final int secondWeights[] = {3, 4, 5, 6, 7, 8, 9, 10, 11, 1, 2};

    /**
     * Constructor creates validation object for checking validity of physical subject citizen id
     * @param physicalId citizen id of physical subject
     * @throws IllegalArgumentException if citizen id is empty or is not belong to physical subject
     */
    public PhysicalIdValidation(CitizenId physicalId) {
        if (physicalId == null) {
            throw new IllegalArgumentException("Citizen id can not be empty");
        }
        if (!physicalId.isPhysical()) {
            throw new IllegalArgumentException("Citizen id must belong to physical subject");
        }
        citizenId = physicalId;
    }

    /**
     * Method checks validity of physical subject id according algorithm
     * @return true if validation is passed successful
     */
    public boolean isValid() {
        int sum = 0;
        for (int i = 0; i < firstWeights.length; i++) {
            sum += (citizenId.getValue().charAt(i) - 0x30) * firstWeights[i];
        }
        if (sum % 11 == 10) {
            sum = 0;
            for (int i = 0; i< secondWeights.length; i++) {
                sum += (citizenId.getValue().charAt(i) - 0x30) * secondWeights[i];
            }
        }
        return sum % 11 == citizenId.getValue().charAt(citizenId.getValue().length() - 1) - 0x30;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhysicalIdValidation that = (PhysicalIdValidation) o;

        return citizenId.equals(that.citizenId);

    }

    @Override
    public int hashCode() {
        return citizenId.hashCode();
    }
}
