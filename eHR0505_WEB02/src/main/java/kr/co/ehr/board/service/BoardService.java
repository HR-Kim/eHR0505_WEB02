package kr.co.ehr.board.service;

import java.util.List;

import kr.co.ehr.cmn.DTO;
import kr.co.ehr.user.service.Search;

public interface BoardService {
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
}
