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

import org.scrumtime.service.user.UserIdentity
import javax.servlet.http.Cookie


class UserSessionService {
    def authenticationService
    def authorizationService

    boolean transactional = true

    def UserIdentity loginDBRealmUser(String username,
                                      String challengePassword) {
        def authenticationToken = authenticationService.authenticateUser(username, challengePassword)
        return authorizeUser(authenticationToken)
    }

    def UserIdentity loginDBRealmUser(Cookie requestCookie){
        def authenticationToken = authenticationService.authenticateUser(requestCookie)
        return authorizeUser(authenticationToken)
    }

    private UserIdentity authorizeUser(authenticationToken){
        def authorizationDefinition = authorizationService.authorizeUser(authenticationToken)
        def userIdentity = new UserIdentity(authenticationToken: authenticationToken,
                authorizationDefinition: authorizationDefinition)
        if (!authenticationToken || !authorizationDefinition)
            userIdentity.hasErrors = true
        else if (authenticationToken.hasErrors() || authorizationDefinition.hasErrors())
            userIdentity.hasErrors = true
        return userIdentity

    }
}