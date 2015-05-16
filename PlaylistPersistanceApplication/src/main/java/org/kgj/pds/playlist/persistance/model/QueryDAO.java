package org.kgj.pds.playlist.persistance.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.persistance.entity.QueryEntity;
import org.kgj.pds.playlist.persistance.messagingProtocol.PlaylistType;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query.Action;
import org.kgj.pds.playlist.persistance.messagingService.ClientAppMessagingServiceManager;

public class QueryDAO implements IDAOService<QueryEntity> {

	protected static final Logger logger = Logger.getLogger(QueryDAO.class);
	public static Properties props;
	static {
		props = new Properties();
		InputStream inStream = org.kgj.pds.playlist.persistance.model.TrackDAO.class.getResourceAsStream("db.properties");
		try {
			props.load(inStream);
			String driverName = props.getProperty("driverName");
			Class.forName(driverName);
		} catch (IOException e) {
			System.err.println("Fichier introuvable");
		} catch (ClassNotFoundException e) {
			System.err.println("Mauvais nom de driver");
		}
	}

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
			pStmt = connection.prepareStatement(sqlOrder2);
			maxId++;
			query.getPlaylist().get(0).setIdentifier(Integer.toString(maxId));
			pStmt.setInt(1, maxId);
			pStmt.setInt(2, 1);
			pStmt.setBoolean(3, true);
			pStmt.setString(4, query.getPlaylist().get(0).getCreator());
			pStmt.setString(5, ClientAppMessagingServiceManager.getInstance().queryToString(query));
			logger.info(pStmt.toString());
			logger.info(ClientAppMessagingServiceManager.getInstance().queryToString(query));
			pStmt.executeUpdate();
			return maxId;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return -1;
	}

	public Query readByUser(String username) {
		String urlDatabase = props.getProperty("urlDatabase");
		String login = props.getProperty("login");
		String password = props.getProperty("password");
		ClientAppMessagingServiceManager clientAppMessagingService = ClientAppMessagingServiceManager.getInstance();

		try {
			Connection connection = DriverManager.getConnection(urlDatabase, login, password);
			String sqlOrder = props.getProperty("queryRead");

			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);
			pStmt.setString(1, username);
			pStmt.setString(2, username);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				Query query = clientAppMessagingService.stringToQuery(rs.getString("query"));
				while (rs.next()) {
					Query subQuery = clientAppMessagingService.stringToQuery(rs.getString("query"));
					query.getPlaylist().addAll(subQuery.getPlaylist());
				}
				return query;
			}

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
	public QueryEntity read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(Query query) {
		String urlDatabase = props.getProperty("urlDatabase");
		String login = props.getProperty("login");
		String password = props.getProperty("password");
		ClientAppMessagingServiceManager clientAppMessagingService = ClientAppMessagingServiceManager.getInstance();

		try {
			Connection connection = DriverManager.getConnection(urlDatabase, login, password);

			String sqlOrder = props.getProperty("queryEnableDisable");
			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);

			PlaylistType currentPlaylist = query.getPlaylist().get(0);
			currentPlaylist.setVersion(currentPlaylist.getVersion() + 1);
			pStmt.setString(1, currentPlaylist.getIdentifier() + "," + currentPlaylist.getVersion() + "," + false + "," + currentPlaylist.getCreator() + ","
					+ ClientAppMessagingServiceManager.getInstance().queryToString(query));

			ResultSet rs = pStmt.executeQuery();
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	public boolean updatePlaylist(Query query) {
		String urlDatabase = props.getProperty("urlDatabase");
		String login = props.getProperty("login");
		String password = props.getProperty("password");
		ClientAppMessagingServiceManager clientAppMessagingService = ClientAppMessagingServiceManager.getInstance();

		try {
			Connection connection = DriverManager.getConnection(urlDatabase, login, password);

			String sqlOrder = props.getProperty("queryEnableDisable");
			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);

			PlaylistType currentPlaylist = query.getPlaylist().get(0);
			currentPlaylist.setVersion(currentPlaylist.getVersion() + 1);
			pStmt.setString(1, currentPlaylist.getIdentifier() + "," + currentPlaylist.getVersion() + "," + true + "," + currentPlaylist.getCreator() + ","
					+ ClientAppMessagingServiceManager.getInstance().queryToString(query));

			ResultSet rs = pStmt.executeQuery();
			return true;
		} catch (Exception e) {

		}
		return false;
	}

}
