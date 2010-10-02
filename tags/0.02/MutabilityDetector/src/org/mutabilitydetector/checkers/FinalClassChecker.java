/* 
 * Mutability Detector
 *
 * Copyright 2009 Graham Allan
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.mutabilitydetector.checkers;

import static org.mutabilitydetector.IAnalysisSession.IsImmutable.DEFINITELY;
import static org.mutabilitydetector.IAnalysisSession.IsImmutable.MAYBE;

import org.mutabilitydetector.MutabilityReason;
import org.objectweb.asm.Opcodes;

public class FinalClassChecker extends AbstractMutabilityChecker {

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		if((access & Opcodes.ACC_FINAL) == 0) {
			result = MAYBE;
			addResult("Is not declared final, and thus may be immutable.", null, MutabilityReason.NOT_DECLARED_FINAL);
		} else {
			result = DEFINITELY;
		}
		
	}

}