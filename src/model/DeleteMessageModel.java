package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.LoginBean;

public class DeleteMessageModel {
	public LoginBean deleteMessage(LoginBean bean, int deleteMessageNo) {
		// 初期化
		StringBuilder sb = new StringBuilder();

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

			// SQL作成
			sb.append(" UPDATE ");
			sb.append(" T_MESSAGE_INFO ");
			sb.append(" SET ");
			sb.append(" DELETE_FLAG = 1 ");
			sb.append(" WHERE ");
			sb.append(" MESSAGE_NO = " + deleteMessageNo);

			// SQL実行
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sb.toString());
			//メッセージ削除の結果の出力内容を設定。
			if (rs == 0) {
				bean.setErrorMessage("メッセージを削除できませんでした");
			} else {
				bean.setErrorMessage("メッセージを削除しました");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// sqlの接続は絶対に切断
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bean;

	}
}