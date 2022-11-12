package hw.streamandoptional.hw28streamandoptional.service;

import hw.streamandoptional.hw28streamandoptional.exception.InvalidInputException;
import hw.streamandoptional.hw28streamandoptional.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.xml.validation.Validator;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {
    private final EmployeeService out = new EmployeeServiceImpl();

    @AfterEach
    public void AfterEach() {
        out.getEmployees().forEach(employee -> out.removeEmployee(employee.getFirstName(), employee.getLastName()));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest1(String firstName, String lastName, Integer salary, Integer department) {
        Employee expected = new Employee(firstName, lastName, salary, department);

        assertThat(out.getAll).isEmpty();
        out.addEmployee(firstName, lastName, salary, department);
        assertThat(out.getEmployees())
                .hasSize(1)
                .containsExactly(expected);
        assertThat(out.findEmployee(expected.getFirstName(), expected.getLastName()))
                .isNotNull()
                .isEqualTo(expected);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> out.addEmployee(firstName, lastName, salary, department));
    }

    @Test
    public void addNegativeTest3() {
        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> out.addEmployee("Ivan#", "Ivanov", 1, 55_000));

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> out.addEmployee("Petr", "!Yan", 1, 55_000));

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> out.addEmployee(null, "Ivanov", 2, 55_000));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removeNegativeTest(String firstName, String lastName, Integer salary, Integer department) {
        assertThat(out.getEmployees().isEmpty());
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> out.removeEmployee("test", "test"));

        Employee expected = new Employee(firstName, lastName, salary, department);
        out.addEmployee(firstName, lastName, salary, department);
        assertThat(out.getEmployees())
                .hasSize(1)
                .containsExactly(expected);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> out.removeEmployee("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removePositiveTest(String firstName, String lastName, Integer salary, Integer department) {
        assertThat(out.getEmployees()).isEmpty();
        Employee expected = new Employee(firstName, lastName, salary, department);
        out.addEmployee(firstName, lastName, salary, department);

        assertThat(out.getEmployees())
                .hasSize(1)
                .containsExactly(expected);

        assertThat(out.removeEmployee(firstName, lastName)).isEqualTo(expected);
        assertThat(out.getEmployees()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("params")
    public void findNegativeTest(String firstName, String lastName, Integer salary, Integer department) {
        assertThat(out.getEmployees()).isEmpty();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> out.findEmployee("test", "test"));

        Employee expected = new Employee(firstName, lastName, salary, department);
        out.addEmployee(firstName, lastName, salary, department);
        assertThat(out.getEmployees())
                .hasSize(1)
                .containsExactly(expected);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> out.findEmployee("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void findPositiveTest(String firstName, String lastName, Integer salary, Integer department) {
        assertThat(out.getEmployees()).isEmpty();
        Employee expected = new Employee(firstName, lastName, salary, department);
        out.addEmployee(firstName, lastName, salary, department);

        assertThat(out.getEmployees())
                .hasSize(1)
                .containsExactly(expected);

        assertThat(out.findEmployee(firstName, lastName)).isEqualTo(expected);
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 1, 55_000),
                Arguments.of("Petr", "Yan", 1, 65_000),
                Arguments.of("Maria", "Sharapova", 2, 75_000)
        );
    }
}