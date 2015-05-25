package org.kgj.pds.playlist.persistance.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.kgj.pds.playlist.persistance.entity.UsersEntity;

public class UsersDAO implements IDAOService<UsersEntity> {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	
	private static Properties props;
	static {
		props = new Properties();
		InputStream inStream = org.kgj.pds.playlist.persistance.model.UsersDAO.class.getResourceAsStream("db.properties");
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
	public boolean create(UsersEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UsersEntity read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(UsersEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(UsersEntity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UsersEntity> searchAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
