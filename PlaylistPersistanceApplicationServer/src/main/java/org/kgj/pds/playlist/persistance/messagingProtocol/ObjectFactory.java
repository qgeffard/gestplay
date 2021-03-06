//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.31 at 02:06:12 PM CEST 
//


package org.kgj.pds.playlist.persistance.messagingProtocol;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.kgj.pds.playlist.metier.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Playlist_QNAME = new QName("http://xspf.org/ns/0/", "playlist");
    private final static QName _AttributionTypeLocation_QNAME = new QName("http://xspf.org/ns/0/", "location");
    private final static QName _AttributionTypeIdentifier_QNAME = new QName("http://xspf.org/ns/0/", "identifier");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.kgj.pds.playlist.metier.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Query }
     * 
     */
    public Query createQuery() {
        return new Query();
    }

    /**
     * Create an instance of {@link Query.Status }
     * 
     */
    public Query.Status createQueryStatus() {
        return new Query.Status();
    }

    /**
     * Create an instance of {@link Query.UserManager }
     * 
     */
    public Query.UserManager createQueryUserManager() {
        return new Query.UserManager();
    }

    /**
     * Create an instance of {@link Query.Action }
     * 
     */
    public Query.Action createQueryAction() {
        return new Query.Action();
    }

    /**
     * Create an instance of {@link PlaylistType }
     * 
     */
    public PlaylistType createPlaylistType() {
        return new PlaylistType();
    }

    /**
     * Create an instance of {@link AttributionType }
     * 
     */
    public AttributionType createAttributionType() {
        return new AttributionType();
    }

    /**
     * Create an instance of {@link ExtensionType }
     * 
     */
    public ExtensionType createExtensionType() {
        return new ExtensionType();
    }

    /**
     * Create an instance of {@link LinkType }
     * 
     */
    public LinkType createLinkType() {
        return new LinkType();
    }

    /**
     * Create an instance of {@link TrackType }
     * 
     */
    public TrackType createTrackType() {
        return new TrackType();
    }

    /**
     * Create an instance of {@link TrackListType }
     * 
     */
    public TrackListType createTrackListType() {
        return new TrackListType();
    }

    /**
     * Create an instance of {@link MetaType }
     * 
     */
    public MetaType createMetaType() {
        return new MetaType();
    }

    /**
     * Create an instance of {@link Query.Status.Error }
     * 
     */
    public Query.Status.Error createQueryStatusError() {
        return new Query.Status.Error();
    }

    /**
     * Create an instance of {@link Query.UserManager.User }
     * 
     */
    public Query.UserManager.User createQueryUserManagerUser() {
        return new Query.UserManager.User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlaylistType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xspf.org/ns/0/", name = "playlist")
    public JAXBElement<PlaylistType> createPlaylist(PlaylistType value) {
        return new JAXBElement<PlaylistType>(_Playlist_QNAME, PlaylistType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xspf.org/ns/0/", name = "location", scope = AttributionType.class)
    public JAXBElement<String> createAttributionTypeLocation(String value) {
        return new JAXBElement<String>(_AttributionTypeLocation_QNAME, String.class, AttributionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xspf.org/ns/0/", name = "identifier", scope = AttributionType.class)
    public JAXBElement<String> createAttributionTypeIdentifier(String value) {
        return new JAXBElement<String>(_AttributionTypeIdentifier_QNAME, String.class, AttributionType.class, value);
    }

}
