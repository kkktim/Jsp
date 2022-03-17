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
		
		//���� ÷�� �� �� ��ȸ
		FileVo fv = dao.selectFile(fid);
		
		//�ٿ�ε� Ƚ�� +1
		dao.updateFileCount(fid);
		
		//���� �ּҷ� ������Ʈ�ѷ��� ������ ���
//		String nName = fv.getnName();
//		String oName = fv.getoName();
//		return "file:"+oName+"|"+nName;
		
		req.setAttribute("oName", fv.getoName());
		req.setAttribute("nName", fv.getnName());
		
		return "file:";
	}
	
}
