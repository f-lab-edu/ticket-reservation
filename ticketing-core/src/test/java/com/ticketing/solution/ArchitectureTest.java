package com.ticketing.solution;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.ImportOptions;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureTest {
    private final JavaClasses classes = new ClassFileImporter(
            new ImportOptions().with(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
    ).importPackages("com.ticketing.solution");

    @Test
    public void domain_package_should_not_access_other_packages() {
        final ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAnyPackage("..application..", "..adapter..");
        rule.check(classes);
    }

    @Test
    public void application_package_should_not_access_adapter_package() {
        final ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat().resideInAnyPackage("..adapter..");
        rule.check(classes);
    }

    @Test
    void repository_annotation_should_only_be_used_in_persistence_layer() {
        final ArchRule rule = ArchRuleDefinition.classes().that().areAnnotatedWith(Repository.class)
                .should().resideInAPackage("..adapter.persistence..");
        rule.check(classes);
    }

    @Test
    void configure_annotation_should_only_be_used_in_config_package() {
        final ArchRule rule = ArchRuleDefinition.classes().that().areAnnotatedWith(Configuration.class)
                .should().resideInAPackage("..adapter.config..");
        rule.check(classes);
    }
}