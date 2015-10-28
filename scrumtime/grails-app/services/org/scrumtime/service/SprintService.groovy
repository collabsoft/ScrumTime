/**
 *  Copyright 2008   scrumtime.org owners
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License. 
 *
**/
package org.scrumtime.service

import org.scrumtime.domain.sprint.Sprint
import org.scrumtime.domain.release.Release
import org.scrumtime.domain.release.Release

class SprintService {

    boolean transactional = true

    def Sprint autoCreate(Release release, String username) {
        def sprint = new Sprint(release: release,
                name: 'Sprint 1', description: 'Auto generated sprint for ' + release.name,
                createdBy:username)
        sprint.save(flush: true)
        return sprint
    }
}
