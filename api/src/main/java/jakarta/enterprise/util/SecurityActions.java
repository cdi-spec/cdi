/*
 * JBoss, Home of Professional Open Source
 * Copyright 2018, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.1-SNAPSHOT (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.1-SNAPSHOT
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jakarta.enterprise.util;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 *
 * This utility class is used to optimize invocation made through the SecurityManager
 *
 * @author Antoine Sabot-durand
 */

final class SecurityActions {

    private SecurityActions() {

    }

    static void setAccessible(Method method) {
        if (System.getSecurityManager() != null) {
            AccessController.doPrivileged(
                    (PrivilegedAction<?>) () -> {
                        method.setAccessible(true);
                        return null;
                    }
            );
        } else {
            method.setAccessible(true);
        }
    }


    static Method[] getDeclaredMethods(Class<?> clazz) {
        if (System.getSecurityManager() != null) {
            return AccessController.doPrivileged(
                    (PrivilegedAction<Method[]>) clazz::getDeclaredMethods
            );
        } else {
            return clazz.getDeclaredMethods();
        }
    }
}
