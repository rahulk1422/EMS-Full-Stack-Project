package net.fullStackProject.ems_backend.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import net.fullStackProject.ems_backend.dto.EmployeeDto;
import net.fullStackProject.ems_backend.entity.Employee;
import net.fullStackProject.ems_backend.exception.ResourceNotFoundException;
import net.fullStackProject.ems_backend.mapper.EmployeeMapper;
import net.fullStackProject.ems_backend.repository.EmployeeRepository;
import net.fullStackProject.ems_backend.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee =EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmplpoyee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmplpoyee);
	}
	
	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
	    Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id: " + employeeId));
	    
	    return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		// TODO Auto-generated method 

		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		
	Employee employee =	employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee is not exist with given id :"+ employeeId));
		
	employee.setFirstName(updatedEmployee.getFirstName());
	employee.setLastName(updatedEmployee.getLastName());
	employee.setEmail(updatedEmployee.getEmail());
	
    Employee updatedEmployeeObj = employeeRepository.save(employee);
	
		return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		Employee employee =	employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee is not exist with given id :"+ employeeId));
		
		employeeRepository.deleteById(employeeId);
	}


}
