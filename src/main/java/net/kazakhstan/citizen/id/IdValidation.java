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
 * This interface provides method for checking validity of citizen id
 */
public interface IdValidation {

    /**
     * Method return validity of citizen id
     * @return true if algorithm checksum is equal to checksum of citizen id
     */
    boolean isValid();
}
