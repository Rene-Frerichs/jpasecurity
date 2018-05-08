/*
 * Copyright 2008 - 2016 Arne Limburg
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
package org.jpasecurity.collection;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/**
 * A list-implementation of a secure collection.
 * @see AbstractSecureCollection
 * @author Arne Limburg
 */
public class SecureList<E> extends AbstractList<E> {

    private AbstractSecureCollection<E, List<E>> secureCollection;

    public SecureList(List<E> list) {
        secureCollection = new DefaultSecureCollection<>(list);
    }

    /**
     * Returns the element with the specified index in the filtered collection.
     * This index may differ from the index of that element in the original collection.
     */
    @Override
    public E get(int index) {
        return secureCollection.getFiltered().get(index);
    }

    /**
     * Sets the specified element to the specified index in the filtered collection,
     * replacing the element at that index.
     * The index of the replaced element may differ in the original collection,
     * though the same element is replaced.
     */
    @Override
    public E set(int index, E element) {
        E old = secureCollection.getFiltered().set(index, element);
        int originalIndex = getOriginal().indexOf(old);
        return getOriginal().set(originalIndex, element);
    }

    /**
     * Adds the specified element at the specified index in the filtered collection.
     * In the original collection the element will be added just before the element
     * that was located at that index in the filtered collection before the addition.
     * If the specified index is the same as the size of the filtered collection,
     * the element is added at the end of both collections.
     */
    @Override
    public void add(int index, E element) {
        if (index == secureCollection.getFiltered().size()) {
            secureCollection.add(element);
        } else {
            E old = secureCollection.getFiltered().get(index);
            int originalIndex = getOriginal().indexOf(old);
            secureCollection.getFiltered().add(index, element);
            getOriginal().add(originalIndex, element);
        }
    }

    /**
     * Removes the element with the specified index in the filtered collection.
     * This index may differ from the index of that element in the original collection,
     * though the same element is removed in the original collection.
     */
    @Override
    public E remove(int index) {
        E old = secureCollection.getFiltered().remove(index);
        secureCollection.remove(old);
        return old;
    }

    /**
     * Filters the specified collection and adds the elements at the specified index
     * in the filtered collection.
     * In the original collection the elements will be added just before the element
     * that was located at that index in the filtered collection before the addition.
     * If the specified index is the same as the size of the filtered collection,
     * the element is added at the end of both collections.
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (index == secureCollection.getFiltered().size()) {
            return secureCollection.addAll(collection);
        } else {
            E old = secureCollection.getFiltered().get(index);
            int originalIndex = getOriginal().indexOf(old);
            boolean result = secureCollection.getFiltered().addAll(index, collection);
            getOriginal().addAll(originalIndex, collection);
            return result;
        }
    }

    @Override
    public int size() {
        return secureCollection.size();
    }

    public boolean isInitialized() {
        return secureCollection.isInitialized();
    }

    List<E> getOriginal() {
        return secureCollection.getOriginal();
    }

    void initialize(boolean checkAccess) {
        secureCollection.initialize(checkAccess);
    }
}
