package org.kgj.playlist.metier.checkAndDispatch;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.data.LocalStorage;
import org.kgj.pds.playlist.metier.messagingProtocol.Query;
import org.kgj.pds.playlist.metier.messagingProtocol.Query.UserManager.User;
import org.kgj.pds.playlist.metier.serveurHttp.ServeurHttpPersistenceSide;

public class CheckerPersistenceSide {
	private static LocalStorage localStorage = ServeurHttpPersistenceSide.localStorage;
	private static final Logger logger = Logger.getLogger(CheckerPersistenceSide.class);
	public CheckerPersistenceSide(){
		
	}
	public void check(Query query) {
		switch (query.getAction().getNameAction()) {
		case "login":
		case "test":
			checkLogin(query);
			break;
		default:
			break;
		}
		
	}
	
	public void checkLogin(Query query) {
		boolean allowed = validLogin(query.getUserManager().getUser());
		if (allowed) {
			Query.Status status = new Query.Status();
			status.setSucced("succed");
			query.setStatus(status);
		} else {
			Query.Status status = new Query.Status();
			Query.Status.Error error = new Query.Status.Error();
			error.setSource("Metier");
			error.setMessage("Invalid login or password");
			status.setError(error);
			query.setStatus(status);
		}
		
	}
	
	public boolean validLogin(User user) {
		boolean valid = false;

		if (localStorage.getUsers().containsKey(user.getLogin())) {
			if (localStorage
					.getUsers()
					.get(user.getLogin())
					.equals(mdpHashInMD5(user.getPassword()))) {
				valid = true;
			}
		}

		return valid;
	}
	
	public String mdpHashInMD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
