package org.kgj.pds.playlist.persistance.model;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kgj.pds.playlist.persistance.entity.TrackEntity;
import org.kgj.pds.playlist.persistance.messagingProtocol.TrackType;

public class TrackDAO implements IDAOService<TrackEntity> {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;

	private static Properties props;
	static {
		props = new Properties();
		InputStream inStream = org.kgj.pds.playlist.persistance.model.TrackDAO.class
				.getResourceAsStream("db.properties");
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
			Connection connection = DriverManager.getConnection(urlDatabase,
					login, password);

			String sqlOrder = props.getProperty("trackRead");
			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);
			pStmt.setString(1, new Integer(id).toString());
			System.out.println(pStmt.toString());
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				TrackEntity track = new TrackEntity();
				track.setId(new Integer(rs.getString("id")));
				track.setLocation(new URI(rs.getString("location")));
				track.setIdentifier(new URI(rs.getString("identifier")));
				track.setTitle((rs.getString("title")));
				track.setCreator((rs.getString("creator")));
				track.setAnnotation((rs.getString("annotation")));
				track.setInfo(new URI(rs.getString("info")));
				track.setImage(new URI(rs.getString("image")));
				track.setAlbum((rs.getString("album")));
				track.setTrackNum(new BigInteger(rs.getString("tracknum")));
				track.setDuration(new BigInteger(rs.getString("duration")));
				track.setLink((rs.getString("link")));
				track.setMeta((rs.getString("meta")));
				track.setExtension((rs.getString("extension")));
				return track;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return false;
		return null;
	}

//	public List<TrackEntity> getTrackByPlaylist(int id) {
//		String urlDatabase = props.getProperty("urlDatabase");
//		String login = props.getProperty("login");
//		String password = props.getProperty("password");
//		try {
//			Connection connection = DriverManager.getConnection(urlDatabase,
//					login, password);
//
//			String sqlOrder = props.getProperty("idTrackByPlaylist");
//			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);
//			pStmt.setString(1, new Integer(id).toString());
//			System.out.println(pStmt.toString());
//			ResultSet rs = pStmt.executeQuery();
//			if (rs.next()) {
//				TrackEntity track = new TrackEntity();
//				track.setId(new Integer(rs.getString("id")));
//				track.setLocation(new URI(rs.getString("location")));
//				track.setIdentifier(new URI(rs.getString("identifier")));
//				track.setTitle((rs.getString("title")));
//				track.setCreator((rs.getString("creator")));
//				track.setAnnotation((rs.getString("annotation")));
//				track.setInfo(new URI(rs.getString("info")));
//				track.setImage(new URI(rs.getString("image")));
//				track.setAlbum((rs.getString("album")));
//				track.setTrackNum(new BigInteger(rs.getString("tracknum")));
//				track.setDuration(new BigInteger(rs.getString("duration")));
//				track.setLink((rs.getString("link")));
//				track.setMeta((rs.getString("meta")));
//				track.setExtension((rs.getString("extension")));
//				return track;
//			}
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// return false;
//		return null;
//		
//		return null;
//	}
	
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

	public TrackType convertToTrackType(TrackEntity userTrack) {
		TrackType track = new TrackType();
		track.setAlbum(userTrack.getAlbum());
		track.setAnnotation(userTrack.getAnnotation());
		track.setCreator(userTrack.getCreator());
		track.setDuration(userTrack.getDuration());
		track.setImage(userTrack.getImage().toString());
		track.setInfo(userTrack.getInfo().toString());
		track.setTitle(userTrack.getTitle());
		track.setTrackNum(userTrack.getTrackNum());
		return track;
	}

}
