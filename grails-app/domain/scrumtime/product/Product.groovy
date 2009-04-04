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
package scrumtime.product

import scrumtime.release.Release
import scrumtime.user.SystemUser

class Product implements Comparable{
    Date dateCreated
	Date lastUpdated

    String name
    String description
    static hasMany = [releases: Release, owners : SystemUser]
    SortedSet releases
    SortedSet owners
    String visibility

    static constraints = {
		name(unique: true,blank:false,nullable:false, size:1..80)
        description(blank:false, size:1..512)
        visibility(nullable:false, blank:false)
    }
    
    
    def int compareTo(obj) {
        def result = 0
        if (obj instanceof Product){
            def product = (Product)obj
            if (this.organization == product.organization)
                result = this.name.compareTo(product.name)
            else
                result = this.organization.compareTo(product.organization)
        }
        return result
    }


}
