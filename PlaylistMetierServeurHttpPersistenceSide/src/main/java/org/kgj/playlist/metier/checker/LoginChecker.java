package org.kgj.playlist.metier.checker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

import org.kgj.pds.playlist.metier.data.LocalStorage;
import org.kgj.pds.playlist.metier.messagingProtocol.Query.UserManager.User;

public class LoginChecker {
	private static final Logger logger = Logger.getLogger(LoginChecker.class);

	LocalStorage localStorage;

	public LoginChecker(LocalStorage localstorage) {
		localStorage = localstorage;
	}

	public boolean validLogin(User user) {
		boolean valid = false;

		if (localStorage.getUsers().containsKey(user.getLogin())) {
			logger.info(mdpHashInMD5(user.getPassword()));
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
