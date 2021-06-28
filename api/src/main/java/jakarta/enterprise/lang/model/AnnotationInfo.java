package jakarta.enterprise.lang.model;

import jakarta.enterprise.lang.model.declarations.ClassInfo;
import java.lang.annotation.Repeatable;
import java.util.Collection;

public interface AnnotationInfo {
    /**
     * Target of this annotation.
     * That is, the declaration, the type parameter or the type use on which this annotation is present.
     * TODO what if this annotation is a nested annotation?
     * TODO what if this annotation doesn't have a known target (e.g. qualifier of a synthetic bean)?
     *
     * @return target of this annotation
     */
    AnnotationTarget getTarget();

    /**
     * Declaration of this annotation's type.
     *
     * @return declaration of this annotation
     */
    ClassInfo<?> getDeclaration();

    /**
     * Fully qualified name of this annotation.
     * Equivalent to {@code declaration().name()}.
     *
     * @return fully qualified name of this annotation
     */
    default String getName() {
        return getDeclaration().name();
    }

    /**
     * Returns whether this annotation is repeatable. In other words, returns whether
     * this annotation's type is meta-annotated with {@code @Repeatable}.
     *
     * @return whether this annotation is repeatable
     */
    default boolean isRepeatable() {
        return getDeclaration().hasAnnotation(Repeatable.class);
    }

    /**
     * Whether this annotation has an attribute with given {@code name}.
     *
     * @param name attribute name
     * @return whether this annotation has an attribute with given {@code name}
     */
    boolean hasAttribute(String name);

    /**
     * Value of this annotation's attribute with given {@code name}.
     * TODO what if it doesn't exist? null, exception, or change return type to Optional
     *
     * @param name attribute name
     * @return value of this annotation's attribute with given {@code name}
     */
    AnnotationAttributeValue getAttribute(String name);

    default boolean hasValue() {
        return hasAttribute("value");
    }

    default AnnotationAttributeValue getValue() {
        return getAttribute("value");
    }

    /**
     * All attributes of this annotation.
     *
     * @return all attributes of this annotation
     */
    Collection<AnnotationAttribute> getAttributes();
}
