package jakarta.enterprise.lang.model;

// TODO "attribute" is a colloquial expression, perhaps use something closer to the JLS? AnnotationMember?
public interface AnnotationAttribute {
    String name();

    AnnotationAttributeValue value();
}
