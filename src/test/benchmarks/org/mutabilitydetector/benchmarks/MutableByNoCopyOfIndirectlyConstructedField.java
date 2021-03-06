package org.mutabilitydetector.benchmarks;

/*
 * #%L
 * MutabilityDetector
 * %%
 * Copyright (C) 2008 - 2014 Graham Allan
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

public class MutableByNoCopyOfIndirectlyConstructedField {

    @SuppressWarnings("unused")
    private final CharSequence name;

    public MutableByNoCopyOfIndirectlyConstructedField(IFieldFactory fieldFactory) {
        this.name = fieldFactory.getName();
    }

}

interface IFieldFactory {
    public CharSequence getName();
}

final class FieldFactory implements IFieldFactory {
    @Override
    public CharSequence getName() {
        return "name";
    }

    public static CharSequence getNewName() {
        return "name";
    }
}
