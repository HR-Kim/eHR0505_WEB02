/**
 * 
 */
package kr.co.ehr.user.service;

import kr.co.ehr.cmn.DTO;

/**
 * @author sist
 *
 */
public class Login extends DTO {

	/** 사용자 ID*/
	private String u_id    ;
	/** 사용자 비번*/
	private String passwd  ;
	
	
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	
}
