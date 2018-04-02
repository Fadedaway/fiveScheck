package com.magic.jovi.repositories.support;

import com.magic.jovi.entities.BaseEntity;
import com.magic.jovi.repositories.impl.BaseJpaRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by fanjiawei on 2018/4/2
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {


    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new MyRepositoryFactory(em);
    }

    private static class MyRepositoryFactory<T extends BaseEntity, I extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager em;

        public MyRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseJpaRepositoryImpl(getEntityInformation((Class<T>) information.getDomainType()), em);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseJpaRepositoryImpl.class;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T, ID extends Serializable> JpaEntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
            return (JpaEntityInformation<T, ID>) JpaEntityInformationSupport.getEntityInformation(domainClass, em);
        }
    }
}

