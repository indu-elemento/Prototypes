package org.quarkus.test.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.jboss.logging.annotations.Param;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.quarkus.test.entity.Account;
import org.quarkus.test.entity.projection.AccountDTO;
import org.quarkus.test.repo.AccountRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/account")
@ApplicationScoped
public class AccountManagement {

    private AccountRepo accountRepo;

    @Inject
    public AccountManagement(AccountRepo accountRepo){
        this.accountRepo = accountRepo;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello..This is Account management..";
    }

    @POST
    @Path("/create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Account createAccount(@Param Account account) {

        accountRepo.persist(account);

        return getAccount(Long.valueOf(account.id));
    }

    @GET
    @Path("/get/{accId}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam Long accId) {

        Account account = accountRepo.findById(accId);
        if (account == null) {
            throw new WebApplicationException("Account with id of " + accId + " does not exist.", 404);
        }
        return account;
    }

    @GET
    @Path("/count")
    @Transactional
    public long getAccountCount() {

        return accountRepo.count();
    }

    @GET
    @Path("/filter/{region}")
    @Transactional
    public List<Account> getRegionBasedAccounts(@PathParam String region) {

        return accountRepo.list("region", region);
    }

    @GET
    @Path("/find/{region}")
    @Transactional
    public List<Account> getFilteredAccounts(@PathParam String region) {

        PanacheQuery<Account> query = accountRepo.find("region", region);
        return query.list().stream().collect(Collectors.toList());
    }

    @GET
    @Path("/projection/{region}")
    @Transactional
    public List<AccountDTO> getProjectedAccounts(@PathParam String region) {

        PanacheQuery<AccountDTO> query = accountRepo.find("region", region).project(AccountDTO.class);
        List<AccountDTO> result =  query.list();
        result.forEach(acc -> System.out.println(acc.toString()));
        return result;
    }
}
