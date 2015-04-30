package org.kgj.pds.playlist.Utils;

import org.kgj.pds.playlist.presentation.messagingProtocol.Query;

public class QueryManager {

	// --------------------------
	// User management
	// --------------------------
	public static Query setUserManagerLoginPassword(Query query, String login,
			String password) {

		Query.UserManager userManager = new Query.UserManager();
		Query.UserManager.User user = new Query.UserManager.User();
		user.setLogin(login);
		user.setPassword(password);
		userManager.setUser(user);
		query.setUserManager(userManager);

		return query;
	}

	public static Query setUserManagerConnected(Query query, String sessionHash) {

		Query.UserManager userManager = new Query.UserManager();
		userManager.setConnectedToken(sessionHash);
		query.setUserManager(userManager);

		return query;
	}

	// --------------------------
	// Status management
	// --------------------------
	public static Query setStatusInProgress(Query query) {

		Query.Status status = new Query.Status();
		status.setProgress("In progress");
		query.setStatus(status);

		return query;
	}

	public static Query setStatusSucced(Query query) {

		Query.Status status = new Query.Status();
		status.setSucced("succed");
		query.setStatus(status);

		return query;
	}

	public static Query setStatusError(Query query, String source,
			String message) {

		Query.Status status = new Query.Status();
		Query.Status.Error error = new Query.Status.Error();
		error.setMessage(message);
		error.setSource(source);
		status.setError(error);
		query.setStatus(status);

		return query;
	}

	// --------------------------
	// Action management
	// --------------------------
	public static Query setActionUndo(Query query) {

		Query.Action action = new Query.Action();
		action.setNameAction("undo");
		action.setIdAction("1");
		query.setAction(action);

		return query;
	}

	public static Query setActionRedo(Query query) {

		Query.Action action = new Query.Action();
		action.setNameAction("redo");
		action.setIdAction("2");
		query.setAction(action);

		return query;
	}

	public static Query setActionCreate(Query query) {

		Query.Action action = new Query.Action();
		action.setNameAction("create");
		action.setIdAction("3");
		query.setAction(action);

		return query;
	}

	public static Query setActionDelete(Query query) {

		Query.Action action = new Query.Action();
		action.setNameAction("delete");
		action.setIdAction("4");
		query.setAction(action);

		return query;
	}

	public static Query setActionUpdate(Query query) {

		Query.Action action = new Query.Action();
		action.setNameAction("update");
		action.setIdAction("5");
		query.setAction(action);

		return query;
	}

	public static Query setActionRead(Query query) {

		Query.Action action = new Query.Action();
		action.setNameAction("read");
		action.setIdAction("6");
		query.setAction(action);

		return query;
	}

	public static Query setActionLogin(Query query) {

		Query.Action action = new Query.Action();
		action.setNameAction("login");
		action.setIdAction("7");
		query.setAction(action);

		return query;
	}
}
