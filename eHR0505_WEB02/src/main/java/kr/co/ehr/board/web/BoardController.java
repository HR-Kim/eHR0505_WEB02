package kr.co.ehr.board.web;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import kr.co.ehr.board.service.BoardService;
import kr.co.ehr.cmn.StringUtil;
import kr.co.ehr.code.service.Code;
import kr.co.ehr.code.service.CodeService;
import kr.co.ehr.user.service.Search;
import kr.co.ehr.user.service.User;
import kr.co.ehr.user.service.UserService;

@Controller
public class BoardController {
Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="downloadView")
	private View download;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	CodeService codeService;
	
	//View
	private final String VIEW_LIST_NM = "board/board_list";
	private final String VIEW_MNG_NM = "board/board_mng";
	//http://localhost:8080/ehr/user/do_user_view.do
	
    //ExcelDown
	@RequestMapping(value="board/exceldown.do",method=RequestMethod.GET)
	public ModelAndView excelDown(HttpServletRequest req,ModelAndView mView) {
		Search search=new Search();
		/** 페이지 사이즈 */
		String pageSize = StringUtil.nvl(req.getParameter("pageSize"),"10");
		/** 페이지 번호 */
		String pageNum = StringUtil.nvl(req.getParameter("pageNum"),"1");
		/** 검색조건 */
		String searchDiv = StringUtil.nvl(req.getParameter("searchDiv"));
		/** 검색어 */
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"));
		
		/** 확장자 */
		String ext = StringUtil.nvl(req.getParameter("ext"));		
		
		search.setPageSize(Integer.valueOf(pageSize));
		search.setPageNum(Integer.valueOf(pageNum));
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);
		
		String saveFileNm = this.boardService.excelDown(search,ext);//저장파일명
		String orgFileNm  = "보드관리_"+StringUtil.cureDate("yyyyMMdd")+"."+ext;
		
		mView.setView(download);
		
		File  downloadFile=new File(saveFileNm);
		mView.addObject("downloadFile", downloadFile);
		mView.addObject("orgFileNm", orgFileNm);
		
		return mView;
	}
	
	//검색:user/get_retrieve.do
	@RequestMapping(value="board/get_retrieve.do",method=RequestMethod.GET)
	public String get_retrieve(HttpServletRequest req,Search search, Model model) {
		LOG.debug("1=========================");
		LOG.debug("1=param="+search);
		LOG.debug("1=========================");
		
		/** 확장자 */
		String ext = StringUtil.nvl(req.getParameter("ext"));	
		//페이지 사이즈:10
		//페이지 번호:1
		if(search.getPageSize()==0) {
			search.setPageSize(10);
		}

		if(search.getPageNum()==0) {
			search.setPageNum(1);
		}		
		
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
		search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
		LOG.debug("2=========================");
		LOG.debug("2=param="+search);
		LOG.debug("2=========================");
		model.addAttribute("vo", search);
		model.addAttribute("ext", ext);
		//Code:PAGE_SIZE
		Code code=new Code();
		code.setCodeId("PAGE_SIZE");
		//Code정보조회
		List<Code> codeList = (List<Code>) codeService.get_retrieve(code);
		model.addAttribute("codeList", codeList);
		
		code.setCodeId("USER_SEARCH");
		//Code정보조회
		List<Code> codeSearchList = (List<Code>) codeService.get_retrieve(code);
		model.addAttribute("codeSearchList", codeSearchList);
		
		code.setCodeId("EXCEL_TYPE");
		//엑셀유형Code정보조회
		List<Code> excelList = (List<Code>) codeService.get_retrieve(code);
		model.addAttribute("excelList", excelList);
		
		List<User> list = (List<User>) this.boardService.get_retrieve(search);
		model.addAttribute("list", list);
		
		
		//총건수
		int totalCnt = 0;
		if(null !=list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
		}
		model.addAttribute("totalCnt", totalCnt);
		
		return VIEW_LIST_NM;
	}	
}
