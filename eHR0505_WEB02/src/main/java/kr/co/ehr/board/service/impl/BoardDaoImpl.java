package kr.co.ehr.board.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ehr.board.service.Board;
import kr.co.ehr.cmn.DTO;
import kr.co.ehr.cmn.WorkDiv;
import kr.co.ehr.user.service.Search;
import kr.co.ehr.user.service.User;

/**
 * 게시판 DAO
 * @author sist
 *
 */
@Repository
public class BoardDaoImpl implements WorkDiv {
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.ehr.board";
	
	

	public int do_updateReadCnt(DTO dto) {
		String statement = this.NAMESPACE+".do_updateReadCnt";//.do_save
		Board board =  (Board) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+board);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.update(statement, board);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");		
		return flag;
	}
	
	@Override
	public int do_update(DTO dto) {
		String statement = this.NAMESPACE+".do_update";//.do_update
		Board board =  (Board) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+board);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.update(statement, board);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");		
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		String statement = this.NAMESPACE+".do_delete";//.do_save
		Board board =  (Board) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+board);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.delete(statement, board);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");		
		return flag;
	}

	@Override
	public int do_save(DTO dto) {
		String statement = this.NAMESPACE+".do_save";//.do_save
		Board board =  (Board) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+board);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.insert(statement, board);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");		
		return flag;
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		String statement = this.NAMESPACE+".get_selectOne";//kr.co.ehr.user.get_selectOne
		Board board =  (Board) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+board);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		User outUser = this.sqlSessionTemplate.selectOne(statement, board);
		
		LOG.debug("========================");
		LOG.debug("03.outUser="+outUser);
		LOG.debug("========================");		
		return outUser;
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		String statement = this.NAMESPACE+".get_retrieve";//.get_retrieve
		Search search = (Search) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+search);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		List<Board> list = this.sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("========================");
		LOG.debug("03.list="+list);
		LOG.debug("========================");		
		return list;
	}

	@Override
	public List<?> get_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
