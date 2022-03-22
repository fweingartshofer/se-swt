package swt6.spring.basics.ioc.logic.annotationconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swt6.spring.basics.ioc.domain.Employee;
import swt6.spring.basics.ioc.logic.WorkLogService;
import swt6.spring.basics.ioc.util.Log;
import swt6.spring.basics.ioc.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("worklog")
public class WorkLogServiceImpl implements WorkLogService {
    private final Map<Long, Employee> employees = new HashMap<>();

    @Autowired(required = true)
    @Log(Log.Type.STANDARD)
    private Logger logger;

    public WorkLogServiceImpl() {
        init();
    }

    public WorkLogServiceImpl(Logger logger) {
        this();
        this.logger = logger;
    }

    private void init() {
        employees.put(1L, new Employee(1L, "Bill", "Gates"));
        employees.put(2L, new Employee(2L, "James", "Goslin"));
        employees.put(3L, new Employee(3L, "Bjarne", "Stroustrup"));
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Employee findEmployeeById(Long id) {
        Employee empl = employees.get(id);
        logger.log(String.format("findEmployeeById(%s)=%s", id, empl == null ? "<null>" : empl.toString()));
        return empl;
    }

    @Override
    public List<Employee> findAllEmployees() {
        logger.log("findAllEmployees");
        return new ArrayList<>(employees.values());
    }
}
