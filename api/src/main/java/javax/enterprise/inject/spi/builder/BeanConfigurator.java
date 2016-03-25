/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javax.enterprise.inject.spi.builder;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanAttributes;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.PassivationCapable;
import javax.enterprise.util.TypeLiteral;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This API is an helper to configure a new {@link Bean} instance.
 * CDI container must provides an implementation of this interface.
 *
 * BeanConfigurator is not reusable.
 *
 * This builder is not thread safe and shall not be used concurrently.
 *
 * @see AfterBeanDiscovery#addBean()
 * @author Martin Kouba
 * @author Antoine Sabot-Durand
 * @param <T> the class of the bean instance
 * @since 2.0
 */
public interface BeanConfigurator<T> {


    /**
     *
     * Set the class of the configured Bean.
     * If not set, the extension class is used.
     *
     * @param beanClass class of the configured bean
     * @return self
     */
    BeanConfigurator<T> beanClass(Class<?> beanClass);

    /**
     *
     * Add an InjectionPoint to the configured bean
     *
     * @param injectionPoint the injectionPoint to add
     * @return self
     */
    BeanConfigurator<T> addInjectionPoint(InjectionPoint injectionPoint);

    /**
     *
     * Add InjectionPoints to the configured bean
     *
     * @param injectionPoints the injectionPoints to add
     * @return self
     */
    BeanConfigurator<T> addInjectionPoints(InjectionPoint... injectionPoints);

    /**
     *
     * Add InjectionPoints to the configured bean
     *
     * @param injectionPoints the injectionPoints to add
     * @return self
     */
    BeanConfigurator<T> addInjectionPoints(Set<InjectionPoint> injectionPoints);

    /**
     *
     * Replace InjectionPoints for the configured bean
     *
     * @param injectionPoints the injectionPoints for the configured bean
     * @return self
     */
    BeanConfigurator<T> injectionPoints(InjectionPoint... injectionPoints);

    /**
     *
     * Replace InjectionPoints for the configured bean
     *
     * @param injectionPoints the injectionPoints for the configured bean
     * @return self
     */
    BeanConfigurator<T> injectionPoints(Set<InjectionPoint> injectionPoints);

    /**
     *
     * Make the configured bean implements {@link PassivationCapable} and its Id for passivation.
     *
     *
     * @param id for
     * @see PassivationCapable#getId()
     * @return self
     */
    BeanConfigurator<T> id(String id);

    /**
     *
     * Set a {@link Function} to create a bean instance from a {@link CreationalContext}
     *
     * @param callback the Function to create the instance
     * @return self
     */
    <U extends T> BeanConfigurator<U> createWith(Function<CreationalContext<U>, U> callback);

    /**
     *
     * Set a {@link Supplier} to create a bean instance
     *
     *
     * @param callback the Supplier to create the instance
     * @return self
     */
    <U extends T> BeanConfigurator<U> produceWith(Supplier<U> callback);

    /**
     * Set a {@link Function} to create a bean instance from an {@link Instance}
     *
     * @param callback the function to create the instance
     * @return self
     */
    <U extends T> BeanConfigurator<U> produceWith(Function<Instance<Object>, U> callback);

    /**
     * A shortcut for {@code produceWith(() -> existing} where {@code existing} represents an instance whose lifecycle is not managed by CDI.
     *
     * @param instance use as produced instance for the configured bean
     * @return self
     */
    <U extends T> BeanConfigurator<U> producing(U instance);

    /**
     *
     * Set a {@link BiConsumer} to destroy a bean instance from a {@link CreationalContext}.
     * If no destroy callback is specified, a NOOP dispose callback is automatically set.
     *
     * @param callback the BiConsumer to destroy the instance
     * @return self
     */
    BeanConfigurator<T> destroyWith(BiConsumer<T, CreationalContext<T>> callback);

    /**
     *
     * Set a {@link Consumer} to destroy a bean instance.
     * If no dispose callback is specified, a NOOP dispose callback is automatically set.
     *
     * @param callback the Consumer to dispose the instance
     * @return self
     */
    BeanConfigurator<T> disposeWith(Consumer<T> callback);

