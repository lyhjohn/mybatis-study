package com.yohan.mybatisstudy.service;

import com.yohan.mybatisstudy.mapper.EmployeeMapper;
import com.yohan.mybatisstudy.mapper.UserProfileMapper;
import com.yohan.mybatisstudy.model.UserProfile;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserProfileMapper userProfileMapper;

	private final EmployeeMapper employeeMapper;

	public List<UserProfile> getAll() {
		List<UserProfile> userProfileList = userProfileMapper.getUserProfileList();
		if (!CollectionUtils.isEmpty(userProfileList)) {
			userProfileList.forEach(u -> {
				employeeMapper.getByUserId(u.getId())
					.forEach(e -> u.getEmployees().add(e));
			});
		}
		return userProfileList;
	}

	// Transactional 에 의해 예외 발생 시 반영된 db 까지 모두 롤백됨
	// rollbackFor: rollbackFor 으로 지정해주지 않으면 디폴트로 uncheckedException(런타임), Error만 롤백함
	@Transactional(rollbackFor = Exception.class)
	public UserProfile add(UserProfile userProfile) {
		userProfileMapper.insertUserProfile(userProfile);
		return userProfile;
	}

	@Transactional
	public UserProfile getUser(String id) {
		return userProfileMapper.selectUser(id);
	}

	public List<UserProfile> getUserList() {
		return userProfileMapper.getUserProfileListMapper();
	}

	@Transactional
	public int addUser(UserProfile userProfile) {
		return userProfileMapper.insertUserMapper(userProfile);
	}
}
