package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import db.Database;
import ticketplex.User;

public class SOUserLoadAll {
	public static LinkedList<User> execute() {
		LinkedList<User> users = new LinkedList<User>();

		Connection con = Database.getInstance().getConnection();

		String select = "SELECT * FROM users";

		try (Statement stmt = con.createStatement()) {

			ResultSet rs = stmt.executeQuery(select);

			while (rs.next()) {

				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("Email"));

				users.add(user);
			}

		} catch (SQLException e) {
			System.out.println(e);
			System.out.println(e.getMessage());
		}

		return users;
	}
}
