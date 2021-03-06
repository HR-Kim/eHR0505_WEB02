package kr.co.ehr.user.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.ehr.cmn.DTO;

/**
 * 사용자관리 Interface
 * @author sist
 *
 */
public interface UserService {
	/**수정 */
	public int do_update(DTO dto);
	/**ExcelDown */
	public String excelDown(Search vo,String ext);	
	/**목록조회 */
	public List<?> get_retrieve(DTO dto);
	/**삭제 */
	public int do_delete(DTO dto);
	/**단건조회 */
	public DTO get_selectOne(DTO dto);
	/**저장 */
	public int do_save(DTO dto);
	/**등업*/
	public void tx_upgradeLevels() throws SQLException;
	
	/**회원로그인*/
	public DTO do_loginValidation(DTO dto);
	
}
