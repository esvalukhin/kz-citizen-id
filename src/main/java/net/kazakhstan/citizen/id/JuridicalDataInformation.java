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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Eugene Svalukhin.
 */

/**
 * Class provides additional information about juridical subject using citizen id
 */
public class JuridicalDataInformation {

    private final CitizenId citizenId;

    /**
     * Constructor creates object which will be used for getting information from juridical subject id
     * @param citizenId citizen id of juridical subject
     * @throws IllegalArgumentException if citizen id is empty or is not belong to juridical subject
     */
    public JuridicalDataInformation(CitizenId citizenId) {
        if (citizenId == null) {
            throw new IllegalArgumentException("Citizen id should not be empty");
        }
        if (!citizenId.isJuridical()) {
            throw new IllegalArgumentException("Information could be provide only for juridical subject");
        }
        this.citizenId = citizenId;
    }

    /**
     * Method return year of registration or reregistration of juridical subject
     * @return year of operation
     */
    public int getYear() {
        String value = citizenId.getValue();
        String firstTwoDigits = value.substring(0, 2);
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTimeInMillis(sdf.parse(firstTwoDigits).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Method return month of registration or registration of juridical subject
     * @return month which is represented by month constants of {@link Calendar} class
     */
    public int getMonth() {
        String monthTwoDigits = citizenId.getValue().substring(2, 4);
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTimeInMillis(sdf.parse(monthTwoDigits).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Method checks if juridical subject is non resident
     * @return true if subject is non resident
     */
    public boolean isNonResident() {
        return citizenId.getValue().charAt(4) == OrganizationType.NONRESIDENT.value;
    }

    /**
     * Method checks if juridical subject is resident
     * @return true if subject is resident
     */
    public boolean isResident() {
        return citizenId.getValue().charAt(4) == OrganizationType.RESIDENT.value;
    }

    /**
     * Method checks if juridical subject is joint entrepreneur
     * @return true if subject is joint entrepreneur
     */
    public boolean isJointEntrepreneur() {
        return citizenId.getValue().charAt(4) == OrganizationType.JOINT_ENTREPRENEUR.value;
    }

    /**
     * Method checks if juridical subject is head department
     * @return true if subject is head department
     */
    public boolean isHead() {
        return citizenId.getValue().charAt(5) == DepartmentType.HEAD.value;
    }

    /**
     * Method checks if juridical subject is branch department
     * @return true if subject is branch department
     */
    public boolean isBranch() {
        return citizenId.getValue().charAt(5) == DepartmentType.BRANCH.value;
    }

    /**
     * Method checks if juridical subject is agency department
     * @return true if subject is agency department
     */
    public boolean isAgency() {
        return citizenId.getValue().charAt(5) == DepartmentType.AGENCY.value;
    }

    /**
     * Method checks if juridical subject is farm holding
     * @return true if subject is farm holding
     */
    public boolean isFarmHolding() {
        return citizenId.getValue().charAt(5) == DepartmentType.FARM_HOLDING.value;
    }

    /**
     * Method return registration order number of juridical subject which is extracted from citizen id
     * @return registration order number
     */
    public String getOrderNumber() {
        return citizenId.getValue().substring(6,11);
    }

    private enum OrganizationType {
        RESIDENT('4'), NONRESIDENT('5'), JOINT_ENTREPRENEUR('6');

        private char value;

        OrganizationType(char value) {
            this.value = value;
        }
    }

    private enum DepartmentType {
        HEAD('0'), BRANCH('1'), AGENCY('2'), FARM_HOLDING('3');

        private char value;

        DepartmentType(char value) {
            this.value = value;
        }
    }
}
