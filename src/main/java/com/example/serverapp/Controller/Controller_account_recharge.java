package com.example.serverapp.Controller;

import com.example.serverapp.Model.Account_recharge;
import com.example.serverapp.Model.Account_recharge_validation;
import com.example.serverapp.Model.Auction;
import com.example.serverapp.Model.Token;
import com.example.serverapp.Repository.Repo_Rercharge;
import com.example.serverapp.Repository.Repo_Rercharge_validation;
import com.example.serverapp.Repository.Repo_Token;
import com.example.serverapp.Util.ResponseData;
import com.example.serverapp.Util.ResponseError;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/account_recharges")
public class Controller_account_recharge {

    Repo_Rercharge repo_rercharge;

    Repo_Token repo_token;

    Repo_Rercharge_validation repo_rercharge_validation;

    public Repo_Rercharge_validation getRepo_rercharge_validation() {
        return repo_rercharge_validation;
    }

    public void setRepo_rercharge_validation(Repo_Rercharge_validation repo_rercharge_validation) {
        this.repo_rercharge_validation = repo_rercharge_validation;
    }

    public Repo_Rercharge getRepo_rercharge() {
        return repo_rercharge;
    }

    public void setRepo_rercharge(Repo_Rercharge repo_rercharge) {
        setRepo_rercharge(repo_rercharge);
    }

    public Repo_Token getRepo_token() {
        return repo_token;
    }

    public void setRepo_token(Repo_Token repo_token) {
        this.repo_token = repo_token;
    }

    public Controller_account_recharge(Repo_Token repo_token,Repo_Rercharge repo_rercharge,Repo_Rercharge_validation repo_rercharge_validation) {
        this.repo_rercharge = repo_rercharge;
        this.repo_token=repo_token;
        this.repo_rercharge_validation=repo_rercharge_validation;
    }

    @PostMapping()
    public Object create( @RequestHeader("Authorization") String token,@RequestBody Account_recharge account_recharge){
        try{
            Token t=new Token().check_Expiration(token,getRepo_token());
            if(t==null)
                return new ResponseError("Access denied");

            getRepo_rercharge().save(account_recharge);
        }catch (Throwable e){
            return new ResponseError(e.getMessage());
        }
        return new ResponseData("Success");
    }

    @PostMapping("{recharge_id}/valid")
    public Object validate(  @RequestHeader("Authorization") String token,@PathVariable int recharge_id,@RequestBody Account_recharge_validation account_recharge_validation){
        try{
            Token t=new Token().check_Expiration(token,getRepo_token());
            if(t==null)
                return new ResponseError("Access denied");

            Account_recharge r=new Account_recharge();
            r.setId(recharge_id);
            account_recharge_validation.setAccount_recharge(r);

            getRepo_rercharge_validation().save(account_recharge_validation);
        }catch (Throwable e){
            return new ResponseError(e.getMessage());
        }
        return new ResponseData("Success");
    }

    @GetMapping()
    public Object getToValid( @RequestHeader("Authorization") String token){
        try{
            Token t=new Token().check_Expiration(token,getRepo_token());
            if(t==null)
                return new ResponseError("Access denied");

            return  new ResponseData(getRepo_rercharge().getToValid());
        }catch (Throwable e){
            return new ResponseError(e.getMessage());
        }
    }
}
