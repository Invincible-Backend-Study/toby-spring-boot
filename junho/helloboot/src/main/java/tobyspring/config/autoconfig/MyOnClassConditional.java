package tobyspring.config.autoconfig;

import java.util.Map;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

public class MyOnClassConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attr = metadata.getAnnotationAttributes(ConditionalMyonClass.class.getName());
        String str = (String) attr.get("value");
        return ClassUtils.isPresent(str,context.getClassLoader());
    }
}
