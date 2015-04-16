package org.kgj.pds.playlist.persistance.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.kgj.pds.playlist.persistance.entity.PlaylistEntity;

public class PlaylistDAO implements IDAOService<PlaylistEntity> {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	
	private static Properties props;
	static {
		props = new Properties();
		InputStream inStream = org.kgj.pds.playlist.persistance.model.PlaylistDAO.class.getResourceAsStream("db.properties");
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
