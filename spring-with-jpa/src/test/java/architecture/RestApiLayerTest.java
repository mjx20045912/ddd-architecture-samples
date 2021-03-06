package architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;
import study.huhao.demo.adapters.restapi.resources.RequestDto;
import study.huhao.demo.adapters.restapi.resources.ResponseDto;
import study.huhao.demo.domain.core.HumbleObject;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.theClass;

class RestApiLayerTest {
    private JavaClasses classes;

    @BeforeEach
    void setUp() {
        classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("study.huhao.demo");
    }

    @Nested
    class all {

        @Test
        void all_should_be_package_private_except_interfaces() {
            classes()
                    .that().resideInAPackage("..restapi..")
                    .and().areNotInterfaces()
                    .should().bePackagePrivate()
                    .as("All in the rest api layer should be package private, except interfaces.")
                    .check(classes);
        }
    }

    @Nested
    class controller {

        @Test
        void resources_should_be_annotated_with_RestController() {
            classes()
                    .that().resideInAPackage("..restapi..")
                    .and().haveSimpleNameEndingWith("Resource")
                    .should().beAnnotatedWith(RestController.class)
                    .as("The resources should be annotated with 'RestController'.")
                    .check(classes);
        }

        @Test
        void resources_should_be_named_ending_with_Resource() {
            classes()
                    .that().resideInAPackage("..restapi..")
                    .and().areAnnotatedWith(RestController.class)
                    .should().haveSimpleNameEndingWith("Resource")
                    .as("The resources should be named ending with 'Resource'.")
                    .check(classes);
        }
    }

    @Nested
    class request_dto {

        @Test
        void RequestDto_interface_should_extend_HumbleObject() {
            theClass(RequestDto.class)
                    .should().beAssignableTo(HumbleObject.class)
                    .as("The RequestDto interface should extend HumbleObject.")
                    .check(classes);
        }


        @Test
        void request_dtos_should_be_named_ending_with_Request() {
            classes()
                    .that().resideInAPackage("..restapi..")
                    .and().implement(RequestDto.class)
                    .should().haveSimpleNameEndingWith("Request")
                    .as("The request DTOs should be named ending with 'Request'.")
                    .check(classes);
        }

        @Test
        void request_dtos_should_implement_RequestDto() {
            classes()
                    .that().resideInAPackage("..restapi..")
                    .and().haveSimpleNameEndingWith("Request")
                    .and().areNotInterfaces()
                    .should().implement(RequestDto.class)
                    .as("The request DTOs should implement 'RequestDto' interface.")
                    .check(classes);
        }

    }

    @Nested
    class response_dto {

        @Test
        void ResponseDto_interface_should_extend_HumbleObject() {
            theClass(ResponseDto.class)
                    .should().beAssignableTo(HumbleObject.class)
                    .as("The ResponseDto interface should extend HumbleObject.")
                    .check(classes);
        }

        @Test
        void response_dtos_should_be_named_ending_with_Dto() {
            classes()
                    .that().resideInAPackage("..restapi..")
                    .and().implement(ResponseDto.class)
                    .should().haveSimpleNameEndingWith("Dto")
                    .as("The response DTOs should be named ending with 'Dto'.")
                    .check(classes);
        }

        @Test
        void response_dtos_should_implement_ResponseDto() {
            classes()
                    .that().resideInAPackage("..restapi..")
                    .and().haveSimpleNameEndingWith("Dto")
                    .and().areNotInterfaces()
                    .should().implement(ResponseDto.class)
                    .as("The response DTOs should implement 'ResponseDto' interface.")
                    .check(classes);
        }

    }
}
