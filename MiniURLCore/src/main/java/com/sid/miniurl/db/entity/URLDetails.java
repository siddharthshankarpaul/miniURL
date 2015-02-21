package com.sid.miniurl.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "URL_DETAILS")
public class URLDetails implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int oid;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "click_count")
	private int clickCount;

	@ManyToOne
	@JoinColumn(name = "url_id")
	private URLInfo urlOID;

	public URLInfo getUrlOID() {
		return urlOID;
	}

	public void setUrlOID(URLInfo urlOID) {
		this.urlOID = urlOID;
	}

	public URLDetails() {
	}

	public int getOid() {
		return oid;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

}
