package com.novo.converts;

import com.novo.model.employee.Employee;
import com.novo.model.employee.service.EmployeeService;
import com.novo.model.responsible.Responsible;
import com.novo.model.responsible.service.ResponsibleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert the {@link com.novo.model.employee.Employee} object to the {@link  Responsible}
 *
 * @author Mikhail Dedyukhin
 * @see <a href='https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/convert/converter/Converter.html'>Interface Converter<S,T></a>
 * @see <a href='https://www.youtube.com/watch?v=e9mlrHyn73w'>Spring Boot + thymeleaf checkbox binding</a>
 * @since 1.0
 */
@Slf4j
@Component
public class EmployeeResponsibleConverter implements Converter<String, Responsible> {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponsibleService responsibleService;


    @Override
    public Responsible convert(String source) {
        Employee employee = employeeService.findById(Integer.valueOf(source));
        Responsible responsible = Responsible.builder().id(0).first_name(employee.getFirstName()).middle_name(employee.getMiddleName()).last_name(employee.getLastName()).build();
        Responsible savedResponsible = responsibleService.save(responsible);
        log.debug("The responsible saved under the id: {}", savedResponsible.getId());
        return savedResponsible;
    }
}
