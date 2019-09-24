package kr.co.ehr.board.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kr.co.ehr.user.service.User;
import kr.co.ehr.user.web.UserController;

public class LoginValidator implements Validator {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	/**
	 * 해당 타입의 객체를 지원하는지 여부를 리턴
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	/**
	 * 값 검증.
	 */
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
//		if(null == user.getU_id() || user.getU_id().trim().isEmpty()) {
//			errors.rejectValue("u_id", "required");
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "u_id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwd", "required");
		
		if(user.getPasswd().length()<4) {
			errors.rejectValue("passwd", "shortPassword");
		}

	}

}
