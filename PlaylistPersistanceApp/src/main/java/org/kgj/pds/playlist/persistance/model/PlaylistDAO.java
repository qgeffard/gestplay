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
import org.kgj.pds.playlist.persistance.messagingProtocol.PlaylistType;
import org.kgj.pds.playlist.persistance.messagingProtocol.TrackType;

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
			ResultSet rs = pStmt.executeQuery();
			List<PlaylistEntity> listPlaylist = new ArrayList<PlaylistEntity>();
			while(rs.next()) {
				PlaylistEntity playlist = new PlaylistEntity();
				playlist.setId(new Integer(rs.getString("id")));
				playlist.setTitle((rs.getString("title")));
				playlist.setCreator((rs.getString("creator")));
				playlist.setAnnotation((rs.getString("annotation")));
				playlist.setInfo(rs.getString("info"));
				playlist.setLocation(rs.getString("location"));
				playlist.setIdentifier(rs.getString("identifier"));
				playlist.setImage(rs.getString("image"));
				//playlist.setDate(new Date(rs.getString("date_creation")));
				playlist.setLicence(rs.getString("licence"));
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
				while(rs2.next()){
					trackIdPerPlaylist.add(new Integer(rs2.getString("track_id")));
				}
				for (int i = 0; i<trackIdPerPlaylist.size();i++){
					String sqlOrder3 = props.getProperty("trackRead");
					PreparedStatement pStmt3 = connection.prepareStatement(sqlOrder3);
					pStmt3.setString(1, String.valueOf(trackIdPerPlaylist.get(i)));
					System.out.println(trackIdPerPlaylist.get(i));
					
					ResultSet rs3 = pStmt3.executeQuery();
					while(rs3.next()){
						TrackEntity track = new TrackEntity();
						track.setId(new Integer(rs3.getString("id")));
						track.setLocation(new URI(rs3.getString("location")));
						track.setIdentifier(new URI(rs3.getString("identifier")));
						track.setTitle((rs3.getString("title")));
						track.setCreator((rs3.getString("creator")));
						track.setAnnotation((rs3.getString("annotation")));
						track.setInfo(new URI(rs3.getString("info")));
						track.setImage(new URI(rs3.getString("image")));
						track.setAlbum((rs3.getString("album")));
						track.setTrackNum(new BigInteger(rs3.getString("tracknum")));
						track.setDuration(new BigInteger(rs3.getString("duration")));
						track.setLink((rs3.getString("link")));
						track.setMeta((rs3.getString("meta")));
						track.setExtension((rs3.getString("extension")));
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
	
	public PlaylistType convertToListPlaylistType(PlaylistEntity userPlaylist) {
		PlaylistType playlist = new PlaylistType();
		playlist.setTitle(userPlaylist.getTitle());
		playlist.setCreator(userPlaylist.getCreator());
		playlist.setAnnotation(userPlaylist.getAnnotation());
		playlist.setInfo(userPlaylist.getInfo());
		playlist.setLocation(userPlaylist.getLocation());
		playlist.setIdentifier(userPlaylist.getIdentifier());
		playlist.setImage(userPlaylist.getImage());
		//playlist.setDate(new XMLguserPlaylist.getDate());
		playlist.setLicense(userPlaylist.getLicence());
		//playlist.setAttribution(userPlaylist.getAttribution());
		//playlist.setTrackList(userPlaylist.getTracklist());
		
		return playlist;
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
