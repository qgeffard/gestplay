package org.kgj.pds.playlist.persistance.model;

import java.util.List;

import org.kgj.pds.playlist.persistance.entity.QueryEntity;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;

public interface IDAOService<E> {
	boolean create(E e);
	E read(E e);
	boolean update(E e);
	boolean delete(E e);
	List<E> searchAll();
	Query readByUser(String username);
}
