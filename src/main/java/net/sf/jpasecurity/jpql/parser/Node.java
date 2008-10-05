/* Generated By:JJTree: Do not edit this line. Node.java
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
package net.sf.jpasecurity.jpql.parser;

/*
 * All AST nodes must implement this interface.  It provides basic
 * machinery for constructing the parent and child relationships
 * between nodes.
 */
public interface Node extends Cloneable {

    /**
     * This method is called after the node has been made the current
     * node.  It indicates that child nodes can now be added to it.
     */
    void jjtOpen();

    /**
     * This method is called after all the child nodes have been
     * added.
     */
    void jjtClose();

    /**
     * This pair of methods are used to inform the node of its
     * parent.
     */
    void jjtSetParent(Node n);
    Node jjtGetParent();

    /**
     * This method tells the node to add its argument to the node's
     * list of children.
     */
    void jjtAddChild(Node n, int i);

    /**
     * This method returns a child node.  The children are numbered
     * from zero, left to right.
     */
    Node jjtGetChild(int i);

    void jjtSetChild(Node n, int i);

    void jjtRemoveChild(int i);

    /**
     * Return the number of children the node has.
     */
    int jjtGetNumChildren();

    /**
     * Accept the visitor.
     */
    Object jjtAccept(JpqlParserVisitor visitor, Object data);

    /**
     * Returns the value of this node or <tt>null</tt>, if this node does not have a value.
     */
    String getValue();

    /**
     * Visits the visitor.
     */
    void visit(JpqlParserVisitor visitor);

    /**
     * Visits the visitor.
     */
    void visit(JpqlParserVisitor visitor, Object data);

    Node clone();
}
