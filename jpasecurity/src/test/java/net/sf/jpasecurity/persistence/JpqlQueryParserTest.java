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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import net.sf.jpasecurity.security.authentication.TestAuthenticationProvider;

/**
 * @author Arne Limburg
 * @author Johannes Siemer
 */
public class JpqlQueryParserTest extends TestCase {

    public static final String USER1 = "user1";

    public void testCount() {
        executeQuery("SELECT COUNT(tb) FROM FieldAccessAnnotationTestBean tb");
    }

    public void testDistinct() {
        executeQuery("SELECT DISTINCT tb FROM FieldAccessAnnotationTestBean tb");
    }

    public void testExists() {
        executeQuery(
            "SELECT tb FROM FieldAccessAnnotationTestBean tb WHERE EXISTS(SELECT tb2 FROM FieldAccessAnnotationTestBean tb2)");
    }

    private void executeQuery(String query) {
        TestAuthenticationProvider.authenticate(USER1);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("annotation-based-field-access");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().rollback();
        entityManager.close();
    }

    public void tearDown() {
        TestAuthenticationProvider.authenticate(null);
    }
}