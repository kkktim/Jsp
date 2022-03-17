package kr.co.board2.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.board2.controller.CommonService;
import kr.co.board2.dao.ArticleDao;
import kr.co.board2.vo.UserVo;

public class DeleteService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession sess = req.getSession();
		UserVo user = (UserVo) sess.getAttribute("sessUser");
		if(user == null) {
			return "redirect:/Board2/user/login.do";
		}else {
			String no = req.getParameter("no");
			String fid = req.getParameter("fid");
			String dfile = req.getParameter("file");
			String nName = req.getParameter("nName");
			
			ArticleDao dao = ArticleDao.getInstance();
			
			if(dfile == null) {
				dao.deleteArticle(no);
			}else {
				dao.deleteArticle(no);
				dao.deleteFile(fid);
				String path = req.getServletContext().getRealPath("/file");
				File file = new File(path+"/"+nName);
				file.delete();
			}
			
			
			
			return "redirect:/Board2/list.do";
		}
		
	}

}
