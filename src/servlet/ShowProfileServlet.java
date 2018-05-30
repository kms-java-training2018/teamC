package servlet;

import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.MainPageBean;
import model.MainPageModel;

public class ShowProfileServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		req.getRequestDispatcher("/WEB-INF/jsp/showProfile.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		req.getRequestDispatcher("/WEB-INF/jsp/showProfile.jsp").forward(req, res);

		// 初期化
		StringBuilder sb = new StringBuilder();

		// セッション情報取得
		HttpSession session = req.getSession();

		// もしもセッションが無ければエラー
		if (session.getAttribute("session") != null) { // ログインデータ取得
			LoginBean loginBean = (LoginBean) req.getAttribute("loginBean");

			// 初期化
			MainPageModel model = new MainPageModel();
			MainPageBean bean = new MainPageBean();

			// 認証処理
			try {
				bean = model.authentication(bean, loginBean);
			} catch (Exception e) {
				e.printStackTrace();
				req.getAttribute("loginBean");
			}

			//メッセージ、グループメッセージからbeanを受け取る。後に編集し、代入
			String userId = "0001"; // bean.getUserId();
			String myPageText = "0001"; // bean.get();

			Connection conn = null;
			String url = "jdbc:oracle:thin:@192.168.51.67";
			String user = "DEV_TEAM_C";
			String dbPassword = "C_DEV_TEAM";

			// JDBCドライバーのロード
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {

				// 入れなかった場合
				e.printStackTrace();
			}
			// 接続作成
			try {
				conn = DriverManager.getConnection(url, user, dbPassword);

				//SQL作成
				sb.append("SELECT ");
				sb.append(" user_name , my_page_text ");
				sb.append("FROM ");
				sb.append(" m_user ");
				sb.append("WHERE ");
				sb.append(" user_id = '" + userId + "' ");
				sb.append(" IN user_id = '" + userId + "'");


				//DBの行すべての値を取得するjava文
					 Statement stmt = (Statement) conn.createStatement();
					 //String sql = "SELECT * FROM kabukatable";
					 ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
			}


			 catch (SQLException e) {
				e.printStackTrace();

				// sqlの接続は絶対に切断
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				// 表示画面に表示する用のBean
				req.setAttribute("MainPageBean", bean);
				req.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(req, res);
			}
		}
	}
}