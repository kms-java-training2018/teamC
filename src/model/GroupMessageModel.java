package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.GroupMessageBean;
import bean.LoginBean;

/**
 * グループメッセージ画面ビジネスロジック
 */
public class GroupMessageModel {

	public GroupMessageBean authentication(GroupMessageBean bean, LoginBean loginBean, String GroupNo) {

		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 入れなかった場合
			e.printStackTrace();
		}

		// 接続作成
		try {
			conn = DriverManager.getConnection(url, user, dbPassword);
			// 初期化
			StringBuilder sb = new StringBuilder();
			//SQL作成
			sb.append(" SELECT GROUP_NAME ");
			sb.append(" FROM  M_GROUP ");
			sb.append(" WHERE GROUP_NO = " + GroupNo);

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				// Beanに追加
				bean.setGroupName(rs.getString("GROUP_NAME"));
			}

			// 初期化
			sb = new StringBuilder();
			sb.append(" SELECT MU.USER_NAME, TM.MESSAGE, MU.USER_NO ");
			sb.append(" FROM  M_USER MU, T_MESSAGE_INFO TM ");
			sb.append(" WHERE TM.TO_SEND_GROUP_NO = " + GroupNo);
			sb.append(" AND TM.SEND_USER_NO = MU.USER_NO ");

			// SQL実行
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				// Beanに追加
				bean.setName(rs.getString("USER_NAME"));
				bean.setText(rs.getString("MESSAGE"));
				bean.setNumber(rs.getString("USER_NO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("だめでした");
			// sqlの接続は絶対に切断
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// TODO 自動生成されたメソッド・スタブ
		return bean;
	}

	//	public LoginBean authentication(String[] No) {
	//		// 初期化
	//		StringBuilder sb = new StringBuilder();
	//		String group_no = bean.getGroup_no();
	//		String user_no = bean.getMember_no();
	//
	//		//GroupMessageBean
	//		GroupMessageBean groupMessageBean = new GroupMessageBean();
	//
	//		// 接続作成
	//		try {
	//			conn = DriverManager.getConnection(url, user, dbPassword);
	//
	//			// SQL作成（会員番号、グループ番号）
	//			sb.append("SELECT ");
	//			sb.append(" group_no,user_no ");
	//			sb.append("FROM ");
	//			sb.append(" t_group_info ");
	//			sb.append(" WHERE ");
	//			sb.append(" my_group_no = '" + my_group_no + "' ");
	//			sb.append(" de_group_no = '" + de_group_no + "'");
	//			sb.append(" my_user_no = '" + my_user_no + "'");
	//			sb.append(" de_user_no = '" + de_user_no + "'");
	//
	//			// SQL実行（会員番号、グループ番号）
	//			Statement stmt = conn.createStatement();
	//			ResultSet rs = stmt.executeQuery(sb.toString());
	//
	//			if (!rs.next()) {
	//				bean.setErrorMessage("会員番号、グループ番号が一致しませんでした。");
	//			} else {
	//				bean.setUserNo(rs.getString("user_no"));
	//				bean.setUserName(rs.getString("user_name"));
	//				bean.setErrorMessage("");
	//			}
	//
	//			// SQL作成（グループ会話情報）
	//			sb.append("SELECT ");
	//			sb.append(" message ");
	//			sb.append("FROM ");
	//			sb.append(" t_message_info ");
	//			sb.append(" WHERE ");
	//			sb.append(" group_message = '" + group_message + "' ");
	//
	//			// SQL実行（グループ会話情報）
	//			Statement stmt = conn.createStatement();
	//			ResultSet rs = stmt.executeQuery(sb.toString());
	//
	//			if (!rs.next()) {
	//				bean.setErrorMessage("会話情報を取得出来ませんでした。");
	//			} else {
	//				bean.setUserNo(rs.getString("user_no"));
	//				bean.setUserName(rs.getString("user_name"));
	//				bean.setErrorMessage("");
	//			}
	//
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//			// sqlの接続は絶対に切断
	//		} finally {
	//			try {
	//				conn.close();
	//			} catch (SQLException e) {
	//				e.printStackTrace();
	//			}
	//		}
	//
	//		return bean;
	//
	//		// もしもグループ番号、会員番号が取得出来なければエラー
	//		if (group_no && user_no.getAttribute)
	//			;
	//
	//		// もしも会話情報が取得出来なければエラー
	//		if (group_message.getAttribute)
	//			;
	//	}
}