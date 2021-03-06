/*
 * Copyright 2012 JPA Security.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.jpasecurity.model.client;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Joe Amoros
 */
@Entity
public class DataDeliveryMethod extends AbstractEntity<Integer> {

    private static final int LENGTH = 30;

    @Basic(optional = false)
    @Column(nullable = false, length = LENGTH)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
