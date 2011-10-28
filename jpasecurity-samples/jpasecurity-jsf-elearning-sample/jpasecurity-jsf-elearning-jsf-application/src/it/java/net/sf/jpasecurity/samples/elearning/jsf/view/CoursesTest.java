package net.sf.jpasecurity.samples.elearning.jsf.view;
/*
 * Copyright 2011 Raffaela Ferrari open knowledge GmbH
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

import net.sf.jpasecurity.samples.elearning.jsf.view.AbstractHtmlTestCase.Role;

import org.jaxen.JaxenException;
import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/*
 * @auhtor Raffaela Ferrari
 */

public class CoursesTest extends AbstractHtmlTestCase {
    public CoursesTest() {
        super("http://localhost:8080/");
    }

    @Ignore
    @Test
    public void unauthenticated() throws JaxenException {
        ElearningAssert.assertCoursesPage(getPage("courses.xhtml"), Role.GUEST);
    }

    @Ignore
    @Test
    public void authenticatedAsTeacher() throws JaxenException {
        ElearningAssert.assertCoursesPage(getPage("courses.xhtml"), Role.GUEST);
        ElearningAssert.assertCoursesPage(authenticateAsTeacher("courses.xhtml"), Role.TEACHER);
        ElearningAssert.assertCoursesPage(getPage("courses.xhtml"), Role.TEACHER);
    }

    @Ignore
    @Test
    public void authenticatedAsStudent() throws JaxenException {
        ElearningAssert.assertCoursesPage(getPage("courses.xhtml"), Role.GUEST);
        ElearningAssert.assertCoursesPage(authenticateAsStudent("courses.xhtml"), Role.STUDENT);
        ElearningAssert.assertCoursesPage(getPage("courses.xhtml"), Role.STUDENT);
    }
    
    @Ignore
    @Test
    public void formBasedAuthenticatedAsTeacher() throws JaxenException {
        ElearningAssert.assertCoursesPage(getPage("courses.xhtml"), Role.GUEST);
        authenticateFormBasedAsTeacher();
        ElearningAssert.assertCoursesPage(getPage("courses.xhtml"), Role.TEACHER);
    }
    
    @Ignore
    @Test
    public void formBasedAuthenticatedAsStudent() throws JaxenException {
        ElearningAssert.assertCoursesPage(getPage("courses.xhtml"), Role.GUEST);
        authenticateFormBasedAsStudent();
        ElearningAssert.assertCoursesPage(getPage("courses.xhtml"), Role.STUDENT);
    }
    
    @Ignore
    @Test
    public void linkTest() throws JaxenException {
        HtmlPage courseLink = testLink("courses.xhtml", "Shakespeare course");
        ElearningAssert.assertCoursePage(courseLink, Role.GUEST);
    }
}