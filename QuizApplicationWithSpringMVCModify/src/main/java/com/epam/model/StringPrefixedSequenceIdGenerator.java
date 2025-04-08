package com.epam.model;

import java.io.Serializable;
import java.util.Properties;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.HibernateException;
import org.hibernate.boot.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class StringPrefixedSequenceIdGenerator extends SequenceStyleGenerator {

	public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
	public static final String VALUE_PREFIX_DEFAULT = "";
	public String valuePrefix;

	public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
	public static final String NUMBER_FORMAT_DEFAULT = "%d";
	public String numberFormat;
	
	@Override
	public void configure(Type type, Properties params, org.hibernate.service.ServiceRegistry serviceRegistry) throws HibernateException {
		
		super.configure(LongType.INSTANCE, params, serviceRegistry);
		valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
		numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws MappingException {
		return valuePrefix + String.format(numberFormat, super.generate(session, object));
	}

}