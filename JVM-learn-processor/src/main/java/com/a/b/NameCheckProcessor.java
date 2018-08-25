package com.a.b;

import com.google.auto.service.AutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Created by liuzhuang on 2018/8/19.
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class NameCheckProcessor extends AbstractProcessor {
    private Logger log = LoggerFactory.getLogger(NameCheckProcessor.class);

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        log.info("!!!!!!NameCheckProcessor init");
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        log.info("!!!!!!NameCheckProcessor process");
        return false;
    }
}
