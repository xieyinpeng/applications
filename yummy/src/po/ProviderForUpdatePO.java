package po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import eo.ProviderState;
import eo.ProviderType;
import eo.Site;

@Entity(name="providerforupdate")
public class ProviderForUpdatePO {

	@Id
	@Column(length=7)
	int pid;

	@Enumerated(EnumType.STRING)
	Site site;
	@Enumerated(EnumType.STRING)
	ProviderType type;
	String password;
	@OneToOne(fetch = FetchType.EAGER)
	AccountPO account;
	@Enumerated(EnumType.STRING)
	ProviderState state;
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getPid() {
		return pid;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public ProviderType getType() {
		return type;
	}
	public void setType(ProviderType type) {
		this.type = type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AccountPO getAccount() {
		return account;
	}
	public void setAccount(AccountPO account) {
		this.account = account;
	}
	public ProviderState getState() {
		return state;
	}
	public void setState(ProviderState state) {
		this.state = state;
	}
}

/*
create table provider(
pid varchar(7) primary key auto_increment=1000000,
site varchar(50),
type enum(?????),
password varchar(20),
aid varchar(7),
state enum(????),
foreign key(aid) references account(aid)
)auto_increment=1000000;
*/