package io.wks.banktransfer.arch

import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test


class ArchitectureTest {


    companion object {
        lateinit var importedClasses: JavaClasses

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            val options = mutableListOf(
                ImportOption.DoNotIncludeTests(),
            ) as Collection<ImportOption>
            importedClasses = ClassFileImporter(options).importPackages("io.wks.banktransfer")
        }
    }

    @Test
    fun `api package should contain classes that end in Request or Response`() {
        val rule = classes().that().resideInAPackage("..api..")
            .and().areTopLevelClasses()
            .should().haveSimpleNameEndingWith("Request")
            .orShould().haveSimpleNameEndingWith("Response")
            .orShould().haveSimpleNameEndingWith("Api")

        rule.check(importedClasses)
    }

    @Test
    fun `app package should contain classes that end in Controller or ControllerAdvice`() {
        val rule = classes().that().resideInAPackage("..app..")
            .and().areTopLevelClasses()
            .should().haveSimpleNameEndingWith("Controller")
            .orShould().haveSimpleNameEndingWith("ControllerAdvice")

        rule.check(importedClasses)
    }

    @Test
    fun `core services should contain classes that end in Service, Request or Exception`() {
        val rule = classes().that().resideInAPackage("..core.services..")
            .and().areTopLevelClasses()
            .should().haveSimpleNameEndingWith("Service")
            .orShould().haveSimpleNameEndingWith("Request")
            .orShould().haveSimpleNameEndingWith("Exception")

        rule.check(importedClasses)
    }

    @Test
    fun `respository classes must end in Repository `() {
        val rule = classes().that().resideInAPackage("..persistence..")
            .and().areTopLevelClasses()
            .should().haveSimpleNameEndingWith("Repository")

        rule.check(importedClasses)
    }

    @Test
    fun `layer`() {
        val rule = layeredArchitecture()
            .consideringAllDependencies()
            .layer("Api").definedBy("..api..")
            .layer("App").definedBy("..app..")
            .layer("Models").definedBy("..core.models..")
            .layer("Service").definedBy("..core.services..")
            .layer("Persistence").definedBy("..persistence..")

            .whereLayer("Api").mayOnlyBeAccessedByLayers("App")
            .whereLayer("App").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("App")
            .whereLayer("Models").mayOnlyBeAccessedByLayers("Service", "Persistence", "App")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service")

        rule.check(importedClasses)
    }

    @Test
    fun `Controller classes should implement an Api`() {
        val rule = classes()
            .that().areTopLevelClasses()
            .and().resideInAPackage("..app..")
            .and().haveSimpleNameEndingWith("Controller")
            .should().implement(
                JavaClass.Predicates.resideInAPackage("..api..")
                    .and(JavaClass.Predicates.simpleNameEndingWith("Api")))

        rule.check(importedClasses)
    }
}