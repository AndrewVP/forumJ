/**
 * 
 */
package org.forumj.hibernate.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.forumj.common.db.entity.IFJFolder;
import org.forumj.common.db.entity.IUser;

/**
 * @author Евгений
 *
 */
@Entity
@Table(name="fdfolders")
public class FJFolder implements IFJFolder {

	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="d_cr")
	private Date createDate;
	
	@Column(name="flname")
	private String name;
	
	@Column(name="user")
	@ManyToOne()
	@JoinColumn(name="id",table="users")
	private IUser user;
	
	
	
	public FJFolder(Date createDate, String name, Long id, IUser user) {
		super();
		this.createDate = createDate;
		this.name = name;
		this.id = id;
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see org.forumj.common.db.entity.IFJFolder#setCreateDate(java.util.Date)
	 */
	@Override
	public void setCreateDate(Date createDate) {
		this.createDate=createDate;

	}

	/* (non-Javadoc)
	 * @see org.forumj.common.db.entity.IFJFolder#getCreateDate()
	 */
	@Override
	public Date getCreateDate() {
		return createDate;
	}

	/* (non-Javadoc)
	 * @see org.forumj.common.db.entity.IFJFolder#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name=name;

	}

	/* (non-Javadoc)
	 * @see org.forumj.common.db.entity.IFJFolder#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.forumj.common.db.entity.IFJFolder#setUser(org.forumj.common.db.entity.IUser)
	 */
	@Override
	public void setUser(IUser user) {
		this.user=user;

	}

	/* (non-Javadoc)
	 * @see org.forumj.common.db.entity.IFJFolder#getUser()
	 */
	@Override
	public IUser getUser() {
		return user;
	}

	/* (non-Javadoc)
	 * @see org.forumj.common.db.entity.IFJFolder#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id=id;

	}

	/* (non-Javadoc)
	 * @see org.forumj.common.db.entity.IFJFolder#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

}
