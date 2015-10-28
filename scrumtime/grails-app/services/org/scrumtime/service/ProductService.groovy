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

import org.scrumtime.domain.product.Product
import org.scrumtime.domain.organization.Organization
import org.scrumtime.domain.user.SystemUser

class ProductService {

    boolean transactional = true

    def Product autoCreate(Organization organization, String username, String nickName) {
        def product = new Product(organization: organization,
                name: nickName + '_Product', description: 'Auto generated product for ' + organization.name,
                createdBy: username)
        product.save(flush: true)
        return product
    }
}
