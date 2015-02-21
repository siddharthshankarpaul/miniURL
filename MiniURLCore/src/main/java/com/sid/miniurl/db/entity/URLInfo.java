package com.sid.miniurl.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "URL_INFO")
public class URLInfo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int oid;

	@Column(name = "url")
	private String url;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "urlOID")
	private List<URLDetails> urlDetails = new ArrayList<>();

	public int getOid() {
		return oid;
	}

	public URLInfo() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<URLDetails> getUrlDetails() {
		return urlDetails;
	}

	public void setUrlDetails(List<URLDetails> urlDetails) {
		this.urlDetails = urlDetails;
	}

}
