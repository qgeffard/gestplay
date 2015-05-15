package org.kgj.pds.playlist.persistance.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.kgj.pds.playlist.persistance.entity.PlaylistXMLEntity;
import org.kgj.pds.playlist.persistance.entity.QueryEntity;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;
import org.kgj.pds.playlist.persistance.messagingService.ClientAppMessagingServiceManager;

public class QueryDAO implements IDAOService<QueryEntity> {

	protected static final Logger logger = Logger.getLogger(QueryDAO.class);
	public static Properties props;

	@Override
	public boolean create(QueryEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	public int create(Query query) {
		String urlDatabase = props.getProperty("urlDatabase");
		String login = props.getProperty("login");
		String password = props.getProperty("password");
		int maxId = 0;

		try {
			Connection connection = DriverManager.getConnection(urlDatabase, login, password);
			String sqlOrder = props.getProperty("queryMaxId");

			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				maxId = Integer.valueOf(rs.getString("id"));
			}

			String sqlOrder2 = props.getProperty("queryInsert");
			maxId++;

			pStmt.setString(1,
					maxId + "," + 1 + "," + true + "," + query.getPlaylist().get(0).getCreator() + "," + ClientAppMessagingServiceManager.getInstance().queryToString(query));

			return maxId;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return -1;
	}

	@Override
	public Query readByUser(String username) {
		String urlDatabase = props.getProperty("urlDatabase");
		String login = props.getProperty("login");
		String password = props.getProperty("password");

		try {
			Connection connection = DriverManager.getConnection(urlDatabase, login, password);
			String sqlOrder = props.getProperty("queryRead");

			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);
			pStmt.setString(1, username);
			pStmt.setString(2, username);

			ResultSet rs = pStmt.executeQuery();

			return ClientAppMessagingServiceManager.getInstance().stringToQuery(rs.toString());

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return null;

	}

	@Override
	public boolean update(QueryEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(QueryEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<QueryEntity> searchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryEntity read(QueryEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

}
