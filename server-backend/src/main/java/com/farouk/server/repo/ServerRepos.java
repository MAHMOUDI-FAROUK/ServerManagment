package com.farouk.server.repo;

import com.farouk.server.Model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepos extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
