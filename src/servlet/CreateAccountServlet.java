package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ResistAccountBean;
import model.CreateAccount;

/**
 * グループ作成用サーブレット
 */
public class CreateAccountServlet extends HttpServlet {

	/**
	 * 初期表示
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 初期化
		ResistAccountBean bean = new ResistAccountBean();
		CreateAccount model = new CreateAccount();
		String direction = "/WEB-INF/jsp/login.jsp";

		// パラメータの取得
		bean.setUserId(new String(req.getParameter("userId").getBytes("ISO-8859-1")));
		bean.setPassword(new String(req.getParameter("password").getBytes("ISO-8859-1")));
		bean.setUserName(new String(req.getParameter("userName").getBytes("ISO-8859-1")));
		bean.setProfile(new String(req.getParameter("profile").getBytes("ISO-8859-1")));

		//文字入力チェック
		bean = model.checkData(bean);
		//OKだったら登録、Noだったらエラー
		if (bean.isCheckJudge() == true) {

			try {
				// 認証処理
				bean = model.resistUser(bean);

			} catch (Exception e) {
				direction = "/WEB-INF/jsp/createAccount.jsp";
				bean.setErrorMessage("登録できませんでした。");
				req.setAttribute("loginError", bean);
			}

			// 取得に成功した場合セッション情報をセット
			if (bean.getErrorMessage() == null) {
				bean.setErrorMessage("会員登録出来ました。");
				req.setAttribute("loginError", bean);

			}
		} else {
			direction = "/WEB-INF/jsp/createAccount.jsp";
			req.setAttribute("loginError", bean);
		}

		req.getRequestDispatcher(direction).forward(req, res);
	}

}
