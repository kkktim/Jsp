package kr.co.farmstory2.service.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.ArticleDao;

public class DeleteService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		String cate = req.getParameter("cate");
		String type = req.getParameter("type");
		
		String no = req.getParameter("no");
		String nName = ArticleDao.getInstance().deleteArticle(no);
		if(nName != null) {
			ArticleDao.getInstance().deleteFile(no);
			String path = req.getServletContext().getRealPath("/file");
			File attched_file = new File(path+"/"+nName);
			attched_file.delete();
		}
		return "redirect:/Farmstory2/board/list.do?cate="+cate+"&type="+type;
	}
	
}
