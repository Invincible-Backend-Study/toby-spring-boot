package tobySpringBoot.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "tobySpringBoot.config.autoconfig.DispatcherServletConfig",
                "tobySpringBoot.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
