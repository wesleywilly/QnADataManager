package paraphrases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class ParaphraseDatabase {

	private final Connection connection;
	
	public ParaphraseDatabase(String dbFile) throws SQLException {
		//Class.forName("org.sqlite.JDBC");
		this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
	}
	
	public Set<String> query(String word) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from paraphrase where " +
				"word1 = '" + word + "' or word2 = '" + word + "'");
		HashSet<String> result = new HashSet<>();
		while (rs.next()) {
			String word1 = rs.getString("word1");
			String word2 = rs.getString("word2");
			if (!word.equals(word1) && !result.contains(word1)) {
				result.add(word1);
			}
			if (!word.equals(word2) && !result.contains(word2)) {
				result.add(word2);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		try {
			ParaphraseDatabase db = new ParaphraseDatabase("/home/diorge/Documents/projects/paraphrases/data/database");
			System.out.println(db.query("fear"));
			System.out.println(db.query("accident"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
