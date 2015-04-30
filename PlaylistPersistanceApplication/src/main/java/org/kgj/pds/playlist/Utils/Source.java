package org.kgj.pds.playlist.Utils;

public enum Source {
	METIER("Metier"), PERSISTANCE("Persistance"), PRESENTATION("Presentation");

	protected String name;

	/** Constructeur */
	Source(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
