//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.17 at 03:55:18 PM CET 
//


package org.kgj.pds.playlist.metier.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryId" type="{http://xspf.org/ns/0/}tokenType"/>
 *         &lt;element name="responseId" type="{http://xspf.org/ns/0/}tokenType"/>
 *         &lt;element name="userManager">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="connectedToken" type="{http://xspf.org/ns/0/}tokenType"/>
 *                   &lt;element name="user">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="login">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}Name">
 *                                   &lt;minLength value="3"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="password">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;minLength value="8"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="status">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="succed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="progress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="error">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="source">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="model"/>
 *                                   &lt;enumeration value="controller"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="action">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="idAction" type="{http://xspf.org/ns/0/}tokenType"/>
 *                   &lt;element name="nameAction">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="undo"/>
 *                         &lt;enumeration value="redo"/>
 *                         &lt;enumeration value="create"/>
 *                         &lt;enumeration value="delete"/>
 *                         &lt;enumeration value="update"/>
 *                         &lt;enumeration value="read"/>
 *                         &lt;enumeration value="login"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="data" type="{http://xspf.org/ns/0/}PlaylistType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "queryId",
    "responseId",
    "userManager",
    "status",
    "action",
    "data"
})
@XmlRootElement(name = "query")
public class Query {

    @XmlElement(namespace = "", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String queryId;
    @XmlElement(namespace = "", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String responseId;
    @XmlElement(namespace = "", required = true)
    protected Query.UserManager userManager;
    @XmlElement(namespace = "", required = true)
    protected Query.Status status;
    @XmlElement(namespace = "", required = true)
    protected Query.Action action;
    @XmlElement(namespace = "", required = true)
    protected List<PlaylistType> data;

    /**
     * Gets the value of the queryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryId() {
        return queryId;
    }

    /**
     * Sets the value of the queryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryId(String value) {
        this.queryId = value;
    }

    /**
     * Gets the value of the responseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseId() {
        return responseId;
    }

    /**
     * Sets the value of the responseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseId(String value) {
        this.responseId = value;
    }

    /**
     * Gets the value of the userManager property.
     * 
     * @return
     *     possible object is
     *     {@link Query.UserManager }
     *     
     */
    public Query.UserManager getUserManager() {
        return userManager;
    }

    /**
     * Sets the value of the userManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.UserManager }
     *     
     */
    public void setUserManager(Query.UserManager value) {
        this.userManager = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Status }
     *     
     */
    public Query.Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Status }
     *     
     */
    public void setStatus(Query.Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Action }
     *     
     */
    public Query.Action getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Action }
     *     
     */
    public void setAction(Query.Action value) {
        this.action = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the data property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlaylistType }
     * 
     * 
     */
    public List<PlaylistType> getData() {
        if (data == null) {
            data = new ArrayList<PlaylistType>();
        }
        return this.data;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="idAction" type="{http://xspf.org/ns/0/}tokenType"/>
     *         &lt;element name="nameAction">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="undo"/>
     *               &lt;enumeration value="redo"/>
     *               &lt;enumeration value="create"/>
     *               &lt;enumeration value="delete"/>
     *               &lt;enumeration value="update"/>
     *               &lt;enumeration value="read"/>
     *               &lt;enumeration value="login"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idAction",
        "nameAction"
    })
    public static class Action {

        @XmlElement(namespace = "", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String idAction;
        @XmlElement(namespace = "", required = true)
        protected String nameAction;

        /**
         * Gets the value of the idAction property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdAction() {
            return idAction;
        }

        /**
         * Sets the value of the idAction property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdAction(String value) {
            this.idAction = value;
        }

        /**
         * Gets the value of the nameAction property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNameAction() {
            return nameAction;
        }

        /**
         * Sets the value of the nameAction property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNameAction(String value) {
            this.nameAction = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="succed" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="progress" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="error">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="source">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="model"/>
     *                         &lt;enumeration value="controller"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "succed",
        "progress",
        "error"
    })
    public static class Status {

        @XmlElement(namespace = "")
        protected String succed;
        @XmlElement(namespace = "")
        protected String progress;
        @XmlElement(namespace = "")
        protected Query.Status.Error error;

        /**
         * Gets the value of the succed property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSucced() {
            return succed;
        }

        /**
         * Sets the value of the succed property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSucced(String value) {
            this.succed = value;
        }

        /**
         * Gets the value of the progress property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProgress() {
            return progress;
        }

        /**
         * Sets the value of the progress property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProgress(String value) {
            this.progress = value;
        }

        /**
         * Gets the value of the error property.
         * 
         * @return
         *     possible object is
         *     {@link Query.Status.Error }
         *     
         */
        public Query.Status.Error getError() {
            return error;
        }

        /**
         * Sets the value of the error property.
         * 
         * @param value
         *     allowed object is
         *     {@link Query.Status.Error }
         *     
         */
        public void setError(Query.Status.Error value) {
            this.error = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="source">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="model"/>
         *               &lt;enumeration value="controller"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "message",
            "source"
        })
        public static class Error {

            @XmlElement(namespace = "", required = true)
            protected String message;
            @XmlElement(namespace = "", required = true)
            protected String source;

            /**
             * Gets the value of the message property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMessage() {
                return message;
            }

            /**
             * Sets the value of the message property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMessage(String value) {
                this.message = value;
            }

            /**
             * Gets the value of the source property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSource() {
                return source;
            }

            /**
             * Sets the value of the source property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSource(String value) {
                this.source = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="connectedToken" type="{http://xspf.org/ns/0/}tokenType"/>
     *         &lt;element name="user">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="login">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}Name">
     *                         &lt;minLength value="3"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="password">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;minLength value="8"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "connectedToken",
        "user"
    })
    public static class UserManager {

        @XmlElement(namespace = "")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String connectedToken;
        @XmlElement(namespace = "")
        protected Query.UserManager.User user;

        /**
         * Gets the value of the connectedToken property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getConnectedToken() {
            return connectedToken;
        }

        /**
         * Sets the value of the connectedToken property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setConnectedToken(String value) {
            this.connectedToken = value;
        }

        /**
         * Gets the value of the user property.
         * 
         * @return
         *     possible object is
         *     {@link Query.UserManager.User }
         *     
         */
        public Query.UserManager.User getUser() {
            return user;
        }

        /**
         * Sets the value of the user property.
         * 
         * @param value
         *     allowed object is
         *     {@link Query.UserManager.User }
         *     
         */
        public void setUser(Query.UserManager.User value) {
            this.user = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="login">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}Name">
         *               &lt;minLength value="3"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="password">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;minLength value="8"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "login",
            "password"
        })
        public static class User {

            @XmlElement(namespace = "", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String login;
            @XmlElement(namespace = "", required = true)
            protected String password;

            /**
             * Gets the value of the login property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLogin() {
                return login;
            }

            /**
             * Sets the value of the login property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLogin(String value) {
                this.login = value;
            }

            /**
             * Gets the value of the password property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPassword() {
                return password;
            }

            /**
             * Sets the value of the password property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPassword(String value) {
                this.password = value;
            }

        }

    }

}
