package jakarta.enterprise.lang.model;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Annotation target is anything that can be annotated.
 * That is:
 *
 * <ul>
 * <li>a <i>declaration</i>, such as a class, method, field, etc.</li>
 * <li>a <i>type parameter</i>, occurring in class declarations and method declarations</li>
 * <li>a <i>type use</i>, such as a type of method parameter, a type of field, a type argument, etc.</li>
 * </ul>
 */
public interface AnnotationTarget {
    // TODO specify equals/hashCode (for the entire .lang.model hierarchy)
    // TODO settle on using Optional everywhere, or allowing null everywhere; it's a mix right now

    /**
     * Returns whether an annotation is present on this annotation target.
     * @param annotationType The annotation type.
     * @return <code>true</code> if the given annotation type is present or <code>false</code> of the given annotation type is <code>null</code> or not present
     */
    boolean hasAnnotation(String annotationType);

    /**
     * Returns whether an annotation is present on this annotation target.
     * @param annotationType The annotation type.
     * @return <code>true</code> if the given annotation type is present or <code>false</code> of the given annotation type is <code>null</code> or not present
     */
    default boolean hasAnnotation(Class<? extends Annotation> annotationType) {
        return hasAnnotation(annotationType.getName());
    }

    /**
     * Returns <code>true</code> if any annotation is present that matches the passed predicate.
     * @param predicate The predicate which receives an {@link jakarta.enterprise.lang.model.AnnotationInfo}
     * @return <code>true</code> if the given predicate matches any annotation
     */
    boolean hasAnnotation(Predicate<AnnotationInfo> predicate);

    /**
     * Retrieves an annotation of the given type name.
     * @param annotationType The annotation type
     * @return An {@link jakarta.enterprise.lang.model.AnnotationInfo} if present
     */
    Optional<AnnotationInfo> getAnnotation(String annotationType);

    /**
     * Retrieves an annotation of the given type.
     * @param annotationType The annotation type
     * @return An {@link jakarta.enterprise.lang.model.AnnotationInfo} if present
     */
    default Optional<AnnotationInfo> getAnnotation(Class<? extends Annotation> annotationType) {
        return getAnnotation(annotationType.getName());
    }

    /**
     * Obtains a collection of zero or many repeated {@link jakarta.enterprise.lang.model.AnnotationInfo} instances
     * for the given repeatable annotation type.
     *
     * <p>The passed type should be an annotation that is meta annotated with {@link java.lang.annotation.Repeatable}.</p>
     *
     * @param annotationType The annotation repeatable type. That is the a
     * @return An immutable collection of {@link jakarta.enterprise.lang.model.AnnotationInfo}
     */
    Collection<AnnotationInfo> getRepeatableAnnotations(Class<? extends Annotation> annotationType);

    /**
     * Resolves all of the {@link jakarta.enterprise.lang.model.AnnotationInfo} instances that match the given predicate.
     * @param predicate The predicate
     * @return An immutable collection of {@link jakarta.enterprise.lang.model.AnnotationInfo} instances
     */
    Collection<AnnotationInfo> getAnnotations(Predicate<AnnotationInfo> predicate);

    /**
     * @return An immutable collection of {@link jakarta.enterprise.lang.model.AnnotationInfo} instances
     */
    Collection<AnnotationInfo> getAnnotations();
}
