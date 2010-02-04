package fr.univartois.ili.fsnet.facade.forum.iliforum;

import javax.persistence.EntityManager;

import fr.univartois.ili.fsnet.entities.Property;

/**
 * 
 * @author mickael watrelot - micgamers@gmail.com
 * 
 */

public class FSNetConfigurationFacade {

	private final EntityManager em;

	public FSNetConfigurationFacade(EntityManager em) {
		this.em = em;
	}

	public int getSMTPPort() {
		return Integer.parseInt(em.find(Property.class,
				Property.FsnetProperty.SMTP_PORT).getValue());
	}

	public void setSMTPPort(int smtpPort) {
		Property smtpPortConf = em.find(Property.class,
				Property.FsnetProperty.SMTP_PORT);
		if (smtpPortConf == null) {
			smtpPortConf = new Property();
			smtpPortConf.setKey(Property.FsnetProperty.SMTP_PORT);
			smtpPortConf.setValue(String.valueOf(smtpPort));
			em.persist(smtpPortConf);
		} else {
			smtpPortConf.setValue(String.valueOf(smtpPort));
			em.merge(smtpPortConf);
		}

	}

	public int getSMTPHost() {
		return Integer.parseInt(em.find(Property.class,
				Property.FsnetProperty.SMTP_HOST).getValue());
	}

	public void setSMTPHost(int smtpHost) {
		Property smtpHostConf = em.find(Property.class,
				Property.FsnetProperty.SMTP_HOST);
		if(smtpHostConf == null) {
			smtpHostConf = new Property();
			smtpHostConf.setKey(Property.FsnetProperty.SMTP_HOST);
			smtpHostConf.setValue(String.valueOf(smtpHost));
			em.persist(smtpHostConf);
		} else {
			smtpHostConf.setValue(String.valueOf(smtpHost));
			em.merge(smtpHostConf);
		}
	}
	
	
}