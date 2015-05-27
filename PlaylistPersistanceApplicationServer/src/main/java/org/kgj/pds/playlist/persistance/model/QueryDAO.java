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

import javax.lang.model.type.IntersectionType;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.Utils.QueryMarshaller;
import org.kgj.pds.playlist.persistance.entity.QueryEntity;
import org.kgj.pds.playlist.persistance.messagingProtocol.PlaylistType;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;

public class QueryDAO implements IDAOService<QueryEntity> {

	protected static final Logger logger = Logger.getLogger(QueryDAO.class);
	public static Properties props;
	static {
		props = new Properties();
		InputStream inStream = org.kgj.pds.playlist.persistance.model.QueryDAO.class.getResourceAsStream("db.properties");
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
			pStmt.setString(5, QueryMarshaller.queryToString(query));
			logger.info(pStmt.toString());
			logger.info(QueryMarshaller.queryToString(query));
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

		try {
			Connection connection = DriverManager.getConnection(urlDatabase, login, password);
			String sqlOrder = props.getProperty("queryRead");

			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);
			pStmt.setString(1, username);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				Query query = QueryMarshaller.stringToQuery(rs.getString("query"));
				while (rs.next()) {
					Query subQuery = QueryMarshaller.stringToQuery(rs.getString("query"));
					query.getPlaylist().addAll(subQuery.getPlaylist());
				}
				return query;
			}

		} catch (SQLException e1) {
			logger.error(e1.getMessage());
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

		try {
			Connection connection = DriverManager.getConnection(urlDatabase, login, password);

			String sqlOrder = props.getProperty("queryEnableDisable");
			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);

			PlaylistType currentPlaylist = query.getPlaylist().get(0);

			int maxVersion = getMaxVersionByID(Integer.valueOf(currentPlaylist.getIdentifier()));
			currentPlaylist.setVersion(String.valueOf(maxVersion + 1));

			pStmt.setInt(1, Integer.valueOf(currentPlaylist.getIdentifier()));
			pStmt.setInt(2, Integer.valueOf(currentPlaylist.getVersion()));
			pStmt.setBoolean(3, false);
			pStmt.setString(4, currentPlaylist.getCreator());
			pStmt.setString(5, QueryMarshaller.queryToString(query));

			pStmt.executeUpdate();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean updatePlaylist(Query query) {
		logger.info("enter update");
		logger.info(QueryMarshaller.queryToString(query));
		String urlDatabase = props.getProperty("urlDatabase");
		String login = props.getProperty("login");
		String password = props.getProperty("password");

		try {
			Connection connection = DriverManager.getConnection(urlDatabase, login, password);

			PlaylistType currentPlaylist = query.getPlaylist().get(0);

			int maxVersion = getMaxVersionByID(Integer.valueOf(currentPlaylist.getIdentifier()));

			String sqlOrder = props.getProperty("queryEnableDisable");
			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);

			currentPlaylist.setVersion(String.valueOf(maxVersion + 1));
			pStmt.setInt(1, Integer.valueOf(currentPlaylist.getIdentifier()));
			pStmt.setInt(2, Integer.valueOf(currentPlaylist.getVersion()));
			pStmt.setBoolean(3, true);
			pStmt.setString(4, currentPlaylist.getCreator());
			pStmt.setString(5, QueryMarshaller.queryToString(query));
			pStmt.executeUpdate();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	public int getMaxVersionByID(int id) {

		String urlDatabase = props.getProperty("urlDatabase");
		String login = props.getProperty("login");
		String password = props.getProperty("password");

		Connection connection;
		int maxVersion = 0;
		try {
			connection = DriverManager.getConnection(urlDatabase, login, password);

			String sqlOrder = props.getProperty("queryMaxVersion");
			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				maxVersion = Integer.valueOf(rs.getString("version"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return maxVersion;
	}

	public Query undoUpdate(Query query, String username) {
		String urlDatabase = props.getProperty("urlDatabase");
		String login = props.getProperty("login");
		String password = props.getProperty("password");

		Connection connection;
		int maxVersion = 0;
		try {
			connection = DriverManager.getConnection(urlDatabase, login, password);

			String sqlOrder = props.getProperty("selectVersion");
			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);
			pStmt.setString(1, query.getPlaylist().get(0).getIdentifier());
			pStmt.setString(2, username);
			
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("version") > maxVersion );
					maxVersion = rs.getInt("version");
			}
			
			String sqlOrder2 = props.getProperty("deleteByMaxVersion");
			PreparedStatement pStmt2 = connection.prepareStatement(sqlOrder2);
			pStmt2.setString(1, query.getPlaylist().get(0).getIdentifier());
			pStmt2.setInt(2, maxVersion);
			pStmt2.setString(3, username);
			int rs2 = pStmt2.executeUpdate();
			
			
			
			String sqlOrder1 = props.getProperty("queryReadById");
			PreparedStatement pStmt1 = connection.prepareStatement(sqlOrder1);
			pStmt1.setString(1, query.getPlaylist().get(0).getIdentifier());
			pStmt1.setInt(2, maxVersion-1);
			pStmt1.setString(3, username);
			ResultSet rs1 = pStmt1.executeQuery();
			if (rs1.next()) {
				Query query1 = QueryMarshaller.stringToQuery(rs1.getString("query"));
				while (rs1.next()) {
					Query subQuery = QueryMarshaller.stringToQuery(rs1.getString("query"));
					query1.getPlaylist().addAll(subQuery.getPlaylist());
				}
				return query1;
			}
			return null;
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
