package net.sf.jpasecurity.samples.elearning.jsf.view;
/*
 * Copyright 2011 Arne Limburg
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


import static org.junit.Assert.assertEquals;


import org.jaxen.JaxenException;
import org.junit.Test;
import org.junit.Ignore;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author Arne Limburg
 */

public class IndexTest extends AbstractHtmlTestCase {

    public IndexTest() {
        super("http://localhost:8282/elearning/");
    }

    @Ignore
    @Test
    public void unauthenticated() throws JaxenException {
        ElearningAssert.assertIndexPage(getPage(""), Role.GUEST);
        ElearningAssert.assertIndexPage(getPage("index.xhtml"), Role.GUEST);
    }

    @Ignore
    @Test
    public void authenticatedAsTeacher() throws JaxenException {
        ElearningAssert.assertIndexPage(getPage("index.xhtml"), Role.GUEST);
        ElearningAssert.assertIndexPage(authenticateAsTeacher("index.xhtml"), Role.TEACHER);
        ElearningAssert.assertIndexPage(getPage("index.xhtml"), Role.TEACHER);
    }

    @Ignore
    @Test
    public void authenticatedAsStudent() throws JaxenException {
        ElearningAssert.assertIndexPage(getPage("index.xhtml"), Role.GUEST);
        ElearningAssert.assertIndexPage(authenticateAsStudent("index.xhtml"), Role.STUDENT);
        ElearningAssert.assertIndexPage(getPage("index.xhtml"), Role.STUDENT);
    }
    
    @Ignore
    @Test
    public void formBasedAuthenticatedAsTeacher() throws JaxenException {
        ElearningAssert.assertIndexPage(getPage("index.xhtml"), Role.GUEST);
        authenticateFormBasedAsTeacher();
        ElearningAssert.assertIndexPage(getPage("index.xhtml"), Role.TEACHER);
    }
    
    @Ignore
    @Test
    public void formBasedAuthenticatedAsStudent() throws JaxenException {
        ElearningAssert.assertIndexPage(getPage("index.xhtml"), Role.GUEST);
        authenticateFormBasedAsStudent();
        ElearningAssert.assertIndexPage(getPage("index.xhtml"), Role.STUDENT);
    }
}