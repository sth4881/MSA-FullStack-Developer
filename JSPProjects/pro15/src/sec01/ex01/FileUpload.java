package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}//end do..

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}//end post..

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
			String encoding = "utf-8";
			File currentDirPath = new File("C:\\file_repo");
			//업로드 파일 위치 지정
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//파일 경로 설정
			factory.setRepository(currentDirPath);
			//파일 업로드 최대 크기 지정
			factory.setSizeThreshold(1024 * 1024);

			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				//업로드 할 목록 가져오기
				List items = upload.parseRequest(request);
				
				for (int i = 0; i < items.size(); i++) {
					//업로드 목록 하나식 가져오기
					FileItem fileItem = (FileItem) items.get(i);
					
					//일반 input 속성 출력
					if (fileItem.isFormField()) {
						//<input type="text"
						System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					} else {
						System.out.println("파라미터명:" + fileItem.getFieldName());
						System.out.println("파일명:" + fileItem.getName());
						System.out.println("파일크기:" + fileItem.getSize() + "bytes");
						//파일 사이즈 확인	
						if (fileItem.getSize() > 0) {
							int idx = fileItem.getName().lastIndexOf("\\");
							if (idx == -1) {
								idx = fileItem.getName().lastIndexOf("/");
							}//end if
							//업로드 경로에서 파일이름 가져오기
							String fileName = fileItem.getName().substring(idx + 1);
							//파일 이름 설정
							File uploadFile = new File(currentDirPath + "\\" + fileName);
							//파일 업로드
							fileItem.write(uploadFile);
						} // end if
					} // end if
				} // end for
			} catch (Exception e) {
				e.printStackTrace();
			}//end try
	}//end doh..

}//end class
