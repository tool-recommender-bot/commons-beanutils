/*
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//beanutils/src/test/org/apache/commons/beanutils/ConvertUtilsTestCase.java,v 1.1 2002/03/18 16:32:43 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2002/03/18 16:32:43 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.commons.beanutils;


import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * <p>
 *  Test Case for the ConvertUtils class.
 * </p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2002/03/18 16:32:43 $
 */

public class ConvertUtilsTestCase extends TestCase {

    // ---------------------------------------------------- Instance Variables


    // ---------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public ConvertUtilsTestCase(String name) {
        super(name);
    }


    // -------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    public void setUp() {

        ConvertUtils.deregister();

    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(ConvertUtilsTestCase.class));
    }


    /**
     * Tear down instance variables required by this test case.
     */
    public void tearDown() {
        ;    // No action required
    }


    // ------------------------------------------------ Individual Test Methods


    /**
     * Negative scalar conversion tests.  These rely on the standard
     * default value conversions in ConvertUtils.
     */
    public void testNegativeScalar() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("foo", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("foo", Byte.TYPE);
        assertTrue(value instanceof Byte);
        assertEquals(((Byte) value).byteValue(), (byte) 0);

        value = ConvertUtils.convert("foo", Byte.class);
        assertTrue(value instanceof Byte);
        assertEquals(((Byte) value).byteValue(), (byte) 0);

        value = ConvertUtils.convert("foo", Double.TYPE);
        assertTrue(value instanceof Double);
        assertEquals(((Double) value).doubleValue(), (double) 0.0,
                     (double) 0.005);

        value = ConvertUtils.convert("foo", Double.class);
        assertTrue(value instanceof Double);
        assertEquals(((Double) value).doubleValue(), (double) 0.0,
                     (double) 0.005);

        value = ConvertUtils.convert("foo", Float.TYPE);
        assertTrue(value instanceof Float);
        assertEquals(((Float) value).floatValue(), (float) 0.0,
                     (float) 0.005);

        value = ConvertUtils.convert("foo", Float.class);
        assertTrue(value instanceof Float);
        assertEquals(((Float) value).floatValue(), (float) 0.0,
                     (float) 0.005);

        value = ConvertUtils.convert("foo", Integer.TYPE);
        assertTrue(value instanceof Integer);
        assertEquals(((Integer) value).intValue(), (int) 0);

        value = ConvertUtils.convert("foo", Integer.class);
        assertTrue(value instanceof Integer);
        assertEquals(((Integer) value).intValue(), (int) 0);

        value = ConvertUtils.convert("foo", Byte.TYPE);
        assertTrue(value instanceof Byte);
        assertEquals(((Byte) value).byteValue(), (byte) 0);

        value = ConvertUtils.convert("foo", Long.class);
        assertTrue(value instanceof Long);
        assertEquals(((Long) value).longValue(), (long) 0);

        value = ConvertUtils.convert("foo", Short.TYPE);
        assertTrue(value instanceof Short);
        assertEquals(((Short) value).shortValue(), (short) 0);

        value = ConvertUtils.convert("foo", Short.class);
        assertTrue(value instanceof Short);
        assertEquals(((Short) value).shortValue(), (short) 0);

    }


    /**
     * Positive array conversion tests.
     */
    public void testPositiveArray() {

        String values1[] = { "10", "20", "30" };
        Object value = ConvertUtils.convert(values1, Integer.TYPE);
        int shape[] = new int[0];
        assertEquals(shape.getClass(), value.getClass());
        int results1[] = (int[]) value;
        assertEquals(results1[0], 10);
        assertEquals(results1[1], 20);
        assertEquals(results1[2], 30);

        String values2[] = { "100", "200", "300" };
        value = ConvertUtils.convert(values2, shape.getClass());
        assertEquals(shape.getClass(), value.getClass());
        int results2[] = (int[]) value;
        assertEquals(results2[0], 100);
        assertEquals(results2[1], 200);
        assertEquals(results2[2], 300);

    }


    /**
     * Positive scalar conversion tests.
     */
    public void testPositiveScalar() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("true", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("yes", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("yes", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("on", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("on", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("false", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("false", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("no", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("no", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("off", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("off", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("123", Byte.TYPE);
        assertTrue(value instanceof Byte);
        assertEquals(((Byte) value).byteValue(), (byte) 123);

        value = ConvertUtils.convert("123", Byte.class);
        assertTrue(value instanceof Byte);
        assertEquals(((Byte) value).byteValue(), (byte) 123);

        value = ConvertUtils.convert("a", Character.TYPE);
        assertTrue(value instanceof Character);
        assertEquals(((Character) value).charValue(), 'a');

        value = ConvertUtils.convert("a", Character.class);
        assertTrue(value instanceof Character);
        assertEquals(((Character) value).charValue(), 'a');

        value = ConvertUtils.convert("123.456", Double.TYPE);
        assertTrue(value instanceof Double);
        assertEquals(((Double) value).doubleValue(), (double) 123.456,
                     (double) 0.005);

        value = ConvertUtils.convert("123.456", Double.class);
        assertTrue(value instanceof Double);
        assertEquals(((Double) value).doubleValue(), (double) 123.456,
                     (double) 0.005);

        value = ConvertUtils.convert("123.456", Float.TYPE);
        assertTrue(value instanceof Float);
        assertEquals(((Float) value).floatValue(), (float) 123.456,
                     (float) 0.005);

        value = ConvertUtils.convert("123.456", Float.class);
        assertTrue(value instanceof Float);
        assertEquals(((Float) value).floatValue(), (float) 123.456,
                     (float) 0.005);

        value = ConvertUtils.convert("123", Integer.TYPE);
        assertTrue(value instanceof Integer);
        assertEquals(((Integer) value).intValue(), (int) 123);

        value = ConvertUtils.convert("123", Integer.class);
        assertTrue(value instanceof Integer);
        assertEquals(((Integer) value).intValue(), (int) 123);

        value = ConvertUtils.convert("123", Long.TYPE);
        assertTrue(value instanceof Long);
        assertEquals(((Long) value).longValue(), (long) 123);

        value = ConvertUtils.convert("123", Long.class);
        assertTrue(value instanceof Long);
        assertEquals(((Long) value).longValue(), (long) 123);

        value = ConvertUtils.convert("123", Short.TYPE);
        assertTrue(value instanceof Short);
        assertEquals(((Short) value).shortValue(), (short) 123);

        value = ConvertUtils.convert("123", Short.class);
        assertTrue(value instanceof Short);
        assertEquals(((Short) value).shortValue(), (short) 123);

        String input = null;

        input = "2002-03-17";
        value = ConvertUtils.convert(input, Date.class);
        assertTrue(value instanceof Date);
        assertEquals(input, value.toString());

        input = "20:30:40";
        value = ConvertUtils.convert(input, Time.class);
        assertTrue(value instanceof Time);
        assertEquals(input, value.toString());

        input = "2002-03-17 20:30:40.0";
        value = ConvertUtils.convert(input, Timestamp.class);
        assertTrue(value instanceof Timestamp);
        assertEquals(input, value.toString());

    }


}
