/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts2.interceptor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <!-- START SNIPPET: javadoc -->
 *
 * A simple wrapper around an exception, providing an easy way to print out the stack trace of the exception as well as
 * a way to get a handle on the exception itself.
 *
 * <!-- END SNIPPET: javadoc -->
 *
 * @author Matthew E. Porter (matthew dot porter at metissian dot com)
 */
public class ExceptionHolder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Exception exception;

    /**
     * Holds the given exception
     *
     * @param exception  the exception to hold.
     */
    public ExceptionHolder(Exception exception) {
        this.exception = exception;
    }

    /**
     * Gets the held exception
     *
     * @return the held exception
     */
    public Exception getException() {
        return this.exception;
    }

    /**
     * Gets the held exception stack trace.
     *
     * @return stack trace
     */
    public String getExceptionStack() {
        if (getException() == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        formatThrowable(sb, getException());
        return sb.toString();
    }

    private void formatThrowable(StringBuilder sb, Throwable t) {
        sb.append(t).append(System.lineSeparator());
        for (StackTraceElement element : t.getStackTrace()) {
            sb.append("\tat ").append(element).append(System.lineSeparator());
        }

        for (Throwable suppressed : t.getSuppressed()) {
            sb.append("Suppressed: ");
            formatThrowable(sb, suppressed);
        }

        Throwable cause = t.getCause();
        if (cause != null) {
            sb.append("Caused by: ");
            formatThrowable(sb, cause);
        }
    }

}
