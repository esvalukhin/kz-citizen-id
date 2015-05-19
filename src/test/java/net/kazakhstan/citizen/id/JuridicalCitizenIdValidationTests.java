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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Eugene Svalukhin.
 */
public class JuridicalCitizenIdValidationTests {

    @Test
    public void shouldJuridicalIdValid() throws Exception {
        CitizenId juridicalId = new CitizenId("081140000436");
        JuridicalIdValidation validation = new JuridicalIdValidation(juridicalId);
        assertTrue(validation.isValid());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfIdIsEmpty() throws Exception {
        new JuridicalIdValidation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldValidateOnlyJuridicalId() throws Exception {
        CitizenId citizenId = new CitizenId("880319350220");
        new JuridicalIdValidation(citizenId);
    }

    @Test
    public void shouldBeObjectsEqualIfHaveIdenticalCitizenIdObject() throws Exception {
        CitizenId juridicalId = new CitizenId("000740000728");
        JuridicalIdValidation object1 = new JuridicalIdValidation(juridicalId);
        JuridicalIdValidation object2 = new JuridicalIdValidation(juridicalId);
        assertTrue(object1.equals(object2));
        assertTrue(object2.equals(object1));
    }

    @Test
    public void shouldBeObjectsEqualIfCitizenIdHasIdenticalNumber() throws Exception {
        CitizenId juridical1 = new CitizenId("000740000728");
        CitizenId juridical2 = new CitizenId("000740000728");
        JuridicalIdValidation validation1 = new JuridicalIdValidation(juridical1);
        JuridicalIdValidation validation2 = new JuridicalIdValidation(juridical2);
        assertTrue(validation1.equals(validation2));
        assertTrue(validation2.equals(validation1));
    }

    @Test
    public void shouldHaveIdenticalHashcodeIfHasIdenticalCitizenId() throws Exception {
        CitizenId juridical = new CitizenId("000740000728");
        JuridicalIdValidation validation1 = new JuridicalIdValidation(juridical);
        JuridicalIdValidation validation2 = new JuridicalIdValidation(juridical);
        assertTrue(validation1.hashCode() == validation2.hashCode());
    }

    @Test
    public void shouldHaveIdenticalHashcodeIfHasCitizenIdWithIdenticalNumber() throws Exception {
        JuridicalIdValidation validation1 = new JuridicalIdValidation(new CitizenId("000740000728"));
        JuridicalIdValidation validation2 = new JuridicalIdValidation(new CitizenId("000740000728"));
        assertTrue(validation1.hashCode() == validation2.hashCode());
    }

    @Test
    public void shouldNotBeEqualIfCitizenIdsHasDifferentNumber() throws Exception {
        CitizenId juridical1 = new CitizenId("000740000728");
        CitizenId juridical2 = new CitizenId("081140000436");
        JuridicalIdValidation validation1 = new JuridicalIdValidation(juridical1);
        JuridicalIdValidation validation2 = new JuridicalIdValidation(juridical2);
        assertFalse(validation1.equals(validation2));
        assertFalse(validation2.equals(validation1));
    }

    @Test
    public void shouldNotHaveIdenticalHashcodeIfCitizenIdHasDifferentNumber() throws Exception {
        JuridicalIdValidation validation1 = new JuridicalIdValidation(new CitizenId("000740000728"));
        JuridicalIdValidation validation2 = new JuridicalIdValidation(new CitizenId("081140000436"));
        assertFalse(validation1.hashCode() == validation2.hashCode());

    }
}
