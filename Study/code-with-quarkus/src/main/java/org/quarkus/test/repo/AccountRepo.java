package org.quarkus.test.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.quarkus.test.entity.Account;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepo implements PanacheRepository<Account> {
}
