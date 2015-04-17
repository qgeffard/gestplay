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
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.kgj.pds.playlist.persistance.entity.PlaylistEntity;
import org.kgj.pds.playlist.persistance.entity.TrackEntity;

public class PlaylistDAO implements IDAOService<PlaylistEntity> {
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
	public boolean create(PlaylistEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PlaylistEntity read(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<PlaylistEntity> getPlaylistByUser(String username) {
		// TODO Auto-generated method stub
		String urlDatabase = props.getProperty("urlDatabase");
		String login = props.getProperty("login");
		String password = props.getProperty("password");
		try {
			Connection connection = DriverManager.getConnection(urlDatabase,
					login, password);

			String sqlOrder = props.getProperty("playlistByUser");
			PreparedStatement pStmt = connection.prepareStatement(sqlOrder);
			pStmt.setString(1, username);
			System.out.println(pStmt.toString());
			ResultSet rs = pStmt.executeQuery();
			List<PlaylistEntity> listPlaylist = new ArrayList<PlaylistEntity>();
			while(rs.next()) {
				PlaylistEntity playlist = new PlaylistEntity();
				playlist.setId(new Integer(rs.getString("id")));
				playlist.setTitle((rs.getString("title")));
				playlist.setCreator((rs.getString("creator")));
				playlist.setAnnotation((rs.getString("annotation")));
				playlist.setInfo(new URI(rs.getString("info")));
				playlist.setLocation(new URI(rs.getString("location")));
				playlist.setIdentifier(new URI(rs.getString("identifier")));
				playlist.setImage(new URI(rs.getString("image")));
				playlist.setDate(new Date(rs.getString("date")));
				playlist.setLicence(new URI(rs.getString("licence")));
				playlist.setAttribution(rs.getString("attribution"));
				playlist.setLink(rs.getString("link"));
				playlist.setMeta(rs.getString("meta"));;
				playlist.setExtension((rs.getString("extension")));
				playlist.setVersion(rs.getString("version"));
				//Gestion de la liste de tracks contenues dans la playlist
				String sqlOrder2 = props.getProperty("idTrackByPlaylist");
				PreparedStatement pStmt2 = connection.prepareStatement(sqlOrder2);
				pStmt2.setString(1, String.valueOf(playlist.getId()));
				ResultSet rs2 = pStmt2.executeQuery();
				List<Integer> trackIdPerPlaylist = new ArrayList<Integer>();
				List<TrackEntity> trackList = new ArrayList<TrackEntity>();
				for (int i = 0; i<trackIdPerPlaylist.size();i++){
					String sqlOrder3 = props.getProperty("trackRead");
					PreparedStatement pStmt3 = connection.prepareStatement(sqlOrder3);
					pStmt3.setString(1, String.valueOf(trackIdPerPlaylist.get(i)));
					trackIdPerPlaylist.get(i);
					ResultSet rs3 = pStmt3.executeQuery();
					while(rs3.next()){
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
						trackList.add(track);
					}
				}
				playlist.setTracklist(trackList);
				listPlaylist.add(playlist);
			}
			return listPlaylist;
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return false;
		return null;
	}

	@Override
	public boolean update(PlaylistEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(PlaylistEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PlaylistEntity> searchAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
