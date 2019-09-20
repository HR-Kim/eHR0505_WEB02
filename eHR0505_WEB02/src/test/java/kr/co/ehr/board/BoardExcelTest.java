package kr.co.ehr.board;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import kr.co.ehr.board.service.Board;
import kr.co.ehr.board.service.impl.BoardDaoImpl;
import kr.co.ehr.cmn.UserExcelWriter;
import kr.co.ehr.code.service.Code;
import kr.co.ehr.code.service.impl.CodeDaoImpl;
import kr.co.ehr.user.service.Level;
import kr.co.ehr.user.service.Search;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//@Test NAME_ASCENDING으로 수행.
public class BoardExcelTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private UserExcelWriter userExcelWriter;
	
	@Autowired
	BoardDaoImpl boardDaoImpl;
	
	
	List<Board> boards;
	
	@Before
	public void setUp() {
		boards = Arrays.asList(
				 new Board("1","J제목01",2,"J내용01","admin","2019/09/20")
				,new Board("2","J제목02",0,"J내용02","admin","2019/09/20")
				,new Board("3","J제목03",0,"J내용03","admin","2019/09/20")
				,new Board("4","J제목04",0,"J내용04","admin","2019/09/20") 
				,new Board("5","J제목05",0,"J내용05","admin","2019/09/20")
				);
	}
	
	@Test
	public void userExcelWriter() {
		List<String> headers = Arrays.asList("게시글_순번"
				,"제목"
				,"조회수"
				,"내용"
				,"등록자ID"
				,"등록일"
				);
		
		String saveFileNm = userExcelWriter.xlsxWriterGeneralization(boards, headers);
		LOG.info("saveFileNm:"+saveFileNm);
		
	}
	
	@Test
	@Ignore
	public void get_retrieve() {
		Search vo = new Search(10,1,"",""); //생성자 참고해서 하드코딩
		List<Board> list = (List<Board>) boardDaoImpl.get_retrieve(vo); //List에 담아야함. Board에 대한내용을
		
		for(Board board : list) {
			LOG.debug("99:"+board.toString());
		}
		
	}
	
	@Test
	@Ignore
	public void do_update() {
		Board board = boards.get(0);
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		for(Board vo:boards) {
			boardDaoImpl.do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");		
		for(Board vo:boards) {
			boardDaoImpl.do_save(vo);
		}
		
		Board board01 = boards.get(0);
		board01.setTitle("강슬기U");
		board01.setContents("J내용99");
		board01.setReadCnt(99);
		board01.setRegId("admin99");
		
		int flag = boardDaoImpl.do_update(board01);
		assertThat(1, is(1));
		
		Board vsUser = (Board) boardDaoImpl.get_selectOne(board01);
		
		assertThat(board01.getTitle(), is(vsUser.getTitle()));
		assertThat(board01.getContents(), is(vsUser.getContents()));
		assertThat(board01.getReadCnt(), is(vsUser.getReadCnt()));	
		assertThat(board01.getRegId(), is(vsUser.getRegId()));	
		assertThat(board01.getBoardId(), is(vsUser.getBoardId()));	
	}
	
	@Test
	@Ignore
	public void addAndGet() {
		Board board = boards.get(0);
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		for(Board vo:boards) {
			boardDaoImpl.do_delete(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");		
		for(Board vo:boards) {
			boardDaoImpl.do_save(vo);
		}
		
		LOG.debug("======================================");
		LOG.debug("=03. 단건조회=");
		LOG.debug("======================================");		
		for(Board vo:boards) {
			Board boardOne = (Board) boardDaoImpl.get_selectOne(vo);
			checkUser(boardOne,vo);
		}
		
	
	}
	
	private void checkUser(Board board01,Board vsUser) { 
		assertThat(board01.getTitle(), is(vsUser.getTitle()));
		assertThat(board01.getContents(), is(vsUser.getContents()));
		assertThat(board01.getReadCnt(), is(vsUser.getReadCnt()));	
		assertThat(board01.getRegId(), is(vsUser.getRegId()));	
		assertThat(board01.getBoardId(), is(vsUser.getBoardId()));	
	}
	
	@Test
	@Ignore
	public void do_save() {
		Board board = boards.get(0);
		boardDaoImpl.do_delete(board);
		boardDaoImpl.do_save(board);
	}
	
	@Test
	@Ignore
	public void do_delete() {
		Board board = boards.get(0);
		board.setBoardId("1");
		boardDaoImpl.do_delete(board);
	}
	
	
	@Test
	public void getBean() {
		LOG.debug("====================");
		LOG.debug("=context="+context);
		LOG.debug("=boardDaoImpl="+boardDaoImpl);
		LOG.debug("====================");
		assertThat(context,  is(notNullValue()));
		assertThat(boardDaoImpl,  is(notNullValue()));
	}
	
	
	@After
	public void tearDown() {
	
	}
	
	
	
}


