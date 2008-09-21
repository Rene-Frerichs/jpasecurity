/*
 * Copyright 2008 Arne Limburg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package net.sf.jpasecurity.persistence;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;



/**
 * @author Arne Limburg
 */
public class SecureSortedSet<E> extends AbstractSecureCollection<E, SortedSet<E>> implements SortedSet<E> {

    public SecureSortedSet(SortedSet<E> sortedSet, SecureEntityHandler entityHandler) {
        super(sortedSet, entityHandler);
    }
    
    SecureSortedSet(SortedSet<E> original, SortedSet<E> filtered, SecureEntityHandler entityHandler) {
        super(original, filtered, entityHandler);
    }

    protected SortedSet<E> createFiltered() {
        if (getOriginal().comparator() == null) {
            return new TreeSet<E>();
        } else {
            return new TreeSet<E>(getOriginal().comparator());
        }
    }

    public Comparator<? super E> comparator() {
        return getFiltered().comparator();
    }

    public E first() {
        return getFiltered().first();
    }

    public E last() {
        return getFiltered().last();
    }

    public SortedSet<E> headSet(E toElement) {
        return new SecureSortedSet(getOriginal().headSet(toElement),
                                     getFiltered().headSet(toElement),
                                     getEntityHandler());
    }

    public SortedSet<E> subSet(E fromElement, E toElement) {
        return new SecureSortedSet(getOriginal().subSet(fromElement, toElement),
                                     getFiltered().subSet(fromElement, toElement),
                                     getEntityHandler());
    }

    public SortedSet<E> tailSet(E fromElement) {
        return new SecureSortedSet(getOriginal().tailSet(fromElement),
                                     getFiltered().tailSet(fromElement),
                                     getEntityHandler());
    }
}