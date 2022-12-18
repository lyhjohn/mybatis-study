package com.yohan.mybatisstudy.mapper;

import com.yohan.mybatisstudy.model.Employee;
import com.yohan.mybatisstudy.model.UserProfile;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserProfileMapper {

	@Select("select * from UserProfile Where id=#{id}")
	@ResultMap(value = "UserMap")
		// @Results로 지정한 것들을 복사할 필요 없이 value로 재사용 가능
	UserProfile getUserProfile(@Param("id") Long id);

	@Select("select * from UserProfile")
	@Results(id = "UserMap", value = {
		// 서브쿼리를 넣고 이 구문을 작성 안하면 메인쿼리의 id가 null로 나온다. (서브쿼리의 id와 컬럼이름이 같기 때문)
		@Result(property = "id", column = "id"),
		@Result(property = "username", column = "name"),
		@Result(property = "userPhone", column = "phone"),
		@Result(property = "employees", column = "id",// 매핑된 테이블의 컬럼을 가져옴
			many = @Many(select = "com.yohan.mybatisstudy.mapper.EmployeeMapper.getByUserId"))
		// 서브쿼리
	})
	List<UserProfile> getUserProfileList();

	// 영향받은 컬럼 수를 반환
	@Insert("insert into UserProfile(name, phone, address) values(#{UserProfile.username},"
		+ "#{UserProfile.userPhone}, #{UserProfile.address})")
	@Options(useGeneratedKeys = true, keyProperty = "id") // 키 자동생성
	// model 객체의 id 속성에 key값이 저장되어 반환됨
	int insertUserProfile(@Param("UserProfile") UserProfile userProfile);

	@Update("update UserProfile set name=#{name}, phone=#{phone}, address=#{address} where id=#{id}")
	int updateUserProfile(@Param("id") String id, @Param("name") String name,
		@Param("phone") String phone, @Param("address") String address);

	@Delete("delete from UserProfile where id=#{id}")
	int deleteUserProfile(@Param("id") String id);

	UserProfile selectUser(String id);

	List<UserProfile> getUserProfileListMapper();

	int insertUserMapper(UserProfile userProfile);

	List<UserProfile> selectUserAndEmployeeUser_ID(Long id);


//	@Select("select * from UserProfile u left join employee e on u.id = e.user_id where u.id = #{id}")
//	@ResultMap(value = "UserMap")
//	UserProfile getUserProfileAndEmployees(@Param("id") Long id);
}
