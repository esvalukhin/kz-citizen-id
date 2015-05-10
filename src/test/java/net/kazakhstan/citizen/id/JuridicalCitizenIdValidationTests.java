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

}