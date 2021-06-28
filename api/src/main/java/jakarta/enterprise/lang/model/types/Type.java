package jakarta.enterprise.lang.model.types;

import jakarta.enterprise.lang.model.AnnotationTarget;

/**
 * Types are:
 *
 * <ul>
 * <li>a <i>void</i> type</li>
 * <li>a <i>primitive</i> type, such as {@code int}</li>
 * <li>a <i>class</i> type, such as {@code String}</li>
 * <li>an <i>array</i> type, such as {@code int[]} or {@code String[][]}</li>
 * <li>a <i>parameterized type</i>, such as {@code List<String>}</li>
 * <li>a <i>type variable</i>, such as {@code T} when used in a class that declares a type parameter {@code T}</li>
 * <li>a <i>wildcard</i> type, such as the type argument declared in {@code List<? extends Number>}</li>
 * </ul>
 */
public interface Type extends AnnotationTarget {

    enum Kind {
        /** E.g. when method returns {@code void}. */
        VOID,
        /** E.g. when method returns {@code int}. */
        PRIMITIVE,
        /** E.g. when method returns {@code String}. */
        CLASS,
        /** E.g. when method returns {@code int[]} or {@code String[][]}. */
        ARRAY,
        /** E.g. when method returns {@code List<String>}. */
        PARAMETERIZED_TYPE,
        /** E.g. when method returns {@code T} and {@code T} is a type parameter of the declaring class. */
        TYPE_VARIABLE,
        /**
         * E.g. when method returns {@code List<? extends Number>}. On the first level, we have a {@code PARAMETERIZED_TYPE},
         * but on the second level, the first (and only) type argument is a {@code WILDCARD_TYPE}.
         */
        WILDCARD_TYPE,
    }

    Kind kind();

    default boolean isVoid() {
        return kind() == Kind.VOID;
    }

    default boolean isPrimitive() {
        return kind() == Kind.PRIMITIVE;
    }

    default boolean isClass() {
        return kind() == Kind.CLASS;
    }

    default boolean isArray() {
        return kind() == Kind.ARRAY;
    }

    default boolean isParameterizedType() {
        return kind() == Kind.PARAMETERIZED_TYPE;
    }

    default boolean isTypeVariable() {
        return kind() == Kind.TYPE_VARIABLE;
    }

    default boolean isWildcardType() {
        return kind() == Kind.WILDCARD_TYPE;
    }

    default VoidType asVoid() {
        throw new IllegalStateException("Not a void");
    }

    default PrimitiveType asPrimitive() {
        throw new IllegalStateException("Not a primitive");
    }

    default ClassType asClass() {
        throw new IllegalStateException("Not a class");
    }

    default ArrayType asArray() {
        throw new IllegalStateException("Not an array");
    }

    default ParameterizedType asParameterizedType() {
        throw new IllegalStateException("Not a parameterized type");
    }

    default TypeVariable asTypeVariable() {
        throw new IllegalStateException("Not a type variable");
    }

    default WildcardType asWildcardType() {
        throw new IllegalStateException("Not a wildcard type");
    }
}
