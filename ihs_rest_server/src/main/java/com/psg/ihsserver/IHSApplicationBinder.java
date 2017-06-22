package com.psg.ihsserver;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import com.psg.ihsserver.dao.PatientDao;
import com.psg.ihsserver.daoimpl.PatientDaoImpl;

public class IHSApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(PatientDaoImpl.class).to(PatientDao.class).in(RequestScoped.class);
    }
}