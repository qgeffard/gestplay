package org.kgj.pds.playlist.persistance.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.kgj.pds.playlist.persistance.entity.TrackEntity;

public class TrackDAO implements IDAOService<TrackEntity> {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	
	private static Properties props;
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
	public boolean create(TrackEntity e) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public TrackEntity read(int id) {
		// TODO Auto-generated method stub
		String urlDatabase = props.getProperty("urlDatabase");
		String login = props.getProperty("login");
		String password = props.getProperty("password");
		try {
			Connection connection = DriverManager.getConnection(urlDatabase, login, password);
			String sqlOrder = props.getProperty("formuleInsert");
			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);
			pStmt.setString(1, e.getData());
			pStmt.execute();
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(TrackEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TrackEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TrackEntity> searchAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
