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
 * Class checks validity of citizen id of juridical subject according to official algorithm which is represented on
 * page http://adilet.zan.kz/rus/docs/P1300000853
 */
public class JuridicalIdValidation implements IdValidation {

    private final CitizenId citizenId;

    private final int firstOrderWeights[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private final int secondOrderWeights[] = {3, 4, 5, 6, 7, 8, 9, 10, 11, 1, 2};

    /**
     * Constructor creates validation object for juridical citizen id
     * @param citizenId citizen id which will be checked
     * @throws IllegalArgumentException if citizen id is null or is not belong to juridical subject
     */
    public JuridicalIdValidation(CitizenId citizenId) {
        if (citizenId == null) {
            throw new IllegalArgumentException("Citizen id can not be empty");
        }
        if (!citizenId.isJuridical()) {
            throw new IllegalArgumentException("Citizen id must belong to juridical subject");
        }
        this.citizenId = citizenId;
    }

    /**
     * Method checks validity of juridical subject citizen id
     * @return true if validation passed successful
     */
    public boolean isValid() {
        int sum = 0;
        for (int i = 0; i < firstOrderWeights.length; i++) {
            sum += (citizenId.getValue().charAt(i) - 0x30) * firstOrderWeights[i];
        }
        if (sum % 11 == 10) {
            sum = 0;
            for (int i = 0; i< secondOrderWeights.length; i++) {
                sum += (citizenId.getValue().charAt(i) - 0x30) * secondOrderWeights[i];
            }
        }
        return sum % 11 == citizenId.getValue().charAt(citizenId.getValue().length() - 1) - 0x30;
    }
}
