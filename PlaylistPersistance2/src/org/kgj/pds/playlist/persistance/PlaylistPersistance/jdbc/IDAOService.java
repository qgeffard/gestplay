package org.kgj.pds.playlist.persistance.PlaylistPersistance.jdbc;

import java.util.List;

public interface IDAOService<E> {
	boolean create(E e);
	E read(int id);
	boolean update(E e);
	boolean delete(E e);
	List<E> searchAll();
}
