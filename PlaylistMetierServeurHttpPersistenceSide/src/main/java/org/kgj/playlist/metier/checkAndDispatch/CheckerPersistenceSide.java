package org.kgj.playlist.metier.checkAndDispatch;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.Utils.QueryManager;
import org.kgj.pds.playlist.Utils.QueryMarshaller;
import org.kgj.pds.playlist.Utils.Source;
import org.kgj.pds.playlist.metier.data.LocalStorage;
import org.kgj.pds.playlist.metier.messagingProtocol.Query;
import org.kgj.pds.playlist.metier.messagingProtocol.Query.UserManager.User;
import org.kgj.pds.playlist.metier.serveurHttp.ServeurHttpPersistenceSide;

public class CheckerPersistenceSide {
	private static LocalStorage localStorage = ServeurHttpPersistenceSide.localStorage;
	private static final Logger logger = Logger.getLogger(CheckerPersistenceSide.class);

	public CheckerPersistenceSide() {

	}

	public void check(Query query) {
		switch (query.getAction().getNameAction()) {
		case "login":
		case "test":
			checkLogin(query);
			break;
		case "undo":
		case "redo":
			undoRedo(query);
			break;
		default:
			break;
		}

	}

	private void undoRedo(Query query) {
		if (query.getAction().getNameAction().equals("undo")) {

			Map<Integer, String> commandUndo = localStorage.getCommandUndoByUser(query.getUserManager().getUser().getLogin());
			if (commandUndo != null) {
				String commandToUndo = commandUndo.get(Collections.max(commandUndo.keySet()));
				logger.info("Query a undo :");
				logger.info(commandToUndo);
				
				Query queryUndo = QueryMarshaller.stringToQuery(commandToUndo);

				switch (queryUndo.getAction().getNameAction()) {
				case "create":
					QueryManager.flushPlaylist(query);
					localStorage.addQueryToCommandRedo(query.getUserManager().getUser().getLogin(), commandToUndo);
					QueryManager.setActionDelete(query);
					query.getPlaylist().addAll(queryUndo.getPlaylist());
					break;

				case "update":
					QueryManager.flushPlaylist(query);
					localStorage.addQueryToCommandRedo(query.getUserManager().getUser().getLogin(), commandToUndo);
					query.getPlaylist().addAll(queryUndo.getPlaylist());
					break;

				case "delete":
					QueryManager.flushPlaylist(query);
					localStorage.addQueryToCommandRedo(query.getUserManager().getUser().getLogin(), commandToUndo);
					QueryManager.setActionCreate(query);
					query.getPlaylist().addAll(queryUndo.getPlaylist());
					break;

				default:
					break;
				}
				query.setResponseId("undo");
				localStorage.deleteTheMaxUndoCommand(query.getUserManager().getUser().getLogin());
			} else {
				logger.error("nothing to undo");
				QueryManager.setStatusError(query, Source.METIER.getName(), "Nothing to undo");
			}

		} else {

			Map<Integer, String> commandRedo = localStorage.getCommandRedoByUser(query.getUserManager().getUser().getLogin());
			String commandToRedo = commandRedo.get(Collections.max(commandRedo.keySet()));
			Query queryRedo = QueryMarshaller.stringToQuery(commandToRedo);

			switch (queryRedo.getAction().getNameAction()) {
			case "create":
				QueryManager.flushPlaylist(query);
				localStorage.addQueryToCommandUndo(query.getUserManager().getUser().getLogin(), commandToRedo);
				QueryManager.setActionCreate(query);
				query.getPlaylist().addAll(queryRedo.getPlaylist());
				break;

			case "update":
				QueryManager.flushPlaylist(query);
				localStorage.addQueryToCommandUndo(query.getUserManager().getUser().getLogin(), commandToRedo);
				QueryManager.setActionUpdate(query);
				query.getPlaylist().addAll(queryRedo.getPlaylist());
				break;

			case "delete":
				QueryManager.flushPlaylist(query);
				localStorage.addQueryToCommandUndo(query.getUserManager().getUser().getLogin(), commandToRedo);
				QueryManager.setActionDelete(query);
				query.getPlaylist().addAll(queryRedo.getPlaylist());
				break;

			default:
				break;
			}
			query.setResponseId("undo");
			localStorage.deleteTheMaxRedoCommand(query.getUserManager().getUser().getLogin());
		}
	}

	public void saveCommand(Query query) {
		logger.info(query);
		logger.info(QueryMarshaller.queryToString(query));
		localStorage.addQueryToCommandUndo(query.getUserManager().getUser().getLogin(), QueryMarshaller.queryToString(query));
	}

	public void checkLogin(Query query) {
		boolean allowed = validLogin(query.getUserManager().getUser());
		if (allowed) {
			QueryManager.setStatusSucced(query);
		} else {
			QueryManager.setStatusError(query, Source.METIER.getName(), "Invalid login or password");
		}
	}

	public boolean validLogin(User user) {
		boolean valid = false;

		if (localStorage.getUsers().containsKey(user.getLogin())) {
			if (localStorage.getUsers().get(user.getLogin()).equals(mdpHashInMD5(user.getPassword()))) {
				valid = true;
			}
		}

		return valid;
	}

	public String mdpHashInMD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
