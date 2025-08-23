package com.logship.warehouse.service.service;

import com.logship.warehouse.service.controller.request.AddEmployeeRequest;
import com.logship.warehouse.service.dto.WarehouseEmployeeDTO;
import com.logship.warehouse.service.entity.Warehouse;
import com.logship.warehouse.service.entity.WarehouseEmployee;
import com.logship.warehouse.service.exception.ExceptionManager;
import com.logship.warehouse.service.logging.LogUtil;
import com.logship.warehouse.service.mapper.WarehouseEmployeeMapper;
import com.logship.warehouse.service.repository.WarehouseEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseEmployeeService {

    private final WarehouseEmployeeMapper warehouseEmployeeMapper;
    private final WarehouseEmployeeRepository warehouseEmployeeRepository;

    public void addEmployee(AddEmployeeRequest addEmployeeRequest) {
        try {
            WarehouseEmployee employee = warehouseEmployeeMapper.requestToEntityMapper(addEmployeeRequest);
            warehouseEmployeeRepository.save(employee);
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while adding employee to warehouse");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.EMPLOYEE_ADD_ERROR);
        }
    }

    public List<WarehouseEmployeeDTO> getEmployees(Long warehouseId) {
        try {
            List<WarehouseEmployee> employees = warehouseEmployeeRepository.getWarehouseEmployeesByWarehouse(Warehouse.builder().id(warehouseId).build());
            return warehouseEmployeeMapper.entityToDTOMapper(employees);
        } catch (Exception e) {
            LogUtil.printInfo(getClass(), "Error while fetching warehouse employees");
            LogUtil.printError(e);
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.EMPLOYEE_FETCH_ERROR);
        }
    }
}
