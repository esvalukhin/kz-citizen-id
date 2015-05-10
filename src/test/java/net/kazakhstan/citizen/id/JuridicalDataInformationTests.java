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

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Eugene Svalukhin.
 */
public class JuridicalDataInformationTests {
    @Test
    public void shouldReturnTheYear() throws Exception {
        CitizenId citizenId = new CitizenId("081140000436");
        JuridicalDataInformation information = new JuridicalDataInformation(citizenId);
        assertEquals(2008, information.getYear());
    }

    @Test
    public void shouldReturnTheMonth() throws Exception {
        CitizenId citizenId = new CitizenId("081140000436");
        JuridicalDataInformation information = new JuridicalDataInformation(citizenId);
        assertEquals(Calendar.NOVEMBER, information.getMonth());
    }

    @Test
    public void shouldReturnTrueIfResident() throws Exception {
        CitizenId citizenId = new CitizenId("081140000436");
        JuridicalDataInformation information = new JuridicalDataInformation(citizenId);
        assertTrue(information.isResident());
        assertFalse(information.isNonResident());
        assertFalse(information.isJointEntrepreneur());

    }

    @Test
    public void shouldReturnTrueIfNonResident() throws Exception {
        CitizenId citizenId = new CitizenId("070850004167");
        JuridicalDataInformation information = new JuridicalDataInformation(citizenId);
        assertFalse(information.isResident());
        assertTrue(information.isNonResident());
        assertFalse(information.isJointEntrepreneur());
    }

    @Test
    public void shouldReturnTrueIfJointEntrepreneur() throws Exception {
        CitizenId citizenId = new CitizenId("081160000436");
        JuridicalDataInformation information = new JuridicalDataInformation(citizenId);
        assertFalse(information.isResident());
        assertFalse(information.isNonResident());
        assertTrue(information.isJointEntrepreneur());
    }

    @Test
    public void shouldReturnTrueIfHead() throws Exception {
        CitizenId citizenId = new CitizenId("081140000436");
        JuridicalDataInformation information = new JuridicalDataInformation(citizenId);
        assertTrue(information.isHead());
        assertFalse(information.isBranch());
        assertFalse(information.isAgency());
        assertFalse(information.isFarmHolding());
    }

    @Test
    public void shouldReturnTrueIfBranch() throws Exception {
        CitizenId citizenId = new CitizenId("980141004945");
        JuridicalDataInformation information = new JuridicalDataInformation(citizenId);
        assertFalse(information.isHead());
        assertTrue(information.isBranch());
        assertFalse(information.isAgency());
        assertFalse(information.isFarmHolding());
    }

    @Test
    public void shouldReturnTrueIfAgency() throws Exception {
        CitizenId citizenId = new CitizenId("120142005544");
        JuridicalDataInformation information = new JuridicalDataInformation(citizenId);
        assertFalse(information.isHead());
        assertFalse(information.isBranch());
        assertTrue(information.isAgency());
        assertFalse(information.isFarmHolding());
    }

    @Test
    public void shouldReturnTrueIfFarmHolding() throws Exception {
        CitizenId citizenId = new CitizenId("081143000436");
        JuridicalDataInformation information = new JuridicalDataInformation(citizenId);
        assertFalse(information.isHead());
        assertFalse(information.isBranch());
        assertFalse(information.isAgency());
        assertTrue(information.isFarmHolding());
    }

    @Test
    public void shouldReturnOrderNumber() throws Exception {
        CitizenId citizenId = new CitizenId("081140000436");
        JuridicalDataInformation information = new JuridicalDataInformation(citizenId);
        assertEquals("00043", information.getOrderNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfCitizenIdNull() throws Exception {
        new JuridicalDataInformation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfIsNotJuridicalSubject() throws Exception {
        CitizenId citizenId = new CitizenId("880319350220");
        new JuridicalDataInformation(citizenId);
    }
}
