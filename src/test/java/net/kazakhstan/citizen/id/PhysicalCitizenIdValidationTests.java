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
public class PhysicalCitizenIdValidationTests {
    @Test
    public void shouldPhysicalIdCorrectByFirstOrderWeights() throws Exception {
        CitizenId id = new CitizenId("880319350220");
        PhysicalIdValidation validation = new PhysicalIdValidation(id);
        assertTrue(validation.isValid());
    }

    @Test
    public void shouldPhysicalIdCorrectBySecondOrderWeights() throws Exception {
        CitizenId physicalId = new CitizenId("880319350220");
        PhysicalIdValidation validation = new PhysicalIdValidation(physicalId);
        assertTrue(validation.isValid());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfIdIsEmpty() throws Exception {
        new PhysicalIdValidation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldValidateOnlyPhysicalId() throws Exception {
        CitizenId juridicalId = new CitizenId("081140000436");
        new PhysicalIdValidation(juridicalId);
    }

    @Test
    public void shouldBeObjectsEqualIfHaveIdenticalCitizenIdObject() throws Exception {
        CitizenId juridicalId = new CitizenId("880319350220");
        PhysicalIdValidation object1 = new PhysicalIdValidation(juridicalId);
        PhysicalIdValidation object2 = new PhysicalIdValidation(juridicalId);
        assertTrue(object1.equals(object2));
        assertTrue(object2.equals(object1));
    }

    @Test
    public void shouldBeObjectsEqualIfCitizenIdHasIdenticalNumber() throws Exception {
        CitizenId juridical1 = new CitizenId("880319350220");
        CitizenId juridical2 = new CitizenId("880319350220");
        PhysicalIdValidation validation1 = new PhysicalIdValidation(juridical1);
        PhysicalIdValidation validation2 = new PhysicalIdValidation(juridical2);
        assertTrue(validation1.equals(validation2));
        assertTrue(validation2.equals(validation1));
    }

    @Test
    public void shouldHaveIdenticalHashcodeIfHasIdenticalCitizenId() throws Exception {
        CitizenId juridical = new CitizenId("880319350220");
        PhysicalIdValidation validation1 = new PhysicalIdValidation(juridical);
        PhysicalIdValidation validation2 = new PhysicalIdValidation(juridical);
        assertTrue(validation1.hashCode() == validation2.hashCode());
    }

    @Test
    public void shouldHaveIdenticalHashcodeIfHasCitizenIdWithIdenticalNumber() throws Exception {
        PhysicalIdValidation validation1 = new PhysicalIdValidation(new CitizenId("880319350220"));
        PhysicalIdValidation validation2 = new PhysicalIdValidation(new CitizenId("880319350220"));
        assertTrue(validation1.hashCode() == validation2.hashCode());
    }

    @Test
    public void shouldNotBeEqualIfCitizenIdsHasDifferentNumber() throws Exception {
        CitizenId juridical1 = new CitizenId("880319350220");
        CitizenId juridical2 = new CitizenId("880525000017");
        PhysicalIdValidation validation1 = new PhysicalIdValidation(juridical1);
        PhysicalIdValidation validation2 = new PhysicalIdValidation(juridical2);
        assertFalse(validation1.equals(validation2));
        assertFalse(validation2.equals(validation1));
    }

    @Test
    public void shouldNotHaveIdenticalHashcodeIfCitizenIdHasDifferentNumber() throws Exception {
        PhysicalIdValidation validation1 = new PhysicalIdValidation(new CitizenId("880319350220"));
        PhysicalIdValidation validation2 = new PhysicalIdValidation(new CitizenId("880525000017"));
        assertFalse(validation1.hashCode() == validation2.hashCode());

    }
}
