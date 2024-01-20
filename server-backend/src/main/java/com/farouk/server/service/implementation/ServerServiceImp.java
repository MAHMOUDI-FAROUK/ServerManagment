package com.farouk.server.service.implementation;

import com.farouk.server.Model.Server;
import com.farouk.server.enumeration.Status;
import com.farouk.server.repo.ServerRepos;
import com.farouk.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.farouk.server.enumeration.Status.SERVER_DOWN;
import static com.farouk.server.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImp implements ServerService {
    private final ServerRepos serverRepos;
    @Override
    public Server create(Server server) {
        log.info("Saving new server : {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepos.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepos.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepos.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers: {}");
        return serverRepos.findAll(PageRequest.of(0 , limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id : {}", id);
        return serverRepos.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server : {}", server.getName());
        return serverRepos.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id : {}", id);
        serverRepos.deleteById(id);
        return TRUE;
    }
    private String setServerImageUrl() {
        String[] imageNames = {"server1.png" , "server2.png" , "server3.png" };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image" + imageNames[new Random().nextInt(3)]).toUriString();
    }
}
