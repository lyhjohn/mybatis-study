package com.yohan.mybatisstudy.mapper;

import com.yohan.mybatisstudy.model.Employee;
import com.yohan.mybatisstudy.model.UserProfile;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {
	@Select("select * from Employee Where id=#{id}")
	@ResultMap(value = "EmployeeMap") // @Results로 지정한 것들을 복사할 필요 없이 value로 재사용 가능
	Employee getEmployee(@Param("id") Long id);

	@Select("select * from Employee")
	@Results(id = "EmployeeMap", value = {
		@Result(property = "name", column = "employee_name"),
		@Result(property = "address", column = "employee_address")
	})
	List<Employee> getEmployeeList();

	// 영향받은 컬럼 수를 반환
	@Insert("insert into employee(employee_name, employee_address, user_id) values(#{Employee.name},"
		+ "#{Employee.address}, ${Employee.user_id})")
	@Options(useGeneratedKeys = true, keyProperty = "id") // model의 id 속성에 key값이 저장되어 반환됨
	int insertEmployee(@Param("Employee") Employee employee);

	@Update("update Employee set employee_name=#{employee.name}, employee_address=#{employee.address} where id=#{id}")
	int updateEmployee(@Param("id") Long id, Employee employee);

	@Delete("delete from Employee where id=#{id}")
	int deleteEmployee(@Param("id") Long id);

	@Select("select * from employee where user_id=#{id}")
	@ResultMap(value = "EmployeeMap")
	List<Employee> getByUserId(@Param("id") Long id);

}
