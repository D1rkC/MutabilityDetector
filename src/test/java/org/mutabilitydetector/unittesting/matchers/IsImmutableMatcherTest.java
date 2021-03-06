package org.mutabilitydetector.unittesting.matchers;

/*
 * #%L
 * MutabilityDetector
 * %%
 * Copyright (C) 2008 - 2014 mutabilitydetector.org
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

import org.hamcrest.StringDescription;
import org.junit.Test;
import org.mutabilitydetector.AnalysisResult;
import org.mutabilitydetector.MutableReasonDetail;
import org.mutabilitydetector.TestUtil;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mutabilitydetector.AnalysisResult.analysisResult;
import static org.mutabilitydetector.AnalysisResult.definitelyImmutable;
import static org.mutabilitydetector.IsImmutable.IMMUTABLE;
import static org.mutabilitydetector.IsImmutable.NOT_IMMUTABLE;
import static org.mutabilitydetector.MutabilityReason.ABSTRACT_TYPE_INHERENTLY_MUTABLE;
import static org.mutabilitydetector.MutableReasonDetail.newMutableReasonDetail;
import static org.mutabilitydetector.TestUtil.unusedMutableReasonDetail;
import static org.mutabilitydetector.locations.CodeLocation.ClassLocation.fromInternalName;

public class IsImmutableMatcherTest {

    private final String newline = System.getProperty("line.separator");

    private final IsImmutableMatcher matcher = IsImmutableMatcher.hasIsImmutableStatusOf(IMMUTABLE);

    @Test
    public void matchesForSameIsImmutableResult() throws Exception {
        AnalysisResult result = definitelyImmutable("a.b.c");
        assertThat(matcher.matches(result), is(true));
    }

    @Test
    public void doesNotMatchForDifferentIsImmutableResult() throws Exception {
        AnalysisResult nonMatchingResult = analysisResult("c.d.e", NOT_IMMUTABLE, unusedMutableReasonDetail());
        assertThat(matcher.matches(nonMatchingResult), is(false));
    }

    @Test
    public void hasDescriptiveErrortMessageForMismatch() throws Exception {
        Collection<MutableReasonDetail> reasons = new ArrayList<MutableReasonDetail>();
        reasons.add(newMutableReasonDetail("unused message",
                fromInternalName("c/d/e"),
                ABSTRACT_TYPE_INHERENTLY_MUTABLE));
        AnalysisResult nonMatchingResult = analysisResult("c.d.e", NOT_IMMUTABLE, reasons);

        StringDescription description = new StringDescription();
        matcher.describeMismatch(nonMatchingResult, description);

        assertThat(description.toString(), containsString("c.d.e is actually " + NOT_IMMUTABLE + newline));
    }

}
