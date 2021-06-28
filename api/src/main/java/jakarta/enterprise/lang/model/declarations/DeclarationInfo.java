package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.AnnotationTarget;
import jakarta.enterprise.lang.model.types.Type;

/**
 * Declarations are:
 *
 * <ul>
 * <li>a <i>package</i>, as declared in {@code package-info.java}</li>
 * <li>a <i>class</i>, including interfaces, enums, and annotations</li>
 * <li>a <i>field</i></li>
 * <li>a <i>method</i>, including constructors</li>
 * <li>a <i>method parameter</i></li>
 * </ul>
 */
public interface DeclarationInfo extends AnnotationTarget {
    // TODO reevaluate the is*/as*/kind() approach (everywhere!); maybe type checks and casts are better, maybe
    //  something completely different is even better

    enum Kind {
        /** Packages can be annotated in {@code package-info.java}. */
        PACKAGE,
        CLASS,
        METHOD,
        PARAMETER,
        FIELD,
    }

    Kind kind();

    default boolean isPackage() {
        return kind() == Kind.PACKAGE;
    }

    default boolean isClass() {
        return kind() == Kind.CLASS;
    }

    default boolean isMethod() {
        return kind() == Kind.METHOD;
    }

    default boolean isParameter() {
        return kind() == Kind.PARAMETER;
    }

    default boolean isField() {
        return kind() == Kind.FIELD;
    }

    default PackageInfo asPackage() {
        throw new IllegalStateException("Not a package");
    }

    default ClassInfo<?> asClass() {
        throw new IllegalStateException("Not a class");
    }

    default MethodInfo<?> asMethod() {
        throw new IllegalStateException("Not a method");
    }

    default ParameterInfo asParameter() {
        throw new IllegalStateException("Not a parameter");
    }

    default FieldInfo<?> asField() {
        throw new IllegalStateException("Not a field");
    }
}
