package com.example.serverapp.Controller;

import com.example.serverapp.Repository.Repo_category;
import com.example.serverapp.Util.ResponseData;
import com.example.serverapp.Util.ResponseError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class Controller_category {

    Repo_category repo_category;

    public Controller_category(Repo_category repo_category) {
        setRepo_category(repo_category);
    }

    public Repo_category getRepo_category() {
        return repo_category;
    }

    public void setRepo_category(Repo_category repo_category) {
        this.repo_category = repo_category;
    }

    @GetMapping()
    public Object findAll(){
        try{
            return  new ResponseData(getRepo_category().findAll());
        }catch (Throwable e){
            return new ResponseError(e.getMessage());
        }
    }
}
