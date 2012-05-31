/*
 *    Copyright (c) 2008-2011 Graham Allan
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.mutabilitydetector.locations;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Function;

@Immutable
public final class Dotted extends ClassName {

    private Dotted(String className) {
        super(className);
    }
    
    public static final Function<String, Dotted> TO_DOTTED = new Function<String, Dotted>() {
		@Override public Dotted apply(String className) { return dotted(className); }
    };

    public static Dotted dotted(String dottedClassName) {
        return new Dotted(new ClassNameConverter().dotted(dottedClassName));
    }

    public static Dotted fromSlashed(Slashed slashedClassName) {
        String converted = new ClassNameConverter().dotted(slashedClassName.asString());
        return dotted(converted);
    }

    public static Dotted fromSlashedString(String slashedClassNameString) {
        String dottedClassNameString = new ClassNameConverter().dotted(slashedClassNameString);
        return dotted(dottedClassNameString);
    }

    public static Dotted fromClass(Class<?> clazz) {
        return dotted(clazz.getName());
    }

}
