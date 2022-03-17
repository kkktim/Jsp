package kr.co.board2.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.board2.controller.CommonService;
import kr.co.board2.dao.ArticleDao;
import kr.co.board2.vo.FileVo;

public class FileDownloadService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		
		String fid = req.getParameter("fid");
		
		ArticleDao dao = ArticleDao.getInstance();
		
		//파일 첨부 된 글 조회
		FileVo fv = dao.selectFile(fid);
		
		//다운로드 횟수 +1
		dao.updateFileCount(fid);
		
		//리턴 주소로 메인컨트롤러로 보내는 방법
//		String nName = fv.getnName();
//		String oName = fv.getoName();
//		return "file:"+oName+"|"+nName;
		
		req.setAttribute("oName", fv.getoName());
		req.setAttribute("nName", fv.getnName());
		
		return "file:";
	}
	
}
