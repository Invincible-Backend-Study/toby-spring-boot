package tobyspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(final AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attributes = importingClassMetadata.getAllAnnotationAttributes(
                EnableMyConfigurationProperties.class.getName());
        Class propertyClass = (Class) attributes.getFirst("value");
        return new String[] {propertyClass.getName()};
    }
}