    /**
     * Read the information from the given annotated type. All relevant information is overwritten.
     *
     * @param type class to read information from
     * @return self
     */
    <U extends T> BeanConfigurator<U> read(AnnotatedType<U> type);

    /**
     * Read the information from the given bean attributes. All relevant information is overwritten.
     *
     * @param beanAttributes beanAttributes to read information from
     * @return self
     */
    BeanConfigurator<T> read(BeanAttributes<?> beanAttributes);

    /**
     *
     * Add a type to the bean types
     *
     * @param type the type to add
     * @return self
     */
    BeanConfigurator<T> addType(Type type);

    /**
     *
     * Add a type to the bean types
     *
     * @param typeLiteral the type to add
     * @return self
     */
    BeanConfigurator<T> addType(TypeLiteral<?> typeLiteral);

    /**
     *
     * Add types to the bean types
     *
     * @param types types to add
     * @return self
     */
    BeanConfigurator<T> addTypes(Type... types);

    /**
     *
     * Add types to the bean types
     *
     * @param types types to add
     * @return self
     */
    BeanConfigurator<T> addTypes(Set<Type> types);

    /**
     * Adds an unrestricted set of bean types for the given type as if it represented a bean class of a managed bean.
     * Illegal bean types are omitted.
     *
     * @param type to build the closure from
     * @return self
     */
    BeanConfigurator<T> addTransitiveTypeClosure(Type type);

    /**
     *
     * Replace bean types
     *
     * @param types the types of the configured bean
     * @return self
     */
    BeanConfigurator<T> types(Type... types);

    /**
     *
     * Replace bean types
     *
     * @param types the types of the configured bean
     * @return self
     */
    BeanConfigurator<T> types(Set<Type> types);

    /**
     *
     * Replace Bean scope
     *
     * @param scope new scope for the configured bean
     * @return self
     */
    BeanConfigurator<T> scope(Class<? extends Annotation> scope);

    /**
     *
     * Add a qualifier to the configured bean
     * If the builder declares the {@link Default} qualifier, it's automatically removed.
     *
     * @param qualifier qualifier to add
     * @return self
     */
    BeanConfigurator<T> addQualifier(Annotation qualifier);

    /**
     *
     * Add qualifiers to the bean.
     * If the builder declares the {@link Default} qualifier, it's automatically removed.
     *
     * @param qualifiers qualifiers to add
     * @return self
     */
    BeanConfigurator<T> addQualifiers(Annotation... qualifiers);

    /**
     *
     * Add qualifiers to the bean.
     * If the builder declares the {@link Default} qualifier, it's automatically removed.
     *
     * @param qualifiers qualifiers to add
     * @return self
     */
    BeanConfigurator<T> addQualifiers(Set<Annotation> qualifiers);

    /**
     * Replace all qualifiers.
     *
     * @param qualifiers qualifiers for the build bean
     * @return self
     */
    BeanConfigurator<T> qualifiers(Annotation... qualifiers);

    /**
     * Replace all qualifiers.
     *
     * @param qualifiers for the configured bean
     * @return self
     */
    BeanConfigurator<T> qualifiers(Set<Annotation> qualifiers);

    /**
     *
     * Add a stereotype to the configured bean
     *
     * @param stereotype stereotype to add
     * @return self
     */
    BeanConfigurator<T> addStereotype(Class<? extends Annotation> stereotype);

    /**
     *
     * Add stereotypes to the configured bean
     *
     * @param stereotypes stereotypes to add
     * @return self
     */
    BeanConfigurator<T> addStereotypes(Set<Class<? extends Annotation>> stereotypes);

    /**
     *
     * Replace stereotypes on the configured bean
     *
     * @param stereotypes for the configured bean
     * @return self
     */
    BeanConfigurator<T> stereotypes(Set<Class<? extends Annotation>> stereotypes);

    /**
     *
     * Set the name of the configured bean
     *
     * @param name name for the configured bean
     * @return self
     */
    BeanConfigurator<T> name(String name);

    /**
     *
     * Change the alternative status of the configured bean.
     * By default the configured bean is not an alternative.
     *
     * @param value value for alternative property
     * @return self
     */
    BeanConfigurator<T> alternative(boolean value);

}
