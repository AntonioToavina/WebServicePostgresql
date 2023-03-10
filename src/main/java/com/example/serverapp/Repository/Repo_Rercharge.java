package com.example.serverapp.Repository;

import com.example.serverapp.Model.Account_recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Repo_Rercharge extends JpaRepository<Account_recharge, Integer> {

    @Query(value = "SELECT * from account_recharge where id not in (SELECT account_recharge_id from account_recharge_validation)",nativeQuery = true)
    Account_recharge getToValid( );
}
