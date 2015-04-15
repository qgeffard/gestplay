package org.kgj.playlist.metier.checker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.kgj.pds.playlist.metier.messagingProtocol.Query.UserManager.User;
import org.kgj.pds.playlist.metier.serveurHttp.LocalStorage;

public class LoginChecker {
	LocalStorage localStorage;

	public LoginChecker(LocalStorage localstorage) {
		localStorage = localstorage;
	}

	public boolean validLogin(User user) {
		boolean valid = false;

		if (localStorage.getUsers().containsKey(user.getLogin())) {
			try {
				if (localStorage
						.getUsers()
						.get(user.getLogin())
						.equals(MessageDigest.getInstance("MD5").digest(
								user.getPassword().getBytes()))) {
					valid = true;
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return valid;
	}

}
