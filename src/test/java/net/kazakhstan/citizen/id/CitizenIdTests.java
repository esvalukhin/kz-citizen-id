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

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Eugene Svalukhin.
 */
public class CitizenIdTests {
    @Test
    public void shouldCreatePhysicalSubjectId() throws Exception {
        CitizenId citizenId = new CitizenId("880319350220");
        assertTrue(citizenId.isPhysical());
        assertEquals(CitizenType.PHYSICAL, citizenId.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateIdIfValueIsEmpty() throws Exception {
        new CitizenId("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateIdIfValueIsNull() throws Exception {
        new CitizenId(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateIdIfValueLengthLess12Symbols() throws Exception {
        new CitizenId("88031935022");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateIdIfValueLengthMore12Symbols() throws Exception {
        new CitizenId("8803193502201");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCreateOnlyIfValueHasDigits() throws Exception {
        new CitizenId("88±s≈Ω/35&2a");
    }

    @Test
    public void shouldCreateJuridicalSubjectId() throws Exception {
        CitizenId citizenId = new CitizenId("081140000436");
        assertTrue(citizenId.isJuridical());
    }

    @Test
    public void objectsShouldBeEqualWithIdenticalNumbers() throws Exception {
        CitizenId firstObject = new CitizenId("880319350220");
        CitizenId secondObject = new CitizenId("880319350220");
        assertTrue(firstObject.equals(secondObject));
        assertTrue(secondObject.equals(firstObject));
    }

    @Test
    public void shouldBeEqualToItself() throws Exception {
        CitizenId citizenId = new CitizenId("880319350220");
        assertTrue(citizenId.equals(citizenId));
    }

    @Test
    public void shouldNotBeEqualsWithDifferentNumbers() throws Exception {
        CitizenId firstObject = new CitizenId("880319350220");
        CitizenId secondObject = new CitizenId("910501000044");
        assertTrue(!firstObject.equals(secondObject));
        assertTrue(!secondObject.equals(firstObject));
    }

    @Test
    public void shouldContainsOneObjectInHashCollectionWithIdenticalNumber() throws Exception {
        CitizenId firstId = new CitizenId("880319350220");
        Set<CitizenId> hashSet = new HashSet<CitizenId>();
        hashSet.add(firstId);
        assertEquals(1, hashSet.size());
        CitizenId secondId = new CitizenId("880319350220");
        hashSet.add(secondId);
        assertEquals(1, hashSet.size());
    }

    @Test
    public void shouldContainsAllObjectsWithDifferentNumbersInHashCollection() throws Exception {
        CitizenId firstId = new CitizenId("880319350220");
        Set<CitizenId> hashSet = new HashSet<CitizenId>();
        hashSet.add(firstId);
        assertEquals(1, hashSet.size());
        CitizenId secondId = new CitizenId("910501000044");
        hashSet.add(secondId);
        assertEquals(2, hashSet.size());
    }
}
