package com.msg.adm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "absence")
@SequenceGenerator(name = "absence_id_seq", sequenceName = "ABSENCE_ID_SEQ", allocationSize = 1, initialValue = 1)
//@NamedQueries({ @NamedQuery(name = "absence.getAllAbsence", query = "SELECT u FROM UserEntity u ORDER BY u.username"),
//		@NamedQuery(name = "user.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username"),
//		@NamedQuery(name = "user.update", query = "UPDATE UserEntity u SET u.role = :role, u.password = :password  WHERE u.id = :id") })
public class AbsenceEntity implements Serializable {

	private static final long serialVersionUID = 228155141263233185L;

	private Long id;
	private Long userId;
	private Long replacementId;
	private Date createdDate;
	private Date startDate;
	private Date startHour;
	private Date endHour;
	private String reason;
	private int status;

	@Id
    @GeneratedValue(generator = "absence_id_seq", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getReplacementId() {
		return replacementId;
	}

	public void setReplacementId(Long replacementId) {
		this.replacementId = replacementId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartHour() {
		return startHour;
	}

	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}

	public Date getEndHour() {
		return endHour;
	}

	public void setEndHour(Date endHour) {
		this.endHour = endHour;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
