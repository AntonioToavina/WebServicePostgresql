package com.example.serverapp.Repository;

import com.example.serverapp.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo_admin extends JpaRepository<Admin,Integer> {
}
