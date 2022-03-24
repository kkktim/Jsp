package kr.co.farmstory2.service.board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.ArticleDao;
import kr.co.farmstory2.vo.ArticleVo;

public class WriteService implements CommonService {

	@Override
	public String businessProc(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getMethod().equals("GET")) {
			String cate = req.getParameter("cate");
			String type = req.getParameter("type");
			
			req.setAttribute("cate", cate);
			req.setAttribute("type", type);
			return "/board/write.jsp";
		}else {
			//multipart ���� ������ ����
			//���ε� ������ ����� �ý��� ���
			String path = req.getServletContext().getRealPath("/file");
			//�ִ� ���� ��뷮 10MB
			int maxsize = 1024 * 1024 * 10;
			
			MultipartRequest mr = null;
			try {
				mr = new MultipartRequest(req, path, maxsize, "UTF-8", new DefaultFileRenamePolicy());
			} catch (IOException e) {
				e.printStackTrace();
			}
			ArticleVo av = new ArticleVo();
			String cate = mr.getParameter("cate");
			av.setType(mr.getParameter("type"));
			System.out.println(av.getType());
			av.setUid(mr.getParameter("uid"));
			System.out.println(av.getUid());
			
			av.setTitle(mr.getParameter("title"));
			av.setContent(mr.getParameter("content"));
			String fname = mr.getFilesystemName("fname");
			av.setFile(fname != null ? 1 : 0);
			
			av.setRegip(req.getRemoteAddr());
			
			//�� ���̺� insert
			int no = ArticleDao.getInstance().insertArticle(av);

			//������ ÷�� �Ǿ��ٸ�,
			if(av.getFile() == 1) {
				
				//���ϸ� ����
				String nName = renameFile(path, fname, av.getUid());
				//���� ���̺� insert
				ArticleDao.getInstance().insertFile(no, fname, nName);
			}
			
			return "redirect:/Farmstory2/board/list.do?cate="+cate+"&type="+av.getType();
		}
		
		
	}//businessProc end...
	public String renameFile(String path, String fname, String uid) {
		int i = fname.lastIndexOf(".");
		String exe = fname.substring(i);
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy_MM_dd_HH_mm_ss_");
		String first = sdf.format(today);
		String newName = first+uid+exe;
		
		File ofile = new File(path+"/"+fname);
		File nfile = new File(path+"/"+newName);
		
		ofile.renameTo(nfile);
		
		return newName;
	}

}
